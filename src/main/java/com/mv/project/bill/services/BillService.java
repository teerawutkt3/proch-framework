package com.mv.project.bill.services;

import com.mv.project.bill.entities.Bill;
import com.mv.project.bill.repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    public List<Bill> findAll(){
        Iterable<Bill> result = billRepository.findAll();
        List<Bill> billList = new ArrayList<>();
        result.forEach(billList::add);
        return billList;
    }

    public Bill findById(long id){
        return billRepository.findById(id).get();
    }

    public void delete(long id){
        billRepository.deleteById(id);
    }
    public void save(Bill bill){
        billRepository.save(bill);
    }
}
