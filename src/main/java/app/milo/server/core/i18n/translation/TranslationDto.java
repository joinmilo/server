package app.milo.server.core.i18n.translation;

import java.util.HashMap;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.i18n.entities.TranslatableEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TranslationDto {
  
  private Class<TranslatableEntity<BaseEntity>> translatableClass;
  
  private String longestContentField;
  
  private HashMap<String, String> sourceFields = new HashMap<>();
  
  public TranslationDto putSourcField(String field, String value) {
    sourceFields.put(field, value);
    setLongestContenField(field, value);
    return this;
  }

  private TranslationDto setLongestContenField(String field, String value) {
    if (longestContentField == null
        || value.toString().length() > sourceFields.get(longestContentField).length()) {
      longestContentField = field;
    }
    return this;
  }

  public String getLongestContent() {
    return sourceFields != null && !sourceFields.isEmpty()
        ? sourceFields.get(longestContentField)
        : null;
  }

}
