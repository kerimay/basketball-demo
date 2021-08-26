package com.kerimay.basketball.repository;

import com.kerimay.basketball.domain.Stat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StatRepository extends JpaRepository<Stat, UUID> {

    List<Stat> getStatsByPlayer_Id(int id);
}
