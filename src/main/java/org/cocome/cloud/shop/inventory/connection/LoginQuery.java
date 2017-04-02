package org.cocome.cloud.shop.inventory.connection;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.security.auth.login.LoginException;
import org.cocome.cloud.logic.stub.ILoginManager;
import org.cocome.cloud.logic.stub.ILoginManagerService;
import org.cocome.cloud.logic.stub.NotInDatabaseException_Exception;
import org.cocome.tradingsystem.inventory.application.usermanager.CredentialTO;
import org.cocome.tradingsystem.inventory.application.usermanager.CredentialType;
import org.cocome.tradingsystem.inventory.application.usermanager.Role;
import org.cocome.tradingsystem.inventory.application.usermanager.UserTO;
import org.springframework.stereotype.Component;

/**
 * Implements the authentication of users with the CoCoME login service.
 * This class is an extension of GlassFish's login capabilities.
 * 
 * @author Tobias Pöppke
 * @author Robert Heinrich
 */
@Component
public class LoginQuery {
	
	ILoginManagerService managerService = new ILoginManagerService();
	ILoginManager loginManager = managerService.getILoginManagerPort();
	
	public UserTO authenticateUser(String username, String password) throws LoginException {

		UserTO user = initUserTO(username,password);
		UserTO authUser;
		
		try {
			if(loginManager.checkCredentials(user)) {
				authUser = loginManager.getUserTO(user, user.getUsername());
			} else {
				throw new LoginException("Username or Password incorrect.");
			}
		} catch (NotInDatabaseException_Exception e) {
			throw new LoginException(e.getMessage());
		}
		
		return authUser;
	}

	private UserTO initUserTO(String username, String password) {
		UserTO user = new UserTO();
		user.setUsername(username);
		
		CredentialTO credential = new CredentialTO();
		credential.setType(CredentialType.PASSWORD);
		credential.setCredentialString(password);
		
		user.getCredentials().add(credential);
		return user;
	}

}
