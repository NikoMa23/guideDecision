package com.zh.sliver_dev_final;

import com.zh.dao.UserDao;
import com.zh.utils.JwtUtils;
import org.assertj.core.util.Strings;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

@SpringBootTest
class SliverDevFinalApplicationTests {

    @Autowired
    private UserDao userDao;

    @Test
    void test(){
        System.out.println(userDao.getById(1));
    }

    @Test
    void test2(){
        System.out.println(userDao.getByUsername("zh"));
    }
    @Test
    void test3(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(7);

        System.out.println(bCryptPasswordEncoder.encode("198105"));
    }

    @Test
    void testJWT(){
        System.out.println(JwtUtils.parseJwtToken("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwiaWF0IjoxNzA2OTYzMzQxLCJleHAiOjE3MDcwNDk" +
                "3NDEsInJvbGUiOiJ1c2VyIn0.s9pKCjrxhMjCYstnSw80DqBjWfbWUvAxt43iyXt6Mi8").getClass());
    }
    @Test
    void  testDate(){
        Date date = new Date(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis());
        long time = System.currentTimeMillis();
        System.out.println(Long.toString(time));
        System.out.println(Long.parseLong(Long.toString(time)));
        var expirationTime = date.getTime();
        System.out.println(date.toString());
        System.out.println(expirationTime);
        System.out.println(new Date(expirationTime));
    }
    @Test
    void contextLoads() {
    }

}
