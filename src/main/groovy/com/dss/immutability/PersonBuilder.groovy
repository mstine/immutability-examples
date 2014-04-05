package com.dss.immutability

class PersonBuilder extends BuilderSupport {

    def expandos

    PersonBuilder() {
        expandos = [person: new Expando(),
                    address: new Expando(),
                    homePhone: new Expando(),
                    mobilePhone: new Expando()]

        expandos.person.build = {
            new Person(expandos.person.firstName,
                    expandos.person.lastName,
                    new Address(expandos.address.streetNumber,
                            expandos.address.street,
                            expandos.address.city,
                            expandos.address.state,
                            expandos.address.zip,
                            buildPhoneNumber(expandos.homePhone),
                            buildPhoneNumber(expandos.mobilePhone)))
        }
    }

    private buildPhoneNumber(phoneNumberExpando) {
        new PhoneNumber(phoneNumberExpando.areaCode, phoneNumberExpando.prefix, phoneNumberExpando.lineNumber)
    }

    def createNode(Object name, Map attributes) {
        attributes.each { key, value ->
            expandos[name]."${key}" = value
        }
        expandos[name]
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
