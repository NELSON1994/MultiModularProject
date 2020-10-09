package com.nelson.rokel.repository;

import com.nelson.rokel.entity.OnlineActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * @Aurthor:Nelson
 */
@Repository
public interface OnlineActivityRepository extends JpaRepository<OnlineActivity,Long> {
}
