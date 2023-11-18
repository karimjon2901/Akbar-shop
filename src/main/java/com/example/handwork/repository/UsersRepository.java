package com.example.handwork.repository;

import com.example.handwork.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByIdAndIsActive(Integer id, short i);

    Optional<Users> findFirstByPhoneNumberAndIsActive(String phoneNumber, short i);
}