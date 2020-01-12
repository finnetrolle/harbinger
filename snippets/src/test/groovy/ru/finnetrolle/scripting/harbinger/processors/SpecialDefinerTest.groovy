package ru.finnetrolle.scripting.harbinger.processors

import ru.finnetrolle.scripting.harbinger.Context
import spock.lang.Specification

class SpecialDefinerTest extends Specification {

    def "Tag 4 must be powered by 2"() {
        given:
        Context context = Context.builder()
            .setSource(['4':'100'])
            .setDestination([:])
            .build()

        when:
        SpecialDefiner.process(context)

        then:
        context.destination.get('4') == '10000'
    }


}
