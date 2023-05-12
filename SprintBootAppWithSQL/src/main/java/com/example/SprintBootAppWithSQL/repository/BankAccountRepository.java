package com.example.SprintBootAppWithSQL.repository;

import com.example.SprintBootAppWithSQL.entities.BankAccount;
import com.example.SprintBootAppWithSQL.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

}

