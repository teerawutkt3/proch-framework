package com.mv.project.ums.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mv.project.common.entities.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "USER_ROLE")
@Getter
@Setter
public class UserRole extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Role role;

    @Override
    public String toString() {
        return "UserRole{" +
                "user=" + user +
                ", role=" + role +
                '}';
    }
}
