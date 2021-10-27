package tw.billy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import tw.billy.model.UserDao;
import tw.knowlife.model.VideoBean;


@WebServlet("/TestVideoServlet")
public class TestVideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "http://localhost:63317");
		response.addHeader("Access-Control-Allow-Methods", "*");
		response.addHeader("Access-Control-Allow-Headers", "Accept,Authorization,DNT,Content-Type,Referer,User-Agent");
		response.addHeader("Access-Control-Allow-Credentials","true"); // 允許攜帶驗證資訊
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		UserDao userDao = new UserDao();
		List<VideoBean> list = userDao.findAllViedo();
		
		Gson gson = new Gson();
		String listjson = gson.toJson(list);
		out.println(listjson);
		
	}

}
