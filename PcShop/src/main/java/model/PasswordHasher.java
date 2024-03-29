package model;

import java.security.MessageDigest;
import java.util.Base64;

public class PasswordHasher {
	
	public static String hashPassword(String password) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(password.getBytes());
		byte[] digest = md.digest();
		
		return Base64.getEncoder().encodeToString(digest);
	}
	
	public static boolean checkPassword(String inputPass, String dbPass) throws Exception {
		//比較結果を返す
		return hashPassword(inputPass).equals(dbPass);
	}
}
