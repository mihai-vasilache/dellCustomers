package com.dell.customers;

import com.dell.InsertWithIdException;
import com.dell.supertypes.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomersService extends BaseService {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final CustomersRepository customersRepository;
    private final CustomerMapper customerMapper;

    public List<Customer> findByNameOrEmail(String name, String email) {
        return customersRepository.findByNameOrEmail(name, email);
    }

    public Customer addOrUpdate(Customer customer) {
        if (customer.hasAssignedId()) {
            LOG.debug("Customer has assigned ID");
            if (!customersRepository.existsById(customer.getId())) {
                throw new InsertWithIdException("Customer with id " + customer.getId() + " does not exist.");
            }
        }
        checkArgument(EmailValidator.getInstance().isValid(customer.getEmail()), "Invalid email: %s", customer.getEmail());

        Optional<Customer> existingCustomer = customersRepository.findByEmailIgnoreCase(customer.getEmail());
        if(existingCustomer.isPresent()) {
            LOG.debug("updated by email");
            Customer persisted = existingCustomer.get();
            customerMapper.update(customer, existingCustomer.get());
            return persisted;
        }
        return customersRepository.save(customer);
    }


}
