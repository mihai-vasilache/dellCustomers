package com.dell.supertypes.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Getter
@Setter
@MappedSuperclass
public abstract class AuditedEntity extends VersionedEntity {

	@Column(name = "CREATED_ON", nullable = false, unique = false)
	@Access(AccessType.FIELD)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonIgnore
	protected Date createdOn;

	@Column(name = "LAST_MODIFIED_ON", nullable = false, unique = false)
	@Access(AccessType.FIELD)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonIgnore
	protected Date lastModifiedOn;
	
	
	@PrePersist
	private void populateAuditData() {
		Date currentTimestamp = new Date();
		if (entityIsNew()) {
			createdOn = currentTimestamp;
			lastModifiedOn = currentTimestamp;
		}
		lastModifiedOn = currentTimestamp;
	}
	
	@PreUpdate
	private void preUpdate() {
		lastModifiedOn = new Date();
	}

	public boolean entityIsNew() {
		return getVersion() == null;
	}
	
	public boolean entityIsPersisted() {
		return ! entityIsNew();
	}

}
