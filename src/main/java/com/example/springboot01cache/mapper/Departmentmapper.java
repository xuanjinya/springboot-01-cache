package com.example.springboot01cache.mapper;

import com.example.springboot01cache.bean.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author huang.beijin
 * @date 2020/2/29 16:45
 * @description
 */

@Mapper
public interface Departmentmapper {

    @Select("SELECT * FROM department WHERE id = #{id}")
    Department getDepartmentById(Integer id);
}
