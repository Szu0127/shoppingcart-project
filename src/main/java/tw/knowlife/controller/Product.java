package tw.knowlife.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
@MultipartConfig(location = "D:/MyProject/website/KLweb/images")
@WebServlet("/product/*")
public class Product extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Product() {
		super();
		// 檢查上傳檔案之儲存目錄是否存在?若否,則立即建立
		File dir = new File("D:/MyProject/website/KLweb/images");
		if (!dir.exists())
			dir.mkdir();
	}
	// 查詢
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();

		String responseText = "{ \"query-status\": \"fail\" }";
		String productId = request.getParameter("productId");
		if (productId != null) {
			// 將empid的字串內容去除'/'字元
//			productId = productId.replace("/", "");
			if (productId.matches("")) {// 當empid為 空字串
				responseText = getAllProduct();
			} else if (productId.matches("\\d+")) {// 當empid為一個數目，例如:"3"
				responseText = getProductById(productId);
			}
		}
		out.print(responseText);
		
//		request.setAttribute("responseText", responseText);
//		request.getRequestDispatcher("productUploadPage.jsp").forward(request, response);
	}

	// 新增
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		String username = request.getParameter("username");
		username = username == null ? "" : username;
		String greeting = String.format("%s您好<br/>", username);
		request.setAttribute("greeting", greeting);
		String imgFileName = null;
		ArrayList<HashMap<String, String>> messages = new ArrayList<HashMap<String, String>>();
		for (Part part : request.getParts()) { // request.getParts() 傳回Collection<Part>物件
			if ("uploadFile".equals(part.getName())) {
				// 取得上傳的檔案名稱
				imgFileName = part.getSubmittedFileName();
//				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
//				String extension = FilenameUtils.getExtension(filename);
//				filename = "images/thumbnail" + dtf.format(LocalDateTime.now())+"."+extension;
				// 取得上傳的檔案大小
				long filesize = part.getSize();
				HashMap<String, String> message = new HashMap<String, String>();
				if (imgFileName.equals("")) {// 當用戶端未上傳任何檔案時
					message.put("text", String.format("您上傳無效的檔案，請重新執行...", username));
					message.put("imgUrl", "");
				} else {
					// 將上傳檔案存檔
					part.write(imgFileName);
					message.put("text", String.format("檔案上傳成功(檔名:%s 大小:%,dbytes)...",imgFileName, filesize));
					message.put("imgUrl", String.format("videoImgUpload_uploadedImg?filename=%s", imgFileName));
				}
				messages.add(message);
			}
		
		}
		PrintWriter out = response.getWriter();

		String productName = request.getParameter("productName");
		String productClass = request.getParameter("productClass");
		String producPrice = request.getParameter("producPrice");

		String responseText = insertProduct(productName, productClass, producPrice, imgFileName);
		out.print(responseText);
		request.setAttribute("messages", messages);
		request.getRequestDispatcher("/WEB-INF/views/uploadResponse.jsp").forward(request, response);
	}

