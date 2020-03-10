package com.mv.project.bill.repositories;

import com.mv.project.bill.entities.Bill;
import com.mv.project.common.repositories.CommonJpaCrudRepository;

import java.util.List;

public interface BillRepository extends CommonJpaCrudRepository<Bill, Long> {
    List<Bill> findByCreatedByOrderByCreatedDateDesc(String createdBy);
    List<Bill> findByIsShowAndCreatedByOrderByCreatedDateDesc(String isShow, String createdBy);
}
