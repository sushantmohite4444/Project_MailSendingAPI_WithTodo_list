package com.example.demo.utility;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class ImageEncoder {
	    public static String getImgData(byte[] byteData) {
	        return Base64.getMimeEncoder().encodeToString(byteData);
	    }
	    
	    public static byte[] getDefaultImageAsByteArray() throws IOException {
	        // Load the default image from the classpath
	        ClassPathResource resource = new ClassPathResource("static/defaultImage/default_profile_pic.jpg");

	        // Read the file into a byte array
	        try (InputStream is = resource.getInputStream();
	             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

	            byte[] buffer = new byte[1024];
	            int length;
	            while ((length = is.read(buffer)) > 0) {
	                byteArrayOutputStream.write(buffer, 0, length);
	            }
	            return byteArrayOutputStream.toByteArray();
	        }
	}
}

