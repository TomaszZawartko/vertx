package com.tzawartko.vertx.offer;

/**
 * Container/Bean to store information about offer.
 * 
 * @author Tomasz Zawartko
 *
 */
public class Offer {

	private String id;
	private String name;
	private String price;

	public Offer(String id, String name, String price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + "]";
	}
}
