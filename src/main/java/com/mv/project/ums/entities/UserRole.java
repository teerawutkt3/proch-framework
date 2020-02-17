package com.mv.project.ums.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mv.project.common.entities.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USER_ROLE")
@Getter
@Setter
public class UserRole extends BaseEntity {

	private static final long serialVersionUID = 3239830667174582965L;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private User user;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Role role;

	@Override
	public String toString() {
		return "UserRole{" + "user=" + user + ", role=" + role + '}';
	}
}
