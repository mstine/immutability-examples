package com.dss.immutability

import com.dss.immutability.Foo
import com.dss.immutability.ImmutableFoo
import org.dozer.DozerBeanMapper
import spock.lang.Specification

class FooMappingSpec extends Specification {

    def "can map Foo to ImmutableFoo"() {

        given:

        def mapper = new DozerBeanMapper(["mapping.xml"])
        def foo = new Foo(bar: "bar", baz: "baz")

        when:

        def iFoo = mapper.map(foo, ImmutableFoo.Builder.class).build()

        then:

        iFoo.bar == "bar"
        iFoo.baz == "baz"
    }

    def "can map ImmutableFoo to Foo"() {

        given:

        def mapper = new DozerBeanMapper(["mapping.xml"])
        def iFoo = new ImmutableFoo.Builder()
                .withBar("bar")
                .withBaz("baz")
                .build()

        when:

        def foo = mapper.map(iFoo, Foo.class)

        then:

        foo.bar == "bar"
        foo.baz == "baz"
    }
}
