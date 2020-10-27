package com.mv.project.bill.repositories;

import com.mv.project.bill.entities.BillHistories;
import com.mv.project.common.repositories.CommonJpaCrudRepository;

import java.util.Date;
import java.util.List;

public interface BillHistoryRepository extends CommonJpaCrudRepository<BillHistories, Long>, BillHistoryRepositoryCustom {

    List<BillHistories> findByCreatedByAndPayDateBetweenOrderByCreatedDateDesc(String createdBy, Date start, Date end);
}
