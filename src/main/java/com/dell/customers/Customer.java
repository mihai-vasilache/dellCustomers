package com.dell.customers;

import com.dell.supertypes.domain.AuditedEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CUSTOMERS")
public class Customer  extends AuditedEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

}
