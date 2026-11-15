package com.it_bunchhean.studentmanagement.specification;

import com.it_bunchhean.studentmanagement.constant.Role;
import com.it_bunchhean.studentmanagement.entity.User;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class UserSpecificatioin {

    public static Specification<User> search(String keyword, Role role) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (keyword != null && !keyword.isBlank()) {

                String pattern = "%" + keyword.trim().toLowerCase() + "%";

                Predicate username = criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("username")),
                        pattern
                );

                Predicate email = criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("email")),
                        pattern
                );

                predicates.add(criteriaBuilder.or(username, email));
            }

            if (role != null) {
                predicates.add(
                        criteriaBuilder.equal(root.get("role"), role)
                );
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
