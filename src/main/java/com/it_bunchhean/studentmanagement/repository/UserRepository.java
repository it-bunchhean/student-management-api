package com.it_bunchhean.studentmanagement.repository;

import com.it_bunchhean.studentmanagement.constant.Role;
import com.it_bunchhean.studentmanagement.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    @Query("""
        SELECT u FROM User u 
        WHERE (:keyword IS NULL
            OR LOWER(u.username) LIKE LOWER(CONCAT('%', :keyword, '%')) 
            OR LOWER(u.email)    LIKE LOWER(CONCAT('%', :keyword, '%')))
        AND (:role IS NULL OR u.role = :role)
    """)
    Page<User> search(@Param("keyword") String keyword,
                      @Param("role")Role role,
                      Pageable pageable
                      );
}
