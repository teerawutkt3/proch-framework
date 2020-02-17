package com.mv.project.ums.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.mv.project.common.entities.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USER", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "USER_ID", nullable = false, columnDefinition = "BIGINT UNSIGNED"))
public class User extends BaseEntity {

	private static final long serialVersionUID = 146134598360995282L;
	private String username;
	private String password;
	private String token;
	
	@Override
	public String toString() {
		return "User{" +
				"username='" + username + '\'' +
				'}';
	}
}
