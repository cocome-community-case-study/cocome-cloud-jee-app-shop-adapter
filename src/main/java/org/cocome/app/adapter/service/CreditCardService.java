package org.cocome.app.adapter.service;

import java.util.Arrays;
import java.util.List;

import org.cocome.app.adapter.json.JsonCreditcard;
import org.springframework.stereotype.Component;

@Component
public class CreditCardService {

	private List<JsonCreditcard> cards = Arrays.asList(new JsonCreditcard(1, "JsonMaster 1234", 1234),
			new JsonCreditcard(2, "JsonVisa 4567", 4567));

	public List<JsonCreditcard> getCarts(String user) {
		return this.cards ;
	}

	public void addCard(String user, JsonCreditcard card) {
		cards.add(card);
	}

	public Boolean checkPin(String user, JsonCreditcard card, Integer pin) {
		return card.getPin() == pin;
	}

}
