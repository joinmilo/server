package app.wooportal.server.core.push;

import java.util.HashMap;
import java.util.Map;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class MessageDto {

  private String title;
  
  private String content;
  
  private NotificationType type;
  
  private Map<String, String> data = new HashMap<>();
  
  public MessageDto(
      String title,
      String content,
      NotificationType type) {
    setTitle(title);
    setContent(content);
    setType(type);
    
    this.data.put("type", type.toString());
  }
  
  public MessageDto(
      String title,
      String content,
      Map<String, String> data,
      NotificationType type) {
    setTitle(title);
    setContent(content);
    setType(type);
    
    this.data.put("type", type.toString());
    this.data.putAll(data);
  }
  
}
