package ru.finnetrolle.scripting.harbinger;

/**
 * THis interface defines API to process specified tag
 */
public interface TagProcessor {

  /**
   * Process context
   * @param context context of processing
   */
  void process(Context context);

  /**
   * I really don't decided yet if I need this method here...
   * @return tag for this processor instance
   */
  String getProcessingTag();

}
