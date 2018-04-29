package com.dell.customers;

import com.dell.customers.generated.model.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CustomerMapper {

    Customer valueOf(CustomerDto contractDto);

    CustomerDto toDto(Customer contract);

    Customer update(Customer source, @MappingTarget Customer destination);

}
