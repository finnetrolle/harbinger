package ru.finnetrolle.scripting.harbinger.runner;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.finnetrolle.scripting.harbinger.Harbinger;
import ru.finnetrolle.scripting.harbinger.util.Pair;

public class ExampleRunner {

  private static final Logger LOG = LoggerFactory.getLogger(ExampleRunner.class);
  static final String[] PATH_TO_DIR_WITH_SOURCES = {"snippets\\src\\main\\groovy\\ru\\finnetrolle\\scripting\\harbinger\\snippets\\"};
  static final Map<String, String> SOURCE = Stream.of(
      Pair.from("1", "1000"),
      Pair.from("2", "season"),
      Pair.from("3", "world"),
      Pair.from("4", "123"),
      Pair.from("5", "322.48")
  ).collect(Collectors.toMap(Pair::getKey, Pair::getValue));

  static void logMap(Map<String, String> map) {
    map.forEach((k, v) -> LOG.info("{}={}", k, v));
  }

  public static void main(String[] args) {
    Harbinger harbinger = new Harbinger(PATH_TO_DIR_WITH_SOURCES);
    logMap(SOURCE);

    harbinger.loadProcessors("example.ini");
    logMap(harbinger.process(SOURCE));

    harbinger.loadProcessors("example2.ini");
    logMap(harbinger.process(SOURCE));
  }


}
