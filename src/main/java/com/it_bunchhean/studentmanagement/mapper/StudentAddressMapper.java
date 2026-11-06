package com.it_bunchhean.studentmanagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.it_bunchhean.studentmanagement.entity.StudentAddress;
import com.it_bunchhean.studentmanagement.dto.request.StudentAddressRequest;
import com.it_bunchhean.studentmanagement.dto.response.StudentAddressResponse;

@Mapper(componentModel = "spring")
public interface StudentAddressMapper {

    StudentAddressResponse toResponse(StudentAddress studentAddress);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "student", ignore = true)
    StudentAddress toEntity(StudentAddressRequest request);
}
