package org.cocome.app.adapter.json;

public class JsonCreditcard {

	private Integer id;
	private String name;
	private Integer pin;

	public JsonCreditcard(Integer id, String name, Integer pin) {
		super();
		this.id = id;
		this.name = name;
		this.pin = pin;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getPin() {
		return pin;
	}

}
