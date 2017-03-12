package org.cocome.app.adapter.json;

import java.util.List;

public class JsonEnterprise {
	
	private String name;
	private List<JsonStore> stores;
	
	public JsonEnterprise(String name, List<JsonStore> stores) {
		super();
		this.name = name;
		this.stores = stores;
	}

	public String getName() {
		return name;
	}

	public List<JsonStore> getStores() {
		return stores;
	}
	
	public void addStore(JsonStore store)
	{
		this.stores.add(store);
	}
}
