package com.mv.project.bill.entities;

import com.mv.project.common.constants.ProjectConstant;
import com.mv.project.common.entities.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "BILL")
@Getter
@Setter
@AttributeOverride(name = "ID", column = @Column(name = "BILL_ID", nullable = false))
public class Bill extends BaseEntity {

	private static final long serialVersionUID = -16987209137533321L;
	@Column(length = 100, nullable = false)
	private String title;
	@Column(precision = 10, scale = 2, nullable = false)
	private BigDecimal amount;
	@Column(length = 1000)
	private String description;
	@Column(length = 1)
	private String isShow = ProjectConstant.Flag.Y;
	@Column(length = 1000)
	private String remark;
	private Date payDate;

	@Override
	public String toString() {
		return "Bill{" + "title='" + title + '\'' + ", amount=" + amount + ", description='" + description + '\''
				+ ", id=" + id + '}';
	}
}
