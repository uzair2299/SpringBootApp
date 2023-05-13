package com.example.SprintBootAppWithSQL.services;

import com.example.SprintBootAppWithSQL.dto.BankAccountDto;
import com.example.SprintBootAppWithSQL.dto.EmployeeDto;
import com.example.SprintBootAppWithSQL.entities.BankAccount;
import com.example.SprintBootAppWithSQL.entities.Designation;
import com.example.SprintBootAppWithSQL.entities.Employee;
import com.example.SprintBootAppWithSQL.repository.BankAccountRepository;
import com.example.SprintBootAppWithSQL.repository.DesignationRepository;
import com.example.SprintBootAppWithSQL.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountService {
    @Autowired
    BankAccountRepository bankAccountRepository;
    @Autowired
    EmployeeService employeeService;


    public void saveBankAccountDetails(BankAccountDto bankAccountDto) {
        EmployeeDto employeeDto = employeeService.getEmployeeById(bankAccountDto.getEmployeeId());
        if (!employeeDto.equals(null)) {
            BankAccount bankAccount = MapperUtil.mapObject(bankAccountDto,BankAccount.class);
            Employee employee = MapperUtil.mapObject(employeeDto,Employee.class);
            bankAccount.setEmployee(employee);
            bankAccountRepository.save(bankAccount);
        }
    }

}
