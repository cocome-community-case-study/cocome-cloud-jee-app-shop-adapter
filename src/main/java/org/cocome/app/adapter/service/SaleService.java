package org.cocome.app.adapter.service;

import java.util.List;

import org.cocome.app.adapter.json.JsonCreditcard;
import org.cocome.app.adapter.json.JsonItem;
import org.cocome.app.adapter.json.JsonStore;
import org.springframework.stereotype.Component;

@Component
public class SaleService {

	public Boolean processSale(String user, JsonStore store, List<JsonItem> items, JsonCreditcard card) {
		return true;
	}

}