//	刪除
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/jason;charset=utf-8");
		response.setHeader("access-control-allow-origin", "*");
		PrintWriter out = response.getWriter();

		String responseText = "{\"delete-status\": \"fail\"}";
		String empid = request.getPathInfo();
		if (empid != null) {
			empid = empid.replace("/", "");
			if (empid.matches("\\d+")) {
				responseText = deleteEmp(empid);
			}
		}
		out.println(responseText);
	}

	// 修改
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();

		String responseText = "{ \"update-status\": \"fail\" }";
		String empid = request.getPathInfo();
		// 1. 假設用戶端發出請求之網址為 http://localhost:8080/api/employees/3
		// 則 request.getContextPath()=>/api request.getServletPath()=>/employees
		// requets.getPathInfo()=>/3
		// 2. 假設用戶端發出請求之網址為 http://localhost:8080/api/employees/
		// 則 request.getContextPath()=>/api request.getServletPath()=>/employees
		// requets.getPathInfo()=>/
		// 3. 假設用戶端發出請求之網址為 http://localhost:8080/api/employees
		// 則 request.getContextPath()=>/api request.getServletPath()=>/employees
		// requets.getPathInfo()=>null
		if (empid != null) {
			// 將empid的字串內容去除'/'字元
			empid = empid.replace("/", "");
			if (empid.matches("\\d+")) {// 當empid為一個數目，例如:"3"
				String employeeid = empid;
				String firstname = request.getParameter("firstname");
				String lastname = request.getParameter("lastname");
				String title = request.getParameter("title");
				String birthdate = request.getParameter("birthdate");
				String hiredate = request.getParameter("hiredate");
				String address = request.getParameter("address");
				String city = request.getParameter("city");
				responseText = updateEmp(employeeid, firstname, lastname, title, birthdate, hiredate, address, city);
			}
		}
		out.print(responseText);
	}

	// Google Chrome在用戶端發送具特殊HTTP方法(即GET、POST、
	// HEAD以外的方法，例如PUT方法)的跨來源的請求時，會進行下列的特殊處理：
	// 1. 先送出一個HTTP方法為OPTIONS的請求(即預檢請求/Preflight Requset)給這個跨來源的伺服端。
	// 2.
	// 等候並接收到伺服端的回應訊息後，檢視其內的「Access-Control-Allow-Methods」的標頭值，以確定這個跨來源的伺服端是否支援這個特殊的HTTP方法？若是，方正式送出該請求。
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
	}

	private String getAllProduct() {
		String returnText = "null";
		Connection conn = getConnection();
		if (conn != null) {
			try {
				String sql = "select productId 產品編號,productName 產品名字,productClass 產品分類,productPrice 產品售價,productImg 產品圖檔名 from product_data";
//				String sql = "select*from video_data";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				returnText = resultSetToJsonArray(rs);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return returnText;
	}

	private String getProductById(String pid) {
		String returnText = "null";
		Connection conn = getConnection();
		if (conn != null) {
			try {
//				String sql = "select [video_ID],[video_name],[video_pic_src],[video_vsrc] from video_data where video_ID = ?";
				String sql = "select productId 產品編號,productName 產品名字,productClass 產品分類,productPrice 產品售價,productImg 產品圖檔名 from product_data where productId = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, pid);
				ResultSet rs = pstmt.executeQuery();
				returnText = resultSetToJsonObject(rs);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return returnText;
	}

	private String insertProduct(String productName, String productClass, String productPrice, String productImg) {
		String returnText = "{ \"insert-status\": \"fail\" }";
		Connection conn = getConnection();
		if (conn != null) {
			try {
				// 啟用Employees資料表之識別欄位手動新增功能
				String sql = "insert into product_data(productName,productClass,productPrice,productImg) "
						+ "values(?,?,?,?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, productName);
				pstmt.setString(2, productClass);
				pstmt.setString(3, productPrice);
				pstmt.setString(4, productImg);
				pstmt.executeUpdate();
				returnText = "{ \"insert-status\": \"success\" }";
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return returnText;
	}

	private String updateEmp(String employeeid, String firstname, String lastname, String title, String birthdate,
			String hiredate, String address, String city) {
		String returnText = "{ \"update-status\": \"fail\" }";
		Connection conn = getConnection();
		if (conn != null) {
			try {
				String sql = "update employees set firstname=?,lastname=?,title=?,birthdate=?,hiredate=?,address=?,city=? "
						+ "where employeeid=?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, firstname);
				pstmt.setString(2, lastname);
				pstmt.setString(3, title);
				pstmt.setString(4, birthdate);
				pstmt.setString(5, hiredate);
				pstmt.setString(6, address);
				pstmt.setString(7, city);
				pstmt.setString(8, employeeid);
				pstmt.executeUpdate();
				returnText = "{ \"update-status\": \"success\" }";
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return returnText;
	}

//	刪除沒寫完
	private String deleteEmp(String employeeid) {
		String returnText = "{\"delete-status\": \"fail\"}";
		Connection conn = getConnection();
		if (conn != null) {
			try {
				String sql = "delete from employees where employeeid=?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, employeeid);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return employeeid;
	}

	private String resultSetToJsonObject(ResultSet rs) {
		String returnText = "null";
		try {
			ResultSetMetaData rsmd = rs.getMetaData();

			if (rs.next()) {
				JSONObject jsonObject = new JSONObject();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					String colName = rsmd.getColumnName(i);
					String colValue = null;
					int colType = rsmd.getColumnType(i);
					switch (colType) {
					case Types.TIMESTAMP:
					case Types.DATE:
						colValue = String.format("%tY/%<tm/%<td", rs.getDate(i));
						break;
					default:
						colValue = rs.getString(i);
					}

					jsonObject.put(colName, colValue);
				}
				returnText = jsonObject.toString();
			}

		} catch (SQLException | JSONException e) {
			e.printStackTrace();
		}

		return returnText;
	}

	private String resultSetToJsonArray(ResultSet rs) {
		String returnText = "null";
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			JSONArray jsonArray = new JSONArray();

			while (rs.next()) {
				JSONObject jsonObject = new JSONObject();

				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					String colName = rsmd.getColumnName(i);
					String colValue = null;
					int colType = rsmd.getColumnType(i);
					switch (colType) {
					case Types.TIMESTAMP:
					case Types.DATE:
						colValue = String.format("%tY/%<tm/%<td", rs.getDate(i));
						break;
					default:
						colValue = rs.getString(i);
					}

					jsonObject.put(colName, colValue);
				}

				jsonArray.put(jsonObject);
			}

			returnText = jsonArray.toString();

		} catch (SQLException | JSONException e) {
			e.printStackTrace();
		}

		return returnText;
	}

	private Connection getConnection() {
		Connection conn = null;
		try {
			DataSource ds = getDataSource();
			if (ds != null)
				conn = ds.getConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	private DataSource getDataSource() {
		DataSource ds = null;
		try {
			InitialContext ic = new InitialContext();
			Context context = (Context) ic.lookup("java:comp/env");
			ds = (DataSource) context.lookup("jdbc/webDB");// northwind
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return ds;
	}
}
