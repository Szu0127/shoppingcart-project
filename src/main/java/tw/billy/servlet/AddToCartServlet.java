package tw.billy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.billy.model.Cart;


@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		try(PrintWriter out = response.getWriter()){
			ArrayList<Cart> cartList = new ArrayList<>();
			//shopping-cart/add-to-cart?id=1
			
			int id = Integer.parseInt(request.getParameter("id"));
			Cart cm = new Cart();
			cm.setPid(id);
			cm.setQuantity(1);//設定數量			
			
			
			//連線
			HttpSession session = request.getSession();
			ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
			
			if(cart_list == null) {//當購物車都沒有物品時
				cartList.add(cm);//加入購物車
				session.setAttribute("cart-list", cartList);//設屬性為cart-list
				//out.println("session created and added the list");
				//購物車創建並添加進列表
				//We are storing them in our session.
				response.sendRedirect("real_Products.jsp");
			}else {//當cart已經有商品時加入List中
				//out.println("cart add exist");//已添加進購物車
				cartList = cart_list;
//				for(Cart c:cartList) {
//				out.print(c.getId());}加進購物車回傳ID=1

				boolean exist = false;//加設不存在
				
				//cartList.contains(cm);//包含
				
				for(Cart c:cart_list) {
					if(c.getPid() == id) {
						exist = true;
						//out.println("product exist");//商品存在
						out.println("<h3 style='color:crimson; text-align:center'>"
								  + "已加入購物車"//已添加入購物車(按一次加入購物車(存放在購物車的list)，不會跳轉到Cart頁面)
							      + "<a href='cart.jsp'>進入購物車</a>"//按第二次顯示已存在在購物車，引道進入購物車Cart頁面
							      + "</h3>");
					}
				}
				
					if(!exist) {
						cartList.add(cm);
						//out.println("product added");//已被加入(已存在的購物車內再添加入商品)
						response.sendRedirect("real_Products.jsp");
				}
			}
//			for(Cart c:cart_list) {
//				out.println(c.getId());
//			}
			
		}
	}
}

