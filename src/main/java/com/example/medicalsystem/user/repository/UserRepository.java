package com.example.medicalsystem.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findByToken(String token);

    User findByUserId(String userId);

    User findByUserIdAndPassword(String userId, String password);
}
