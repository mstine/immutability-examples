import spock.lang.Specification

class PersonBuilderSpec extends Specification {

    def "build the person"() {

        given:

        def personBuilder = new PersonBuilder()

        when:

        def person = personBuilder.person(firstName: "John", lastName: "Doe") {
                                    address(streetNumber: "123",
                                            street: "Anywhere St.",
                                            city: "Anytown",
                                            state: "USA",
                                            zip: "12345") {
                                                homePhone(areaCode: "123", prefix: "456", lineNumber: "7890")
                                                mobilePhone(areaCode: "234", prefix: "567", lineNumber: "8901")
                                            }
                                    }.build()

        then:

        person.firstName == "John"
        person.address.city == "Anytown"
        person.address.mobilePhone.prefix == "567"

    }
}
