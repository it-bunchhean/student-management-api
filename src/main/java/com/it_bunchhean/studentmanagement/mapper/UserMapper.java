package com.it_bunchhean.studentmanagement.mapper;

import com.it_bunchhean.studentmanagement.dto.request.RegisterRequest;
import com.it_bunchhean.studentmanagement.dto.request.UpdateUserRequest;
import com.it_bunchhean.studentmanagement.dto.response.UserResponse;
import com.it_bunchhean.studentmanagement.entity.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserMapper {

    UserResponse toResponse(User user);

    List<UserResponse> toResponseList(List<User> users);

    @Mapping(target = "username", source = "username", qualifiedByName = "trim")
    @Mapping(target = "email", source = "email", qualifiedByName = "normalizeEmail")
    User toEntity(RegisterRequest request);

    @Mapping(target = "username", source = "username", qualifiedByName = "trim")
    @Mapping(target = "email", source = "email", qualifiedByName = "normalizeEmail")
    void updateEntity(RegisterRequest request, @MappingTarget User user);

    @Mapping(target = "firstName", source = "firstName", qualifiedByName = "trim")
    @Mapping(target = "lastName", source = "lastName", qualifiedByName = "trim")
    @Mapping(target = "phoneNumber", source = "phoneNumber", qualifiedByName = "trim")
    void updateEntityFromRequest(UpdateUserRequest request, @MappingTarget User user);

    @Condition
    default boolean hasText(String value) {
        return value != null && !value.isBlank();
    }

    @Named("trim")
    default String trim(String value) {
        return value == null ? null : value.trim();
    }

    @Named("normalizeEmail")
    default String normalizeEmail(String value) {
        return value == null ? null : value.trim().toLowerCase();
    }
}
