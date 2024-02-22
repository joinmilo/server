package app.wooportal.server.features.navigator.base;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;


@Service
public class NavigatorChoiceService
    extends DataService<NavigatorChoiceEntity, NavigatorChoicePredicateBuilder> {

  public NavigatorChoiceService(DataRepository<NavigatorChoiceEntity> repo,
      NavigatorChoicePredicateBuilder predicate) {
    super(repo, predicate);
  }

//  public List<NavigatorChoiceEntity> getPossibleNodes(NavigatorChoiceEntity node) { 
//    var nodes = repo.findAll();
//    List<NavigatorChoiceEntity> parentNodes = new ArrayList<>();
//    getParentNodes(node, parentNodes);
//    nodes.removeAll(parentNodes);
//    return nodes;
//  }

//  public void getParentNodes(NavigatorChoiceEntity node, List<NavigatorChoiceEntity> parentNodes) {
//    if (node.getParents() != null && !node.getParents().isEmpty()) {
//      node.getParents().forEach(connection -> {
//        parentNodes.add(connection.getParent());
//        getParentNodes(connection.getParent(), parentNodes);
//      });
//    }
//  }
}
