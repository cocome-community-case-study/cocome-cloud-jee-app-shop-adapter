package org.cocome.app.adapter.service;

import org.springframework.stereotype.Component;

@Component
public class LoginServcie {

	public Boolean login(String user, String password) {
		return "user".equals(user) && "password".equals(password);
	}

}
