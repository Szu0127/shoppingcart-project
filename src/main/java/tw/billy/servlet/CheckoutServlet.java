package tw.billy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.billy.conn.DataBaseConnection;
import tw.billy.model.Cart;
import tw.billy.model.OrderDetail;
import tw.billy.model.OrderDetailDao;

@WebServlet("/cart-checkout")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (PrintWriter out = response.getWriter()) {
			// out.println("check out select");

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			
			// 取回 all cart product			
			ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			
			// user驗證
			//預設取得UserID
			HttpSession session = request.getSession(false);
			Integer userId = (Integer) session.getAttribute("userId");

			
			// check 驗證與cart list
			if (cart_list != null) {// && auth != null) {
				for (Cart c : cart_list) {
					// 加入資料庫
					OrderDetail order = new OrderDetail();
					order.setPid(order.getPid());
					// order.setUid(auth.getId());
					order.setOrderquentity(c.getQuantity() );
					order.setDate(formatter.format(date));

					OrderDetailDao odDao = new OrderDetailDao(DataBaseConnection.getConnection());

					boolean result = odDao.InsertOrderDetail(order, 0);
					
					if (!result)
						break;
				}

				cart_list.clear();
				response.sendRedirect("orderDetail.jsp");
			} else {
				//未登入
				// if(auth == null)response.sendRedirect("login.jsp");
				response.sendRedirect("cart.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
