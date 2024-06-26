package app.milo.server.core.seo.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Target({METHOD, FIELD})
@Retention(RUNTIME)
@JsonSerialize
@JsonDeserialize
public @interface SlugSource {
  
  public String[] field() default "";
}