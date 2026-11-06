package com.taskflow.studentmanagement.service.impl;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.taskflow.studentmanagement.entity.BaseEntity;
import com.taskflow.studentmanagement.exception.ResourceNotFoundException;
import com.taskflow.studentmanagement.dto.BaseRequestDTO;
import com.taskflow.studentmanagement.dto.BaseResponseDTO;
import com.taskflow.studentmanagement.mapper.BaseMapper;
import com.taskflow.studentmanagement.repository.BaseRepository;
import com.taskflow.studentmanagement.service.BaseService;

@Transactional
public abstract class BaseServiceImpl <E extends BaseEntity,
                                       Q extends BaseRequestDTO,
                                       R extends BaseResponseDTO,
                                       ID extends Serializable,
                                       Rep extends BaseRepository<E, ID>,
                                       Map extends BaseMapper<E, Q, R>>
                                    implements BaseService<Q, R, ID> {

    protected final Rep repository;
    protected final Map mapper;

    protected BaseServiceImpl(Rep repository, Map mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public R save(Q request) {
        E entity = mapper.toEntity(request);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public List<R> saveAll(List<Q> requests) {
        if (requests == null || requests.isEmpty()) {
            return Collections.emptyList();
        }
        List<E> entities = requests.stream()
            .map(mapper::toEntity)
            .collect(Collectors.toList());
        return mapper.toResponseList(repository.saveAll(entities));
    }

    @Override
    public Optional<R> findById(ID id) {
        validateId(id);
        return repository.findById(id).map(mapper::toResponse);
    }

    @Override
    public List<R> findAll() {
        return mapper.toResponseList(repository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<R> findAll(Pageable pageable) {
        Objects.requireNonNull(pageable, "Pageable must not be null ");
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(ID id) {
        validateId(id);
        return repository.findByIdAndActive(id, true).isPresent();
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }

    @Override
    public void deleteById(ID id) {
        E entity = findEntityOrThrow(id);
        softDelete(entity);
    }

    @Override
    public void delete(Q request) {
        Objects.requireNonNull(request, "Request must not be null");
        E entity = mapper.toEntity(request);
        softDelete(entity);
    }

    @Override
    public void deleteAll() {
        List<E> entities = repository.findAll();
        entities.forEach(this::softDelete);
        repository.saveAll(entities);
    }

    @Override
    public R activate(ID id) {
        E entity = findEntityOrThrow(id);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public R deactivate(ID id) {
        E entity = findEntityOrThrow(id);
        entity.setActive(false);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public List<R> findAllActive() {
        return mapper.toResponseList(repository.findAllByActive(true));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<R> findAllActive(Pageable pageable) {
        Objects.requireNonNull(pageable, "Pageable must not be null");
        return repository.findAllByActive(true, pageable)
            .map(mapper::toResponse);
    }

    @Override
    public Optional<R> findByIdAndActive(ID id, boolean active) {
        validateId(id);
        return repository.findByIdAndActive(id, active)
            .map(mapper::toResponse);
    }

    protected E findEntityOrThrow(ID id) {
        validateId(id);
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
    }

    protected void softDelete(E entity) {
        entity.setActive(false);
        entity.setDeleted(true);
        repository.save(entity);
    }

    /**
     * Guards against null/blank IDs before hitting the database.
     * */
    private void validateId(ID id) {
        Objects.requireNonNull(id, "ID must not be null");
    }
}
