package com.it_bunchhean.studentmanagement.service;

public interface LoginAttemptService {

    void recordFailure(String identifier, String ip);
    void recordSuccess(String identifire);
    boolean isBlocked(String identifier, String ip);
}
