package com.taskflow.studentmanagement.utils;

import java.util.concurrent.ThreadLocalRandom;

public class Util {

    // Generate 6 digit string one time password otp
    public static String generateOtp() {
        int randomOtp = ThreadLocalRandom.current().nextInt(100000, 1000000);
       return String.valueOf(randomOtp);
    }

}