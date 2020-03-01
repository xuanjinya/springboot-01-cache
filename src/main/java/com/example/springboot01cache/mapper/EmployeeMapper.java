package com.example.springboot01cache.mapper;

import com.example.springboot01cache.bean.Employee;
import org.apache.ibatis.annotations.*;

/**
 * @author huang.beijin
 * @date 2020/2/29 16:47
 * @description
 */

@Mapper
public interface EmployeeMapper {

    @Select("SELECT * FROM employee WHERE id=#{id}")
    public Employee getEmpById(int id);


    @Update("UPDATE employee SET lastName=#{lastName},email=#{email},gender=#{gender},d_id=#{dId} WHERE id=#{id}")
    public void updateEmp(Employee employee);


    @Delete("DELETE FROM employee where id=#{id}")
    public void deleteEmpById(Integer id);


    @Insert("INSERT INTO employee(lastName,email,gender,d_id) VALUE{#{lastName},#{email},#{gender},#{dId}}")
    public void insertEmp(Employee employee);


    @Select("SELECT * FROM employee WHERE last_name=#{lastName}")
    Employee getEmpByLastName(String name);
}
