package app.wooportal.server.test.units.core.config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import javax.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import app.wooportal.server.core.context.GraphQlContextAdapter;

public class GraphQlContextAdapterTest {
  
  private GraphQlContextAdapter adapter = new GraphQlContextAdapter(new ObjectMapper());
    
  @Test
  public void getSingleSaveContextOk() throws IOException {
    setRequest("{\n"
        + "   \"variables\":{\n"
        + "      \"entity\":\n"
        + "         {\n"
        + "            \"field\":\"testField\",\n"
        + "            \"childField\":{\n"
        + "               \"field\":\"testFieldChild\"\n"
        + "            }\n"
        + "         }\n"
        + "   }\n"
        + "}");
    
    var result = adapter.getSingleSaveContext();
    
    assertThat(result.size()).isEqualTo(2);
  }
  
  @Test
  public void getMultiSaveContextOk() throws IOException {
    setRequest("{\n"
        + "   \"variables\":{\n"
        + "      \"entities\":[\n"
        + "         {\n"
        + "            \"field\":\"testField\",\n"
        + "            \"childField\":{\n"
        + "               \"field\":\"testFieldChild\"\n"
        + "            }\n"
        + "         }\n"
        + "      ]\n"
        + "   }\n"
        + "}");
    
    var result = adapter.getMultiSaveContext();
    
    assertThat(result.size()).isEqualTo(1);
  }
  
  @Test
  public void getSingleSaveContextBadPayload() throws IOException {
    setRequest("{\n"
        + "   \"variables\":{\n"
        + "      \"doesNotExist\":\n"
        + "         {\n"
        + "            \"field\":\"testField\",\n"
        + "            \"childField\":{\n"
        + "               \"field\":\"testFieldChild\"\n"
        + "            }\n"
        + "         }\n"
        + "   }\n"
        + "}");
    
    var result = adapter.getSingleSaveContext();
    
    assertThat(result).isNull();
  }
  
  public void setRequest(String payload) throws IOException {
    var requestMock = mock(HttpServletRequest.class);
    when(requestMock.getReader()).thenReturn(new BufferedReader(new StringReader(payload)));
    adapter.setRequest(requestMock);
  }
  
}
