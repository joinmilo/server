package app.milo.server.core.context;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import graphql.language.Field;

public interface ApiContextAdapter {

  public JsonNode getSingleSaveContext();

  public ArrayNode getMultiSaveContext();
  
  public List<Field> getSingleReadContext();
  
  public List<Field> getMultiReadContext();
}
