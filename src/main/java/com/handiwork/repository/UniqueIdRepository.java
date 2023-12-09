package com.handiwork.repository;

import com.handiwork.entity.UniqueId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UniqueIdRepository extends JpaRepository<UniqueId, Integer> {
    Optional<UniqueId> findById(String id);
}
