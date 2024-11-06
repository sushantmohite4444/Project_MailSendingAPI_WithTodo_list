package com.example.demo.Entity;

import java.util.Random;

public class Create_OTP {
	 public static String generateOTP() {
	        Random random = new Random();
	        int otp = 100000 + random.nextInt(900000); // Generate 6-digit OTP
	        return String.valueOf(otp);
	    }
}
