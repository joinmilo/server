package app.wooportal.server.base.cms.plugin;

import java.util.Optional;

import org.springframework.stereotype.Service;

import app.wooportal.server.base.cms.menuItem.MenuItemService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.core.utils.ReflectionUtils;

@Service
public class PluginService extends DataService<PluginEntity, PluginPredicateBuilder> {
  
  public static String articlesPlugin = "articles";
  public static String authorsPlugin = "authors";
  public static String calendarPlugin = "calendar";
  public static String contestsPlugin = "contests";
  public static String dealsPlugin = "deals";
  public static String eventsPlugin = "events";
  public static String formsPlugin = "forms";
  public static String guestarticlePlugin = "guestarticle";
  public static String mapPlugin = "map";
  public static String mediaPlugin = "media";
  public static String organisationsPlugin = "organisations";
  public static String reportsPlugin = "reports";
  public static String surveysPlugin = "surveys";

  public PluginService(
      DataRepository<PluginEntity> repo,
      PluginPredicateBuilder predicate,
      MenuItemService menuItemService) {
    super(repo, predicate);
   
    addService("menuItems", menuItemService);
  }
  
  public Boolean changeActivation(String pluginId, Boolean active) {
    var feature = repo.findOne(
        singleQuery(predicate.withId(pluginId)).addGraph(graph("menuItems"))
    );
    
    if (feature.isPresent()) {
      var newPlugin = ReflectionUtils.copy(feature.get());
      newPlugin.setActive(active);
      
      if (active == null || !active) {
        newPlugin.setMenuItems(null);
      }

      save(newPlugin);
      return true;      
    }
    return false;
  }
  
  public PluginEntity getArticlesPlugin() {
    var feature = getByKey(articlesPlugin);
    
    if (feature.isEmpty()) {
      throw new RuntimeException("Include Plugin: " + articlesPlugin);
    }
    
    return feature.get();
  }
  
  public PluginEntity getAuthorsPlugin() {
    var feature = getByKey(authorsPlugin);
    
    if (feature.isEmpty()) {
      throw new RuntimeException("Include Plugin: " + authorsPlugin);
    }
    
    return feature.get();
  }
  
  public PluginEntity getCalendarPlugin() {
    var feature = getByKey(calendarPlugin);
    
    if (feature.isEmpty()) {
      throw new RuntimeException("Include Plugin: " + calendarPlugin);
    }
    
    return feature.get();
  }
  
  public PluginEntity getContestsPlugin() {
    var feature = getByKey(contestsPlugin);
    
    if (feature.isEmpty()) {
      throw new RuntimeException("Include Plugin: " + contestsPlugin);
    }
    
    return feature.get();
  }
  
  public PluginEntity getDealsPlugin() {
    var feature = getByKey(dealsPlugin);
    
    if (feature.isEmpty()) {
      throw new RuntimeException("Include Plugin: " + dealsPlugin);
    }
    
    return feature.get();
  }
  
  public PluginEntity getEventsPlugin() {
    var feature = getByKey(eventsPlugin);
    
    if (feature.isEmpty()) {
      throw new RuntimeException("Include Plugin: " + eventsPlugin);
    }
    
    return feature.get();
  }
  
  public PluginEntity getFormsPlugin() {
    var feature = getByKey(formsPlugin);
    
    if (feature.isEmpty()) {
      throw new RuntimeException("Include Plugin: " + formsPlugin);
    }
    
    return feature.get();
  }
  
  public PluginEntity getGuestarticlePlugin() {
    var feature = getByKey(guestarticlePlugin);
    
    if (feature.isEmpty()) {
      throw new RuntimeException("Include Plugin: " + guestarticlePlugin);
    }
    
    return feature.get();
  }
  
  public PluginEntity getMapPlugin() {
    var feature = getByKey(mapPlugin);
    
    if (feature.isEmpty()) {
      throw new RuntimeException("Include Plugin: " + mapPlugin);
    }
    
    return feature.get();
  }
  
  public PluginEntity getMediaPlugin() {
    var feature = getByKey(mediaPlugin);
    
    if (feature.isEmpty()) {
      throw new RuntimeException("Include Plugin: " + mediaPlugin);
    }
    
    return feature.get();
  }
  
  public PluginEntity getOrganisationsPlugin() {
    var feature = getByKey(organisationsPlugin);
    
    if (feature.isEmpty()) {
      throw new RuntimeException("Include Plugin: " + organisationsPlugin);
    }
    
    return feature.get();
  }
  
  public PluginEntity getReportsPlugin() {
    var feature = getByKey(reportsPlugin);
    
    if (feature.isEmpty()) {
      throw new RuntimeException("Include Plugin: " + reportsPlugin);
    }
    
    return feature.get();
  }
  
  public PluginEntity getSurveysPlugin() {
    var feature = getByKey(surveysPlugin);
    
    if (feature.isEmpty()) {
      throw new RuntimeException("Include Plugin: " + surveysPlugin);
    }
    
    return feature.get();
  }

  public Optional<PluginEntity> getByKey(String code) {
    return repo.findOne(singleQuery(predicate.withCode(code)));
  }

}