package com.dell.customers

import org.mapstruct.factory.Mappers
import spock.lang.Specification

import javax.persistence.EntityNotFoundException

import static com.dell.customers.CustomersTestData.customer
import static com.dell.customers.CustomersTestData.customer
import static com.dell.customers.CustomersTestData.testUUID

class CustomerServiceTest extends Specification {

    CustomersService customersService
    def customerRepositoryMock = Mock(CustomersRepository)

    def setup() {
        customersService = new CustomersService(customerRepositoryMock)
    }


    def "get customers"() {
        given: "a search criteria"
        def name = ""
        def email = ""

        when: "trying to retrieve all customers"
        def result = customersService.findByNameOrEmail(name, email)

        then: "verify that the repository is invoked"
        1 * customerRepositoryMock.findByNameOrEmail("", "") >> [customer]

        then:
        result.size() == 1
        result[0] == customer

        where:
        customer = customer([id: testUUID])
    }

}
