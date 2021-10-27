package tw.billy.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class OrderDetailDao {

	private Connection conn;
	
	//要有this.conn = conn
	public OrderDetailDao(Connection conn) {
		this.conn = conn;
	}

	// 添加訂單明細
	public boolean InsertOrderDetail(OrderDetail model , int uid) {
		boolean result = false;
		try {

			String strsql = "INSERT INTO [dbo].[orderdetails]\r\n"
					+ " ([orderNo],[product_ID],[user_ID],[order_quantity],[date]) VALUES(?,?,?,?,?)";

			PreparedStatement pstmt = conn.prepareStatement(strsql);

			OrderDetail odDao = new OrderDetail();
			//OrderDao oDao = new OrderDao(conn);//連線
			OrderDao oDao = new OrderDao(conn);
     		pstmt.setString(1, oDao.getOrderNo(uid));
			pstmt.setInt(2, odDao.getPid());
			pstmt.setInt(3, odDao.getUid());
			pstmt.setInt(4, odDao.getOrderquentity());
			pstmt.setString(5, odDao.getDate());

			if (pstmt.executeUpdate() > 0) {
				result = true;
            }

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	


	

}
