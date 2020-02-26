package com.mv.project.bill.controllers;

import com.mv.project.bill.entities.Bill;
import com.mv.project.bill.services.BillService;
import com.mv.project.common.beans.ResponseData;
import com.mv.project.common.constants.ProjectConstant;
import com.mv.project.common.utils.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bill")
public class BillController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BillService billService;

    @GetMapping("/")
    public ResponseData<List<Bill>> findAll() {
        ResponseData<List<Bill>> responseData = new ResponseData<>();
        try {
            responseData.setData(billService.findAll());
            MessageUtil.setMessageSuccess(responseData);
            logger.info("Method findAll success");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("Method findAll fail");
            MessageUtil.setMessageFail(responseData);
        }
        return responseData;
    }

    @PostMapping("/")
    public ResponseData<Bill> save(@RequestBody Bill bill) {
        ResponseData<Bill> responseData = new ResponseData<>();
        try {
            logger.info("Method save bill ==> {}", bill.toString());
            billService.save(bill);
            MessageUtil.setMessageSuccess(responseData, ProjectConstant.ResponseMessage.Save.SUCCESS);
        } catch (Exception e) {
            logger.error("Method save error params => {}" , bill.toString());
            e.printStackTrace();
            MessageUtil.setMessageFail(responseData, e.getMessage());
        }
        return responseData;
    }

    @PutMapping("/{id}")
    public ResponseData<Bill> update(@PathVariable long id, @RequestBody Bill bill) {
        ResponseData<Bill> responseData = new ResponseData<>();
        try {
            logger.info("Method update id: {}, bill ==> {}", id, bill.toString());
            billService.save(bill);
            MessageUtil.setMessageSuccess(responseData, ProjectConstant.ResponseMessage.Save.SUCCESS);
        } catch (Exception e) {
            logger.error("Method update id: {} error params => {}", id, bill.toString());
            e.printStackTrace();
            MessageUtil.setMessageFail(responseData, e.getMessage());
        }
        return responseData;
    }

    @DeleteMapping("/{id}")
    public ResponseData<Bill> delete(@PathVariable long id){
        ResponseData<Bill> responseData = new ResponseData<>();
        try {
            logger.info("Method update id: {}", id);
            Bill bill = billService.findById(id);
            billService.delete(id);
            responseData.setData(bill);
            MessageUtil.setMessageSuccess(responseData, ProjectConstant.ResponseMessage.Delete.SUCCESS);
        } catch (Exception e) {
            logger.error("Method update id: {} error!", id);
            e.printStackTrace();
            MessageUtil.setMessageFail(responseData, e.getMessage());
        }
        return responseData;
    }

}
