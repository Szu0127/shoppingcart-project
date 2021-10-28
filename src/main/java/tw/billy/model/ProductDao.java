package tw.billy.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public ProductDao(Connection conn) {
		this.conn = conn;
	}

	// 取得所有商品
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

	// 取得單一商品(AddToCart)
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

	// 取得單一商品
	public Product getSingleProduct(int pid) {
		Product p = null;
		String sqlStr = " select * from products where product_ID=?";
		try {

			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			pstmt.setInt(1, pid);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				p = new Product();
				p.setPid(rs.getInt("product_ID"));
				p.setName(rs.getString("product_name"));
				p.setCategory(rs.getString("category"));
				p.setPrice(rs.getInt("product_price"));
				p.setFilename(rs.getString("filename"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

	// 計算購物車總金額
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
}
