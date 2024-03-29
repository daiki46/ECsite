package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Sales;

public class SalesDAO {
	public int insertAll(List<Sales> list) throws Exception{
		String sql = "INSERT INTO t_sales (user_id, product_id, quantity, sales_date) values(?, ?, ?, ?)";
		int cnt = 0;
		try (Connection con = MyConnection.getConnection()){
			try(PreparedStatement pstmt = con.prepareStatement(sql)) {
				//オートコミットOFF＝トランザクション開始
				con.setAutoCommit(false);
				
				for(Sales sales : list) {
					pstmt.setString(1, sales.getUserId());
					pstmt.setString(2, sales.getProductId());	
					pstmt.setInt(3, sales.getQuantity());
					pstmt.setDate(4, sales.getSalesDate());
					
					cnt += pstmt.executeUpdate();
				}
				
				//コミット
				con.commit();
			
			} catch(Exception e) {
				//例外が発生した場合ロールバック	
				System.out.println("ロールバックします。");
				con.rollback();
				throw new Exception();
			}
		}
		return cnt;
	}
	
	public List<Sales>selectByUserId(String userId) throws ClassNotFoundException, SQLException {
		String historySql = "select * from t_sales where user_id=?";
		String productSql = "select * from m_product where product_id=?";
		
		//結果格納用
		List<Sales> list = new ArrayList<>();
		
		String sql = 
				"SELECT "
				+ "ts.sales_id,ts.user_id,mu.user_name,ts.product_id,"
				+ "mp.product_name, mp.price,ts.quantity,ts.sales_date "
				+ "FROM "
				+ "t_sales ts "
				+ "INNER JOIN m_product mp ON ts.product_id = mp.product_id "
				+ "LEFT OUTER JOIN m_user mu ON ts.user_id = mu.user_id "
				+ "WHERE ts.user_id = ? "
				+ "ORDER BY ts.sales_date DESC,ts.sales_id ASC";
		
		//購入履歴を取得
		try (Connection con = MyConnection.getConnection()){
			try(PreparedStatement pstmt = con.prepareStatement(sql)){
				pstmt.setString(1, userId);
				
				try(ResultSet rs = pstmt.executeQuery()) {
					while(rs.next()) {
						int salesId = rs.getInt("sales_id");
						String userName = rs.getString("user_name");
						String productId = rs.getString("product_id");
						String productName = rs.getString("product_name");
						int price = rs.getInt("price");
						int quantity = rs.getInt("quantity");
						Date salesDate = rs.getDate("sales_date");
						
						//１件分のオブジェクトを生成してリストに追加
						Sales sales = new Sales(salesId, userId, userName, productId, productName,
								price, quantity, salesDate);
						list.add(sales);
						
					}
				}
			}
		}
		return list;
	}
	
}
