package tw.billy.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tw.knowlife.model.VideoBean;



public class UserDao {
	
	static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=webDB";
	static final String USER = "manager";
	static final String PASSWORD = "mssql";
	
	//創建使用者
	public void createUserData(User user) {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		String sql = "insert into user_data (user_name, user_gender, user_username, user_password, user_phone, user_email, user_amount) "
				+ "values (?,?,?,?,?,?,?)";
		
		try(Connection conn = DriverManager.getConnection(URL,USER,PASSWORD)){
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getGender());
			pstmt.setString(3, user.getUsername());
			pstmt.setString(4, user.getPassword());
			pstmt.setString(5, user.getPhone());
			pstmt.setString(6, user.getEmail());
			pstmt.setInt(7, 0);
			int count = pstmt.executeUpdate();
			System.out.println(count + "筆資料已被新增");
			
			
		} catch (SQLException e) {
			System.out.println("錯誤 資料未被新增");
			e.printStackTrace();
		}
	}
	
	//檢查資料庫有無此組帳號
	public boolean cheakUsername(String username) {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		String sql = "select * from user_data where user_username=?";
		
		
		try(Connection conn = DriverManager.getConnection(URL,USER,PASSWORD)){
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			
			if(true == rs.next()) {
				System.out.println("資料庫已有此組帳密");
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("可使用此組帳號");
		return true;
	}
	
	//檢查資料庫有無此組email
	public boolean cheakEmail(String email) {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		String sql = "select * from user_data where user_email=?";
		
		
		try(Connection conn = DriverManager.getConnection(URL,USER,PASSWORD)){
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			
			if(true == rs.next()) {
				System.out.println("資料庫已有此組電子信箱");
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("可使用此組電子信箱");
		return true;
	}
	
	//使用者登入驗證
	public User loginUser(String username, String password) {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		String sql = "select * from user_data where user_username=? and user_password=?";
		User user = null;
		
		try(Connection conn = DriverManager.getConnection(URL,USER,PASSWORD)){
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			
			if(true == rs.next()) {
				user = new User();
				user.setId(rs.getInt("user_ID"));
				user.setName(rs.getString("user_name"));
				user.setUsername(username);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	//讀取使用者資料
	public User memberData(String username, String password) {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		String sql = "select * from user_data where user_username=? and user_password=?";
		User user = null;
		
		try(Connection conn = DriverManager.getConnection(URL,USER,PASSWORD)){
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			
			if(true == rs.next()) {
				user = new User();
				user.setId(rs.getInt("user_ID"));
				user.setName(rs.getString("user_name"));
				user.setGender(rs.getString("user_gender"));
				user.setUsername(rs.getString("user_username"));
				user.setPassword(rs.getString("user_password"));
				user.setBirthday(rs.getDate("user_birthday"));
				user.setPhone(rs.getString("user_phone"));
				user.setEmail(rs.getString("user_email"));
				user.setAddress(rs.getString("user_address"));
				user.setHabit(rs.getString("user_habit"));
				user.setJob(rs.getString("user_job"));
				user.setAmount(rs.getInt("user_amount"));
				user.setImage(rs.getString("user_image"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	//更新密碼
	public void updatePassword(String username, String password) {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		String sql = "update user_data set user_password=? where user_username=?";
		
		try(Connection conn = DriverManager.getConnection(URL,USER,PASSWORD)){
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, password);
			pstmt.setString(2, username);
			int count = pstmt.executeUpdate();
			System.out.println("密碼已被更新");
			
		} catch (SQLException e) {
			System.out.println("發生錯誤，密碼未更新");
			e.printStackTrace();
		}
	}
	
	//更新生日及地址
	public void updateAddress(String username, Date birthday, String address) {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		String sql = "update user_data set user_birthday=?, user_address=? where user_username=?";
		
		try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)){
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, (java.sql.Date) birthday);
			pstmt.setString(2, address);
			pstmt.setString(3, username);
			pstmt.executeUpdate();
			System.out.println("地址與生日更新成功");
			
		} catch (SQLException e) {
			System.out.println("發生錯誤 地址與生日未更新");
			e.printStackTrace();
		}
	}
	
	//更新行業
	public void updateJob(String username, String job) {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		String sql = "update user_data set user_job=? where user_username=?";
		
		try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)){
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, job);
			pstmt.setString(2, username);
			pstmt.executeUpdate();
			System.out.println("行業更新成功");
			
		} catch (SQLException e) {
			System.out.println("發生錯誤 行業未更新");
			e.printStackTrace();
		}
	}
	
	//更新興趣
	public void updateHabit(String username, String habit) {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		String sql = "update user_data set user_habit=? where user_username=?";
		
		try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)){
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, habit);
			pstmt.setString(2, username);
			pstmt.executeUpdate();
			System.out.println("興趣更新成功");
			
		} catch (SQLException e) {
			System.out.println("發生錯誤 興趣未更新");
			e.printStackTrace();
		}
	}	
	
	//更新圖片路徑
	public void updateImage(String username, String image) {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		String sql = "update user_data set user_image=? where user_username=?";
		
		try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)){
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, image);
			pstmt.setString(2, username);
			pstmt.executeUpdate();
			System.out.println("照片更新成功");
			
			
		} catch (SQLException e) {
			System.out.println("發生錯誤 照片更新失敗");
			e.printStackTrace();
		}
		
	}
	
	//查詢所有影片
	public List<VideoBean> findAllViedo(){
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		String sql = "select video_ID,video_name,video_pic_src from video_data";
		List<VideoBean> list = new ArrayList<VideoBean>();
		
		try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)){
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				VideoBean videoBean = new VideoBean();
				videoBean.setId(rs.getInt("video_ID"));
				videoBean.setVideoName(rs.getString("video_name"));
				videoBean.setImgFileName(rs.getString("video_pic_src"));
				list.add(videoBean);
			}
			System.out.println("影片成功查詢");
			
		} catch (SQLException e) {
			System.out.println("影片查詢失敗");
			e.printStackTrace();
		}
		return list;
	}
	
	//確認餘額
	public User getUserAmount(Integer userId) {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		User user = null;
		String sql = "select user_amount from user_data where user_ID = ?";
		try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)){
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next() == true) {
				user = new User();
				user.setAmount(rs.getInt("user_amount"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	//購買影片後跳扣款
	public void buyVideoUseAmount(Integer videoPrice, Integer userId) {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		String sql = "update user_data set user_amount = user_amount - ? where user_ID = ?";
		try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)){
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, videoPrice);
			pstmt.setInt(2, userId);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
