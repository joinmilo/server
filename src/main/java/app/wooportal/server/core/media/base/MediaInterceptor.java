package app.wooportal.server.core.media.base;

import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.error.exception.NotFoundException;
import app.wooportal.server.core.media.storage.StorageService;

@Service
public class MediaInterceptor extends EmptyInterceptor {

  private static final long serialVersionUID = 1L;
  
  @Autowired
  private StorageService storageService;

  @Override
  public boolean onLoad(
      Object entity, 
      Serializable id, 
      Object[] state,
      String[] propertyNames,
      Type[] types) {
    if (MediaEntity.class.isAssignableFrom(entity.getClass())) {
      String mimeType = null;
      for (int i = 0; i < propertyNames.length; i++) {
        if (propertyNames[i].equals("mimeType")) {
          mimeType = (String) state[i];
          break;
        }
      }
      try {
        if (mimeType != null) {
          ((MediaEntity) entity).setBase64(Base64.getEncoder().encodeToString(
              storageService.read((String) id, MediaHelper.extractFormatFromMimeType(mimeType))));
        }
      } catch (NotFoundException | IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return false;
  }


}
