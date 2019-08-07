package com.proch.project.ums.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.proch.project.common.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USER", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = " USER_ID", nullable = false, columnDefinition = "BIGINT UNSIGNED"))
public class User extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 146134598360995282L;
	private String username;
	private String password;
	private String token;
	@ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
//	private Set<Role> roles;
	private List<Role> roles;

	public User() {

	}

	public User(String username, String password, String token) {
		this.username = username;
		this.password = password;
		this.token = token;
	}

}
