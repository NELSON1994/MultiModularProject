package com.nelson.rokel.repository;


import com.nelson.rokel.entity.FailedTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
@Author: Nelson
@Date: OCT-2020
 */
@Repository
public interface FailedTransactionRepository extends JpaRepository<FailedTransaction,Long> {
}
