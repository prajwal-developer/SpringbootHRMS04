package com.kodnest.project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodnest.project.Service.SarvamService;


@RestController
@RequestMapping("/ai")
public class AIController {
	
	  @Autowired
	    private SarvamService sarvamService;

	    @PostMapping("/chat")
	    public String chat(@RequestBody String prompt) {
	        return sarvamService.chat(prompt);

  }
	    
}
