package ru.finnetrolle.scripting.harbinger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.finnetrolle.scripting.harbinger.util.GroovyProcessorLoader;
import ru.finnetrolle.scripting.harbinger.util.ProcessorDefiner;
import ru.finnetrolle.scripting.harbinger.util.PropertiesUtil;

/**
 * Core class of Harbinger. Load processors, process contexts.
 */
public class Harbinger {

  private final static Logger LOG = LoggerFactory.getLogger(Harbinger.class);
  private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
  private final Lock readLock = readWriteLock.readLock();
  private final Lock writeLock = readWriteLock.writeLock();

  private final GroovyProcessorLoader groovyProcessorLoader;
  private Map<String, TagProcessor> processors = null;

  public Harbinger(String[] pathToDirectoryWithSources) {
    groovyProcessorLoader = new GroovyProcessorLoader(pathToDirectoryWithSources);
  }

  /**
   * Process source map with loaded processors.
   * Processing is source-based.
   * Tags from source that not found in processors wouldn't exist in result.
   * @param source - map with source data
   * @return map with result data
   */
  public Map<String, String> process(Map<String, String> source) {
    Context context = Context.builder()
        .setSource(source)
        .setDestination(new HashMap<>())
        .build();

    try {
      readLock.lock();
      if (processors == null) {
        throw new RuntimeException("Processors are not preloaded");
      }

      source.keySet().forEach(tag -> {
        TagProcessor processor = processors.get(tag);
        if (processor != null) {
          processor.process(context);
        }
      });

    } finally {
      readLock.unlock();
    }

    return context.getDestination();
  }

  /**
   * Load or reload processors according to settings file instructions
   * @param filename - path to file with instructions
   */
  public void loadProcessors(String filename) {
    Map<String, TagProcessor> map = new HashMap<>();

    PropertiesUtil.loadPropertiesFile(filename)
        .orElseThrow(() -> new RuntimeException("Cannot load properties file"))
        .forEach((tag, propName) -> defineProcessor(tag, propName)
            .ifPresent(processor -> map
                .put(tag, processor)));

    try {
      writeLock.lock();
      this.processors = map;
    } finally {
      writeLock.unlock();
    }
  }

  private Optional<TagProcessor> defineProcessor(String tag, String processorName) {
    Optional<TagProcessor> processor = ProcessorDefiner.defineCommonProcessor(tag, processorName);
    if (processor.isPresent()) {
      LOG.info("Processor {} is loaded for tag {}", processor.get().getClass().getName(), tag);
      return processor;
    }

    String fileName = processorName + ".groovy";
    Optional<TagProcessor> optionalTagProcessor = groovyProcessorLoader.load(fileName);
    if (optionalTagProcessor.isPresent()) {
      LOG.info("Dynamic processor {} is loaded for tag {}", fileName, tag);
    } else {
      LOG.error("Cannot load dynamic processor {} for tag {}", fileName, tag);
    }
    return optionalTagProcessor;
  }

}
