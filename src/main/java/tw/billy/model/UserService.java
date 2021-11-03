package tw.billy.model;

import java.util.Date;
import java.util.List;

public class UserService {

	private UserDao userDao = new UserDao();
	
	//創建使用者
	public void createUserData(User user) {
		userDao.createUserData(user);
	}
	
	//檢查帳號有無重複
	public boolean cheakUsername(String username) {
		return userDao.cheakUsername(username);
	}
	
	//檢查email有無重複
	public boolean cheakEmail(String email) {
		return userDao.cheakEmail(email);
	}
	
	//使用者登入
	public User loginUser(String username, String password) {
		return userDao.loginUser(username, password);
	}
	
	//使用者資料
	public User memberData(String username, String password) {
		return userDao.memberData(username, password);
	}
	
	//更換密碼
	public void updatePassword(String username, String password) {
		userDao.updatePassword(username, password);
	}
	
	//更新生日及地址
	public void updateAddress(String username, Date birthday, String address) {
		userDao.updateAddress(username, birthday, address);
	}
	
	//更新行業
	public void updateJob(String username, String job) {
		userDao.updateJob(username, job);
	}
	
	//更新興趣
	public void updateHabit(String username, String habit) {
		userDao.updateHabit(username, habit);
	}
	
	//更新圖片路徑
	public void updateImage(String username, String image) {
		userDao.updateImage(username, image);
	}
	
	//確認餘額
	public User getUserAmount(Integer userId) {
		return userDao.getUserAmount(userId);
	}
	
	//購買商品扣款
	public void buyVideoUseAmount(Integer videoPrice, Integer userId) {
		userDao.buyVideoUseAmount(videoPrice, userId);
	}
}
