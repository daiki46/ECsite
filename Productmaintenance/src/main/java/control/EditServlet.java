package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.ProductDAO;

@WebServlet("/edit-servlet")
public class EditServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//転送先設定
		String url = "complete.jsp";
		
		//リクエストスコープから取得
		req.setCharacterEncoding("UTF-8");
		String productId = (String)req.getParameter("productId");
		String productName = (String) req.getParameter("productName");
		int price = Integer.parseInt((String) req.getParameter("price"));
		
		//更新処理
		ProductDAO productDao = new ProductDAO();
		try {
			int result = productDao.update(productId, productName, price);
			if(result < 1) {
				url = "edit.jsp";
				req.setAttribute("errorMsg", "更新に失敗しました。");
				req.setAttribute("productId", productId);
				req.setAttribute("productName", productName);
				req.setAttribute("price", String.valueOf(price));
			}
		} catch (SQLException e) {
			req.setAttribute("errorMsg", "更新処理でエラーが発生しました");
			req.setAttribute("productId", productId);
			req.setAttribute("productName", productName);
			req.setAttribute("price", String.valueOf(price));
			url = "edit.jsp";
		}
		
		//リクエストスコープに格納
		req.setAttribute("msg", "商品情報の更新");
		
		//転送
		RequestDispatcher dispatcher = req.getRequestDispatcher(url);
		dispatcher.forward(req, resp);
	}

}
