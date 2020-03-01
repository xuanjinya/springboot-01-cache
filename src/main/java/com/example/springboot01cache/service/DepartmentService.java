package com.example.springboot01cache.service;

import com.example.springboot01cache.bean.Department;
import com.example.springboot01cache.mapper.Departmentmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

/**
 * @author huang.beijin
 * @date 2020/3/1 15:21
 * @description
 */
@CacheConfig(cacheNames = "dept", cacheManager = "deptCacheManager")
@Service
public class DepartmentService {

    @Autowired
    Departmentmapper departmentmapper;

    @Qualifier("deptCacheManager") //指定缓存管理器
    @Autowired
    RedisCacheManager deptCacheManager;


/*    @Cacheable
    public Department getDepartmentById(Integer id) {
        System.out.println("查询部门" + id);
        Department department = departmentmapper.getDepartmentById(id);
        return department;
    }*/

    //使用缓存管理器得到缓存，操作缓存
    public Department getDepartmentById(Integer id) {
        System.out.println("查询部门" + id);
        Department department = departmentmapper.getDepartmentById(id);
        //获取某个缓存
        Cache dept = deptCacheManager.getCache("dept");
        dept.put("dept:1", department);//加入到缓存。前缀是 dept  key是dept:1 值是department
        return department;
    }
}
