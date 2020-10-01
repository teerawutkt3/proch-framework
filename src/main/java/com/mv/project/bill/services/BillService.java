package com.mv.project.bill.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mv.project.bill.entities.Bill;
import com.mv.project.bill.entities.BillHistories;
import com.mv.project.bill.repositories.BillRepository;
import com.mv.project.common.constants.ProjectConstant;
import com.mv.project.common.utils.UserLoginUtils;

@Transactional
@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private BillHistoryService billHistoryService;

    public List<Bill> findAll(){
        Iterable<Bill> result = billRepository.findByCreatedByOrderByCreatedDateDesc(UserLoginUtils.getCurrentUsername());
        List<Bill> billList = new ArrayList<>();
        result.forEach(billList::add);

        return billList;
    }

    public List<Bill> findAllBillActive(){
        Iterable<Bill> result = billRepository.findByIsShowAndCreatedByOrderByCreatedDateDesc(ProjectConstant.Flag.Y, UserLoginUtils.getCurrentUsername());
        List<Bill> billList = new ArrayList<>();
        result.forEach(billList::add);

        //==> filter with history
        List<BillHistories> hisList = billHistoryService.findAll();
        for (BillHistories billHis: hisList) {
            billList = billList.stream().filter(e -> !e.getTitle().equals(billHis.getTitle())).collect(Collectors.toList());
        }
        return billList;
    }

    public Bill findById(long id){
        return billRepository.findById(id).get();
    }

    @Transactional(rollbackOn = Exception.class)
    public void delete(long id){
        billRepository.deleteById(id);
    }

    @Transactional(rollbackOn = Exception.class)
    public void setBillIsShowFalse(Bill bill){
        bill.setIsShow(ProjectConstant.Flag.N);
        billRepository.save(bill);
    }

    @Transactional(rollbackOn = Exception.class)
    public void save(Bill bill){
        billRepository.save(bill);
    }

    @Transactional(rollbackOn = Exception.class)
    public void activeIsShow(List<Long> ids){
        Bill bill = null;
        List<Bill> billList = new ArrayList<>();
        List<Bill> bills =  this.findAll();

        //==> Set N Flag All
        for (Bill b: bills) {
            b.setIsShow(ProjectConstant.Flag.N);
        }
        billRepository.saveAll(bills);

        //==> Set Y Falg
        for (Long id: ids) {
            bill = this.findById(id);
            bill.setIsShow(ProjectConstant.Flag.Y);
            billList.add(bill);
        }
        billRepository.saveAll(billList);
    }
}
