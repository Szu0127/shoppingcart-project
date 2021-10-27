package tw.billy.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
	
	private Connection conn;
	
	public OrderDao(Connection conn) {
		this.conn = conn;
	}
	
	//生成訂單編號orderNo
	 public String getOrderNo(int userid) {
	        String orderNo = null;
	        String sqlStr = "select count(orderdetail_ID) from orderdetails";
	        try {
	            
	            Statement stmt = conn.createStatement();
	            ResultSet rs = stmt.executeQuery(sqlStr);
	            if (rs.next()) {
	                orderNo = "KLH-" + userid + "-" + rs.getInt(1);
	            } else {
	                orderNo = "KLH" + userid + "-0";
	            }
	            rs.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return orderNo;
	    }
	
	
    //添加訂單
	public boolean addOrder(String orderNo,int uid) {
		boolean result = false;
		
		try{
			String query = "INSERT INTO [dbo].[order]\r\n"
					+ "([orderNo],[total_price],[order_date]) VALUES (?,?,?,?)";
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			Order or = new Order();
			pstmt.setString(1, orderNo);
			pstmt.setInt(2, or.getUid());//[user_ID]
			pstmt.setInt(3, or.getTotalprice() );
			pstmt.setString(4, or.getDate());
			if (pstmt.executeUpdate() > 0) {
				result = true;
            }
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	// 刪除訂單
    public boolean deleteOrderById(int orderNo) {
        boolean flag = false;
        String sqlStr = "delete from order where orderNo = '" + orderNo + "'";
        try {
        	PreparedStatement pstmt = conn.prepareStatement(sqlStr);
            flag = pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
	
	// 根據用戶ID查找訂單列表
    public List<Order> getOrderInfo(int userid) {
        List<Order> orders = new ArrayList<Order>();
        
        String sqlStr = "select * from Order where user_ID = "+userid;
        try {            
        	PreparedStatement pstmt = conn.prepareStatement(sqlStr);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {     
                 Order order = new Order();
                 order.setOrderNo(rs.getInt("orderNo"));
                 order.setTotalprice(rs.getInt("total_price"));
                
                 // 訂單表中的 user
                 User user = new User();
                 user.setId(rs.getInt("user_ID"));
 //                order.setUid(user.getId());

                 orders.add(order);
             }
             rs.close();
         } catch (SQLException e) {
             e.printStackTrace();
         }
         return orders;
     }

    // 獲得所有訂單
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<Order>();
        //  join
        String sqlStr = "SELECT [orderNO],[user_ID],[total_price],[order_date]\r\n"
        		+ "  FROM [dbo].[order],[dbo].[user] "
        		+ "where [dbo].[order].[user_ID] = [dbo].[user].[user_ID] ";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlStr);
            
            while (rs.next()) {
                
                Order order = new Order();                
                order.setOrderNo(rs.getInt("order_ID"));
                order.setTotalprice(rs.getInt("total_price"));

                User user = new User();
                user.setId(rs.getInt("user_ID"));
                user.setName(rs.getString("user_name"));
//                order.setUid(user);
                
                orders.add(order);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    
     
    //根據OrderNo取得UserID
    public Integer getUserIdByOrderId(int orderNo) {
        // 顯示最近一次訂單
    	String sqlStr = "select user_ID from order  where orderNo = "+ orderNo ;
        int userid = 0;
        try {
  
        	PreparedStatement pstmt = conn.prepareStatement(sqlStr);
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next()) {
                userid = rs.getInt(1);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userid;
    }

}
