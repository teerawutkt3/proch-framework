package com.proch.project.ums.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proch.project.common.entities.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ROLE")
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "ROLE_ID", nullable = false, columnDefinition = "BIGINT UNSIGNED"))
public class Role extends BaseEntity {

    private static final long serialVersionUID = 1465422119343489700L;
    private String roleName;
    private String description;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable
    @JsonIgnore
    private List<User> users = new ArrayList<User>();

    public Role() {}
    public Role (String roleName, String description) {
        this.roleName = roleName;
        this.description = description;
    }
}