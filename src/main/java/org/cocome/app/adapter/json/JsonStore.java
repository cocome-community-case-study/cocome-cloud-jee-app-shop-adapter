package org.cocome.app.adapter.json;

public class JsonStore {

	private String name;
	private Integer id;
	private String location;
	
	public JsonStore()
	{
	}

	public JsonStore(Long id, String name, String location) {
		this.id = id.intValue();
		this.name = name;
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public long getId() {
		return id;
	}

	public String getLocation() {
		return location;
	}

}
