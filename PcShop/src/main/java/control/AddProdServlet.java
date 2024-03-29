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

@WebServlet("/add-prod-servlet")
public class AddProdServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//パラメータ取得
		req.setCharacterEncoding("UTF-8");
		int idx = Integer.parseInt(req.getParameter("idx"));
		
		//セッションオブジェクト取得
		HttpSession session = req.getSession();
		
		//カートへの商品追加処理
		Operation op = new Operation();
		op.addProd(idx, session);
		
		
		//転送先設定
		String url = "cart.jsp";
		
		//転送
		RequestDispatcher dispatcher = req.getRequestDispatcher(url);
		dispatcher.forward(req, resp);
	}

}
