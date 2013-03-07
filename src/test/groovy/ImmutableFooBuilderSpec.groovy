import com.dss.immutability.ImmutableFoo
import spock.lang.Specification

class ImmutableFooBuilderSpec extends Specification {

    def "should build a valid ImmutableFoo"() {
        given:

        def builder = new ImmutableFoo.Builder()

        when:

        def iFoo = builder.withBar("1").withBaz("A").build()

        then:

        iFoo.bar == "1"
        iFoo.baz == "A"
    }
}
