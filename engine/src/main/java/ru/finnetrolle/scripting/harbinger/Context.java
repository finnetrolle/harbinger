package ru.finnetrolle.scripting.harbinger;

import java.util.Map;

/**
 * This is a context of transformation
 * All objects and API should be accessible only from the object of this class
 */
public class Context {

  private final Map<String, String> source;
  private final Map<String, String> destination;

  public Map<String, String> getSource() {
    return source;
  }

  public Map<String, String> getDestination() {
    return destination;
  }

  /**
   * Object of this class can be initialized only via builder
   * @param source - message from source
   * @param destination - message to destination
   */
  private Context(Map<String, String> source,
      Map<String, String> destination) {
    this.source = source;
    this.destination = destination;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private Map<String, String> source;
    private Map<String, String> destination;

    public Builder setSource(Map<String, String> source) {
      this.source = source;
      return this;
    }

    public Builder setDestination(Map<String, String> destination) {
      this.destination = destination;
      return this;
    }

    public Map<String, String> getSource() {
      return source;
    }

    public Map<String, String> getDestination() {
      return destination;
    }

    public Context build() {
      return new Context(source, destination);
    }
  }

}
