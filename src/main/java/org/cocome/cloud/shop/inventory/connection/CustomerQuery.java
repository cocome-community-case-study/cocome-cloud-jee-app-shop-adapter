package org.cocome.cloud.shop.inventory.connection;

import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.cocome.cloud.logic.registry.client.IApplicationHelper;
import org.cocome.cloud.logic.stub.ILoginManager;
import org.cocome.cloud.logic.stub.ILoginManagerService;
import org.cocome.cloud.logic.stub.NotBoundException_Exception;
import org.cocome.cloud.logic.stub.NotInDatabaseException_Exception;
import org.cocome.cloud.registry.service.Names;
import org.cocome.cloud.shop.customer.Customer;
import org.cocome.cloud.shop.inventory.store.Store;
import org.cocome.tradingsystem.inventory.application.store.CustomerWithStoreTO;
import org.cocome.tradingsystem.inventory.application.store.StoreTO;
import org.cocome.tradingsystem.inventory.application.usermanager.CredentialTO;
import org.cocome.tradingsystem.inventory.application.usermanager.CredentialType;
import org.cocome.tradingsystem.inventory.application.usermanager.Role;
import org.cocome.tradingsystem.inventory.application.usermanager.UserTO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;

/**
 * Queries customers from the backend and transforms the response into a
 * Customer instance.
 * 
 * @author Tobias Pöppke
 * @author Robert Heinrich
 */
@Component
@Scope
public class CustomerQuery {
	private static final Logger LOG = Logger.getLogger(CustomerQuery.class);

	ILoginManager loginManager;

	long defaultEnterpriseIndex = -1;

	@Inject
	IApplicationHelper applicationHelper;

	private ILoginManager lookupLoginManager(long loginManagerId) throws NotInDatabaseException_Exception {
		try {
			return applicationHelper.getComponent(Names.getLoginManagerRegistryName(loginManagerId),
					ILoginManagerService.SERVICE, ILoginManagerService.class).getILoginManagerPort();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| MalformedURLException | NoSuchMethodException | SecurityException | NotBoundException_Exception e) {
			if (loginManagerId == defaultEnterpriseIndex) {
				LOG.error("Got exception while retrieving enterprise manager location: " + e.getMessage());
				e.printStackTrace();
				throw new NotInDatabaseException_Exception(e.getMessage());
			} else {
				return lookupLoginManager(defaultEnterpriseIndex);
			}
		}
	}

	public Customer getCurrentCustomer(String username, String password) throws NotInDatabaseException_Exception {
		UserTO userTO = initUserTO(username, password);
		loginManager = lookupLoginManager(defaultEnterpriseIndex);
		Customer customer = new Customer();
		try {
			UserTO serverTO = loginManager.requestAuthToken(userTO);
			CustomerWithStoreTO customerTO = loginManager.getCustomerWithStoreTO(serverTO, username);
			customer.setUserTO(serverTO);
			customer.initCustomerFields(customerTO);
		} catch (NotInDatabaseException_Exception s) {
			LOG.error("Customer could not be retrieved from the database.");
		}
		return customer;
	}

	public boolean createUserWithPassword(String username, String password) throws NotInDatabaseException_Exception {
		loginManager = lookupLoginManager(defaultEnterpriseIndex);
		return loginManager.createNewUser(initUserTO(username, password));
	}

	public boolean registerNewCustomer(Store activeStore, String firstName, String lastName, String mailAddress, String password) throws NotInDatabaseException_Exception {
		CustomerWithStoreTO customer = initCustomerTO(activeStore, firstName, lastName, mailAddress, password);
		loginManager = lookupLoginManager(defaultEnterpriseIndex);
		return loginManager.createNewCustomer(customer);
	}


	private UserTO initUserTO(String username, String password) {
		CredentialTO credential = new CredentialTO();
		credential.setType(CredentialType.PASSWORD);
		credential.setCredentialString(password);

		UserTO userTO = new UserTO();
		userTO.setUsername(username);
		userTO.getCredentials().add(credential);
		return userTO;
	}

	private CustomerWithStoreTO initCustomerTO(Store activeStore, String firstName, String lastName, String mailAddress,
			String password) {
		CustomerWithStoreTO customer = new CustomerWithStoreTO();
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setMailAddress(mailAddress);

		UserTO user = initUserTO(customer.getMailAddress(), password);
		customer.setUsername(user.getUsername());
		customer.getCredentials().addAll(user.getCredentials());
		customer.getRoles().add(Role.CUSTOMER);

		if (activeStore != null) {
			StoreTO store = initStoreTO(activeStore);
			customer.setPreferredStoreTO(store);
		}

		return customer;
	}

	public boolean updateCustomer(Customer customer) throws NotInDatabaseException_Exception {
		loginManager = lookupLoginManager(defaultEnterpriseIndex);
		CustomerWithStoreTO customerTO = new CustomerWithStoreTO();
		customerTO.setId(customer.getId());
		customerTO.setFirstName(customer.getFirstName());
		customerTO.setLastName(customer.getLastName());
		customerTO.setUsername(customer.getUserTO().getUsername());
		customerTO.setPreferredStoreTO(customer.getPreferredStore());

		for (String creditCard : customer.getCreditCardInfos()) {
			customerTO.getCreditCardInfos().add(creditCard);
		}

		return loginManager.updateCustomer(customerTO);
	}

	private StoreTO initStoreTO(Store activeStore) {
		StoreTO store = new StoreTO();
		store.setId(activeStore.getID());
		store.setLocation(activeStore.getLocation());
		store.setName(activeStore.getName());
		return store;
	}
}
