package com.proch.project.common.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;

import com.proch.project.common.utils.UserLoginUtils;
import org.apache.commons.lang3.StringUtils;

import com.proch.project.common.constant.ProjectConstant.Flag;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 5140575813889967178L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", nullable = false, columnDefinition = "BIGINT UNSIGNED")
	protected Long id;

	@Column(name = "IS_DELETED", length = 1)
	protected String isDeleted;

	@Column(name = "CREATED_BY", updatable = false)
	protected String createdBy;

	@Column(name = "CREATED_DATE", updatable = false)
	protected LocalDateTime createdDate;

	@Column(name = "UPDATED_BY", nullable = true)
	protected String updatedBy;

	@Column(name = "UPDATED_DATE", nullable = true)
	protected LocalDateTime updatedDate;

	@PrePersist
	public void prePersist() {
		isDeleted = Flag.N;
		
		if (StringUtils.isBlank(createdBy)) {
			createdBy = UserLoginUtils.getCurrentUsername();
		}
		createdDate = LocalDateTime.now();
	}

	@PreUpdate
	public void preUpdate() {
		if (StringUtils.isBlank(updatedBy)) {
			updatedBy = UserLoginUtils.getCurrentUsername();
		}
		updatedDate = LocalDateTime.now();
	}
}
