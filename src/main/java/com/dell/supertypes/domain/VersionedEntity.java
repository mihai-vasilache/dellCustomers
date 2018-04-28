package com.dell.supertypes.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class VersionedEntity extends BaseEntity {

	@Column(name = "VERSION", nullable = false, insertable=false, updatable=true)
	@Version
	@Access(AccessType.FIELD)
	private Long version;

	public Long getVersion() {
		return version;
	}
	
}
