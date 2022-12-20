package app.wooportal.server.core.information;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.springframework.stereotype.Service;

@Service
public class InformationService {

  public InformationDto getVersion()
      throws FileNotFoundException, IOException, XmlPullParserException {
    var reader = new MavenXpp3Reader();
    var model = reader.read(new FileReader("pom.xml"));
    var version = new InformationDto();
    version.setVersion(model.getVersion());
    return version;
  }
}
