package model;

import java.util.List;

public class Cart {
	private String userId;
	private List<Product> listProd;
	
	
	public Cart(String userId, List<Product> listProd) {
		super();
		this.userId = userId;
		this.listProd = listProd;
	}


	public String getUserId() {
		return userId;
	}


	public List<Product> getListProd() {
		return listProd;
	}
	
	public void add(Product prod){
		listProd.add(prod);
	}
	
	public void remove(int index) {
		listProd.remove(index);
	}
	
	public void clear() {
		listProd.clear();
	}
	
	public int getTotalPrice() {
		int total = 0;
		for(Product prod : listProd) {
			total += prod.getPrice();
		}
		return total;
	}
	
	public String getTotalPriceString() {
		return String.format("%,d", getTotalPrice()) + "円";
	}
	
}
