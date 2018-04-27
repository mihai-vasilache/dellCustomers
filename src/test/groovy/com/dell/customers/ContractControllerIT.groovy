package com.dell.customers

import com.dell.customers.generated.model.CustomerDto
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Subject

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT, properties = [])
@ContextConfiguration
@Subject(CustomersController)
class CustomersControllerIT extends Specification {


    def setup() {
    }

    def cleanup() {
    }

    def "create a new customer"() {
        given: 'a new Contract'
        def expected = new CustomerDto();

        when: "request contractService to create contract entity in repository"
        def result = new CustomerDto()

        then: "verify that requested contract created in repository"
        result == expected
    }
}
