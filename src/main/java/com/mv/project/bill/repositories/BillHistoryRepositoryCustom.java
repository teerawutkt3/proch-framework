package com.mv.project.bill.repositories;

import com.mv.project.bill.vo.BillHisGroup;

import java.util.List;

public interface BillHistoryRepositoryCustom {

  List<BillHisGroup> findGroup();
}
