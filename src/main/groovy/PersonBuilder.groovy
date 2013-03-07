import com.dss.immutability.Address
import com.dss.immutability.Person
import com.dss.immutability.PhoneNumber

class PersonBuilder extends BuilderSupport {

    // Root and inner nodes
    def personExpando
    def addressExpando

    // Leaf nodes
    def homePhone
    def mobilePhone

    PersonBuilder() {
        addressExpando = new Expando()
        personExpando = new Expando()

        personExpando.build = {
            new Person(personExpando.firstName,
                personExpando.lastName,
                    new Address(addressExpando.streetNumber,
                        addressExpando.street,
                        addressExpando.city,
                        addressExpando.state,
                        addressExpando.zip,
                        homePhone,
                        mobilePhone))
        }
    }

    def createNode(Object name, Map attributes) {
        if ("homePhone" == name) {
            homePhone = buildPhoneNumber(attributes)
        } else if ("mobilePhone" == name) {
            mobilePhone = buildPhoneNumber(attributes)
        } else if ("address" == name) {
            fillExpandoProperties(addressExpando, attributes)
        } else if ("person" == name) {
            fillExpandoProperties(personExpando, attributes)
        } else {
            throw new UnsupportedOperationException("${name} is not a supported builder node type!")
        }
    }

    private def fillExpandoProperties(Expando expando, Map attributes) {
        attributes.each { key, value ->
            expando."${key}" = value
        }
        expando
    }

    private def buildPhoneNumber(Map attributes) {
        new PhoneNumber(attributes.areaCode, attributes.prefix, attributes.lineNumber)
    }

    void setParent(Object parent, Object child) {
        // Do nothing...must override.
    }

    def createNode(Object name) {
        // Do nothing...must override
    }

    def createNode(Object name, Map attributes, Object value) {
        // Do nothing...must override.
    }

    def createNode(Object name, Object value) {
        // Do nothing...must override.
    }

}
