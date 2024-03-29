package model;

import java.sql.Date;

public class Sales {
	private int salesId;
	private String userId;
	private String userName;
	private String productId;
	private String productName;
	private int price;
	private int quantity;
	private Date salesDate;
	
	
	
	public Sales(int salesId, String userId, String userName, String productId, String productName, int price,
			int quantity, Date salesDate) {
		super();
		this.salesId = salesId;
		this.userId = userId;
		this.userName = userName;
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
		this.salesDate = salesDate;
	}
	public Sales(String userId, String productId, int quantity, Date salesDate) {
		super();
		this.userId = userId;
		this.productId = productId;
		this.quantity = quantity;
		this.salesDate = salesDate;
	}
	public String getUserId() {
		return userId;
	}
	public String getProductId() {
		return productId;
	}
	public Sales(int salesId, String productId, String productName, int price, int quantity, Date salesDate) {
		super();
		this.salesId = salesId;
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
		this.salesDate = salesDate;
	}
	public int getSalesId() {
		return salesId;
	}
	public void setSalesId(int salesId) {
		this.salesId = salesId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Date getSalesDate() {
		return salesDate;
	}
	public void setSalesDate(Date salesDate) {
		this.salesDate = salesDate;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	public String getPriceString() {
		return String.format("%,d", price) + "円";
	}
}
