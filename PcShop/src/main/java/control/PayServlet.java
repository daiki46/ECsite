package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Operation;

@WebServlet("/pay-servlet")
public class PayServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//パラメータの取得
		req.setCharacterEncoding("UTF-8");
		
		//セッションオブジェクト取得
		HttpSession session = req.getSession();

		//転送先設定
		String url = "pay.jsp";
				
		Operation op = new Operation();
		try {
			op.pay(session);
		} catch (Exception e) {
			req.setAttribute("errorMsg", "清算処理でエラーが発生しました。");
			url = "cart.jsp";
		}
			
		//転送
		RequestDispatcher dispatcher = req.getRequestDispatcher(url);
		dispatcher.forward(req, resp);
	}
	
}
