package com.taskflow.studentmanagement.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.taskflow.studentmanagement.entity.BaseEntity;

@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity, ID extends Serializable> extends JpaRepository<E, ID>, JpaSpecificationExecutor<E> {

    Optional<E> findByIdAndActive(ID id, boolean active);

    List<E> findAllByActive(boolean active);

    Page<E> findAllByActive(boolean active, Pageable pageable);

    boolean existsByIdAndActive(ID id, boolean active);

    long countByActive(boolean active);

}
