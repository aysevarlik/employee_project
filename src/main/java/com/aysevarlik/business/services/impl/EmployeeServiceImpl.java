package com.aysevarlik.business.services.impl;

import com.aysevarlik.business.dto.EmployeeDto;
import com.aysevarlik.business.services.IEmployeeServices;
import com.aysevarlik.data.entity.EmployeeEntity;
import com.aysevarlik.data.repository.EmployeeRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Log4j2
public class EmployeeServiceImpl implements IEmployeeServices {

    @Autowired
    EmployeeRepository repository;

    @Autowired
    ModelMapper mapper;


    @Override
    public EmployeeDto EntityToDto(EmployeeEntity employeeEntity) {
        EmployeeDto employeeDto = mapper.map(employeeEntity, EmployeeDto.class);
        return employeeDto;
    }

    @Override
    public EmployeeEntity DtoToEntity(EmployeeDto employeeDto) {
        EmployeeEntity entity = mapper.map(employeeDto, EmployeeEntity.class);
        return entity;
    }

    //http://localhost:8888/save/employees
    @Override
    @PostMapping("/save/employees")
    public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeEntity entity = DtoToEntity(employeeDto);
        repository.save(entity);
        log.info("Ekleme Başarılı");
        return employeeDto;
    }

    //http://localhost:8888/list/employees
    @Override
    @GetMapping("/list/employees")
    public List<EmployeeDto> getAllEmployee() {
        List<EmployeeDto> list = new ArrayList<>();
        Iterable<EmployeeEntity> listem = repository.findAll();
        for (EmployeeEntity entity : listem) {
            EmployeeDto employeeDto = EntityToDto(entity);
            list.add(employeeDto);
        }
        return list;
    }

    //http://localhost:8888/find/employees/1
    @Override
    @GetMapping("/find/employees/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable(name = "id") Long id) {
        EmployeeEntity employeeEntity=repository.findById(id)
            .orElseThrow( ()->new ResourceNotFoundException(id+ "bulunamadı"));
        EmployeeDto employeeDto=EntityToDto(employeeEntity);
        return ResponseEntity.ok(employeeDto);
    }


    //http://localhost:8888/update/employees/1
    @Override
    @PutMapping("/update/employees/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable(name = "id") Long id,@RequestBody EmployeeDto employeeDto) {
        //DtoToEntity
        EmployeeEntity employeeEntity=DtoToEntity(employeeDto);
        EmployeeEntity employeeFind=repository.findById(id)
                .orElseThrow( ()->new ResourceNotFoundException(id+ "bulunamadı"));

        employeeFind.setEmployeeName(employeeEntity.getEmployeeName());
        employeeFind.setEmployeeEmail(employeeEntity.getEmployeeEmail());
        EmployeeEntity employeeEntity2=  repository.save(employeeFind);

        EmployeeDto employeeDto2=  EntityToDto(employeeEntity2);
        return ResponseEntity.ok(employeeDto2);
    }

    //http://localhost:8888/delete/employees/1
    @Override
    @DeleteMapping("/delete/employees/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable(name = "id") Long id) {
        EmployeeEntity employeeEntity=repository.findById(id)
                .orElseThrow( ()->new ResourceNotFoundException(id+ "id bulunamadı"));
        repository.delete(employeeEntity);
        Map<String,Boolean> response=new HashMap<>();
        response.put("silindi",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}

















