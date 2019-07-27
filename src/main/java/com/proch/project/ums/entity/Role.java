package com.proch.project.ums.entity;

import javax.persistence.*;

import com.proch.project.common.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ROLE")
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "ROLE_ID",
		nullable = false, columnDefinition = "BIGINT UNSIGNED"))
public class Role extends BaseEntity {

	private static final long serialVersionUID = 1465422119343489700L;

	@Column(name = "ROLE_NAME")
	private String roleName;

	@Column(name = "DESCRIPTION")
	private String description;

}