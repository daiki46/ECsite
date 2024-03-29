package model;

public class Password {
	private String userId;
	private String password;
	
	public Password() {
		super();
	}

	public Password(String userId, String password) {
		super();
		this.userId = userId;
		this.password = password;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}
	public String getPassword() {
		return password;
	}
	
}
