package com.gradinar.mynotes.repository;

import com.gradinar.mynotes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Query("SELECT u FROM user u WHERE u.email = ?#{principal?.username}")
    User findCurrentByEmail();

    Boolean existsByEmail(String email);
}
