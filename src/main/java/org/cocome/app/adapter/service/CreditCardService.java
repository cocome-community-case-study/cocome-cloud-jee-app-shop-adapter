package org.cocome.app.adapter.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.cocome.app.adapter.json.JsonCreditcard;
import org.cocome.cloud.logic.stub.NotInDatabaseException_Exception;
import org.cocome.cloud.shop.customer.Customer;
import org.cocome.cloud.shop.inventory.connection.CustomerQuery;
import org.springframework.stereotype.Component;

@Component
public class CreditCardService {
	
	@Inject
	CustomerQuery customerQuery;

	public List<JsonCreditcard> getCarts(String user, String password) throws NotInDatabaseException_Exception {
		List<JsonCreditcard> cards = new ArrayList<>(); 
		Set<String> creditCardInfos2 = customerQuery.getCurrentCustomer(user, password).getCreditCardInfos();
		String[] creditCardInfos = (String[]) creditCardInfos2.toArray(new String[creditCardInfos2.size()]);
		for (int i = 0; i < creditCardInfos.length; i++) {
			String string = creditCardInfos[i];
			cards.add(new JsonCreditcard(i, string, 1234));
		}
		//Add Creditcart for Test
		cards.add(new JsonCreditcard(543, "Master Visa", 1234));
		return cards;
	}

	public List<JsonCreditcard> addCard(String user, String password, JsonCreditcard card) throws NotInDatabaseException_Exception {
		Customer customer = customerQuery.getCurrentCustomer(user, password);
		customer.getCreditCardInfos().add(card.getName());
		customerQuery.updateCustomer(customer);
		return getCarts(user, password);
	}

	public Boolean checkPin(String user, JsonCreditcard card, Integer pin) {
		return card.getPin().equals(pin);
	}

}
