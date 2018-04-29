package com.dell.customers;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomersRepository extends JpaRepository<Customer, String> {

    @Query("Select c from Customer c where " +
            "upper(c.name) like upper(CONCAT('%',:name, '%')) " +
            "and upper(c.email) like upper(CONCAT('%',:email, '%')) " +
            "order by c.name")
    List<Customer> findByNameOrEmail(String name, String email);

    Customer findByEmailIgnoreCase(String email);
}
