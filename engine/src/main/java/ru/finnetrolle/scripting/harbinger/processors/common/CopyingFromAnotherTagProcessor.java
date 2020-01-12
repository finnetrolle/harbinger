package ru.finnetrolle.scripting.harbinger.processors.common;

import ru.finnetrolle.scripting.harbinger.Context;
import ru.finnetrolle.scripting.harbinger.TagProcessor;

/**
 * This processor copy value into current tag from specified tag
 */
public class CopyingFromAnotherTagProcessor implements TagProcessor {

  public static final String ALIAS = "CopyFrom";

  private final String from;
  private final String to;

  private CopyingFromAnotherTagProcessor(String from, String to) {
    this.from = from;
    this.to = to;
  }

  @Override
  public void process(Context context) {
    String value = context.getSource().get(from);
    context.getDestination().put(to, value);
  }

  @Override
  public String getProcessingTag() {
    return null;
  }

  public static Builder from(String tag) {
    return new Builder(tag);
  }

  public static class Builder {
    private final String from;

    private Builder(String from) {
      this.from = from;
    }

    public CopyingFromAnotherTagProcessor to(String tag) {
      return new CopyingFromAnotherTagProcessor(from, tag);
    }
  }

}

