package org.cocome.app.adapter.service;

import javax.inject.Inject;
import javax.security.auth.login.LoginException;

import org.apache.log4j.Logger;
import org.cocome.cloud.shop.inventory.connection.LoginQuery;
import org.springframework.stereotype.Component;

@Component
public class LoginServcie {
	
	private static final Logger LOG = Logger.getLogger(LoginServcie.class);
	
	@Inject
	LoginQuery loginQuery;

	public Boolean login(String user, String password) {
		try {
			return loginQuery.authenticateUser(user, password).getUsername().equals(user);
		} catch (Exception e) {
			LOG.error("LoginException", e);
		}
		return Boolean.FALSE;
	}

}
