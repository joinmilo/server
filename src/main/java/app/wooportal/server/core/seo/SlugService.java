package app.wooportal.server.core.seo;

import java.util.Locale;

import org.springframework.stereotype.Service;

import com.github.slugify.Slugify;

@Service
public class SlugService {

  final Slugify slg = Slugify.builder().locale(Locale.GERMAN).build();

  public String slugify(String slug) {
    return slg.slugify(slug);
  }
}
