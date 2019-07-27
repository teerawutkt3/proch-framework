package com.proch.project.ums.entity;

import javax.persistence.*;

import com.proch.project.common.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ROLE_ASSIGNMENT")
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = " ROLE_ASSIGN_ID",
		nullable = false, columnDefinition = "BIGINT UNSIGNED"))
public class RoleAssignment extends BaseEntity {

	private static final long serialVersionUID = 6439020293151350009L;

	@Column(name = "USER_ID")
	private Long userId;

	@Column(name = "ROLE_ID")
	private Long roleId;

}
