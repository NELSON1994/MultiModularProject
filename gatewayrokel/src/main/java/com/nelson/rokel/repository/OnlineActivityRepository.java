package com.nelson.rokel.repository;

import com.nelson.rokel.entity.OnlineActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
@Author: Nelson
@Date: OCT-2020
 */
@Repository
public interface OnlineActivityRepository extends JpaRepository<OnlineActivity,Long> {
}
