package com.mv.project.ums.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.mv.project.common.entities.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ROLE")
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "ROLE_ID", nullable = false))
public class Role extends BaseEntity {

	private static final long serialVersionUID = 1465422119343489700L;
	private String roleCode;
	private String roleName;
	private String description;

	@Override
	public String toString() {
		return "Role{" + "roleCode='" + roleCode + '\'' + ", roleName='" + roleName + '\'' + ", description='"
				+ description + '\'' + '}';
	}
}