package com.dell.customers;

import com.dell.customers.generated.model.CustomerDto;
import com.dell.customers.generated.server.api.CustomersApi;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CustomersController implements CustomersApi {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final CustomersService customersService;
    private final CustomerMapper customerMapper;

    @Override
    public ResponseEntity<List<CustomerDto>> getCustomers(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "email", required = false) String email) {
        if (name == null) {
            LOG.debug("Null name parameter");
            name = "";
        }
        if (email == null) {
            LOG.debug("Null email parameter");
            email = "";
        }
        return new ResponseEntity<>(customersService.findByNameOrEmail(name, email).stream().map(customerMapper::toDto).collect(Collectors.toList()), OK);
    }

    public ResponseEntity<CustomerDto> addCustomer(@Valid @RequestBody CustomerDto customer) {
        Customer created = customersService.addOrUpdate(customerMapper.valueOf(customer));
        return new ResponseEntity<>(customerMapper.toDto(created), CREATED);
    }

}
