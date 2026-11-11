package com.it_bunchhean.studentmanagement.data;

import com.it_bunchhean.studentmanagement.dto.request.LoginRequest;
import com.it_bunchhean.studentmanagement.dto.request.RegisterRequest;
import com.it_bunchhean.studentmanagement.entity.User;

public final class TestData {

    private TestData() {}

    public static final String USERNAME = "chheandev";
    public static final String EMAIL = "chhean@gamil.com";
    public static final String PASSWORD = "chhean168";
    public static final String HASED_PASSWORD = "hashed";

    public static RegisterRequest registerRequest() {
        return new RegisterRequest(USERNAME, EMAIL, PASSWORD);
    }

    public static LoginRequest loginRequest() {
        return new LoginRequest(EMAIL, PASSWORD);
    }

    public static User user() {
        return User.builder()
                .id(1L)
                .username(USERNAME)
                .email(EMAIL)
                .password(HASED_PASSWORD)
                .build();
    }


    public static String regsiterJson() {
        return """
                {"username":"%s", "email":"%s", "password":"%s"}
                """.formatted(USERNAME, EMAIL, PASSWORD);
    }

    public static String loginJson() {
        return """
                {"email":"%s", "password":"%s"}
                """.formatted(EMAIL, PASSWORD);
    }
}
