package com.mv.project.bill.services;

import com.mv.project.bill.constants.BillConstants;
import com.mv.project.bill.entities.Bill;
import com.mv.project.bill.entities.BillHistories;
import com.mv.project.bill.repositories.BillHistoryRepository;
import com.mv.project.common.utils.DateUtils;
import com.mv.project.common.utils.UserLoginUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BillHistoryService {

    @Autowired
    private BillHistoryRepository billHistoryRepository;

    public List<BillHistories> findAll() {
        String yyyyMM = DateUtils.formatDate(new Date(), DateUtils.YYYYMM);
        Date start = DateUtils.parseDate(yyyyMM + "01 00:00:00", DateUtils.YYYYMMDD_HH_MM_SS);

        String lastDayOfMonth = DateUtils.lastOfMonth(new Date());
        Date end = DateUtils.parseDate(yyyyMM + lastDayOfMonth + " 00:00:00", DateUtils.YYYYMMDD_HH_MM_SS);

        String createdBy = UserLoginUtils.getCurrentUsername();
        return billHistoryRepository.findByCreatedByAndPayDateBetweenOrderByCreatedDateDesc(createdBy, start, end);
    }

    public void pay(Bill bill){
        BillHistories his = new BillHistories();
        his.setTitle(bill.getTitle());
        his.setAmount(bill.getAmount());
        his.setDescription(bill.getDescription());
        his.setPayDate(new Date());
        his.setRemark(bill.getRemark());
        his.setIsPay(BillConstants.IsPay.YES);
        billHistoryRepository.save(his);
    }
}
