package model;

public class Product {
	private String productId;
	private String productName;
	private int price;
	public Product(String productId, String productName, int price) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
	}
	
	public Product() {
		super();
	}

	public String getProductId() {
		return productId;
	}
	public String getProductName() {
		return productName;
	}
	public int getPrice() {
		return price;
	}
	
}
