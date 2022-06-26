package com.aysevarlik.data.entity;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Builder


@Entity
@Table(name = "employees")
public class EmployeeEntity extends BaseEntity{

    @Column(name="employee_name")
    public String employeeName;

    @Column(name="employee_email")
    public String employeeEmail;

    public EmployeeEntity(String employeeName, String employeeEmail) {
        this.employeeName = employeeName;
        this.employeeEmail = employeeEmail;
    }
}
