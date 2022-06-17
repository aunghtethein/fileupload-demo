package com.ot.repo;

import com.ot.model.Staff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface StaffRepo extends JpaRepository<Staff, Integer> {

}
