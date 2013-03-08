package com.dss.jirmdemo

import co.jirm.orm.JirmFactory
import com.dss.jirmdemo.model.Speaker
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.transaction.TransactionConfiguration
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

@ContextConfiguration(["classpath:application-context.xml"])
@TransactionConfiguration(transactionManager="transactionManager")
@Transactional
class JirmSpec extends Specification {

    @Autowired
    JirmFactory factory

    def "should create Speaker"() {
        given:

        def dao = factory.daoFor(Speaker.class)
        def id = createId()
        def speaker = new Speaker(id, "Matt Stine", "Sr. Consultant")

        when:

        dao.insert(speaker)
        def speakerFromDb = dao.findById(id)

        then:

        speaker == speakerFromDb
    }

    def "should update Speaker's title"() {
        given:

        def dao = factory.daoFor(Speaker.class)
        def id = createId()
        def speaker = new Speaker(id, "Matt Stine", "Sr. Consultant")

        when:

        dao.insert(speaker)
        dao.update().set("title", "Sr. Architect").where().property("id", id).execute()
        def speakerFromDb = dao.findById(id)

        then:

        speaker.title == "Sr. Consultant"
        speakerFromDb.title == "Sr. Architect"
    }



    def "should find speakers with fluent query"() {
        given:

        def dao = factory.daoFor(Speaker.class)
        def speakers =
            [new Speaker(createId(), "Venkat Subramaniam", "Founder of Agile Developer, Inc."),
             new Speaker(createId(), "Brian Sletten", "Forward Leaning Software Engineer"),
             new Speaker(createId(), "Ken Sipe", "Architect, Web Security Expert"),
             new Speaker(createId(), "Matthew McCullough", "VP of Training, GitHub")]

        when:

        dao.insert(speakers.iterator(), 4)
        def results = dao.select().where()
                        .property("name").eq("Brian Sletten")
                        .query().forList()

        then:

        results.size() == 1
        results[0].title == "Forward Leaning Software Engineer"
    }

    def "should find speakers with SQL"() {
        given:

        def dao = factory.daoFor(Speaker.class)
        def speakers =
            [new Speaker(createId(), "Venkat Subramaniam", "Founder of Agile Developer, Inc."),
                    new Speaker(createId(), "Brian Sletten", "Forward Leaning Software Engineer"),
                    new Speaker(createId(), "Ken Sipe", "Architect, Web Security Expert"),
                    new Speaker(createId(), "Matthew McCullough", "VP of Training, GitHub")]
        def sql =
            """SELECT id, name, title FROM speakers WHERE
               name LIKE 'matcher' -- {lastNameMatcher}"""

        when:

        dao.insert(speakers.iterator(), 4)
        def results = dao.getSelectBuilderFactory().sql(sql)
            .bind("lastNameMatcher", "% S%")
            .query().forList()

        then:

        results.size() == 3
    }

    private String createId() {
        UUID.randomUUID().toString()
    }

}
