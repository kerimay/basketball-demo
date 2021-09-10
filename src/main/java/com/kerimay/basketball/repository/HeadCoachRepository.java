package com.kerimay.basketball.repository;

import com.kerimay.basketball.domain.HeadCoach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeadCoachRepository extends JpaRepository<HeadCoach, Long> {
    HeadCoach getHeadCoachByName(String name);
}
