package com.mv.project.bill.repositories;

import com.mv.project.bill.vo.BillHisGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class BillHistoryRepositoryImpl implements BillHistoryRepositoryCustom {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public List<BillHisGroup> findGroup() {
    StringBuilder sql = new StringBuilder();
    sql.append(" select month, year, money_type, sum(amount) sum_amount, count(title) count_bill from bill_history");
    sql.append(" GROUP BY money_type, month, year");
    sql.append(" order by month, year, money_type asc");

    List<Object> params = new ArrayList<>();
    List<BillHisGroup> rs = jdbcTemplate.query(sql.toString(), params.toArray(), BeanPropertyRowMapper.newInstance(BillHisGroup.class));

    return rs;
  }
}
