package com.mv.project.bill.entities;


import com.mv.project.common.entities.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "BILL", uniqueConstraints = {@UniqueConstraint(columnNames = {"TITLE"})})
@Getter
@Setter
@AttributeOverride(name = "ID", column = @Column(name = "BILL_ID", nullable = false, columnDefinition = "BIGINT UNSIGNED"))
public class Bill extends BaseEntity {
        @Column(length = 100, nullable = false)
        private String title;
        @Column(precision=10, scale=2, nullable = false)
        private BigDecimal amount;
        @Column(length = 1000)
        private String description;

    @Override
    public String toString() {
        return "Bill{" +
                "title='" + title + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", id=" + id +
                '}';
    }
}
