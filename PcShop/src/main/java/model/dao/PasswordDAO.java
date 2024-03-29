package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Password;

public class PasswordDAO {
	public int update(Password password) throws SQLException, ClassNotFoundException {
		int cnt = 0;
		String sql = "UPDATE m_password SET password=? WHERE user_id=?";
		try(Connection con = MyConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(2, password.getUserId());
			pstmt.setString(1, password.getPassword());
			
			cnt = pstmt.executeUpdate();	//レコード更新処理の実行
		}
		return cnt;
	}
	
	public String selectById(String userId) throws Exception {
		String sql = "SELECT password FROM m_password WHERE user_id=?";
		String pass = null;
		try(Connection con = MyConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, userId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					pass = rs.getString("password");
				}else {
					throw new Exception();
				}
			}
		}
		return pass;
		
	}

}
