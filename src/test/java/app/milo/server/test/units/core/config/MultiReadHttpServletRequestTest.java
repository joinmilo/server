package app.milo.server.test.units.core.config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import app.milo.server.core.config.MultiReadHttpServletRequest;

public class MultiReadHttpServletRequestTest {
  
  private MultiReadHttpServletRequest multiReadServlet;
  
  private String test = "test";
  
  @BeforeEach
  public void init() throws IOException {
    var requestMock = mock(HttpServletRequest.class);
    when(requestMock.getReader()).thenReturn(new BufferedReader(new StringReader(test)));
    when(requestMock.getInputStream()).thenReturn(new MockInputStream());
    multiReadServlet = new MultiReadHttpServletRequest(requestMock);
  }
  
  @Test
  public void getReaderMultipleTimesOk() throws IOException {
    assertThat(multiReadServlet.getReader().readLine()).isEqualTo(test);
    assertThat(multiReadServlet.getReader().readLine()).isEqualTo(test);
  }
  
  public class MockInputStream extends ServletInputStream {
    
    private StringReader reader = new StringReader(test);
    
    public int read() throws IOException {
      return reader.read();
    }

    @Override
    public boolean isFinished() {
      // TODO Auto-generated method stub
      return false;
    }

    @Override
    public boolean isReady() {
      // TODO Auto-generated method stub
      return true;
    }

    @Override
    public void setReadListener(ReadListener listener) {
      // TODO Auto-generated method stub
      
    }    
    
  }

}
