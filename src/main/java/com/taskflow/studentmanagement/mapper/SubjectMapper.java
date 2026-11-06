package com.taskflow.studentmanagement.mapper;

import com.taskflow.studentmanagement.entity.Subject;
import com.taskflow.studentmanagement.dto.request.SubjectRequest;
import com.taskflow.studentmanagement.dto.response.SubjectResponse;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    SubjectResponse toResponse(Subject subject);

    @Mapping(target = "id", ignore = true)
    Subject toEntity(SubjectRequest request);

    @Mapping(target = "id", ignore = true)
    void updateFromRequest(SubjectRequest request, @MappingTarget Subject subject);
}
