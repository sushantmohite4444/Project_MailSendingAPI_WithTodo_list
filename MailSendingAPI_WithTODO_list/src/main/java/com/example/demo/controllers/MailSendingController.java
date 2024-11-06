package com.example.demo.controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Entity.Create_OTP;
import com.example.demo.Entity.UserInfo;
import com.example.demo.Service.EmailService;
import com.example.demo.Service.UserService;
import com.example.demo.utility.ImageEncoder;

@Controller
public class MailSendingController {

	

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private UserInfo uifo;
	
	@Autowired
	private UserService us;
	
	@Autowired
	ImageEncoder imageEncoder;

	@PostMapping("/send-otp/{isregistration}")
	    public String sendOtp(@RequestParam("email") String email,Model m,@PathVariable("isregistration") String isregistration) 
	    		throws UnsupportedEncodingException {
		UserInfo userInfo = us.get(email);
		System.out.println( !isregistration.equals("register"));
		if(userInfo != null && isregistration.equals("register")){
        	m.addAttribute("message","Email registered already");
        	m.addAttribute("popup",true);
            return "Login";
        }
	    	
//	     email =  URLDecoder.decode(email, StandardCharsets.UTF_8.name());
				email.trim();
	    	
	        String otp = Create_OTP.generateOTP();
	        System.out.println( "my otp is "+otp);
	    
	        
	        emailService.sendOtpEmail(email, otp);
	        
	        
	        Map<String,String> map=new HashMap(); 
	        map.put("otp", otp);
	        map.put("email", email);
	        
	        m.addAllAttributes(map);
	        return "OTPCheck";
	    }
	
	@PostMapping("/SetPassword")
	public String setpass(@RequestParam("email")  String email,Model m) {
		
		System.out.println( "inside setPass" +email);
		m.addAttribute("email",email);
		return "SetPassword";
		
	}

	@PostMapping("/submitpass")
	
	    public String verifyOtp(@RequestParam("email") String mail,
	    		@RequestParam ("password") String pass ) {
	        System.out.println( " i am inside submitpasss" + mail);
	        UserInfo userInfo= us.get(mail);
	        
	        if(userInfo == null) {
	        	uifo.setMail(mail);
	        	uifo.setPassword(pass);
	          
	            
	    			System.out.println("file is not empty");
	    		 try {
	    			
	    			uifo.setImage(imageEncoder.getDefaultImageAsByteArray());
	    			System.out.println( uifo.getImage());
	    		} catch (IOException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	    		 
	    		  us.save(uifo); 
	    		
	        }
	        else {
	        	userInfo.setPassword(pass);
	        	us.save(userInfo);
	        }
	        
			return "Login";
	    }
	    
	    
	    
	    


}
