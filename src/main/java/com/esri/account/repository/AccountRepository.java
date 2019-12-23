package com.esri.account.repository;

import com.esri.account.bean.Account;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    Page<Account> findByAccountNo(Pageable pageable, Long accountNo);
}
