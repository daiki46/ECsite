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

@WebServlet("/remove-prod-servlet")
public class RemoveProdServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//パラメータの取得
		req.setCharacterEncoding("UTF-8");
		int idx = Integer.parseInt(req.getParameter("idx"));
		
		//セッションオブジェクト取得
		HttpSession session = req.getSession();
		
		//カートからの商品削除処理
		Operation op = new Operation();
		op.removeProd(idx, session);
		
		//転送先設定
		String url = "cart.jsp";
			
		//転送
		RequestDispatcher dispatcher = req.getRequestDispatcher(url);
		dispatcher.forward(req, resp);
	}
	
}
