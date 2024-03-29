package model;

import java.util.List;

public class Store {
	private String name;
	private List<Product> listProd;
	
	/*********************コンストラクタ**************************/
	/*
	 * @param name
	 * @param listProd
	 */
		
	public Store(String name, List<Product> listProd) {
		super();
		this.name = name;
		this.listProd = listProd;
	}

	public String getName() {
		return name;
	}

	public List<Product> getListProd() {
		return listProd;
	}
	
	/*
	 * 商品を追加する
	 * @param prod 追加する商品
	 */
	public void add(Product prod) {
		listProd.add(prod);
	}
	
	/*
	 * 商品を除去する
	 * @param index 削除する商品のリスト内のインデックス
	 */
	public void remove(int index) {
		listProd.remove(index);
	}
	
	/*
	 * すべての商品を除去する
	 */
	public void clear() {
		listProd.clear();
	}
}
