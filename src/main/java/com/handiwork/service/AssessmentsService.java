package com.handiwork.service;

import com.handiwork.entity.Assessments;
import com.handiwork.repository.AssessmentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class AssessmentsService {
    private final AssessmentsRepository assessmentsRepository;
    public Integer calculate(String productId, Integer assessments){
        Optional<Assessments> byProductId = assessmentsRepository.findByProductId(productId);
        Assessments entity;
        entity = byProductId.orElseGet(Assessments::new);
        entity.setAssessmentCount(entity.getAssessmentCount() != null ? entity.getAssessmentCount() + 1 : 1);
        entity.setSum(entity.getSum() != null ? entity.getSum() + assessments : assessments);
        entity.setAssessment(entity.getAssessment() != null ? entity.getSum() / entity.getAssessmentCount() : assessments);
        assessmentsRepository.save(entity);
        return entity.getAssessment();
    }
}
