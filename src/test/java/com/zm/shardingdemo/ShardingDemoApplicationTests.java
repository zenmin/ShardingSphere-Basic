package com.zm.shardingdemo;

import com.zm.shardingdemo.entity.User;
import com.zm.shardingdemo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest
class ShardingDemoApplicationTests {

    @Autowired
    DataSource dataSource;

    @Autowired
    UserMapper userMapper;

    @Test
    void tables() {
        for (int i = 0; i < 200; i++) {
            userMapper.insert(new User("用户" + i, i % 2 + 10));
        }

    }

    @Test
    void msWrite() {
        for (int i = 0; i < 200; i++) {
            User user = new User("用户" + i, i % 2 + 10);
            userMapper.insert(user);
        }
    }

    @Test
    void msRead() {
        User user = userMapper.selectById(1240558498887585793L);
        System.out.println(user);
    }

}
