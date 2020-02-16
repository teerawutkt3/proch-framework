package com.mv.project.ums.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mv.project.common.entities.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ROLE")
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "ROLE_ID", nullable = false, columnDefinition = "BIGINT UNSIGNED"))
public class Role extends BaseEntity {

    private static final long serialVersionUID = 1465422119343489700L;
    private String roleCode;
    private String roleName;
    private String description;
//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable
//    @JsonIgnore
//    private List<User> users = new ArrayList<User>();

    public Role() {}
    public Role (String roleName, String description) {
        this.roleName = roleName;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleCode='" + roleCode + '\'' +
                ", roleName='" + roleName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}