package model;

public class Product {
	private String id;
	private String name;
	private int price;
	public Product(String id, String name, int price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getPrice() {
		return price;
	}
	
	public String getPriceString() {
		return String.format("%,d", price) + "円";
	}
	
}
