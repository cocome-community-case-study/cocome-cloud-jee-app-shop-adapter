package org.cocome.app.adapter.json;

public class JsonItem {

    private Integer id;
    private String name;
    private String imageURL;
    private String description;
    private Double price;
    private Integer amount;
     
	public JsonItem(Integer id, String name, String imageURL, String description, Double price, Integer amount) {
		super();
		this.id = id;
		this.name = name;
		this.imageURL = imageURL;
		this.description = description;
		this.price = price;
		this.amount = amount;
	}
	
	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getImageURL() {
		return imageURL;
	}
	public String getDescription() {
		return description;
	}
	public Double getPrice() {
		return price;
	}
	public Integer getAmount() {
		return amount;
	}
}
