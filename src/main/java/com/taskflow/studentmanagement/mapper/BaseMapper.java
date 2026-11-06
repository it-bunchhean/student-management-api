package com.taskflow.studentmanagement.mapper;

import java.util.List;
import org.mapstruct.*;

public interface BaseMapper<E, RQ, RS>{

    /**
     * Create: DTO -> Entity
     * */
    E toEntity(RQ request);

    /**
     *  Entity -> Response DTO
     * */
    RS toResponse(E entity);

    List<RS> toResponseList(List<E> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(RQ request, @MappingTarget E entity);

}
