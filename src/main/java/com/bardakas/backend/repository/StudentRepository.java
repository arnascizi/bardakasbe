package com.bardakas.backend.repository;

import com.bardakas.backend.entity.db.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
