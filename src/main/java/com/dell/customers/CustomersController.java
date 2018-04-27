package com.dell.customers;

import com.dell.customers.generated.model.CustomerDto;
import com.dell.customers.generated.server.api.CustomersApi;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CustomersController implements CustomersApi {

    @Override
    public ResponseEntity<List<CustomerDto>> getCustomers(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "email", required = false) String email) {
        return new ResponseEntity<>(new ArrayList<>(), OK);
    }

}
