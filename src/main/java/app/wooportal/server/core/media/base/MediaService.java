package app.wooportal.server.core.media.base;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import org.docx4j.convert.in.xhtml.XHTMLImporterImpl;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Entities.EscapeMode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.error.ErrorMailService;
import app.wooportal.server.core.error.exception.BadParamsException;
import app.wooportal.server.core.error.exception.NotFoundException;
import app.wooportal.server.core.media.attribution.MediaAttributionService;
import app.wooportal.server.core.media.image.ImageService;
import app.wooportal.server.core.media.storage.StorageService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class MediaService extends DataService<MediaEntity, MediaPredicateBuilder> {
  
  private final ErrorMailService errorMailService;

  private final ImageService imageService;
  
  private final MimeTypeService mimeTypeService;

  private final StorageService storageService;

  public MediaService(
      DataRepository<MediaEntity> repo,
      MediaPredicateBuilder predicate,
      MediaAttributionService attributionService,
      ErrorMailService errorMailService,
      ImageService imageService,
      MimeTypeService mimeTypeService,
      StorageService storageService) {
    super(repo, predicate);
    
    addService("attribution", attributionService);

    this.errorMailService = errorMailService;
    this.imageService = imageService;
    this.mimeTypeService = mimeTypeService;
    this.storageService = storageService;
  }
  
  //TODO: preSave transform youtube videos to embed videos, see class VideoMigration.isValidYoutubeLink()
  @Override
  public void preSave(MediaEntity entity, MediaEntity newEntity, JsonNode context) {
    entity.setExtension(mimeTypeService.getFileExtension(newEntity.getMimeType()));
    addContext("extension", context);
    
    //TODO: This must be integrated as a check
    if (newEntity.getUrl() != null && !newEntity.getUrl().isBlank()) {
      if (!isValidURL(newEntity.getUrl()) ||
          (newEntity.getBase64() != null && !newEntity.getBase64().isBlank())) {
        newEntity.setUrl(null);
        removeContext("url", context);
      }
    }
  }

  @Override
  public void postSave(MediaEntity entity, MediaEntity newEntity, MediaEntity saved, JsonNode context) {
    if (newEntity.getBase64() != null && !newEntity.getBase64().isBlank()) {
      byte[] data = Base64.getDecoder().decode(newEntity.getBase64());
      var fileExtension = mimeTypeService.getFileExtension(newEntity.getMimeType());
      if (mimeTypeService.isImageMimeType(newEntity.getMimeType())) {
        data = imageService.resize(data, fileExtension);
      }

      if (entity.getId() != null && !entity.getId().isBlank()) {
        storageService.delete(entity.getId(), fileExtension);
      }
      try {
        storageService.store(saved.getId(), fileExtension, data);
      } catch (IOException e) {
        e.printStackTrace();
        errorMailService.sendErrorMail(e);
      }
    }
  }

  public ResponseEntity<byte[]> download(String id) throws IOException {
    var result = repo.findOne(singleQuery(predicate.withId(id)));

    if (result.isEmpty()) {
      throw new NotFoundException("media does not exist", id);
    }
  
    return ResponseEntity.ok()
        .headers(createHeader(result.get().getName(), result.get().getExtension()))
        .contentType(MediaType.parseMediaType(result.get().getMimeType()))
        .body(storageService.read(id, result.get().getExtension()));
  }

  public ResponseEntity<byte[]> getMedia(String id) throws IOException {
    var result = repo.findOne(singleQuery(predicate.withId(id)));

    if (result.isEmpty()) {
      throw new NotFoundException("media does not exist", id);
    }
    
    return ResponseEntity.ok().contentType(MediaType.parseMediaType(result.get().getMimeType()))
        .body(storageService.read(id, result.get().getExtension()));
  }

  public ResponseEntity<byte[]> export(MediaHtmlDto content) throws Exception {

    switch (content.getType()) {
      case pdf -> {
        return exportPdf(content);
      }
      case docx -> {
        return exportDocx(content);
      }
      default -> throw new BadParamsException(
          "content type: " + content.getType() + " is not provided");
    }
  }

  public ResponseEntity<byte[]> exportPdf(MediaHtmlDto content) {

    try (var os = new ByteArrayOutputStream()) {
      var builder = new PdfRendererBuilder();
      builder.toStream(os);
      builder.withW3cDocument(new W3CDom().fromJsoup(getHtmlDocument(content)), "/");
      builder.run();
      return ResponseEntity.ok().headers(createHeader(content.getName(), "pdf"))
          .contentType(MediaType.APPLICATION_PDF).body(os.toByteArray());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public ResponseEntity<byte[]> exportDocx(MediaHtmlDto content) throws Exception {

    var wordMLPackage = WordprocessingMLPackage.createPackage();
    var XHTMLImporter = new XHTMLImporterImpl(wordMLPackage);
    wordMLPackage.getMainDocumentPart().getContent()
        .addAll(XHTMLImporter.convert(getHtmlDocument(content).html(), null));
    
    try (var os = new ByteArrayOutputStream()) {
      wordMLPackage.save(os);
      
      return ResponseEntity.ok().headers(createHeader(content.getName(), "docx"))
          .body(os.toByteArray());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  private Document getHtmlDocument(MediaHtmlDto content) {
    var document = Jsoup.parse(content.getHtml(), "UTF-8");
    document.outputSettings()
      .syntax(Document.OutputSettings.Syntax.xml)
      .escapeMode(EscapeMode.xhtml);
    return document;
  }

  public HttpHeaders createHeader(String name, String fileExtension) {
    HttpHeaders header = new HttpHeaders();
    header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + name + "." + fileExtension);
    header.add("Cache-Control", "no-cache, no-store, must-revalidate");
    header.add("Pragma", "no-cache");
    header.add("Expires", "0");
    return header;
  }
  
  public ResponseEntity<String> getMimeType(String url) {
    if (isValidURL(url)) {
      try {
        var mimeType = new URL(url).openConnection().getContentType();
        return ResponseEntity.ok(
            mimeTypeService.isMediaMimeType(mimeType)
              ? mimeType
              : null);
      } catch (IOException e) {
        ResponseEntity.ok(null);
      }
    }
    return ResponseEntity.ok(null);
  }
  
  public boolean isValidURL(String urlString) {
    try {
      new URL(urlString);
      return true;
    } catch (MalformedURLException e) {
      return false;
    }
  }
}

