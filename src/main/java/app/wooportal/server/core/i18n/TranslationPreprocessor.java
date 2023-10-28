package app.wooportal.server.core.i18n;

import java.io.IOException;

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import app.wooportal.server.App;
import app.wooportal.server.core.i18n.annotations.Translatable;
import app.wooportal.server.core.utils.ReflectionUtils;
import app.wooportal.server.core.utils.SourceUtils;
import javassist.CannotCompileException;
import javassist.NotFoundException;

public class TranslationPreprocessor {

  public static void preprocess() throws NotFoundException, IOException, CannotCompileException {
    var reflections = new Reflections(new ConfigurationBuilder()
        .setUrls(ClasspathHelper.forPackage(App.class.getPackageName())));
    
    var annotated = reflections.getTypesAnnotatedWith(Entity.class);
    
    for (var clazz : annotated) {
      var fields = ReflectionUtils.getFieldsWithAnnotation(clazz, Translatable.class);
      
      if (fields != null && !fields.isEmpty()) {
        for (var field : fields) {
          SourceUtils.addAnnotationToField(clazz, field.getName(), Transient.class);
        }
      }
    }
  }
  
}
