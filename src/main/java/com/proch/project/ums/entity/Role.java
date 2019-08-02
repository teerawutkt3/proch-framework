package com.proch.project.ums.entity;

import com.proch.project.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable
    private Set<User> users;

    public Role() {

    }

    public Role (String roleName, String description) {
        this.roleName = roleName;
        this.description = description;
    }
}