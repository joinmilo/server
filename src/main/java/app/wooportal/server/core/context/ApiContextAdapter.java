package app.wooportal.server.core.context;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import graphql.language.Field;

public interface ApiContextAdapter {

  public JsonNode getSingleSaveContext();

  public JsonNode getMultiSaveContext();
  
  public List<Field> getSingleReadContext();
  
  public List<Field> getMultiReadContext();
}
