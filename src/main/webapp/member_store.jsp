<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="zh-tw" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<title>會員中心</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<link rel="stylesheet" href="css/member4.css" />
<link rel="stylesheet" href="css/headerfooter.css" />
<script src="js/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="css/headerfooter.css" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

<%
if (session.getAttribute("username") == null) {
	response.setStatus(403);
	response.sendRedirect("http://localhost:8080/KnowLife/login.jsp");
}
%>
</head>
<body>

	<%@include file="include/header.jsp"%>

	<!-- 會員中心 -->
	<section id="memberpage">
		<div class="container-xl">
			<div class="row">
				<div class="leftPage col-md-2 ">
					<div class="box1">
                        	
                             <img class="userPicture" id="userPicture" src="https://forwardsummit.ca/wp-content/uploads/2019/01/avatar-default.png">
                            <button class="userchangeimg" data-bs-toggle="modal" data-bs-target="#memberimage">
                            <img src="https://img.ixintu.com/upload/jpg/20210526/c74600811b7f2c0bba147cafc99700a1_41542_800_720.jpg!ys" width="20px" height="20px" style="border-radius: 100%">
                            </button>
                            <h2 class="pt-4" id="name"></h2>
                            <h2 class="pt-4" id="amount">K幣 </h2>
                        </div>
                            <a href="#"><div class="box1">上傳影片</div></a>
                            <a href="member_myVideo.jsp"><div class="box1">我的影片</div></a>
                        	<a href="member_data.jsp"><div class="box1">修改會員資料</div></a>
                        	<a href="member_store.jsp"><div class="box1">購買查詢</div></a>
                        	<a href="payRecord.jsp"><div class="box1">代幣儲值</div></a>
                        	<a href="http://localhost:8080/KnowLife/LogoutServlet"><div class="box1">登出</div></a>
                        	
                        	<!-- 跳出修改圖片-->
                                <div class="modal fade" id="memberimage">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <!-- header -->
                                            <div class="modal-header">
                                                <h3>上傳圖片</h3>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                                            </div>

                                            <!-- Body -->
                                            <div class="modal-body">
                                                <form action="http://localhost:8080/KnowLife/UpdateUserImageServlet" method="post" enctype="multipart/form-data">
                                                    <div class="form-group">
                                                    	<input type="file" name="myimage" />
                                                    </div>
                                                    
                                                    <!-- submit -->
                                                    <button type="submit" class="btn btn-info">確定修改</button>
                                                </form>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- 修改圖片視窗over -->
				</div>
				<div class="rightPage shadow col-md-8">
					<div class="container-md">
						<h1 class="p-2">影片購買紀錄</h1>
						<hr />
						<div class="buyedbox">
							<a href="https://www.youtube.com/watch?v=bJhp-NmBTMw">
								<div>
									<img class="videoimage" src="images/viedoimage3.gif" />
								</div>
								<div class="videoname">
									<span>10分鐘⏱啞鈴練全身</span>
								</div>
							</a>
							<div class="buyedtime">
								<span>2021/10/10 10:10</span>
							</div>
							<div class="buyedcash">
								<span>100K幣</span>
							</div>
						</div>
						<hr />
						<div class="buyedbox">
							<a href="https://www.youtube.com/watch?v=s47kO53z80U">
								<div>
									<img class="videoimage" src="images/viedoimage.gif" />
								</div>
								<div class="videoname">
									<span>【實測】50元就能吃飽一餐？剩食APP🔥隨機下訂美食福袋</span>
								</div>
							</a>
							<div class="buyedtime">
								<span>2021/10/09 09:09</span>
							</div>
							<div class="buyedcash">
								<span>50K幣</span>
							</div>
						</div>
						<hr />
						<div class="buyedbox">
							<a href="https://www.youtube.com/watch?v=-Y_4rOXeqHQ">
								<div>
									<img class="videoimage" src="images/viedoimage2.gif" />
								</div>
								<div class="videoname">
									<span>學習資料結構、演算法在工作上真的有用嗎? 實際工作經歷不藏私!</span>
								</div>
							</a>
							<div class="buyedtime">
								<span>2021/10/08 08:08</span>
							</div>
							<div class="buyedcash">
								<span>50K幣</span>
							</div>
						</div>
						<hr />
						<div class="buyedbox">
							<a href="https://www.youtube.com/watch?v=2YpyuyREbLI">
								<div>
									<img class="videoimage" src="images/viedoimage4.gif" />
								</div>
								<div class="videoname">
									<span>25分鐘居家啞鈴上半身訓練(肩、胸、背、核心)</span>
								</div>
							</a>
							<div class="buyedtime">
								<span>2021/10/08 08:08</span>
							</div>
							<div class="buyedcash">
								<span>200K幣</span>
							</div>
						</div>
						<hr />

					</div>


				</div>
			</div>
		</div>
	</section>

	<%@include file="include/footer.jsp"%>
	<script src="js/script.js"></script>


		<script>
		$.ajax({
			url : 'http://localhost:8080/KnowLife/MemberDataServlet',
			type : 'get',
			dataType : 'json',
			xhrFields : {
				withCredentials : true
			},
			crossDomain : true,
			success : function(res) {
				$("#name").append(res.name);
				$("#amount").append(res.amount);
                if(res.image == null){
                	$("#userPicture").attr("src", "https://forwardsummit.ca/wp-content/uploads/2019/01/avatar-default.png");
                } else if(res.image != null){
                $("#userPicture").attr("src", res.image);
                }
			}
		})
	</script>

</body>
</html>