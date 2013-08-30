package com.dss.immutability

import org.dozer.DozerBeanMapper
import org.dozer.loader.api.BeanMappingBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DozerConfig {

    static BeanMappingBuilder builder = new BeanMappingBuilder() {
        @Override
        void configure() {
            mapping(Foo.class, ImmutableFoo.Builder.class)
            .fields(field("bar").setMethod("setBar"), field("bar").setMethod("withBar"))
            .fields(field("baz").setMethod("setBaz"), field("baz").setMethod("withBaz"))
        }
    }

    @Bean
    DozerBeanMapper mapper() {
        def mapper = new DozerBeanMapper()
        mapper.addMapping(builder);
        mapper
    }
}
