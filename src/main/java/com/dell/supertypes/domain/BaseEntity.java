package com.dell.supertypes.domain;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@Getter
@Setter
@MappedSuperclass
@EqualsAndHashCode(of={"id"})
@ToString
public abstract class BaseEntity implements Serializable {
	
	@Transient
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private String genratedId = UUID.randomUUID().toString().replaceAll("-", "");
	
	@Id
	@Column(name = "id", columnDefinition="CHAR(32)", length=32, nullable=false)
	private String id = genratedId;
	
	public boolean hasAssignedId() {
		return ! genratedId.equals(id);
	}

}
