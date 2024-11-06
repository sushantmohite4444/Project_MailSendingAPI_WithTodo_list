package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.utility.ImageEncoder;




@SpringBootApplication
public class SendMailExmpleApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(SendMailExmpleApplication.class, args);
		
		System.out.println(ImageEncoder.getDefaultImageAsByteArray());

		}


}


	


