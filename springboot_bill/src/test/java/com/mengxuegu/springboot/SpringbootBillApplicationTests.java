package com.mengxuegu.springboot;

import com.mengxuegu.springboot.entities.Bill;
import com.mengxuegu.springboot.entities.Provider;
import com.mengxuegu.springboot.entities.User;
import com.mengxuegu.springboot.mapper.BillMapper;
import com.mengxuegu.springboot.mapper.ProviderMapper;
import com.mengxuegu.springboot.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootBillApplicationTests {

    @Autowired
    ProviderMapper providerMapper;

    @Autowired
    BillMapper billMapper;

    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
        Provider provider = new Provider();
        provider.setProviderName("货");
        List<Provider> providers = providerMapper.getProviders(provider);
        System.out.println(providers);
    }


    @Test
    public void testUpdate() {
        Provider provider = new Provider();
        provider.setPid(8);
        provider.setProviderCode("p_777");
        provider.setProviderName("测试供应商");
        provider.setPeople("老七");
        provider.setAddress("浙江省");
        provider.setDescribe("测试修改案例");
        provider.setFax("020-1234");
        provider.setPhone("235125");
        providerMapper.updateProvider(provider);
    }

    @Test
    public void testAdd() {
        Provider provider = new Provider();
        provider.setProviderCode("p_777");
        provider.setProviderName("测试供应商");
        provider.setPeople("老七");
        provider.setAddress("浙江省");
        provider.setDescribe("测试案例");
        provider.setFax("020-1234");
        provider.setPhone("235125");
        providerMapper.addProvider(provider);
    }

    @Test
    public void testDel() {
        int i = providerMapper.deleteProvider(8);
        System.out.println(i == 1 ? true : false);
    }

    @Test
    public void testGetBills() {
        Bill bill = new Bill();
        bill.setBillName("包");
        bill.setPay(1);
        List<Bill> bills = billMapper.getBills(bill);
        System.out.println(bills);
    }

    @Test
    public void testGetBillById() {
        Bill bill = billMapper.getBillById(1);
        System.out.println(bill);
    }

    @Test
    public void testUpdateBill() {
        Bill bill = new Bill();
        bill.setBid(7);
        bill.setBillCode("B_006");
        bill.setBillName("撒风格");
        bill.setBillCom("水电费");
        bill.setBillNum(3);
        Provider provider = new Provider();
        provider.setPid(4);
        bill.setProvider(provider);
        bill.setPay(5);
        bill.setMoney(23435.0);
        billMapper.updateBill(bill);
    }

    @Test
    public void testAddBill() {
        Bill bill = new Bill();
        bill.setBillCode("B_006");
        bill.setBillName("技术虐");
        bill.setBillCom("s");
        bill.setBillNum(3);
        Provider provider = new Provider();
        provider.setPid(3);
        bill.setProvider(provider);
        bill.setPay(5);
        bill.setMoney(2345.0);
        billMapper.addBill(bill);
    }

    @Test
    public void testDeleteBill() {
        billMapper.deleteBill(7);

    }

    @Test
    public void testFindUser() {
        User user = new User();
        user.setUsername("w");
        List<User> users = userMapper.getUsers(user);
        System.out.println(users);
    }

    @Test
    public void testFindOne() {
        User user = userMapper.getUserById(1);
        System.out.println(user);
    }

    @Test
    public void testAddUser() {
        User user = new User();
        user.setUsername("susu");
        user.setGender(1);
        user.setRealName("苏苏");
        user.setPassword("123456");
        user.setBirthday(new Date());
        user.setUserType(3);
        userMapper.addUser(user);
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setId(6);
        user.setUsername("caiya");
        user.setGender(1);
        user.setRealName("菜牙");
        user.setPassword("123456");
        user.setBirthday(new Date());
        user.setUserType(3);
        userMapper.updateUser(user);
    }

    @Test
    public void testDeleteUser() {
        userMapper.deleteUser(5);
    }

}
