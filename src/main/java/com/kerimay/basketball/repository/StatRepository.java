package com.kerimay.basketball.repository;

import com.kerimay.basketball.domain.Stat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatRepository extends JpaRepository<Stat, Double> {
}
