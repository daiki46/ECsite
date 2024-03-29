package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;
import model.dao.ProductDAO;

@WebServlet("/regist-servlet")
public class RegistServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//転送先設定
		String url = "complete.jsp";
		
		//リクエストパラメータの取得
		req.setCharacterEncoding("UTF-8");
		String productId = req.getParameter("productId");
		String productName = req.getParameter("productName");
		int price = Integer.parseInt(req.getParameter("price"));
		
		Product product = new Product(productId, productName, price);
		ProductDAO productDao = new ProductDAO();
		try {
			int cnt = productDao.insert(product);
			if(cnt <= 0) {
				url = "regist.jsp";
				req.setAttribute("errorMsg", "登録できませんでした。");
			}
		} catch (SQLException e) {
			req.setAttribute("errorMsg", "登録処理中にエラーが発生しました。");
			url = "regist.jsp";
		}
		
		//リクエストスコープに格納
		req.setAttribute("msg", "登録");
		
		//転送
		RequestDispatcher dispatcher = req.getRequestDispatcher(url);
		dispatcher.forward(req, resp);
	}
	
}
