package com.nelson.rokel.repository;


import com.nelson.rokel.entity.PosIris;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
@Author: Nelson
@Date: OCT-2020
 */
@Repository
public interface PosIrisRepository extends JpaRepository<PosIris,Long> {
    List<PosIris> findBySerialNumber(String serial);
}
