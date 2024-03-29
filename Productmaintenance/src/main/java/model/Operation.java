package model;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import model.dao.ProductDAO;

public class Operation {
	/*
	 * 検索結果のリストをセッションスコープに格納
	 */
	public void search(HttpSession session, String productName) throws SQLException {
		ProductDAO productDao = new ProductDAO();
		List<Product> list;
		if(productName.equals("")) {
			list = productDao.selectByAll();
		}else {
			list = productDao.selectByProductName(productName);
		}
		//セッションスコープに格納
		session.setAttribute("result", list);
	}
	
	/*
	 * 削除処理
	 */
	public void delete(HttpSession session, int idx) throws SQLException {
		List<Product> list = (List<Product>)session.getAttribute("result");
		ProductDAO productDao = new ProductDAO();
		Product product = list.get(idx);
		
		String productId = product.getProductId();
		
		productDao.delete(productId);
		
	}
	
}
