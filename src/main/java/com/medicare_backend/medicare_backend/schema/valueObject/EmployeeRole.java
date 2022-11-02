package com.medicare_backend.medicare_backend.schema.valueObject;

import javax.persistence.*;

@Entity
@Table(name = "employeeRole")
public class EmployeeRole {

    private long employeeRoleId;
    private String employeeRoleName;
    private boolean employeeRoleIsDoctor;

    public EmployeeRole() {

    }

    public EmployeeRole(String employeeRoleName, boolean employeeRoleIsDoctor) {

        this.employeeRoleName = employeeRoleName;
        this.employeeRoleIsDoctor = employeeRoleIsDoctor;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getEmployeeRoleId() {
        return employeeRoleId;
    }

    public void setEmployeeRoleId(long employeeRoleId) {
        this.employeeRoleId = employeeRoleId;
    }

    public String getEmployeeRoleName() {
        return this.employeeRoleName;
    }

    public void setEmployeeRoleName(String employeeRoleName) {
        this.employeeRoleName = employeeRoleName    ;
    }

    public boolean getEmployeeRoleIsDoctor() {
        return this.employeeRoleIsDoctor;
    }

    public void setEmployeeRoleIsDoctor(Boolean employeeRoleIsDoctor) {
        this.employeeRoleIsDoctor = employeeRoleIsDoctor;
    }
}
