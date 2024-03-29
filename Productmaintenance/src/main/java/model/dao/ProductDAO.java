package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Product;

public class ProductDAO {
	public List<Product> selectByAll() throws SQLException{
		//格納用
		List<Product> list = new ArrayList<>();
		String sql = "SELECT product_id,product_name,price FROM m_product ORDER BY product_id ASC";
		try (Connection con = MyConnection.connection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			try(ResultSet rs = pstmt.executeQuery()){
				while(rs.next()) {
					String productId = rs.getString("product_id");
					String productName = rs.getString("product_name");
					int price = rs.getInt("price");
					
					//リストに追加
					list.add(new Product(productId, productName, price));
				}
			}
		}
		return list;
	}

	public List<Product> selectByProductName(String name) throws SQLException {
		//格納用
		List<Product> list = new ArrayList<>();
		
		String sql = "SELECT product_id, product_name, price FROM m_product where "
				+ "product_name like ? ORDER BY product_id ASC";
		
		try (Connection con = MyConnection.connection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, "%" + name + "%");
			try(ResultSet rs = pstmt.executeQuery()){
				while(rs.next()) {
					String productId = rs.getString("product_id");
					String productName = rs.getString("product_name");
					int price = rs.getInt("price");
					
					//リストに追加
					list.add(new Product(productId, productName, price));
				}
			}
		}
		return list;
	}
	
	public int insert(Product product) throws SQLException {
		String sql = "INSERT INTO m_product (product_id, product_name, price) "
				+ "values(?, ?, ?) ";
		int cnt;
		try (Connection con = MyConnection.connection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, product.getProductId());
			pstmt.setString(2, product.getProductName());
			pstmt.setInt(3, product.getPrice());
			cnt = pstmt.executeUpdate();
		}
		return cnt;
	}
	
	/*
	 * 削除処理
	 */
	public int delete(String productId) throws SQLException {
		int cnt;
		String sql = "DELETE FROM m_product WHERE product_id=?";
		
		try (Connection con = MyConnection.connection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, productId);
			cnt = pstmt.executeUpdate();
		}
		
		return cnt;
	}
	
	/*
	 * 更新処理
	 */
	public int update(String productId, String productName, int price) throws SQLException {
		int cnt = 0;
		String sql = "UPDATE m_product SET product_name=?,price=? WHERE product_id=?";
		
		try (Connection con = MyConnection.connection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, productName);
			pstmt.setInt(2, price);
			pstmt.setString(3, productId);
			
			cnt = pstmt.executeUpdate();
		}
		return cnt;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
