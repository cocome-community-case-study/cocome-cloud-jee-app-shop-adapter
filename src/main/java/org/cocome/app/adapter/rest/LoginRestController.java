package org.cocome.app.adapter.rest;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import org.cocome.app.adapter.service.LoginServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/login")
public class LoginRestController {
	
	@Autowired
	LoginServcie loginservice;

    @RequestMapping("/")
    public Boolean index(HttpServletResponse response, @RequestParam String user, @RequestParam String password) {
		response.setHeader("Access-Control-Allow-Origin","*");
		return loginservice.login(user,password);
        
    }

}