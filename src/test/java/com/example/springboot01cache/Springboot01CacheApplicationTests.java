package com.example.springboot01cache;

import com.example.springboot01cache.bean.Employee;
import com.example.springboot01cache.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class Springboot01CacheApplicationTests {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate; //操作字符串

    @Autowired
    RedisTemplate redisTemplate; //k-v都是对象

    @Autowired
    RedisTemplate<Object, Employee> empRedisTemplate;

    @Test
    public void contextLoads() {
        Employee empById = employeeMapper.getEmpById(1);
        System.out.println(empById);
    }

    /*
     * Redis 常见的五大数据类型
     * 测试redis String List Set Hash(散列) ZSet(有序集合)
     * stringRedisTemplate.opsForValue()[String 字符串]
     * stringRedisTemplate.opsForList()[List 列表]
     * */
    @Test
    public void test01() {
        //给Redis 保存数据
//        stringRedisTemplate.opsForValue().append("msg", "Hello");
//        String msg = stringRedisTemplate.opsForValue().get("msg");
        stringRedisTemplate.opsForList().leftPush("myList", "1");
        stringRedisTemplate.opsForList().leftPush("myList", "2");

    }

    @Test
    public void test02() {
        Employee empById = employeeMapper.getEmpById(1);
        //默认如果保存对象，使用jdk序列化机制，序列化的数据保存在redis中
//        redisTemplate.opsForValue().set("emp01", empById);
        //1.将数据以JSON格式保存 (1)自己将对象转换为json  (2) 使用redis的序列化规则
        empRedisTemplate.opsForValue().set("emp01", empById);
    }


}
