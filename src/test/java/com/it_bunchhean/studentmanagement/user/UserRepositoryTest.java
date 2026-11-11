package com.it_bunchhean.studentmanagement.user;

import com.it_bunchhean.studentmanagement.constant.Role;
import com.it_bunchhean.studentmanagement.entity.User;
import com.it_bunchhean.studentmanagement.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import static com.it_bunchhean.studentmanagement.data.TestData.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void findByEmail_returnsUser() {

        User user = User.builder()
                .username(USERNAME)
                .email(EMAIL)
                .password(PASSWORD)
                .role(Role.USER)
                .build();

        userRepository.save(user);

        assertThat(userRepository.findByEmail(EMAIL)).isPresent();
        assertThat(userRepository.existsByUsername(USERNAME)).isTrue();
        assertThat(userRepository.existsByEmail("nobody@gmail.com")).isFalse();

    }

    @Test
    void save_duplicateEmail_violatesUniqueConstraint() {
        userRepository.saveAndFlush(User.builder()
                        .username("a")
                        .email("same@gmail.com")
                        .password("x")
                        .role(Role.USER)
                        .build());
        assertThatThrownBy(() -> userRepository.saveAndFlush(
                User.builder()
                        .username("b")
                        .email("same@gmail.com")
                        .password("x")
                        .role(Role.USER)
                        .build()))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

//    @Test
//    void search_filtersByKeywordAndRole() {
//        userRepository.save(user());
//        userRepository.save(User.builder()
//                .username("admin")
//                .email("admin@gmail.com")
//                .password("admin123")
//                .role(Role.ADMIN)
//                .build());
//
//        Page<User> result = userRepository.search("so", Role.USER, PageRequest.of(0, 10));
//        assertThat(result.getContent()).extracting(User::getUsername).containsExactly("chheandev");
//    }
}
