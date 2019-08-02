package com.proch.project.ums.entity;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.proch.project.common.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USER", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = " USER_ID",
        nullable = false, columnDefinition = "BIGINT UNSIGNED"))
public class User extends BaseEntity {

    private static final long serialVersionUID = 4970294902409000877L;

    private String username;
    private String password;
    private String token;
    @ManyToMany(mappedBy = "users")
    private Set<Role> roles;

    public User() {

    }

    public User(String username, String password, String token) {
        this.username = username;
        this.password = password;
        this.token = token;
    }

}
