package com.proch.project.ums.entity;

import javax.persistence.*;

import com.proch.project.common.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USER")
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = " USER_ID",
		nullable = false, columnDefinition = "BIGINT UNSIGNED"))
public class User extends BaseEntity {

	private static final long serialVersionUID = 4970294902409000877L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private Long id;
	private String username;
	private String password;
	private String token;

}
