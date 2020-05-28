package com.mengxuegu.springboot.controller;

import com.mengxuegu.springboot.entities.Bill;
import com.mengxuegu.springboot.entities.Provider;
import com.mengxuegu.springboot.mapper.BillMapper;
import com.mengxuegu.springboot.mapper.ProviderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class BillController {

    @Autowired
    private ProviderMapper providerMapper;

    @Autowired
    BillMapper billMapper;

    @GetMapping("/bills")
    public String list(Map<String, Object> map, Bill bill) {
        List<Bill> bills = billMapper.getBills(bill);
        List<Provider> providers = providerMapper.getAllProviders();

        map.put("bills", bills);
        map.put("providers", providers);
        //用于搜索处回写数据
        map.put("billName", bill.getBillName());
        map.put("pay", bill.getPay());
        if (bill.getProvider() == null || bill.getProvider().getPid() == null) {
            map.put("pid", 0);
        } else {
            map.put("pid", bill.getProvider().getPid());
        }
        return "bill/list";
    }

    @GetMapping("/bill/{bid}")
    public String view(@PathVariable("bid") Integer bid, Map<String, Object> map,
                       @RequestParam(value = "type", defaultValue = "view") String type) {
        Bill bill = billMapper.getBillById(bid);

        //修改页面中,获取下拉列表的所有供应商并且展示相应供应商
        if( "update".equals(type)) {
            List<Provider> providers = providerMapper.getAllProviders();
//            map.put("pid", bill.getProvider().getPid());
            map.put("providers", providers);
        }


        map.put("bill", bill);
        //如果type=null,进入view页面,否则进入update
        return "bill/" + type;
    }

    @PutMapping("/bill")
    public String update(Bill bill) {
        System.out.println("后台执行了");
        billMapper.updateBill(bill);
        return "redirect:/bills";
    }

    @GetMapping("/bill")
    public String toAddPage(Map<String, Object> map) {
        List<Provider> providers = providerMapper.getAllProviders();
        map.put("providers", providers);
        return "bill/add";
    }

    @PostMapping("/bill")
    public String add(Bill bill) {
        billMapper.addBill(bill);
        return "redirect:/bills";
    }

    @DeleteMapping("/bill/{bid}")
    public String delete(@PathVariable("bid") Integer bid) {
        billMapper.deleteBill(bid);
        return "redirect:/bills";
    }


}
