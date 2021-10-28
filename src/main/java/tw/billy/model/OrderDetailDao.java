package tw.billy.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDao {

	private Connection conn;

	// 要有this.conn = conn
	public OrderDetailDao(Connection conn) {
		this.conn = conn;
	}

	// 添加訂單明細
	public boolean InsertOrderDetail(OrderDetail model, int uid) {
		boolean result = false;
		try {

			String strsql = "INSERT INTO [dbo].[orderdetails]\r\n"
					+ " ([orderNo],[product_ID],[user_ID],[order_quantity],[date]) VALUES(?,?,?,?,?)";

			PreparedStatement pstmt = conn.prepareStatement(strsql);

			
			// OrderDao oDao = new OrderDao(conn);//連線
			OrderDao oDao = new OrderDao(this.conn);
			pstmt.setString(1, oDao.getOrderNo(uid));
			pstmt.setInt(2, model.getPid());
			pstmt.setInt(3, model.getUid());
			pstmt.setInt(4, model.getOrderquentity());
			pstmt.setString(5, model.getDate());
			if (pstmt.executeUpdate() > 0) {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	// 用uid查詢所有訂單
	public List<OrderDetail> UserOrders(int uid) {
		List<OrderDetail> list = new ArrayList<>();
		try {
			// orderdetail_ID由大至小排序(DESC)=>由最近一筆訂單為優先做排序
			// ORDER BY ASC 由小到大
			String sqlstr = "SELECT * FROM orderdetails WHERE user_ID = ? ORDER BY orderdetails.orderdetail_ID DESC";

			PreparedStatement pstmt = conn.prepareStatement(sqlstr);
			pstmt.setInt(1, uid);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				OrderDetail odBean = new OrderDetail();
				ProductDao pDao = new ProductDao(this.conn);

				int pid = rs.getInt("product_ID");
				Product product = pDao.getSingleProduct(pid);
				odBean.setOrderdetailid(rs.getInt("orderdetail_ID"));
				odBean.setOrderNo(rs.getString("orderNo"));
				odBean.setName(product.getName());// 商品名稱
				odBean.setCategory(product.getCategory());// 商品類別
				// odBean.set(product.getPrice()*rs.getInt("order_quentity"));//訂單總金額
				odBean.setOrderquentity(rs.getInt("order_quantity"));// 訂單數量
				odBean.setDate(rs.getString("date"));
				list.add(odBean);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
