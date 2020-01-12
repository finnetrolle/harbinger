package ru.finnetrolle.scripting.harbinger.runner;

import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.finnetrolle.scripting.harbinger.Harbinger;

public class ReloadExampleRunner {

  static final Logger LOG = LoggerFactory.getLogger(ReloadExampleRunner.class);

  public static void main(String[] args) {

    Runner runner = new Runner();
    Thread thread = new Thread(runner);
    thread.start();

    Scanner scanner = new Scanner(System.in);
    scanner.hasNext();
    thread.interrupt();

  }

  static class Runner implements Runnable {

    @Override
    public void run() {
      Harbinger harbinger = new Harbinger(ExampleRunner.PATH_TO_DIR_WITH_SOURCES);
      LOG.info("press any key to stop process...");
      while (!Thread.interrupted()) {
        try {
          harbinger.loadProcessors("reloadable.ini");
          ExampleRunner.logMap(harbinger.process(ExampleRunner.SOURCE));
          Thread.sleep(5000);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
          LOG.info("The end");
        }
      }
    }
  }



}
