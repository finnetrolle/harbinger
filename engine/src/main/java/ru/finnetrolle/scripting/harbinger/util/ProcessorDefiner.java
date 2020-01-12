package ru.finnetrolle.scripting.harbinger.util;

import java.util.Optional;
import ru.finnetrolle.scripting.harbinger.TagProcessor;
import ru.finnetrolle.scripting.harbinger.processors.common.CopyingFromAnotherTagProcessor;
import ru.finnetrolle.scripting.harbinger.processors.common.CopyingProcessor;

/**
 * This class is for loading common precompiled processors
 */
public class ProcessorDefiner {

  /**
   * Object of this class should not be initialized
   */
  private ProcessorDefiner() {
  }

  public static Optional<TagProcessor> defineCommonProcessor(String tag, String processorName) {
    if (CopyingProcessor.ALIAS.equals(processorName)) {
      return Optional.of(CopyingProcessor.forTag(tag));
    }

    String[] splitted = processorName.split(":");
    if (splitted.length == 2 && CopyingFromAnotherTagProcessor.ALIAS.equals(splitted[0])) {
      return Optional.of(CopyingFromAnotherTagProcessor.from(splitted[1]).to(tag));
    }

    return Optional.empty();
  }

}
