package com.kerimay.user.repository;

import com.kerimay.user.domain.Role;
import com.kerimay.user.domain.User;
import com.kerimay.user.domain.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<User, Long> {

    Optional<Role> findByName(RoleName roleName);
}
