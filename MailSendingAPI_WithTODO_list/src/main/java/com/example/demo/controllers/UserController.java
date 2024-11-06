package com.example.demo.controllers;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.Entity.Completed;
import com.example.demo.Entity.Tasks;
import com.example.demo.Entity.UserInfo;
import com.example.demo.Service.CompletedService;
import com.example.demo.Service.TaskService;
import com.example.demo.Service.UserService;
import com.example.demo.utility.ImageEncoder;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

@Controller

public class UserController {
	@Autowired
	UserService us;

	@Autowired
	 UserInfo uinfo;
	
	@Autowired
	Tasks tst;
	
	@Autowired
	TaskService ts;
	
	@Autowired
	Completed com;
	
	@Autowired
	CompletedService cs;
	
	@Autowired
	ImageEncoder imageEncoder;

	@RequestMapping("")
	public String reditec() {
		System.out.println("sushant mohite");
		return "redirect:/Login";
	}

	@RequestMapping("/Login")
	public String name() {
		System.out.println( "inside login  = " +uinfo);
		return "Login";// /Home
	}

	@PostMapping("/Home")
	public String List(@ModelAttribute("info") UserInfo info, Model m) {
		System.out.println( "inside Home" +uinfo);
		
		String email = info.getMail();
		String pass = info.getPassword();
		
		
		UserInfo userInfo;
		 userInfo = us.get(email);
		
		if( userInfo == null) {
			m.addAttribute("popup",true);
			m.addAttribute("message","Your mail id is not registred");
			return "Login";
		}	
		else if( userInfo!=null && pass.equals( userInfo.getPassword())) {
			uinfo =  userInfo;
			m.addAttribute("popup",false);
      		m.addAttribute("m",  userInfo);

		} else {
			m.addAttribute("popup",true);
			m.addAttribute("message","Password is incorrect");
			return "Login";
		}
		
		m.addAttribute("imageEncoder", imageEncoder);
			
		return "Home";
	}

	@RequestMapping("/Register")
	public String RegisterUser() {

		return "Register";

	}

	@PostMapping("/Save")
	public String Savedata(@RequestParam("name") String name,
			@RequestParam("last_name") String lname,@RequestParam("mail") 
			String mail,@RequestParam("file") MultipartFile file,Model m ) {
		
		System.out.println(mail);
		
		uinfo=us.get(mail);
		uinfo.setName(name);
		uinfo.setLast_name(lname);
		
		if(!file.isEmpty()) {
			System.out.println("file is not empty");
		 try {
			byte[] fileContent = file.getBytes();
			uinfo.setImage(fileContent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		
		UserInfo ui = us.save(uinfo);
		uinfo = ui;
              System.out.println("Inside save method" + uinfo);
              
              m.addAttribute("m",uinfo);
              m.addAttribute("imageEncoder",imageEncoder);
              
		return "Home";

	}

	@RequestMapping("/Task")

	public String taskform(@RequestParam("m") String u,Model mo) {
				
		 uinfo = us.get(u);

		System.out.println(u);
		System.out.println("inside Task " +uinfo);
		mo.addAttribute("uinfo",uinfo);
		return "AddTasks";
	}

	@PostMapping("/Addtask")
	public String Savetask(@ModelAttribute("task") Tasks task,
			RedirectAttributes rd) {
        
		System.out.println("Inside Add = " + uinfo);
		System.out.println(task);
		 java.util.List<Tasks> lTask = new ArrayList<>() ;
		
		 if(! uinfo.getTasksm().isEmpty())
		 lTask.addAll( uinfo.getTasksm());  
		 lTask.add(task);
		
		 uinfo.setTasksm(lTask);
		 us.save(uinfo);
		 uinfo = us.get(uinfo.getMail());
		rd.addAttribute("m",uinfo);
		return "redirect:/home";
	}
	
	@RequestMapping("/home")
	public String norefresh(@ ModelAttribute("m") UserInfo U, Model m) {
		m.addAttribute("m",U);
		m.addAttribute("imageEncoder",imageEncoder);
		return "Home";
	}

	

	@PostMapping("/deletetask")
	public String deleteandsave(@RequestParam("Tid") int id,
			@RequestParam("m") String gmail,Model m) {
		
		Tasks td = ts.delete(id);
		
		   com.setCompleted_task(td.getTask());		
		   
		 java.util.List<Completed> complist =new ArrayList<>();
		 
		 uinfo =us.get(gmail);
		 if(!uinfo.getComp().isEmpty())
			  complist=uinfo.getComp();
				complist.add(com);
				uinfo.setComp(complist);
				
				us.save(uinfo);
				
			m.addAttribute("m",uinfo);
			m.addAttribute("imageEncoder",imageEncoder);
				return "Home";
				
	}
	
	@RequestMapping("/Backtotask")
	public String Backtotask(@RequestParam("Cid") int id,
			@RequestParam("m") String gmail,Model m) {
		
		Completed td = cs.backtotask(id);
		
		 tst.setTask(td.getCompleted_task());		
		   
		 java.util.List<Tasks> tasks =new ArrayList<>();
		 
		 uinfo =us.get(gmail);
		 if(!uinfo.getTasksm().isEmpty())
			  tasks=uinfo.getTasksm();
				tasks.add(tst);
				uinfo.setTasksm(tasks);
				us.save(uinfo);
			m.addAttribute("m",uinfo);
			m.addAttribute("imageEncoder",imageEncoder);
				return "Home";
	}
	
	@RequestMapping("/deletecomp")
	public String deletecomp(@RequestParam("Cid") int id,
			@RequestParam("m") String gmail,Model m) { 
		             cs.delete(id);
		          uinfo =us.get(gmail);
		 
			     m.addAttribute("m",uinfo);
			     m.addAttribute("imageEncoder",imageEncoder);
				return "Home";
	}
	
	
	
	
	@GetMapping("/logout")
    public String logoutUser() {
        // Perform logout operation
        return "redirect:/Login";
    }
	
	
	
	
	
	
	@GetMapping("/Mailverification/{registerOrforgot}")
	
		public String Verifyemail(@PathVariable("registerOrforgot") String registerOrforgot ,Model m) {
		
		m.addAttribute("isregistration", registerOrforgot);

		return "MailVerification";
	}
	
	 @PostMapping("/saveFile/{Email}")
	 @ResponseBody
	   public String saveFile(@RequestParam("file") MultipartFile file, @PathVariable ("Email") String email ){
	     try {
	      String fileName = file.getOriginalFilename();
	      String contentType = file.getContentType();
	      
	      byte[] fileContent = file.getBytes();
	      
	      UserInfo userInfo = us.get(email) ;
	      System.out.println(userInfo +""+ email);
	      userInfo.setImage(fileContent);
	      us.save(userInfo);
	    
	      return "File saved successfully";
	     }
	   
	     catch(Exception e) {
	      return "File not saved";
	    }
	   }
	 
	 
	// https://spring.io/guides/gs/uploading-files
}
