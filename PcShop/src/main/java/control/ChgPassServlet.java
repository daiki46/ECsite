package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cart;
import model.Operation;

@WebServlet("/chg-pass-servlet")
public class ChgPassServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		//パラメータ取得
		req.setCharacterEncoding("UTF-8");
		
		String password = req.getParameter("password");
		String again = req.getParameter("again");
		
		//転送先設定
		String url = "select.jsp";
		
		if(password.equals(again)) {
			try {
				Cart cart = (Cart)req.getSession().getAttribute("cart");
				String userId = cart.getUserId();
				Operation op = new Operation();
				op.chgPassword(userId, password);
			} catch (Exception e) {
				//転送先設定
				url = "chg-pass.jsp";
				req.setAttribute("errorMsg", "パスワードの変更時にエラーが発生しました。");
			}
			
		} else {
			req.setAttribute("errorMsg", "入力値が一致しませんでした。");
			//転送先設定
			url = "chg-pass.jsp";
		}
		
		
		
			
		//転送
		RequestDispatcher dispatcher = req.getRequestDispatcher(url);
		dispatcher.forward(req, resp);		
	}
	
}
