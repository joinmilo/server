package app.wooportal.server.base.search;

import app.wooportal.server.base.cms.components.plugin.PluginEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class SearchDto {

  private String slug;
  
  private String name;

  private PluginEntity plugin;

}
