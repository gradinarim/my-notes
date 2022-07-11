package com.gradinar.mynotes.repository;

import com.gradinar.mynotes.entity.Role;
import com.gradinar.mynotes.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(RoleName roleName);
}
