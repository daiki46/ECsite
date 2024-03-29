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

import model.Product;

@WebServlet("/edit-screen-servlet")
public class EditScreenServlet extends HttpServlet{

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//転送先設定
		String url = "edit.jsp";
		
		//セッションスコープ取得
		HttpSession session = req.getSession();
		List<Product> list = (List<Product>)session.getAttribute("result");
		int idx = Integer.parseInt(req.getParameter("idx"));
		String productId = list.get(idx).getProductId();
		String productName = list.get(idx).getProductName();
		int price = list.get(idx).getPrice();
		
		//リクエストスコープに格納
		req.setAttribute("productId", productId);
		req.setAttribute("productName", productName);
		req.setAttribute("price", price);
		//転送
		RequestDispatcher dispatcher = req.getRequestDispatcher(url);
		dispatcher.forward(req, resp);
	}

}
