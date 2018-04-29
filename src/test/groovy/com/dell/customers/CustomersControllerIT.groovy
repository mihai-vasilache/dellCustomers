package com.dell.customers

import com.dell.BaseIT
import com.dell.FeignApiTestClientGenerator
import com.dell.customers.generated.client.api.CustomersApi
import feign.FeignException
import org.flywaydb.test.annotation.FlywayTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.test.context.TestExecutionListeners
import spock.lang.Subject

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

import static com.dell.customers.CustomersTestData.customerDto
import static com.dell.customers.CustomersTestData.testUUID
import static org.springframework.http.HttpStatus.NOT_FOUND

@FlywayTest(locationsForMigrate = ["classpath:db/testdata/customers"], overrideLocations = false)
@TestExecutionListeners([
    org.springframework.boot.test.autoconfigure.SpringBootDependencyInjectionTestExecutionListener,
    org.springframework.test.context.transaction.TransactionalTestExecutionListener,
    org.flywaydb.test.junit.FlywayTestExecutionListener,])
@Subject(CustomersController)
class CustomersControllerIT extends BaseIT {

    @Value('http://localhost:${local.server.port}/api/v1/')
    String baseUrl

    @Autowired
    FeignApiTestClientGenerator feignApiTestClientGenerator
    CustomersApi customerApi

    @PersistenceContext
    protected EntityManager em;

    def setup() {
        customerApi = feignApiTestClientGenerator.generate(CustomersApi, baseUrl)
    }

    def cleanup() {
    }

    def "get all customers"() {
        when: "request all customers"
        def result = customerApi.getCustomers(null, null)

        then: "verify that requested customer match expected size"
        result.size() == 5

        then: "verify that return order match"
        result[0].name == "Anselma Tommis"
        result[1].name == "Cele Layson"
        result[2].name == "Ezequiel Pagel"
        result[3].name == "Gabbey Coady"
        result[4].name == "Joye Lively"
    }

    def "get specific customer"() {
        given: 'a search criteria'
        def name = "Joye"
        def email = "@cam.ac.uk"
        def expected = customerDto([name: "Joye Lively", email: "jlively0@cam.ac.uk"])

        when: "request customers"
        def result = customerApi.getCustomers(name, email)

        then: "verify that requested customer match"
        result.size() == 1
        result[0].name == expected.name
        result[0].email == expected.email
    }

    def "get multiple customers"() {
        given: 'a search criteria'
        def name = "c"
        def email = null

        when: "request customers"
        def result = customerApi.getCustomers(name, email)

        then: "verify that requested customer match"
        result.size() == 2
        result[0].name == "Cele Layson"
        result[1].name == "Gabbey Coady"
    }

    def "add customer with id"() {
        given: 'a customer'
        def customer = customerDto([id: testUUID]);

        when: "trying to add the customer"
        def result = customerApi.addCustomer(customer)

        then: "an error code is returned"
        def exception = thrown(FeignException)
        exception.status() == NOT_FOUND.value()
        exception.getMessage().contains("Customer with id " + testUUID + " does not exist.")
    }

    def "add customer"() {
        given: 'a customer'
        def customer = customerDto();

        when: "add the customer"
        def result = customerApi.addCustomer(customer)

        then: "the customer is added to the database and returned"
        def persistedCustomer = em.find(Customer, result.id)
        def customersCount = em.createQuery("SELECT count(*) FROM Customer").getSingleResult()
        result.id == persistedCustomer.id
        result.name == customer.name
        result.name == persistedCustomer.name
        result.email == customer.email
        result.email == persistedCustomer.email
        customersCount == 6
    }

    def "update customer using add method"() {
        given: 'a customer'
        def customer = customerDto(email: "");

        when: "add the customer"
        def result = customerApi.addCustomer(customer)

        then: "the customer is added to the database and returned"
        def persistedCustomer = em.find(Customer, result.id)
        def customersCount = em.createQuery("SELECT count(*) FROM Customer").getSingleResult()
        result.id == persistedCustomer.id
        result.name == customer.name
        result.name == persistedCustomer.name
        result.email == customer.email
        result.email == persistedCustomer.email
        customersCount == 6
    }
}
