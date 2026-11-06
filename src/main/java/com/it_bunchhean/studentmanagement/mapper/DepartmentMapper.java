package com.it_bunchhean.studentmanagement.mapper;

import com.it_bunchhean.studentmanagement.config.BaseMapperConfig;
import com.it_bunchhean.studentmanagement.entity.Department;
import com.it_bunchhean.studentmanagement.dto.request.DepartmentRequest;
import com.it_bunchhean.studentmanagement.dto.response.DepartmentResponse;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapperConfig.class)
public interface DepartmentMapper extends BaseMapper<Department, DepartmentRequest, DepartmentResponse> { }
