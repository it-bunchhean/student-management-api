package com.taskflow.studentmanagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.taskflow.studentmanagement.entity.StudentAddress;
import com.taskflow.studentmanagement.dto.request.StudentAddressRequest;
import com.taskflow.studentmanagement.dto.response.StudentAddressResponse;

@Mapper(componentModel = "spring")
public interface StudentAddressMapper {

    StudentAddressResponse toResponse(StudentAddress studentAddress);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "student", ignore = true)
    StudentAddress toEntity(StudentAddressRequest request);
}
