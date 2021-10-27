package tw.billy.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.billy.model.User;
import tw.billy.model.UserService;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		UserService userService = new UserService();
		User user = userService.loginUser(username, password);

		if (user != null) {
			
			
			String sessionId = session.getId();
			session.setAttribute("userId", user.getId());
			session.setAttribute("name", user.getName());
			session.setAttribute("username", username);
			session.setAttribute("password", password);
			System.out.println(sessionId);

			
			response.sendRedirect("http://localhost:8080/KnowLife/index.jsp");
			
//			request.getRequestDispatcher("/MemberDataServlet").include(request, response);
		

		}
		if (user == null) {
			out.println("帳號密碼輸入錯誤 請重新確認");
			response.setHeader("refresh", "2;http://localhost:8080/KnowLife/login.jsp");
		}
	}

}
