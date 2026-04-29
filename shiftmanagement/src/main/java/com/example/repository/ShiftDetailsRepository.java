package com.example.repository;

import com.example.entity.ShiftDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShiftDetailsRepository extends JpaRepository<ShiftDetails, Long> {
}