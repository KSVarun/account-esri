package com.esri.account.repository;

import com.esri.account.bean.Api;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface APIRepository extends JpaRepository<Api,Long> {
    Api findByKey(String key);


}
