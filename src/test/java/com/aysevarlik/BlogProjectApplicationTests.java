package com.aysevarlik;

import com.aysevarlik.data.entity.EmployeeEntity;
import com.aysevarlik.data.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BlogProjectApplicationTests implements ITestData{

    @Autowired
    EmployeeRepository repository;

    @Override
    @Test
    public void testCreate() {
        EmployeeEntity employeeEntity=EmployeeEntity.builder().employeeName("Ayse").employeeEmail("aysevarlik@gmail.com").build();
        repository.save(employeeEntity);
        assertNotNull(repository.findById(1L).get());
    }

    @Override
    @Test
    public void testFind() {
        EmployeeEntity employeeEntity=repository.findById(1L).get();
        assertEquals("Ayse",employeeEntity.getEmployeeName());
    }

    @Override
    @Test
    public void testList() {
        List<EmployeeEntity> entityList=repository.findAll();
        assertThat(entityList).size().isGreaterThan(0);
    }

    @Override
    @Test
    public void testUpdate() {
        EmployeeEntity employeeEntity=repository.findById(1L).get();
        employeeEntity.setEmployeeName("Eicha");
        repository.save(employeeEntity);
        assertNotEquals("Ayse",repository.findById(1L).get().getEmployeeName());
    }

    @Override
    @Test
    public void testDelete() {
        repository.deleteById(1L);
        assertThat(repository.existsById(1L)).isFalse();
    }


}
