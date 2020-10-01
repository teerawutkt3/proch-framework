package com.mv.project.bill.entities;


import com.mv.project.common.constants.ProjectConstant;
import com.mv.project.common.entities.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "BILL_HISTORY")
@Getter
@Setter
@AttributeOverride(name = "ID", column = @Column(name = "BILL_HISTORY_ID", nullable = false))
public class BillHistories extends BaseEntity {
    
	private static final long serialVersionUID = -3524200643762926184L;
	@Column(length = 100, nullable = false)
    private String title;
    @Column(precision=10, scale=2, nullable = false)
    private BigDecimal amount;
    @Column(length = 1000)
    private String description;
    @Column(length = 1000)
    private String remark;
    private Date payDate;
    @Column(length = 1)
    private String isPay = ProjectConstant.Flag.Y;
    @Column(length = 10)
    private String moneyType;

    @Override
    public String toString() {
        return "BillHistories{" +
                "title='" + title + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", remark='" + remark + '\'' +
                ", payDate=" + payDate +
                ", isPay='" + isPay + '\'' +
                ", id=" + id +
                ", isDeleted='" + isDeleted + '\'' +
                '}';
    }
}
