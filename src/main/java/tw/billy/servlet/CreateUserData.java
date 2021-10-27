package tw.billy.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.billy.model.User;
import tw.billy.model.UserService;


@WebServlet("/CreateUserData")
public class CreateUserData extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public CreateUserData() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String name, gender, username, password, phone, email;
		
		name = request.getParameter("name");
		gender = request.getParameter("gender");
		username = request.getParameter("username");
		password = request.getParameter("password");
		phone = request.getParameter("phone");
		email = request.getParameter("email");
		
		User user = new User();
		user.setName(name);
		user.setGender(gender);
		user.setUsername(username);
		user.setPassword(password);
		user.setPhone(phone);
		user.setEmail(email);
		user.setAmount(0);
		
		UserService userService = new UserService();
		if(false == userService.cheakUsername(username)) {
			out.println("此帳號已被使用");
			response.setHeader("refresh","2;url=http://localhost:8080/KnowLife/register.jsp");
		} else if (false == userService.cheakEmail(email)) {
			out.println("此電子郵件已被使用");
			response.setHeader("refresh","2;url=http://localhost:8080/KnowLife/register.jsp");
		}
		
		if(true == userService.cheakUsername(username)) {
			if(true == userService.cheakEmail(email)) {
				userService.createUserData(user);
				
				out.println("註冊成功 正在將您導向回首頁...");
				response.setHeader("refresh","2;url=http://localhost:8080/KnowLife/index.jsp");
			}
		}
	}

}
