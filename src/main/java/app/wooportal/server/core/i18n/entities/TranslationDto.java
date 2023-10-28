package app.wooportal.server.core.i18n.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TranslationDto {
  
  @JsonProperty(value = "detected_langs")
  private String[] detectedLangs;
  
  @JsonProperty(value = "source_lang")
  private String sourceLang;
  
  @JsonProperty(value = "target_lang")
  private String targetLang;
  
  private String[] translated;
  
  @JsonProperty(value = "translation_time")
  private Double translationTime;

}
