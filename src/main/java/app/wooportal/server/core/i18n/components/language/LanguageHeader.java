package app.wooportal.server.core.i18n.components.language;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LanguageHeader implements Comparable<LanguageHeader> {

  private String language;
  
  private double value;
  
  public LanguageHeader(String unpreparedHeaderValue) {
    String[] langValue = unpreparedHeaderValue.split(";");
    this.language = langValue[0].substring(0, 2);
    this.value = langValue.length == 1
        ? 1.0
        : parseValue(langValue[1]);
  }

  private double parseValue(String langValue) {
    try {
      return Double.parseDouble(langValue.replaceAll("q=", ""));
    } catch (NumberFormatException e) {
      return 0.2;
    }
  }
  
  @Override
  public int compareTo(LanguageHeader toCompare) {
    return Double.compare(this.getValue(), toCompare.getValue()); 
  }
}
