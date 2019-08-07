package com.proch.project.ums.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proch.project.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ROLE")
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "ROLE_ID",
        nullable = false, columnDefinition = "BIGINT UNSIGNED"))
public class Role extends BaseEntity {

    private static final long serialVersionUID = 1465422119343489700L;

    private String roleName;
    private String description;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable
    @JsonIgnore
//    private Set<User> users;
    private List<User> users;
    public Role() {

    }

    public Role (String roleName, String description) {
        this.roleName = roleName;
        this.description = description;
    }
}