package app.wooportal.server.features.navigator.page;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;


@Service
public class NavigatorPageService
    extends DataService<NavigatorPageEntity, NavigatorPagePredicateBuilder> {

  public NavigatorPageService(DataRepository<NavigatorPageEntity> repo,
      NavigatorPagePredicateBuilder predicate) {
    super(repo, predicate);
  }

  public NavigatorPageEntity getStartPage() {
    return repo.findAll(collectionQuery(predicate.withoutParentChoice())).get(0);
  }

//  public List<NavigatorPageEntity> getPossiblePages(NavigatorPageEntity page) {
//    var pages = repo.findAll();
//    List<NavigatorPageEntity> parentpages = new ArrayList<>();
//    getParentpages(page, parentpages);
//    pages.removeAll(parentpages);
//    return pages;
//  }
//
//  public void getParentpages(NavigatorPageEntity page, List<NavigatorPageEntity> parentpages) {
//    
//    if (page.getParentChoices() != null && !page.getParentChoices()  .isEmpty()) {
//      
//      page.getParents().forEach(connection -> {
//        parentpages.add(connection.getParent());
//        getParentpages(connection.getParent(), parentpages);
//      });
//    }
//  }
}
