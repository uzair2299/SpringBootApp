package com.example.SprintBootAppWithSQL.services;

import com.example.SprintBootAppWithSQL.dto.EmployeeDto;
import com.example.SprintBootAppWithSQL.entities.Department;
import com.example.SprintBootAppWithSQL.entities.Designation;
import com.example.SprintBootAppWithSQL.entities.Employee;
import com.example.SprintBootAppWithSQL.entities.WorkType;
import com.example.SprintBootAppWithSQL.repository.DepartmentRepository;
import com.example.SprintBootAppWithSQL.repository.DesignationRepository;
import com.example.SprintBootAppWithSQL.repository.EmployeeRepository;
import com.example.SprintBootAppWithSQL.repository.WorkTypeRepository;
import com.example.SprintBootAppWithSQL.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    DesignationRepository designationRepository;
    @Autowired
    WorkTypeRepository workTypeRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        employeeDto.setPassword(passwordEncoder.encode(employeeDto.getPassword()));

        Optional<Department> department = departmentRepository.findById(employeeDto.getDepartment_id());
        Optional<Designation> designation = designationRepository.findById(employeeDto.getDesignation_id());
        Optional<WorkType> workType  = workTypeRepository.findById(employeeDto.getWork_type_id());

        Employee employee = MapperUtil.mapObject(employeeDto, Employee.class);

        if (department.isPresent()) {
            employee.setDepartment(department.get());
        }
        if (designation.isPresent()) {
            employee.setDesignation(designation.get());
        }

        if (workType.isPresent()) {
            employee.setWorkType(workType.get());
        }


        employee = employeeRepository.save(employee);
        EmployeeDto result = MapperUtil.mapObject(employee, EmployeeDto.class);
        result.setPassword("");
        return result;
    }
}