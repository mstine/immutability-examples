package com.dss.immutability

class PersonBuilder extends BuilderSupport {

    def personExpando
    def addressExpando
    def homePhoneExpando
    def mobilePhoneExpando

    PersonBuilder() {
        addressExpando = new Expando()
        personExpando = new Expando()
        homePhoneExpando = new Expando()
        mobilePhoneExpando = new Expando()

        personExpando.build = {
            new Person(personExpando.firstName,
                    personExpando.lastName,
                    new Address(addressExpando.streetNumber,
                            addressExpando.street,
                            addressExpando.city,
                            addressExpando.state,
                            addressExpando.zip,
                            new PhoneNumber(homePhoneExpando.areaCode, homePhoneExpando.prefix, homePhoneExpando.lineNumber),
                            new PhoneNumber(mobilePhoneExpando.areaCode, mobilePhoneExpando.prefix, mobilePhoneExpando.lineNumber)))
        }
    }

    def createNode(Object name, Map attributes) {
        if ("homePhone" == name) {
            fillExpandoProperties(homePhoneExpando, attributes)
        } else if ("mobilePhone" == name) {
            fillExpandoProperties(mobilePhoneExpando, attributes)
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
