package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Operation;
import model.Product;

@WebServlet("/search-servlet")
public class SearchServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//転送先設定
		String url = "result.jsp";
		Operation op = new Operation();
		List<Product> list;
		//リクエストパラメータの取得
		req.setCharacterEncoding("UTF-8");
		String productName = (String) req.getParameter("productName");
		
		//セッションスコープの取得
		HttpSession session = req.getSession();
		
		try {
			op.search(session, productName);
			
		} catch (SQLException e) {
			req.setAttribute("errorMsg", "商品検索でエラーが発生しました。");
		}
		
		//リクエストスコープに格納
		req.setAttribute("productName", productName);
		
		//転送
		RequestDispatcher dispatcher = req.getRequestDispatcher(url);
		dispatcher.forward(req, resp);
	}
	
}
