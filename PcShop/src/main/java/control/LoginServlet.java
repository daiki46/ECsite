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

@WebServlet("/login-servlet")
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	/*
	 * @see HttpServlet"doPsot(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		//ログイン処理+転送先設定
		String url = "select.jsp";
		try {
			HttpSession session = request.getSession();
			Operation op = new Operation();
			boolean result = op.loginProc(userId, password, session);
			
			if(!result) {
				request.setAttribute("errorMsg", "ユーザID または パスワード に誤りがあります。");
				url = "login.jsp";
			}else if(password.equals("")) {
				request.setAttribute("errorMsg", "パスワードを設定しましょう。");
				url = "chg-pass.jsp";
			}
		}catch(Exception e){
			request.setAttribute("errorMsg", "ログイン時にエラーが発生しました。");
			url = "login.jsp";
		}
		
		
		//転送
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

}
