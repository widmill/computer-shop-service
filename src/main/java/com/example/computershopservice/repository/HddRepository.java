package com.example.computershopservice.repository;

import com.example.computershopservice.entity.HDD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HddRepository extends JpaRepository<HDD, Long> {
}
