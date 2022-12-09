package app.wooportal.server.core.media.base;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class MediaHtmlDto {

  private String name;
  
  private String html;
  
  private ExportType type;
  
  public String getHtml() {
    if (!html.contains("<body>")) {
      html = String.format("<body style=\"font-family: Arial, Helvetica, sans-serif;\">%s</body>", html);
    }
    
    if (!html.contains("<html>")) {
      html = String.format("""  
        <html>
          <head>
            <style>
              @font-face {
                font-family: Arial;
                src: url(Arial.ttf);
                font-weight: normal;
                font-style: normal;
              }
            </style>
          </head>
          %s
        </html>
      """, html);
    }
    
    return html.strip();
  }
}
