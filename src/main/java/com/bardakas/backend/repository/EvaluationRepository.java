package com.bardakas.backend.repository;

import com.bardakas.backend.entity.db.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    List<Evaluation> findAllByStudentId(long studentId);
}
