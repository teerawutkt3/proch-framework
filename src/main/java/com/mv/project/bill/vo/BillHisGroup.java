package com.mv.project.bill.vo;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class BillHisGroup {

  private Integer month;
  private Integer year;
  private String moneyType;
  private BigDecimal sumAmount;
  private Integer countBill;
  // TODO ==> ถ้าใส่  construct จะใช้ bean row mapper ไม่ได้
}
