package ru.finnetrolle.scripting.harbinger.processors.common;

import ru.finnetrolle.scripting.harbinger.Context;
import ru.finnetrolle.scripting.harbinger.TagProcessor;

/**
 * This processor just copy value from src:tag to dst:tag
 */
public class CopyingProcessor implements TagProcessor {

  public static final String ALIAS = "Copy";

  private final String tag;

  public static CopyingProcessor forTag(String tag) {
    return new CopyingProcessor(tag);
  }

  private CopyingProcessor(String tag) {
    this.tag = tag;
  }

  @Override
  public void process(Context context) {
    String value = context.getSource().get(tag);
    context.getDestination().put(tag, value);
  }

  @Override
  public String getProcessingTag() {
    return tag;
  }
}
