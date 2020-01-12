package ru.finnetrolle.scripting.harbinger.processors

import ru.finnetrolle.scripting.harbinger.Context

class ProcessorToModifyForTag4 {

    static void process(Context context) {
        int v1 = Integer.parseInt(context.source.get("4"))
        int result = v1 * 8
        context.destination.put("4", String.valueOf(result))
    }

}