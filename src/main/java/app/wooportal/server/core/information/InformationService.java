package app.wooportal.server.core.information;

import org.springframework.stereotype.Service;

@Service
public class InformationService {
  
  private final InformationConfiguration infoConfig;
  
  public InformationService(InformationConfiguration infoConfig) {
    this.infoConfig = infoConfig;
  }

  public InformationDto getInfo() {
    var info = new InformationDto();
    info.setVersion(infoConfig.getVersion());
    return info;
  }
}
