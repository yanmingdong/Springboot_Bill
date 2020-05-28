package com.mengxuegu.springboot;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DemoJDBC {

    @Test
    public void testJDBC() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///eesy?serverTimezone=GMT%2B8","root","13325859011YMD");
        String sql="INSERT INTO account VALUES(18,'苏菲',6200);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        boolean execute = preparedStatement.execute();
        System.out.println(execute);
        if (execute){
            System.out.println("执行成功");
        }

    }

}
