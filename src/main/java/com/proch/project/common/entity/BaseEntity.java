package com.proch.project.common.entity;

import com.proch.project.common.constant.ProjectConstant.Flag;
import com.proch.project.common.utils.DateUtils;
import com.proch.project.common.utils.UserLoginUtils;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

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
	protected Date createdDate;

	@Column(name = "UPDATED_BY", nullable = true)
	protected String updatedBy;

	@Column(name = "UPDATED_DATE", nullable = true)
	protected Date updatedDate;

	@PrePersist
	public void prePersist() {
		isDeleted = Flag.N;
		
		if (StringUtils.isBlank(createdBy)) {
			createdBy = UserLoginUtils.getCurrentUsername();
		}
		createdDate = new Date();
	}

	@PreUpdate
	public void preUpdate() {
		if (StringUtils.isBlank(updatedBy)) {
			updatedBy = UserLoginUtils.getCurrentUsername();
		}
		updatedDate = new Date();
	}

	public String getCreatedDateStr(){
		return DateUtils.formatDateToString(createdDate, DateUtils.DD_MM_YYYY);
	}
	public String getUpdatedDateStr(){
		return DateUtils.formatDateToString(updatedDate, DateUtils.DD_MM_YYYY);
	}
}
