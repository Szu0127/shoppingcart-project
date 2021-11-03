<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="tw.billy.conn.DataBaseConnection"%>
<%@page import="tw.billy.model.*"%>
<%@page import="java.util.*"%>
<%@page import="javax.servlet.http.*"%>

<%
List<OrderDetail> orders = null;
//已經有session
//驗證登入
Integer userId = (Integer) session.getAttribute("userId");
if (userId != null) {

	OrderDao oDao = new OrderDao(DataBaseConnection.getConnection());
	String orderNo = oDao.getuserneworderNo(userId);//抓取當次orderN
	orders = oDao.getlistByOrderNo(orderNo);//抓取當次訂購商品
	request.setAttribute("orderNo", orderNo);
} else {
	response.sendRedirect("login.jsp");
}
// ArrayList<Cart> cart_list = (ArrayList) session.getAttribute("cart-list");

// if (cart_list != null) {
// 	ProductDao pDao = new ProductDao(DataBaseConnection.getConnection());
// 	int total = pDao.getTotalCartPrice(cart_list);
// 	request.setAttribute("cart_list", cart_list);
// 	request.setAttribute("total", total);
//
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<link rel="stylesheet" href="css/headerfooter.css">
<link rel="stylesheet" href="css/product.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<title>確認訂單</title>
</head>
<body>
	<%@include file="include/header.jsp"%>
	<!-- header section ends -->

	<section id="cart" style="margin: 0; padding: 20px;">
		<div style="height: 100px;"></div>
		<!--佔位-->
		<div class="row" id="main_container"
			style="margin: 10px 20px 50px 20px;">

			<div class="container">
				<div class="card-header my-3">
					<h1>訂單已完成 訂單編號 : ${orderNo}</h1>
				</div>
				<table class="table table-loght" style="text-align: center;">
					<thead class="thead-light">
						<tr>
							<th scope="col"><h2>商品名稱</h2></th>
							<th scope="col"><h2>商品</h2></th>
							<th scope="col"><h2>個數</h2></th>
							<th scope="col"><h2>金額</h2></th>
							<th scope="col"><h2>訂購日期</h2></th>
						</tr>
					</thead>
					<tbody>
						<%
						if (orders != null) {
							for (OrderDetail od : orders) {
						%>
						<tr>
							<td><h4><%=od.getName()%></h4></td>
							<td><img alt="cart-img"
								src="products_img/<%=od.getFilename()%>"></td>
							<td><h4><%=od.getQuantity()%></h4></td>
							<td><h4><%=od.getPrice()%></h4></td>
							<td><h4><%=od.getDate()%></h4></td>
						</tr>
						<%}}%>
				</table>

				<div class="d-flex py-3">
					<h3>總金額: ${ (total>0)?total:0 } 元</h3>
					<form>
						<h2>送貨資訊</h2>
						<table>
							<tr>
								<td><h3>姓名:</h3></td>
								<td><input type="text" name="username" value=""></td>
							</tr>
							<tr>
								<td><h3>電話:</h3></td>
								<td><input type="text" name="phone">
							</tr>
							<tr>
								<td><h3>7-11店到店:</h3></td>
								<td><input type="button" name="" value="請選擇門市">
							</tr>
						</table>
					</form>
					<!-- <a class="mx-3 btn btn-primary " href="cart-checkout">結帳</a> -->
					<a class="mx-3 btn btn-primary" href="userOrderDetail.jsp">查看所有訂單</a>
				</div>
			</div>
		</div>
	</section>
	<%@ include file="include/helpButton.html"%>
	<%@include file="include/footer.jsp"%>
</body>
</html>