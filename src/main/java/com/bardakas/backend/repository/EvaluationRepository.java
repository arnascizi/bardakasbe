package com.bardakas.backend.repository;

import com.bardakas.backend.entity.db.Evaluation;
import com.bardakas.backend.entity.enums.Stream;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    List<Evaluation> findAllByStudentId(long studentId);

    List<Evaluation> findAllByTeacherId(long teacherId);

    List<Evaluation> findAllByStream(Stream stream);
}
