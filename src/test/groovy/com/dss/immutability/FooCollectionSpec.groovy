package com.dss.immutability

import com.googlecode.totallylazy.Callable1
import com.googlecode.totallylazy.Callable2
import com.googlecode.totallylazy.Predicate
import spock.lang.Specification

import static com.googlecode.totallylazy.Sequences.sequence
import static com.googlecode.totallylazy.collections.TreeList.treeList

class FooCollectionSpec extends Specification {

    def "find just the bar == 1"() {

        given:

        def seq = sequence(new ImmutableFoo.Builder().withBar("1").withBaz("A").build(),
                new ImmutableFoo.Builder().withBar("2").withBaz("B").build(),
                new ImmutableFoo.Builder().withBar("1").withBaz("Z").build(),
                new ImmutableFoo.Builder().withBar("1").withBaz("B").build())

        when:

        def filtered = seq.filter(new Predicate<ImmutableFoo>() {
            @Override
            boolean matches(ImmutableFoo other) {
                return other.bar == "1"
            }
        })

        then:

        seq.size() == 4
        filtered.size() == 3

    }

    def "find just the baz == Z or A"() {

        given:

        def seq = sequence(new ImmutableFoo.Builder().withBar("1").withBaz("A").build(),
                new ImmutableFoo.Builder().withBar("2").withBaz("B").build(),
                new ImmutableFoo.Builder().withBar("1").withBaz("Z").build(),
                new ImmutableFoo.Builder().withBar("1").withBaz("B").build())

        when:

        def filtered = seq.filter(new Predicate<ImmutableFoo>() {
            @Override
            boolean matches(ImmutableFoo other) {
                return other.baz == "Z" || other.baz == "A"
            }
        })

        then:

        seq.size() == 4
        filtered.size() == 2

    }

    def "try PersistentList"() {

        given:

        def p1List = treeList(new ImmutableFoo.Builder().withBar("1").withBaz("A").build())

        when:

        def p2List = p1List.cons(new ImmutableFoo.Builder().withBar("2").withBaz("B").build())

        then:

        p1List.size() == 1
        p2List.size() == 2
    }

    def "filter a TreeList"() {
        given:

        def list = [new ImmutableFoo.Builder().withBar("1").withBaz("A").build(),
                new ImmutableFoo.Builder().withBar("2").withBaz("B").build(),
                new ImmutableFoo.Builder().withBar("1").withBaz("Z").build(),
                new ImmutableFoo.Builder().withBar("1").withBaz("B").build()]
        def pList = treeList(list)

        when:

        def filtered = pList.filter(new Predicate<ImmutableFoo>() {
            @Override
            boolean matches(ImmutableFoo other) {
                return other.baz == "Z" || other.baz == "A"
            }
        })

        then:

        pList.size() == 4
        filtered.size() == 2
    }

    def "from regular list"() {
        given:

        def list = [new ImmutableFoo.Builder().withBar("1").withBaz("A").build(),
                new ImmutableFoo.Builder().withBar("2").withBaz("B").build(),
                new ImmutableFoo.Builder().withBar("1").withBaz("Z").build(),
                new ImmutableFoo.Builder().withBar("1").withBaz("B").build()]

        when:

        def pList = treeList(list)

        then:

        pList.size() == 4
        pList.head().bar == "1"
        pList.tail().head().baz == "B"
    }

    def "sum of the foobars"() {
        given:

        def list = [new ImmutableFoo.Builder().withBar("1").withBaz("A").build(),
                new ImmutableFoo.Builder().withBar("2").withBaz("B").build(),
                new ImmutableFoo.Builder().withBar("1").withBaz("Z").build(),
                new ImmutableFoo.Builder().withBar("1").withBaz("B").build()]
        def pList = treeList(list)

        when:

        def sum = pList.fold(0, new Callable2<Integer, ImmutableFoo, Integer>() {
            @Override
            Integer call(Integer a, ImmutableFoo b) throws Exception {
                return a + Integer.parseInt(b.bar)
            }
        })

        then:

        sum == 5
    }

    def "lowercase the foobazs"() {
        given:

        def list = [new ImmutableFoo.Builder().withBar("1").withBaz("A").build(),
                    new ImmutableFoo.Builder().withBar("2").withBaz("B").build(),
                    new ImmutableFoo.Builder().withBar("1").withBaz("Z").build(),
                    new ImmutableFoo.Builder().withBar("1").withBaz("B").build()]
        def pList = treeList(list)

        when:

        def lowerList = pList.map(new Callable1<ImmutableFoo, ImmutableFoo>() {
            @Override
            ImmutableFoo call(ImmutableFoo o) throws Exception {
                return new ImmutableFoo.Builder().withBar(o.bar).withBaz(o.baz.toLowerCase()).build()
            }
        })

        then:

        lowerList.head().baz == 'a'
        lowerList.tail().head().baz == 'b'
    }
}
