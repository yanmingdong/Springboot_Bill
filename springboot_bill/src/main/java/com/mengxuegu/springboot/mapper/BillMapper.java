package com.mengxuegu.springboot.mapper;

import com.mengxuegu.springboot.entities.Bill;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BillMapper {

    List<Bill> getBills(Bill bill);

    Bill getBillById(Integer bid);

    int addBill(Bill bill);

    int updateBill(Bill bill);

    int deleteBill(Integer bid);

}
