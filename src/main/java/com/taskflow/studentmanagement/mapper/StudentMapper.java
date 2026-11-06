package com.taskflow.studentmanagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.taskflow.studentmanagement.entity.Student;
import com.taskflow.studentmanagement.dto.request.CreateStudentRequest;
import com.taskflow.studentmanagement.dto.request.UpdateStudentRequest;
import com.taskflow.studentmanagement.dto.response.StudentResponse;

@Mapper(componentModel = "spring", uses = {StudentAddressMapper.class})
public interface StudentMapper {

    @Mapping(target = "address", source = "address")
    StudentResponse toResponse(Student student);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    Student toEntity(CreateStudentRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    void updateFromRequest(UpdateStudentRequest request, @MappingTarget Student student);
}
