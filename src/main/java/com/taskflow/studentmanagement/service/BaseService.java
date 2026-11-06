package com.taskflow.studentmanagement.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.taskflow.studentmanagement.dto.BaseRequestDTO;
import com.taskflow.studentmanagement.dto.BaseResponseDTO;

public interface BaseService<Q extends BaseRequestDTO,
                             R extends BaseResponseDTO,
                             ID extends Serializable> {
    
    R save(Q req);

    List<R> saveAll(List<Q> entities);

    Optional<R> findById(ID id);

    List<R> findAll();

    Page<R> findAll(Pageable pageable);

    boolean existsById(ID id);

    long count();

    void deleteById(ID id);

    void delete(Q req);

    void deleteAll();

    R activate(ID id);

    R deactivate(ID id);

    List<R> findAllActive();

    Page<R> findAllActive(Pageable pageable);

    Optional<R> findByIdAndActive(ID id, boolean active);
}
