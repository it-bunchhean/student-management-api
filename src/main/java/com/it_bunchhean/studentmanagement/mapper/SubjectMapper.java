package com.it_bunchhean.studentmanagement.mapper;

import com.it_bunchhean.studentmanagement.entity.Subject;
import com.it_bunchhean.studentmanagement.dto.request.SubjectRequest;
import com.it_bunchhean.studentmanagement.dto.response.SubjectResponse;

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
