package app.wooportal.server.core.i18n;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import javax.transaction.Transactional;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.core.error.ErrorMailService;
import app.wooportal.server.core.i18n.entities.TranslatableEntity;
import app.wooportal.server.core.i18n.translation.TranslationService;

@Aspect
@Service
public class TranslationInterceptor {
  
  private final TranslationService translationService;
  
  private final ErrorMailService errorMailService;
  
  public TranslationInterceptor(
      TranslationService translationService,
      ErrorMailService errorMailService) {
    this.translationService = translationService;
    this.errorMailService = errorMailService;
  }
  
  @Pointcut("execution(* app.wooportal.server.core.repository.DataRepository+.save(..))")
  private void save() {
  }

  @Pointcut(
      "execution(public * app.wooportal.server.core.repository.DataRepository+.findOne(..))")
  private void findOne() {
  }
  
  @Pointcut(
      "execution(* app.wooportal.server.core.repository.DataRepository+.findAll(..))")
  private void findAll() {
  }
  
  
  @Around("findAll()")
  public Object replaceIterableWithTranslations(ProceedingJoinPoint pjp) throws Throwable {
    var result = pjp.proceed();
    if (result instanceof PageableList<?>) {
      var list = ((PageableList<?>) result).getList();
      translationService.localizeList(list);
    }
    return result;
  }

  @Around("findOne()")
  public Object replaceSingleEntityWithTranslation(ProceedingJoinPoint pjp) throws Throwable {
    Object result = pjp.proceed();
    if (result instanceof Optional<?> && ((Optional<?>) result).isPresent()) {
      Object entity = ((Optional<?>) result).get();
      translationService.localizeSingle(entity);
      return Optional.of(entity);
    }
    return result;
  }
  
  @SuppressWarnings("unchecked")
  @Around("save()")
  @Transactional
  public <E extends BaseEntity> Object saveTranslation(ProceedingJoinPoint pjp) throws Throwable {
    Object result = pjp.proceed();
    var savedEntity = pjp.getArgs()[0];

    if (!(savedEntity instanceof TranslatableEntity)) {
      // Defaults should be saved synchronously because request
      // will be gone in async procession
      var savedDefaultLocale = translationService.saveDefaultTranslations((E) savedEntity);
      CompletableFuture.runAsync(() -> {
        try {
          translationService.saveAutoTranslations((E) savedEntity, savedDefaultLocale);
        } catch (Throwable e) {
          e.printStackTrace();
          errorMailService.sendErrorMail(e.getStackTrace().toString());          
        }
      });
    }
    return result;
  }
}
