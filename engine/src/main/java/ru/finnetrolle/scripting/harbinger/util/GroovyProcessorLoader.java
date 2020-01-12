package ru.finnetrolle.scripting.harbinger.util;

import static net.bytebuddy.matcher.ElementMatchers.isDeclaredBy;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static net.bytebuddy.matcher.ElementMatchers.takesArgument;

import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;
import java.io.IOException;
import java.util.Optional;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.finnetrolle.scripting.harbinger.Context;
import ru.finnetrolle.scripting.harbinger.TagProcessor;

/**
 * Read, compile and load groovy snippet into meta space.
 * Then create a processor from interface {@TagProcessor} and link process method to loaded class
 */
public class GroovyProcessorLoader {

  private final static Logger LOG = LoggerFactory.getLogger(GroovyProcessorLoader.class);

  private final String[] paths;

  public GroovyProcessorLoader(String[] paths) {
    this.paths = paths;
  }

  public Optional<TagProcessor> load(String filename) {
    try {
      GroovyScriptEngine engine = new GroovyScriptEngine(paths);
      Class loadedStaticClass = engine.loadScriptByName(filename);

      TagProcessor processor = new ByteBuddy()
          .subclass(TagProcessor.class)
          .method(named("process")
              .and(isDeclaredBy(TagProcessor.class))
              .and(takesArgument(0, Context.class)))
          .intercept(MethodDelegation.to(loadedStaticClass))
          .make()
          .load(loadedStaticClass.getClassLoader())
          .getLoaded()
          .newInstance();

      return Optional.of(processor);

    } catch (ResourceException | IOException | ScriptException | IllegalAccessException | InstantiationException e) {
      LOG.error("Can't load file", e);
    }

    return Optional.empty();
  }


}
