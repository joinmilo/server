package app.wooportal.server.base.cms.components.menuItem;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class MenuItemService
    extends DataService<MenuItemEntity, MenuItemPredicateBuilder> {

  public MenuItemService(
      DataRepository<MenuItemEntity> repo,
      MenuItemPredicateBuilder predicate,
      @Lazy MenuItemService menuItemService) {
    super(repo, predicate);
    
    addService("subMenuItems", menuItemService);    
  }
}
