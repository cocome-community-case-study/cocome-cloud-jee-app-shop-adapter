package org.cocome.app.adapter.json;

import org.cocome.cloud.shop.inventory.store.ProductWrapper;

public class JsonItem {

    private Long id;
    private String name;
    private String imageURL;
    private String description;
    private Double price;
    private Long amount;
     
	public JsonItem(Long id, String name, String imageURL, String description, Double price, Long amount) {
		super();
		this.id = id;
		this.name = name;
		this.imageURL = imageURL;
		this.description = description;
		this.price = price;
		this.amount = amount;
	}
	
	public JsonItem(ProductWrapper productWrapper) {
		this(productWrapper.getID(), productWrapper.getName(), "http://pipsum.com/200x200.jpg", productWrapper.getDescription(), productWrapper.getSalesPrice(), productWrapper.getAmount());
	}

	public Long getId() {
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
	public Long getAmount() {
		return amount;
	}
}
