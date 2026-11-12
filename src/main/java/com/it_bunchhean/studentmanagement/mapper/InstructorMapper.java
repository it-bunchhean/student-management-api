package com.it_bunchhean.studentmanagement.mapper;

import com.it_bunchhean.studentmanagement.config.BaseMapperConfig;
import com.it_bunchhean.studentmanagement.entity.Instructor;
import com.it_bunchhean.studentmanagement.dto.request.InstructorRequest;
import com.it_bunchhean.studentmanagement.dto.response.InstructorResponse;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapperConfig.class)
public interface InstructorMapper extends BaseMapper<Instructor, InstructorRequest, InstructorResponse> {
}
