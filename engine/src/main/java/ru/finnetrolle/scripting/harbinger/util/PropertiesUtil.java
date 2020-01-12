package ru.finnetrolle.scripting.harbinger.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is for properties file to Map<S,S> transformation.
 * ToDo remove this layer
 */
public class PropertiesUtil {

  private final static Logger LOG = LoggerFactory.getLogger(PropertiesUtil.class);

  /**
   * Object of this class should not be initialized
   */
  private PropertiesUtil() {
  }

  public static Optional<Map<String, String>> loadPropertiesFile(String filename) {
    Map<String, String> result = new HashMap<>();
    try (FileInputStream fis = new FileInputStream(new File(filename))) {
      Properties properties = new Properties();
      properties.load(fis);
      properties.forEach((k, v) -> result.put(String.valueOf(k), String.valueOf(v)));
      return Optional.of(result);
    } catch (IOException e) {
      LOG.error("Can't load properties file", e);
    }
    return Optional.empty();
  }

}
