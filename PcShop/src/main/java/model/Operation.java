package model;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import model.dao.PasswordDAO;
import model.dao.ProductDAO;
import model.dao.SalesDAO;

public class Operation {
	public boolean loginProc(String userId, String password, HttpSession session) throws Exception{
		//ログイン認証
		boolean result = authenticate(userId, password);
		if(result) {
			// 店舗データの作成→セッションに格納
			Store store = makeStore();
			session.setAttribute("store", store);
			
			//カート情報の作成（userId設定・商品リストは空）→セッションに格納
			Cart cart = new Cart(userId, new ArrayList<Product>());
			session.setAttribute("cart", cart);
		}
		return result;
	
	}

	/*
	 * 認証する
	 * @param userId
	 * @param password
	 * return 結果(true / false)
	 */
	private boolean authenticate(String userId, String password) throws Exception {
		boolean result = false;
		
		//DBよりパスワード取得
		PasswordDAO passwordDao = new PasswordDAO();
		String dbPass = passwordDao.selectById(userId);
		if(password.equals("") && dbPass.equals("")) {
			//空白（初期状態をtrueにする）
			result = true;
		} else {
			//比較結果の設定
			result = PasswordHasher.checkPassword(password, dbPass);
		}
		return result;
	}

	/*
	 * 店舗情報（店舗名＋選択データ（リスト））を作成する
	 * @return 店舗情報
	 */
	private Store makeStore() throws ClassNotFoundException, SQLException {
		ProductDAO dao = new ProductDAO();
		List<Product> productList = dao.selectAll();
		
		//店舗情報作成
		Store store = new Store("速水PC販売", productList);
		
		//商品追加

		
		return store;
	}
	
	/*
	 * ログアウト時の処理
	 * @paran sessioin
	 */
	public void logoutProc(HttpSession session) {
		session.invalidate();
	}
	//カートに商品を追加
	public void addProd(int idx, HttpSession session) {
		//店舗情報・カート情報の取得（セッションより）
		Store store = (Store) session.getAttribute("store");
		Cart cart = (Cart) session.getAttribute("cart");
		
		if((store != null) && (cart != null)) {
			//カートに指定の商品を追加
			cart.add(store.getListProd().get(idx));
			
			//セッションに再度格納
			session.setAttribute("caet", cart);
		}
	}
	//カートの商品の削除
	public void removeProd(int idx, HttpSession session) {
		//
		Cart cart = (Cart)session.getAttribute("cart");
		cart.remove(idx);
		
		//セッションに再格納
		session.setAttribute("cart", cart);
	}
	//清算処理
	public void pay(HttpSession session) throws Exception{
		Cart cart = (Cart) session.getAttribute("cart");
		if(cart != null) {
			//空の商品リスト作成
			List<Sales> list = new ArrayList<Sales>();
			
			//現在の日付取得
			Date currentDate = new Date(Calendar.getInstance().getTimeInMillis());
			
			//売上リストの作成
			for(Product product : cart.getListProd()) {
				Sales one = new Sales(cart.getUserId(), product.getId(), 1, currentDate);
				list.add(one);
			}
			
			//売上登録
			SalesDAO salesDao = new SalesDAO();
			salesDao.insertAll(list);
			
			//セッションに格納（清算済みデータ）
			session.setAttribute("pay", cart);

			//カート情報の新規作成⇒セッションに格納
			Cart newCart = new Cart(cart.getUserId(), new ArrayList<Product>());
			session.setAttribute("cart", newCart);

		}		
	}
	
	//パスワード変更処理
	public void chgPassword(String userId, String password) throws Exception {
		
		String hashedPassword = PasswordHasher.hashPassword(password);
		
		Password pw = new Password(userId, hashedPassword);
		
		//パスワード変更
		PasswordDAO passwordDao = new PasswordDAO();
		int cnt = passwordDao.update(pw);
		
		if(cnt < 1) {
			throw new Exception();
		}
	}
	
	public List<Sales> getHistory(String userId) throws ClassNotFoundException, SQLException {
		SalesDAO salesDao = new SalesDAO();
		List<Sales> sales = salesDao.selectByUserId(userId);
		
		return sales;
		
	}
}
