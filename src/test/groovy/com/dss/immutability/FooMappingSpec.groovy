package com.dss.immutability

import org.dozer.DozerBeanMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.support.AnnotationConfigContextLoader
import spock.lang.Specification

@ContextConfiguration(loader=AnnotationConfigContextLoader.class,classes=DozerConfig.class)
class FooMappingSpec extends Specification {

    @Autowired
    DozerBeanMapper mapper;

    def "can map Foo to ImmutableFoo"() {

        given:

        def foo = new Foo(bar: "bar", baz: "baz")

        when:

        def iFoo = mapper.map(foo, ImmutableFoo.Builder.class).build()

        then:

        iFoo.bar == "bar"
        iFoo.baz == "baz"
    }

    def "can map ImmutableFoo to Foo"() {

        given:

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
