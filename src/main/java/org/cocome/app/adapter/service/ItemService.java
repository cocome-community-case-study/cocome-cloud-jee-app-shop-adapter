package org.cocome.app.adapter.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.cocome.app.adapter.json.JsonItem;
import org.cocome.app.adapter.json.JsonStore;
import org.cocome.cloud.logic.stub.NotInDatabaseException_Exception;
import org.cocome.cloud.shop.inventory.connection.EnterpriseQuery;
import org.cocome.cloud.shop.inventory.connection.StoreQuery;
import org.cocome.cloud.shop.inventory.store.ProductWrapper;
import org.cocome.cloud.shop.inventory.store.Store;
import org.springframework.stereotype.Component;

@Component
public class ItemService {

	@Inject
	StoreQuery storeQuery;

	@Inject
	EnterpriseQuery enterpriseQuery;

	public List<JsonItem> getAllItems(JsonStore jsonStore) throws NotInDatabaseException_Exception {
		List<JsonItem> items = new ArrayList<>();
		Store store = enterpriseQuery.getStoreByID(jsonStore.getId());
		for (ProductWrapper productWrapper : storeQuery.queryStockItems(store)) {
			items.add(new JsonItem(productWrapper));
		}
		return items;
	}

	public List<JsonItem> getAllItemsByQuery(JsonStore store, String query) throws NotInDatabaseException_Exception {
		List<JsonItem> result = new ArrayList<JsonItem>();

		for (JsonItem jsonItem : getAllItems(store)) {
			if (jsonItem.getName().contains(query))
				result.add(jsonItem);
		}
		return result;
	}

	public JsonItem getItemById(JsonStore store, Long id) throws NotInDatabaseException_Exception {
		for (JsonItem jsonItem : getAllItems(store)) {
			if (jsonItem.getId() == id)
				return jsonItem;
		}
		return null;
	}

}
