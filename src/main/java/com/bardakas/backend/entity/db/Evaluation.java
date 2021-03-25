package com.bardakas.backend.entity.db;

import com.bardakas.backend.config.PostgreSQLEnumType;
import com.bardakas.backend.entity.Teacher;
import com.bardakas.backend.entity.enums.Grade;
import com.bardakas.backend.entity.enums.OverallEvaluation;
import com.bardakas.backend.entity.enums.Stream;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
@Table(name = "evaluation")
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    Long id;

    @NotNull
    @Column(name = "stream")
    @Type(type = "pgsql_enum")
    @Enumerated(EnumType.STRING)
    Stream stream;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    Teacher teacher;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    Student student;

    @Column(name = "teacher_comment")
    String teacherComment;

    @NotNull
    @Column(name = "communication_grade")
    @Type(type = "pgsql_enum")
    @Enumerated(EnumType.STRING)
    Grade communicationGrade;

    @Column(name = "communication_comments")
    String communicationComment;

    @NotNull
    @Column(name = "is_extramile")
    Boolean isExtraMile;

    @Column(name = "is_extramile_comments")
    String extraMileComments;

    @NotNull
    @Column(name = "is_motivated")
    Boolean isMotivated;

    @Column(name = "motivation_comments")
    String motivationComments;

    @NotBlank
    @NotNull
    @Column(name = "direction_comments")
    String directionComments;

    @NotNull
    @Column(name = "ability_to_learn_grade")
    @Type(type = "pgsql_enum")
    @Enumerated(EnumType.STRING)
    Grade abilityToLearnGrade;

    @Column(name = "ability_to_learn_comments")
    String abilityToLearnComments;

    @NotNull
    @Column(name = "overall_evaluation")
    @Type(type = "pgsql_enum")
    @Enumerated(EnumType.STRING)
    OverallEvaluation overallEvaluation;

    @NotBlank
    @NotNull
    @Column(name = "overall_comments")
    String overallComments;

    @NotNull
    @Column(name = "created_at", updatable = false)
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd' 'HH:mm:ss", timezone = "Europe/Vilnius", shape = JsonFormat.Shape.STRING)
    Date createdAt;

    @NotNull
    @Column(name = "updated_at")
    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd' 'HH:mm:ss", timezone = "Europe/Vilnius", shape = JsonFormat.Shape.STRING)
    Date updatedAt;
}

