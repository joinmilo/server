package app.wooportal.server.base.cms.feature;

import java.util.Optional;
import org.springframework.stereotype.Service;
import app.wooportal.server.base.cms.menuItem.MenuItemService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.core.utils.ReflectionUtils;

@Service
public class FeatureService extends DataService<FeatureEntity, FeaturePredicateBuilder> {
  
  public static String articlesFeature = "articles";
  public static String authorsFeature = "authors";
  public static String calendarFeature = "calendar";
  public static String contestsFeature = "contests";
  public static String dealsFeature = "deals";
  public static String eventsFeature = "events";
  public static String formsFeature = "forms";
  public static String guestarticleFeature = "guestarticle";
  public static String mapFeature = "map";
  public static String mediaFeature = "media";
  public static String organisationsFeature = "organisations";
  public static String reportsFeature = "reports";
  public static String surveysFeature = "surveys";

  public FeatureService(
      DataRepository<FeatureEntity> repo,
      FeaturePredicateBuilder predicate,
      MenuItemService menuItemService) {
    super(repo, predicate);
   
    addService("menuItems", menuItemService);
  }
  
  public Boolean changeActivation(String featureId, Boolean active) {
    var feature = repo.findOne(
        singleQuery(predicate.withId(featureId)).addGraph(graph("menuItems"))
    );
    
    if (feature.isPresent()) {
      var newFeature = ReflectionUtils.copy(feature.get());
      newFeature.setActive(active);
      
      if (active == null || !active) {
        newFeature.setMenuItems(null);
      }

      save(newFeature);
      return true;      
    }
    return false;
  }
  
  public FeatureEntity getArticlesFeature() {
    var feature = getByKey(articlesFeature);
    
    if (feature.isEmpty()) {
      throw new RuntimeException("Include Feature: " + articlesFeature);
    }
    
    return feature.get();
  }
  
  public FeatureEntity getAuthorsFeature() {
    var feature = getByKey(authorsFeature);
    
    if (feature.isEmpty()) {
      throw new RuntimeException("Include Feature: " + authorsFeature);
    }
    
    return feature.get();
  }
  
  public FeatureEntity getCalendarFeature() {
    var feature = getByKey(calendarFeature);
    
    if (feature.isEmpty()) {
      throw new RuntimeException("Include Feature: " + calendarFeature);
    }
    
    return feature.get();
  }
  
  public FeatureEntity getContestsFeature() {
    var feature = getByKey(contestsFeature);
    
    if (feature.isEmpty()) {
      throw new RuntimeException("Include Feature: " + contestsFeature);
    }
    
    return feature.get();
  }
  
  public FeatureEntity getDealsFeature() {
    var feature = getByKey(dealsFeature);
    
    if (feature.isEmpty()) {
      throw new RuntimeException("Include Feature: " + dealsFeature);
    }
    
    return feature.get();
  }
  
  public FeatureEntity getEventsFeature() {
    var feature = getByKey(eventsFeature);
    
    if (feature.isEmpty()) {
      throw new RuntimeException("Include Feature: " + eventsFeature);
    }
    
    return feature.get();
  }
  
  public FeatureEntity getFormsFeature() {
    var feature = getByKey(formsFeature);
    
    if (feature.isEmpty()) {
      throw new RuntimeException("Include Feature: " + formsFeature);
    }
    
    return feature.get();
  }
  
  public FeatureEntity getGuestarticleFeature() {
    var feature = getByKey(guestarticleFeature);
    
    if (feature.isEmpty()) {
      throw new RuntimeException("Include Feature: " + guestarticleFeature);
    }
    
    return feature.get();
  }
  
  public FeatureEntity getMapFeature() {
    var feature = getByKey(mapFeature);
    
    if (feature.isEmpty()) {
      throw new RuntimeException("Include Feature: " + mapFeature);
    }
    
    return feature.get();
  }
  
  public FeatureEntity getMediaFeature() {
    var feature = getByKey(mediaFeature);
    
    if (feature.isEmpty()) {
      throw new RuntimeException("Include Feature: " + mediaFeature);
    }
    
    return feature.get();
  }
  
  public FeatureEntity getOrganisationsFeature() {
    var feature = getByKey(organisationsFeature);
    
    if (feature.isEmpty()) {
      throw new RuntimeException("Include Feature: " + organisationsFeature);
    }
    
    return feature.get();
  }
  
  public FeatureEntity getReportsFeature() {
    var feature = getByKey(reportsFeature);
    
    if (feature.isEmpty()) {
      throw new RuntimeException("Include Feature: " + reportsFeature);
    }
    
    return feature.get();
  }
  
  public FeatureEntity getSurveysFeature() {
    var feature = getByKey(surveysFeature);
    
    if (feature.isEmpty()) {
      throw new RuntimeException("Include Feature: " + surveysFeature);
    }
    
    return feature.get();
  }

  public Optional<FeatureEntity> getByKey(String code) {
    return repo.findOne(singleQuery(predicate.withCode(code)));
  }

}