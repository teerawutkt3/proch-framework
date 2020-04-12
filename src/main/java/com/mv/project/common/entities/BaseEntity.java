package com.mv.project.common.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import org.apache.commons.lang3.StringUtils;

import com.mv.project.common.constants.ProjectConstant.Flag;
import com.mv.project.common.utils.DateUtils;
import com.mv.project.common.utils.UserLoginUtils;

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
	@Column(name = "id", nullable = false)
	protected Long id;

	@Column(name = "IS_DELETED", length = 1)
	protected String isDeleted = "N";

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
		updatedDate = new Date();
	}

	@PreUpdate
	public void preUpdate() {
		if (StringUtils.isBlank(updatedBy)) {
			updatedBy = UserLoginUtils.getCurrentUsername();
		}
		updatedDate = new Date();
	}

	public String getCreatedDateStr(){
		return DateUtils.formatDate(createdDate, DateUtils.DD_MM_YYYY_HHMMSS);
	}
	public String getUpdatedDateStr(){
		return DateUtils.formatDate(updatedDate, DateUtils.DD_MM_YYYY_HHMMSS);
	}
	public String getIdStr() {
		return id.toString();
	}
}
