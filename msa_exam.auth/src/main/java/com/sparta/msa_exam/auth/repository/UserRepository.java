package com.sparta.msa_exam.auth.repository;

import com.sparta.msa_exam.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    boolean existsByUsername(String username);
}
