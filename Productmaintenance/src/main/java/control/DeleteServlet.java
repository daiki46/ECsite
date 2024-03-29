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

import model.Product;
import model.dao.ProductDAO;

@WebServlet("/delete-servlet")
public class DeleteServlet extends HttpServlet{

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//転送先設定
		String url = "complete.jsp";
		
		ProductDAO productDao = new ProductDAO();
		
		//セッションスコープ取得
		HttpSession session = req.getSession();
		List<Product> list = (List<Product>)session.getAttribute("result");
		
		//リクエストスコープから取得
		req.setCharacterEncoding("UTF-8");
		int idx = Integer.parseInt(req.getParameter("idx"));
		
		String productId = list.get(idx).getProductId();
		try {
			int result = productDao.delete(productId);
			if(result < 1) {
				req.setAttribute("errorMsg", "削除に失敗しました。");
				url = "result.jsp";
			}
		} catch (SQLException e) {
			req.setAttribute("errorMsg", "削除処理でエラーが発生しました。");
			url = "result.jsp";
		}
		//リクエストスコープに格納
		req.setAttribute("msg", "削除完了");
				
		//転送
		RequestDispatcher dispatcher = req.getRequestDispatcher(url);
		dispatcher.forward(req, resp);
	}

}
