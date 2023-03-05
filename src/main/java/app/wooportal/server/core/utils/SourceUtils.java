package app.wooportal.server.core.utils;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.UnmodifiableClassException;
import java.security.ProtectionDomain;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtField;
import javassist.NotFoundException;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.annotation.Annotation;
import net.bytebuddy.agent.ByteBuddyAgent;

public class SourceUtils {

  public static void addAnnotationToField(Class<?> clazz, String fieldName,
      Class<?> annotationClass) throws NotFoundException, IOException, CannotCompileException {
    var pool = ClassPool.getDefault();
    var ctClass = pool.getCtClass(clazz.getName());
    if (ctClass.isFrozen()) {
      ctClass.defrost();
    }
    var ctField = ctClass.getDeclaredField(fieldName);
    var constPool = ctClass.getClassFile().getConstPool();

    var attr = getAnnotationsAttributeFromField(ctField);
    if (attr == null) {
      attr = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
      ctField.getFieldInfo().addAttribute(attr);
    }
    attr.addAnnotation(new Annotation(annotationClass.getName(), constPool));

    retransformClass(clazz, ctClass.toBytecode());
  }

  private static AnnotationsAttribute getAnnotationsAttributeFromField(CtField ctField) {
    var attrs = ctField.getFieldInfo().getAttributes();
    AnnotationsAttribute attr = null;
    if (attrs != null) {
      var optional = attrs.stream().filter(AnnotationsAttribute.class::isInstance).findFirst();
      if (optional.isPresent()) {
        attr = (AnnotationsAttribute) optional.get();
      }
    }
    return attr;
  }

  private static void retransformClass(Class<?> clazz, byte[] byteCode) {

    var cft = new ClassFileTransformer() {
      @Override
      public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
          ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        return byteCode;
      }
    };

    var instrumentation = ByteBuddyAgent.install();
    try {
      instrumentation.addTransformer(cft, true);
      instrumentation.retransformClasses(clazz);
    } catch (UnmodifiableClassException e) {
      e.printStackTrace();
    } finally {
      instrumentation.removeTransformer(cft);
    }
  }

}
