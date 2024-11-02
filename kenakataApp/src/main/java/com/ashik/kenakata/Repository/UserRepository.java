package com.ashik.kenakata.Repository;

import com.ashik.kenakata.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
