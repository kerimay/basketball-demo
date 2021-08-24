package com.kerimay.basketball.repository;

import com.kerimay.basketball.domain.HeadCoach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HeadCoachRepository extends JpaRepository<HeadCoach, UUID> {
    HeadCoach getHeadCoachByName(String name);
}
