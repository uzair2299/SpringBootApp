package com.example.SprintBootAppWithSQL.services;

import com.example.SprintBootAppWithSQL.dto.BankAccountDto;
import com.example.SprintBootAppWithSQL.entities.Designation;
import com.example.SprintBootAppWithSQL.repository.BankAccountRepository;
import com.example.SprintBootAppWithSQL.repository.DesignationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountService {
    @Autowired
    BankAccountRepository bankAccountRepository;

//    @Autowired
//    public void saveBankAccountDetails(BankAccountDto bankAccountDto){
//
//    }
}