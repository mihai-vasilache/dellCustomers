package com.dell.customers

import com.dell.InsertWithIdException
import org.mapstruct.factory.Mappers
import spock.lang.Specification

import static com.dell.customers.CustomersTestData.customer
import static com.dell.customers.CustomersTestData.testUUID

class CustomerServiceTest extends Specification {

    CustomersService customersService
    def customerRepositoryMock = Mock(CustomersRepository)

    def setup() {
        customersService = new CustomersService(customerRepositoryMock, Mappers.getMapper(CustomerMapper))
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

    def "add with id throws exception"() {
        given: "a Customer"
        def customer = customer(id: testUUID)

        when: "add the customer"
        customersService.addOrUpdate(customer)

        then: "verify that the repository is invoked and exception is thrown"
        thrown(InsertWithIdException)
        1 * customerRepositoryMock.existsById(testUUID) >> false

    }

    def "add with invalid email throws exception"() {
        given: "a Customer"
        def customer = new Customer("test", "invalid_email");

        when: "add the customer"
        customersService.addOrUpdate(customer)

        then: "verify that the repository is invoked"
        thrown(IllegalArgumentException)
    }

    def "update customer by email"() {
        given: "a Customer"
        def customer = new Customer("test", "test@email.com");

        when: "add the customer"
        def result = customersService.addOrUpdate(customer)

        then: "verify that the repository is invoked"
        1 * customerRepositoryMock.findByEmailIgnoreCase(customer.email) >> Optional.of(new Customer("other name", customer.email))

        then:
        result.name == customer.name
    }

    def "add customer"() {
        given: "a Customer"
        def customer = new Customer("test", "test@email.com");

        when: "add the customer"
        def result = customersService.addOrUpdate(customer)

        then: "verify that the repository is invoked"
        1 * customerRepositoryMock.findByEmailIgnoreCase(customer.email) >> Optional.empty()
        1 * customerRepositoryMock.save(customer) >> customer

        then:
        result.name == customer.name
    }

}
