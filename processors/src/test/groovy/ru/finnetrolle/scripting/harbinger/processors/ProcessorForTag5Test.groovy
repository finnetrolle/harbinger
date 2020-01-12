package ru.finnetrolle.scripting.harbinger.processors

import ru.finnetrolle.scripting.harbinger.Context
import spock.lang.Specification

class ProcessorForTag5Test extends Specification {

    def "Processor must write 4 + 5 into 5"() {
        given:
            def context = Context.builder()
                .setSource(['4':'100', '5':'200'])
                .setDestination([:])
                .build()
        when:
            ProcessorForTag5.process(context)

        then:
            context.getDestination().get('5') == String.valueOf(300.0)
    }

}
