package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cart;
import model.Operation;
import model.Sales;

@WebServlet("/get-history-servlet")
public class GetHistoryServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//転送先設定
		String url = "history.jsp";
		
		try {
			//ユーザID 取得
			HttpSession session = req.getSession();
			Cart cart = (Cart) session.getAttribute("cart");
			String userId = cart.getUserId();
			
			//売上リスト（購入履歴）取得処理
			Operation op = new Operation();
			List<Sales> salesList = op.getHistory(userId);
			if(!salesList.isEmpty()) {
				req.setAttribute("history", salesList);
			}else {
				req.setAttribute("errorMsg", "購入履歴がありません。");
			}
		}catch(Exception e){
			req.setAttribute("errorMsg", "購入履歴取得時にエラーが発生しました。");
		}
		
		//転送
		RequestDispatcher dispatcher = req.getRequestDispatcher(url);
		dispatcher.forward(req, resp);
	}
	
}
