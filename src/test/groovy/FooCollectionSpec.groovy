import com.dss.immutability.ImmutableFoo
import com.googlecode.totallylazy.Predicate
import com.googlecode.totallylazy.collections.TreeList
import spock.lang.Specification

import static com.googlecode.totallylazy.Sequences.sequence;

class FooCollectionSpec extends Specification {

    def "find just the bar == 1"() {

        given:

        def seq = sequence(new ImmutableFoo.Builder().bar("1").baz("A").build(),
                new ImmutableFoo.Builder().bar("2").baz("B").build(),
                new ImmutableFoo.Builder().bar("1").baz("Z").build(),
                new ImmutableFoo.Builder().bar("1").baz("B").build())

        when:

        def filtered = seq.filter(new Predicate<ImmutableFoo>() {
            @Override
            boolean matches(ImmutableFoo other) {
                return other.bar == "1"
            }
        })

        then:

        filtered.size() == 3

    }

    def "find just the baz == Z or A"() {

        given:

        def seq = sequence(new ImmutableFoo.Builder().bar("1").baz("A").build(),
                new ImmutableFoo.Builder().bar("2").baz("B").build(),
                new ImmutableFoo.Builder().bar("1").baz("Z").build(),
                new ImmutableFoo.Builder().bar("1").baz("B").build())

        when:

        def filtered = seq.filter(new Predicate<ImmutableFoo>() {
            @Override
            boolean matches(ImmutableFoo other) {
                return other.baz == "Z" || other.baz == "A"
            }
        })

        then:

        filtered.size() == 2

    }

    def "try PersistentList"() {

        given:

        def p1List = TreeList.treeList(new ImmutableFoo.Builder().bar("1").baz("A").build())

        when:

        def p2List = p1List.cons(new ImmutableFoo.Builder().bar("2").baz("B").build())

        then:

        p1List.size() == 1
        p2List.size() == 2
    }
}
