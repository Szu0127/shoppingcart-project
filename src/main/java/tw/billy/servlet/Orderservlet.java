package tw.billy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
import tw.billy.model.Order;
import tw.billy.model.OrderDao;
import tw.billy.model.OrderDetail;
import tw.billy.model.OrderDetailDao;

@WebServlet("/buy-now")
public class Orderservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");

		try (PrintWriter out = response.getWriter()) {

			// 取得日期
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();

			// 預設取得UserID
			HttpSession session = request.getSession(false);
			Integer userId = (Integer) session.getAttribute("userId");

			
			if (userId != null) {
//連線
				OrderDetailDao odDao = new OrderDetailDao(DataBaseConnection.getConnection());
				OrderDao order = new OrderDao(DataBaseConnection.getConnection());
				OrderDetail orderModel = new OrderDetail();
				Order orderB = new Order();
				String orderNo = order.getOrderNo(userId);

				String productId = request.getParameter("id");// 取得的pid字串 //.jsp
				int pQuentity = Integer.parseInt(request.getParameter("quantity"));
				int total = Integer.parseInt(request.getParameter("total"));

				if (pQuentity <= 0) {
					pQuentity = 1;
				}
				// InsertOrderDetail
				orderModel.setPid(Integer.parseInt(productId));// 轉回int
				orderModel.setUid(userId);
				orderModel.setOrderquentity(pQuentity);
				orderModel.setDate(formatter.format(date));
				// addOrder
				orderB.setOrderNo(userId);
				orderB.setUid(userId);
				orderB.setTotalprice(total);
				orderB.setDate(formatter.format(date));

				// insert into DB
				boolean result1 = odDao.InsertOrderDetail(orderModel, userId);
				boolean result2 = order.addOrder(orderNo, pQuentity);

				if (result1) {
					if (result2) {
						// remove cartlist
						ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
						if (cart_list != null) {
							for (Cart c : cart_list) {
								if (c.getPid() == Integer.parseInt(productId)) {
									cart_list.remove(cart_list.indexOf(c));
									break;
								}
							}
						}
						// remove cartlist over
						out.flush();
						out.println("<script>");
						out.println("alert('信息提交成功');");
						out.println("window.location.href='orderDetail.jsp';");
						out.println("</script>");
						out.close();
					}
				} else {
					out.flush();
					out.println("<script>");
					out.println("alert('提交失败，请稍后再试');");
					out.println("window.location.href='GoodsServlet?action=showAllGoods';");
					out.println("</script>");
					out.close();
				}
				out.close();

			} else {
				response.sendRedirect("login.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
