package ru.finnetrolle.scripting.harbinger.processors

import ru.finnetrolle.scripting.harbinger.Context

class ProcessorForTag5 {

    static void process(Context context) {
        int v1 = Integer.parseInt(context.source.get("4"))
        float v2 = Float.parseFloat(context.source.get("5"))
        float sum = v2 + v1
        context.destination.put("5", String.valueOf(sum))
    }

}