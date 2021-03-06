package com.aysevarlik.ui.impl;

import com.aysevarlik.business.dto.EmployeeDto;
import com.aysevarlik.business.services.IEmployeeServices;
import com.aysevarlik.ui.rest.IEmployeeRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin(origins = "http://localhost:3000:")
@RequestMapping("/api/v1")
public class EmployeeRestImpl implements IEmployeeRest {

    @Autowired
    IEmployeeServices services;

    @GetMapping("/index")
    public String getRoot() {
        return "index";
    }

    //SAVE
    // http://localhost:8888/api/v1/employees
    @Override
    @PostMapping("/employees")
    public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto) {
        services.createEmployee(employeeDto);
        return employeeDto;
    }

    //LIST
    // http://localhost:8888/api/v1/employees
    @Override
    @GetMapping("/employees")
    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeDto> listem = services.getAllEmployee();
        return listem;
    }

    //FIND
    // http://localhost:8888/api/v1/employees/1
    @Override
    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable(name = "id") Long id) {
        ResponseEntity<EmployeeDto> response=  services.getEmployeeById(id);
        return response;
    }

    //UPDATE
    // http://localhost:8888/api/v1/employees/2
    @Override
    @PutMapping("/employees/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable(name = "id") Long id,@RequestBody EmployeeDto employeeDto) {
        ResponseEntity<EmployeeDto> response=services.updateEmployee(id,employeeDto);
        return response.ok(employeeDto);
    }

    //DELETE
    // http://localhost:8888/api/v1/employees/2
    @Override
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable(name = "id") Long id) {
        services.deleteEmployee(id);
        Map<String,Boolean> response= new HashMap<>();
        response.put("silindi",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
