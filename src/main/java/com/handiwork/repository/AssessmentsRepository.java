package com.handiwork.repository;

import com.handiwork.entity.Assessments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssessmentsRepository extends JpaRepository<Assessments, Integer> {
    Optional<Assessments> findByProductId(String productId);
}
