package com.mv.project.bill.controllers;

import com.mv.project.bill.entities.Bill;
import com.mv.project.bill.entities.BillHistories;
import com.mv.project.bill.services.BillHistoryService;
import com.mv.project.common.beans.ResponseData;
import com.mv.project.common.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bill-his")
public class BillHistoryController {

    @Autowired
    private BillHistoryService billHistoryService;

    @GetMapping("/")
    public ResponseData<List<BillHistories>> findAll(){
       ResponseData<List<BillHistories>> responseData = new ResponseData<>();
       try {
           responseData.setData(billHistoryService.findAll());
           MessageUtil.setMessageSuccess(responseData);
       }catch (Exception e){
           e.printStackTrace();
           MessageUtil.setMessageFail(responseData);
       }
       return responseData;
    }

    @PostMapping("/pay")
    public ResponseData<?> pay(@RequestBody Bill bill){
        ResponseData<?> responseData = new ResponseData<>();
        try {
            billHistoryService.pay(bill);
            MessageUtil.setMessageSuccess(responseData);
        }catch (Exception e){
            e.printStackTrace();
            MessageUtil.setMessageFail(responseData);
        }
        return responseData;
    }
}
