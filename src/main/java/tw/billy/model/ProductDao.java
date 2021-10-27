package tw.billy.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public ProductDao(Connection conn) {
		this.conn = conn;
	}

	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<Product>();

		String sql = "Select * From products;";
		try {
			pstmt = this.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Product ps = new Product();
				ps.setPid(rs.getInt("product_ID"));
				ps.setName(rs.getString("product_name"));// 商品名
				ps.setCategory(rs.getString("category"));
				ps.setPrice(rs.getInt("product_price"));
				ps.setFilename(rs.getString("filename"));// 檔名

				products.add(ps);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}

	public List<Cart> getCartProducts(ArrayList<Cart> cartList) {
		List<Cart> products = new ArrayList<Cart>();

		try {
			if (cartList.size() > 0) {
				for (Cart item : cartList) {
					String sql1 = "select * from products where product_ID = ?";
					pstmt = this.conn.prepareStatement(sql1);
					pstmt.setInt(1, item.getPid());
					rs = pstmt.executeQuery();
					
					while (rs.next()) {
						Cart row = new Cart();

						row.setPid(rs.getInt("product_ID"));
						row.setName(rs.getString("product_name"));
						row.setFilename(rs.getString("filename"));// 圖片
						row.setCategory(rs.getString("category"));
						row.setPrice(rs.getInt("product_price") * item.getQuantity());// 價錢乘上個數
						row.setQuantity(item.getQuantity());
						products.add(row);
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return products;
	}

	//計算購物車總金額
	public Integer getTotalCartPrice(ArrayList<Cart> cartList) {
		int sum = 0;

		try {
			if (cartList.size() > 0) {
				for (Cart item : cartList) {
					String sql2 = "select product_price from products where product_ID =?";
					pstmt = this.conn.prepareStatement(sql2);
					pstmt.setInt(1, item.getPid());
					rs = pstmt.executeQuery();

					while (rs.next()) {
						sum += rs.getInt("product_price") * item.getQuantity();// 總和=總和+(金額*數量)
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sum;
	}
	
	public Product queryProductInfo(int pid) {

       Product p = new Product();
        
        String sqlStr = " select * from products where id=" + pid;
        try {
            
            Statement stmt = conn.createStatement();           
            ResultSet rs = stmt.executeQuery(sqlStr);
      
            while (rs.next()) {
                p.setName(rs.getString("product_name"));
                p.setCategory(rs.getString("category"));
                p.setPrice(rs.getInt("product_price"));
                p.setQuantity(rs.getInt("product_quantity"));
                p.setFilename(rs.getString("filename"));

            }
            rs.close();
  
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p ;
    }

}
