package com.example.springboot01cache.service;

import com.example.springboot01cache.bean.Employee;
import com.example.springboot01cache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author huang.beijin
 * @date 2020/2/29 17:35
 * @description
 */
 @CacheConfig(cacheNames = "emp",cacheManager = "empCacheManager") //缓存的公共配置 指定整个类的缓存的名字
@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;


    //将方法的运行结果进行缓存，以后要相同的数据，直接从缓存中获取，不调用方法 #root.args[0]:第一个参数 , key = "#root.args[0]",condition = "#id>0"
    @Cacheable(cacheNames = "emp")
    //@Cacheable(value = {"emp"},keyGenerator = "myKeyGenerator")  //使用自定义的key
    public Employee getEmp(Integer id) {
        System.out.println("查询" + id + "号员工");
        Employee empById = employeeMapper.getEmpById(id);
        return empById;
    }

    //运行时机，先调用目标方法，再调用缓存方法
    @CachePut(value = "emp")
    public Employee updateEmp(Employee employee) {
        employeeMapper.updateEmp(employee);
        return employee;
    }

    //删除缓存 指定的key  key = "#id"  删除全部 allEntries = true  方法之前清空缓存 beforeInvocation = true
    @CacheEvict(value = "emp",allEntries = true)
    public void deleteEmp(Integer id) {
        System.out.println("删除员工！");
        //employeeMapper.deleteEmpById(id);
    }

    //定义复杂的缓存规则
    @Caching(
            cacheable = {
                    @Cacheable(value = "emp", key = "#lastName")
            },
            put = {
                    @CachePut(value = "emp", key = "#result.id"),
                    @CachePut(value = "emp", key = "#result.email")
            }
    )
    public Employee getEmpByLastName(String name) {
        Employee emp = employeeMapper.getEmpByLastName(name);
        return emp;
    }

}
