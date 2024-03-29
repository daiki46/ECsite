package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Product;

/*
 * 商品情報DAO
 */
public class ProductDAO {
	public List<Product> selectAll() throws SQLException, ClassNotFoundException {
		
		List<Product> productList = new ArrayList<Product>();
		String sql = "SELECT * FROM m_product ORDER BY product_id";
		
		try (Connection con = MyConnection.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while(rs.next()) {
			String id = rs.getString("product_id");
			String pn = rs.getString("product_name");
			int price = rs.getInt("price");
			Product product = new Product(id, pn, price);
			productList.add(product);
			}
		}
		return productList;
	}		
}
