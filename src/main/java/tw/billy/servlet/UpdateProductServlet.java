package tw.billy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.billy.conn.DataBaseConnection;
import tw.billy.model.Product;
import tw.billy.model.ProductDao;


@WebServlet("/update-product")
public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        try {
			ProductDao pDao = new ProductDao(DataBaseConnection.getConnection());
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        int pid = Integer.parseInt(filter(request.getParameter("pid")));
        String pname = filter(request.getParameter("product_name"));
        int goodsPrice = Integer.parseInt(filter(request.getParameter("Price")));
      //  int leave_amount = Integer.parseInt(filter(req.getParameter("leave_amount")));
        Product pd = new Product();
        pd.setPid(pid);
        pd.setName(pname);
        pd.setPrice(goodsPrice);
 //       oldGoods.setLeave_amount(leave_amount);
 //       if(pDao.updateProduct())) {
            out.flush();
            out.println("<script>");
            out.println("alert('修改成功');");
            out.println("window.location.href='adManage.jsp';");
            out.println("</script>");
            out.close();
//        } else {
//        	out.flush();
//        	out.println("<script>");
//        	out.println("alert('修改成功');");
//        	out.println("window.location.href='adManage.jsp';");
//        	out.println("</script>");
//        	out.close();
//        }
        out.close();
    }
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
	//處理亂碼
    public String filter(String date) throws UnsupportedEncodingException {
        return URLDecoder.decode(date, "UTF-8");
    }
}
