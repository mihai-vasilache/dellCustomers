package com.dell.customers

import com.dell.customers.generated.model.CustomerDto

import static java.util.UUID.randomUUID

class CustomersTestData {

    static def testUUID = randomUUID().toString().replaceAll("-", "")

    static customer(Map params = [:]) {
        customerPrototype(params) as Customer
    }

    static customerDto(Map params = [:]) {
        customerPrototype(params) as CustomerDto
    }

    private static customerPrototype(Map params = [:]) {
        [id   : params.id,
         name : 'Customer Name',
         email: "test@test.com"] << params
    }
}
