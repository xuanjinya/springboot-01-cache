package com.example.springboot01cache.conreoller;

import com.example.springboot01cache.bean.Department;
import com.example.springboot01cache.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huang.beijin
 * @date 2020/3/1 15:29
 * @description
 */
@RestController
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/dept/{id}")
    public Department getDepartment(@PathVariable("id") Integer id) {
        Department departmentById = departmentService.getDepartmentById(id);
        return departmentById;
    }
}
