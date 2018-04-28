package com.dell.customers;

import com.dell.customers.generated.model.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer valueOf(CustomerDto contractDto);

    CustomerDto toDto(Customer contract);

}
