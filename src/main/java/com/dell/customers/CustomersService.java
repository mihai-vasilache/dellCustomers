package com.dell.customers;

import com.dell.supertypes.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomersService extends BaseService {

    private final CustomersRepository customersRepository;

    public List<Customer> findByNameOrEmail(String name, String email) {
        return customersRepository.findByNameOrEmail(name, email);
    }
}
