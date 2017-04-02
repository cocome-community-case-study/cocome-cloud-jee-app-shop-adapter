package org.cocome.app.adapter.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.cocome.app.adapter.json.JsonEnterprise;
import org.cocome.app.adapter.json.JsonStore;
import org.cocome.cloud.logic.stub.NotInDatabaseException_Exception;
import org.cocome.cloud.shop.inventory.connection.EnterpriseQuery;
import org.cocome.cloud.shop.inventory.connection.StoreQuery;
import org.cocome.cloud.shop.inventory.enterprise.Enterprise;
import org.cocome.cloud.shop.inventory.store.Store;
import org.springframework.stereotype.Component;

@Component
public class EnterpriseService {

	@Inject
	EnterpriseQuery enterpriseQuery;

	public List<JsonEnterprise> getEnterprises() throws NotInDatabaseException_Exception {
		
		List<JsonEnterprise> list = new ArrayList<>();
		
		for (Enterprise enterprise : enterpriseQuery.getEnterprises()) {
			JsonEnterprise jsonEnterprise = new JsonEnterprise(enterprise.getName(),enterprise.getId());
			for (Store store : enterpriseQuery.getStores(enterprise.getId())) {
				jsonEnterprise.addStore(new JsonStore(store.getID(), store.getName(), store.getLocation()));
			}
			list.add(jsonEnterprise);
		}

		return list;
	}

}
