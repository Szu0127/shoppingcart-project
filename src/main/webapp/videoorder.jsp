<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="tw.billy.conn.DataBaseConnection"%>
<%@page import="tw.billy.model.*"%>
<%@page import="java.util.*"%>

<%
ArrayList<Cart> cart_list = (ArrayList) session.getAttribute("cart-list");
List<Cart> cartProduct = null;
if (cart_list != null) {
	ProductDao pDao = new ProductDao(DataBaseConnection.getConnection());
	cartProduct = pDao.getCartProducts(cart_list);
	int total = pDao.getTotalCartPrice(cart_list);
	request.setAttribute("cart_list", cart_list);
	request.setAttribute("total", total);
}
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
<title>Cart Page</title>
</head>
<body>
	<%@include file="include/header.jsp"%>

	<!-- header section ends -->

	<section id="cart" style="margin: 0; padding: 20px;">
		<div style="height: 100px;"></div>
		<!--佔位-->
		<div class="row" id="main_container"
			style="margin: 10px 20px 50px 20px;">

				<div class="card-header my-3" id="allproducts">
					<h1>課程訂購資訊</h1>
				</div>
				<table class="table table-loght" style="text-align: center;">
					<thead class="thead-light">
						<tr>
							<th scope="col"><h3>課程代碼</h3></th>
							<th scope="col"><h3>課程名稱</h3></th>
							<th scope="col"><h3>課程類別</h3></th>
							<th scope="col"><h3>課程期間</h3></th>
							<th scope="col"><h3>金額</h3></th>
							<th scope="col"><h3>取消</h3></th>

						</tr>
					</thead>
					<tbody>
						<tr>
							<th>課程代碼</th>
							<td>課程名稱</td>
							<td>健康</td>
							<td>2021/9/1-2021/11/12</td>
							<td>100K幣</td>
							<td><a class="btn btn-sm btn-secondary" href=""><i
									class="fas fa-trash-alt"></i></a></td>
						</tr>
						<tr>
							<th>課程代碼</th>
							<td>課程名稱</td>
							<td>健康</td>
							<td>2021/9/1-2021/11/12</td>

							<td>100K幣</td>
							<td><a class="btn btn-sm btn-secondary" href=""><i
									class="fas fa-trash-alt"></i></a></td>
						</tr>
						<tr>
							<th>課程代碼</th>
							<td>課程名稱</td>
							<td>健康</td>
							<td>2021/9/1-2021/11/12</td>

							<td>100K幣</td>
							<td><a class="btn btn-sm btn-secondary"
								href="remove-from-cart/id="><i class="fas fa-trash-alt"></i></a></td>
						</tr>
				</table>

				<div class="d-flex py-3" style="float: right">
					<h3>總金額: ${ (total>0)?total:0 } 元</h3>
					<button type="submit" class="btn btn-primary btn-sm">BUY</button>
					<a class="mx-3 btn btn-primary" href="payTestNew1.html">結帳</a> <a
						class="mx-3 btn btn-primary" href="real_Products.jsp">繼續購物</a>
				</div>
			</div>
		
	</section>
	<%@ include file="include/helpButton.html"%>
	<%@include file="include/footer.jsp"%>
</body>
</html>