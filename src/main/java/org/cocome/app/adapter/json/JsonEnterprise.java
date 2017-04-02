package org.cocome.app.adapter.json;

import java.util.ArrayList;
import java.util.List;

public class JsonEnterprise {

	private String name;
	private Long id;
	private List<JsonStore> stores = new ArrayList<>();

	public JsonEnterprise(String name, Long id) {
		super();
		this.name = name;
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<JsonStore> getStores() {
		return stores;
	}

	public void addStore(JsonStore store) {
		this.stores.add(store);
	}
}
