package app.milo.server.base.cms.components.page.base;

import org.springframework.stereotype.Service;
import app.milo.server.base.cms.components.menuItem.MenuItemService;
import app.milo.server.base.cms.components.page.embedding.PageEmbeddingService;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

@Service
public class PageService
    extends DataService<PageEntity, PagePredicateBuilder> {

  public PageService(DataRepository<PageEntity> repo,
      PagePredicateBuilder predicate,
      PageEmbeddingService embeddingService,
      MenuItemService menuItemService) {
    super(repo, predicate);
    
    addService("embeddings", embeddingService);
    addService("menuItems", menuItemService);
  }

  public Boolean assignLanding(String pageId) {
    var page = getById(pageId);
    
    if (page.isPresent()) {
      page.get().setIsLanding(true);
      repo.save(page.get());
      
      unassignLandingOthers(pageId);
      
      return true;
    }
    return false;
  }

  private void unassignLandingOthers(String pageId) {
    var others = readAll(collectionQuery(predicate.withLandingTrue().and(predicate.withoutId(pageId))));
    if (others != null && !others.isEmpty()) {
      others.getList().stream().forEach(event -> {
        event.setIsLanding(false);
        repo.save(event);
      });
    }
  }  
}
