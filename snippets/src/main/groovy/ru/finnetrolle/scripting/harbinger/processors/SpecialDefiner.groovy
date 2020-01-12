package ru.finnetrolle.scripting.harbinger.processors

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.finnetrolle.scripting.harbinger.Context

class SpecialDefiner {

    private final static Logger LOG = LoggerFactory.getLogger(SpecialDefiner.class)

    static void process(Context context) {
        LOG.info("Oh my god!!! I'm Special Definer!!!")
        String value = context.getSource().get("4")
        int amount = Integer.parseInt(value)
        long cubed = amount * amount
        String result = String.valueOf(cubed)
        context.destination.put("4", result)
    }

}