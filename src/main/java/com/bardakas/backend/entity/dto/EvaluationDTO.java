package com.bardakas.backend.entity.dto;

import com.bardakas.backend.entity.enums.Grade;
import com.bardakas.backend.entity.enums.OverallEvaluation;
import com.bardakas.backend.entity.enums.Stream;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class EvaluationDTO {

    Long id;

    Stream stream;

    Long teacherId;

    Long studentId;

    String teacherComment;

    Grade communicationGrade;

    String communicationComment;

    Boolean isExtraMile;

    String extraMileComments;

    Boolean isMotivated;

    String motivationComments;

    String directionComments;

    Grade abilityToLearnGrade;

    String abilityToLearnComments;

    OverallEvaluation overallEvaluation;

    String overallComments;

    @JsonFormat(pattern = "yyyy-MM-dd' 'HH:mm:ss", timezone = "Europe/Vilnius", shape = JsonFormat.Shape.STRING)
    Date createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd' 'HH:mm:ss", timezone = "Europe/Vilnius", shape = JsonFormat.Shape.STRING)
    Date updatedAt;
}
