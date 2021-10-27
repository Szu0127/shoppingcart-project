<%@page import="tw.billy.conn.DataBaseConnection"%>
<%@page import="tw.billy.model.*"%>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
ProductDao pd = new ProductDao(DataBaseConnection.getConnection());
List<Product> products = pd.getAllProducts();

ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
if(cart_list !=null){
	request.setAttribute("cart_list", cart_list);
}
%>    
<!DOCTYPE html>
<html >
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Know Life</title>

    <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" href="css/index02style.css">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
</head>
<body>

    <!-- header section starts  -->
    <header>
        <!--首頁左上角三條線-->
        <button class="btn btn" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasExample" aria-controls="offcanvasExample">
            <i class="fas fa-bars"></i>
        </button>

        <div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvasExample" aria-labelledby="offcanvasExampleLabel">
            <div class="offcanvas-header">
                <h5 class="offcanvas-title" id="offcanvasExampleLabel">Offcanvas</h5>
                <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
            </div>
            <div class="offcanvas-body">
                <div class="accordion accordion-flush" id="accordionFlushExample">
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="flush-collapseONE">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapse_user" aria-expanded="false" aria-controls="flush-collapseONE">
                                <img src="images/person-circle.svg" alt="" width="30" height="24" class="d-inline-block align-text-left">會員功能
                            </button>
                        </h2>
                        <div id="flush-collapse_user" class="accordion-collapse collapse" aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
                            <div class="accordion-body">
                                <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">

                                    <li class="nav-item">
                                        <a class="nav-link active" aria-current="page" href="member_data.jsp">
                                            <img src="images/person-circle.svg" alt="" width="30" height="24" class="d-inline-block align-text-left">
                                            資料更改
                                        </a>
                                    </li>

                                    <li class="nav-item">
                                        <a class="nav-link active" aria-current="page" href="member_myVideo.jsp">
                                            <img src="images/chat-dots-fill.svg" alt="" width="30" height="24" class="d-inline-block align-text-left">
                                            我的影片
                                        </a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link active" aria-current="page" href="member_store.jsp">
                                            <img src="images/headset.svg" alt="" width="30" height="24" class="d-inline-block align-text-left">
                                            已購買課程
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="accordion accordion-flush" id="accordionFlushExample">
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="flush-headingOne">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapse_manage" aria-expanded="false" aria-controls="flush-collapseOne">
                                    <img src="images/gear-fill.svg" alt="" width="30" height="24" class="d-inline-block align-text-left">內容管理
                                </button>
                            </h2>
                            <div id="flush-collapse_manage" class="accordion-collapse collapse" aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
                                <div class="accordion-body">
                                    <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
                                        <li class="nav-item">
                                            <a class="nav-link active" aria-current="page" href="channel_Info.jsp">
                                                <img src="images/person-circle.svg" alt="" width="30" height="24" class="d-inline-block align-text-left">
                                                資訊主頁
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link active" aria-current="page" href="#">
                                                <img src="images/card-checklist.svg" alt="" width="30" height="24" class="d-inline-block align-text-left">
                                                頻道管理
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link active" aria-current="page" href="channelUploadPage.jsp">
                                                <img src="images/chat-dots-fill.svg" alt="" width="30" height="24" class="d-inline-block align-text-left">
                                                影片上傳
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link active" aria-current="page" href="#">
                                                <img src="images/headset.svg" alt="" width="30" height="24" class="d-inline-block align-text-left">
                                                學習地圖
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="flush-headingOne">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapse_money" aria-expanded="false" aria-controls="flush-collapseThree">
                                    <img src="images/card-checklist.svg" alt="" width="30" height="24" class="d-inline-block align-text-left">儲值功能
                                </button>
                            </h2>
                            <div id="flush-collapse_money" class="accordion-collapse collapse" aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
                                <div class="accordion-body">
                                    <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">

                                        <li class="nav-item">
                                            <a class="nav-link active" aria-current="page" href="buy_Coin.jsp">
                                                <img src="images/cart4.svg" alt="" width="30" height="24" class="d-inline-block align-text-left">
                                                代幣儲值
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link active" aria-current="page" href="buy_Coin_Record.jsp">
                                                <img src="images/card-checklist.svg" alt="" width="30" height="24" class="d-inline-block align-text-left">
                                                儲值查詢
                                            </a>
                                        </li>


                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="flush-headingOne">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapse_shop" aria-expanded="false" aria-controls="flush-collapseThree">
                                    <img src="images/cart4.svg" alt="" width="30" height="24" class="d-inline-block align-text-left">購物商城
                                </button>
                            </h2>
                            <div id="flush-collapse_shop" class="accordion-collapse collapse" aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
                                <div class="accordion-body">
                                    <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
                                        <li class="nav-item">
                                            <a class="nav-link active" aria-current="page" href="real_Products.jsp">
                                                <img src="images/cart4.svg" alt="" width="30" height="24" class="d-inline-block align-text-left">
                                                精選商品
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link active" aria-current="page" href="cart.jsp">
                                                <img src="images/card-checklist.svg" alt="" width="30" height="24" class="d-inline-block align-text-left">
                                                訂單查詢
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="flush-headingOne">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapse_QA" aria-expanded="false" aria-controls="flush-collapseOne">
                                    <img src="images/headset.svg" alt="" width="30" height="24" class="d-inline-block align-text-left">線上Q&A
                                </button>
                            </h2>
                            <div id="flush-collapse_QA" class="accordion-collapse collapse" aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
                                <div class="accordion-body">
                                    <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">

                                        <li class="nav-item">
                                            <a class="nav-link active" aria-current="page" href="">
                                                <img src="images/chat-dots-fill.svg" alt="" width="30" height="24" class="d-inline-block align-text-left">
                                                即時通訊
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link active" aria-current="page" href="QA.jsp">
                                                <img src="images/headset.svg" alt="" width="30" height="24" class="d-inline-block align-text-left">
                                                線上Q&A
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>


                </div>
            </div>
        </div>


        <a href="index.jsp" class="logo"><span>K</span>now<span>L</span>ife</a>

        <nav class="navbar">
            <a href="videoHomePage.jsp">精選內容</a>
            <a href="#services">主題探索</a>
            <a href="#review">線上教師</a>
            <a href="QA.jsp">聯絡我們</a>
        </nav>

        <div class="icons">
                <i class="fas fa-search" id="search-btn"></i>
                <i class="fas fa-user" id="login-btn"></i>
                <a href="cart.jsp"><i class="fas fa-shopping-cart" id="shopping-cart-btn"></i></a>
                <a href="cart.jsp">
                    <span class="badge bg-secondary rounded-pill">${ cart_list.size() }</span>
                </a> <!-- 加在購物車後面的個數圖示 -->
            </div>

        <form action="" class="search-bar-container">
            <input type="search" id="search-bar" placeholder="search here...">
            <label for="search-bar" class="fas fa-search"></label>
        </form>


    </header>

    <!-- header section ends -->
    <!-- login form container  -->

    <div class="login-form-container" id="form-login">

        <i class="fas fa-times" id="form-close"></i>

        <!--暫時轉跳影片首頁-->
        <form action="">
            <h3>login</h3>
            <input type="email" class="box" placeholder="請輸入e-mail">
            <input type="password" class="box" placeholder="請輸入密碼">
            <input type="submit" value="login now" class="btn">
            <input type="checkbox" id="remember">
            <label for="remember">remember me記住我</label>
            <p>忘記密碼? <a href="#">click here</a></p>
            <p>還未註冊嗎? <a href="register.html">立即註冊</a></p>
            <p>暫時按鈕 <a href="1005videoHomePage.html">click here</a></p>
        </form>

    </div>

    <!-- home section starts  -->

    <section class="home" id="home">

        <div class="content">
            <h3 style="background-color: #333;">在家輕生活 健康輕鬆瘦</h3>
            <p>dicover new places with us, adventure awaits</p>
            <a href="1005videoHomePage.html" class="btn">立即觀看</a>
        </div>

        <div class="controls">
            <span class="vid-btn active" data-src="video2/sportvideo1.mp4"></span>
            <span class="vid-btn" data-src="video2/sportvideo2.mp4"></span>
            <span class="vid-btn" data-src="video2/sportvideo3.mp4"></span>
        </div>

        <div class="video-container">
            <video src="video/sportvideo1.mp4" id="video-slider" loop autoplay muted></video>
        </div>

    </section>

    <!-- home section ends -->
    <!-- packages section starts  -->

    <section class="packages" id="packages">

        <h1 class="heading">
            <span>精</span>
            <span>選</span>
            <span>內</span>
            <span>容</span>
        </h1>

        <div class="box-container">

            <div class="box">
                <img src="images/sport1.jpg" alt="">
                <div class="content">
                    <h3>10分鐘數緩瑜珈</h3>
                    <p></p>
                    <div class="stars">
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="far fa-star"></i>
                    </div>
                    <div class="price"> 50 K幣 <span>100K幣</span> </div>
                    <a href="videoPage.jsp" class="btn">立即觀看</a>
                </div>
            </div>

            <div class="box">
                <img src="images/sport1.jpg" alt="">
                <div class="content">
                    <h3>10分鐘數緩瑜珈</h3>
                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Veritatis, nam!</p>
                    <div class="stars">
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="far fa-star"></i>
                    </div>
                    <div class="price"> 50 K幣 <span>100K幣</span> </div>
                    <a href="#" class="btn">立即觀看</a>
                </div>
            </div>

            <div class="box">
                <img src="images/sport1.jpg" alt="">
                <div class="content">
                    <h3>10分鐘數緩瑜珈</h3>
                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Veritatis, nam!</p>
                    <div class="stars">
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="far fa-star"></i>
                    </div>
                    <div class="price"> 50 K幣 <span>100K幣</span> </div>
                    <a href="#" class="btn">立即觀看</a>
                </div>
            </div>

            <div class="box">
                <img src="images/sport2.jpg" alt="">
                <div class="content">
                    <h3>15分鐘室內有氧</h3>
                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Veritatis, nam!</p>
                    <div class="stars">
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="far fa-star"></i>
                    </div>
                    <div class="price"> 50 K幣 <span>100K幣</span> </div>
                    <a href="#" class="btn">立即觀看</a>
                </div>
            </div>

            <div class="box">
                <img src="images/sport2.jpg" alt="">
                <div class="content">
                    <h3>15分鐘室內有氧</h3>
                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Veritatis, nam!</p>
                    <div class="stars">
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="far fa-star"></i>
                    </div>
                    <div class="price"> 50 K幣 <span>100K幣</span> </div>
                    <a href="#" class="btn">立即觀看</a>
                </div>
            </div>

            <div class="box">
                <img src="images/sport2.jpg" alt="">
                <div class="content">
                    <h3>15分鐘室內有氧</h3>
                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Veritatis, nam!</p>
                    <div class="stars">
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="far fa-star"></i>
                    </div>
                    <div class="price"> 50 K幣 <span>100K幣</span> </div>
                    <a href="#" class="btn">立即觀看</a>
                </div>
            </div>

        </div>

    </section>

    <!-- packages section ends -->
    <!-- services section starts  -->

    <section class="services" id="services">

        <h1 class="heading">
            <span>健</span>
            <span>康</span>
            <span>新</span>
            <span>選</span>
            <span>則</span>
        </h1>

        <div class="box-container">

            <div class="box">
                <a href="https://jo-fitness.com/the-best-home-fitness-equipment-to-buy/">
                    <i class="fas fa-home"></i>
                    <h3>想要在家健身？ 9 款居家健身器材推薦</h3>
                    <p>
                        想要運動卻覺得去健身房很麻煩？家裡離健身房很遠，通勤很花時間？
                        那你可以嘗試打造自己的居家健身房，藉由簡單的健身器材，讓你想練隨時練，增加訓練的便利性，還可以節省健身房昂貴的會費，達成跟上健身房一樣的效果！
                    </p>

                </a>
            </div>
            <div class="box">
                <a href="https://www.womenshealthmag.com/tw/food-nutrition/diet/g34559741/slim-meal/">

                    <i class="fas fa-weight"></i>
                    <h3>想健康瘦? 減肥吃什麼</h3>
                    <p>
                        誰說「減肥餐」一定很難吃、減肥必須要餓肚子？現在這些觀念都已經太過時了！只要吃對方式，減脂減肥真的不用挨餓。
                    </p>
                </a>
            </div>
            <div class="box">
                <a href="#">
                    <i class="fas fa-utensils"></i>
                    <h3>健康減肥食譜大公開</h3>
                    <p>營養師自己都這樣減肥，「進食順序很重要，要從這個先吃..」親授5大超實用減肥菜單公開</p>
                </a>
            </div>
            <div class="box">
                <a href="#">
                    <i class="fas fa-procedures"></i>
                    <h3>想減肥 充足的睡眠很重要</h3>
                    <p>長期處於平常睡少、假日睡多的「社交時差」，易增加肥胖、心血管疾病風險</p>
                </a>
            </div>
            <div class="box">
                <a href="https://www.hpa.gov.tw/Pages/Detail.aspx?nodeid=571&pid=9738"></a>
                <i class="fas fa-walking"></i>
                <h3>運動所消耗的卡路里如何計算?</h3>
                <p>
                    各類運動消耗熱量表運動30分鐘消耗的熱量(大卡)

                    本表係因每個人身體狀況及基礎代謝率不同而訂出熱量消耗量，僅供參考。
                </p>
            </div>
            <div class="box">
                <a href="https://heho.com.tw/archives/75998">
                    <i class="fas fa-viruses"></i>
                    <h3>居家防疫守則</h3>
                    <p>
                        日常生活中 10 種一般民眾預防感染、增強抵抗力的最好方法，呼籲大家齊來防疫，保護自己也保護他人，一起防止這波疫情的擴散、守住台灣。
                    </p>
                </a>
            </div>

        </div>

    </section>

    <!-- services section ends -->
    <!-- review section starts  -->

    <section class="review" id="review">

        <h1 class="heading">
            <span>線</span>
            <span>上</span>
            <span>教</span>
            <span>師</span>

        </h1>

        <div class="swiper-container review-slider">

            <div class="swiper-wrapper">

                <div class="swiper-slide">
                    <div class="box">
                        <img src="images/coach1.jpg" alt="">
                        <h3>john deo</h3>
                        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Ipsa adipisci quisquam sunt nesciunt fugiat odit minus illum asperiores dolorum enim sint quod ipsam distinctio molestias consectetur ducimus beatae, reprehenderit exercitationem!</p>
                        <div class="stars">
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="far fa-star"></i>
                        </div>
                    </div>
                </div>
                <div class="swiper-slide">
                    <div class="box">
                        <img src="images/coach2.jpg" alt="">
                        <h3>john deo</h3>
                        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Ipsa adipisci quisquam sunt nesciunt fugiat odit minus illum asperiores dolorum enim sint quod ipsam distinctio molestias consectetur ducimus beatae, reprehenderit exercitationem!</p>
                        <div class="stars">
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="far fa-star"></i>
                        </div>
                    </div>
                </div>
                <div class="swiper-slide">
                    <div class="box">
                        <img src="images/coach3.jpg" alt="">
                        <h3>john deo</h3>
                        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Ipsa adipisci quisquam sunt nesciunt fugiat odit minus illum asperiores dolorum enim sint quod ipsam distinctio molestias consectetur ducimus beatae, reprehenderit exercitationem!</p>
                        <div class="stars">
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="far fa-star"></i>
                        </div>
                    </div>
                </div>
                <div class="swiper-slide">
                    <div class="box">
                        <img src="images/coach4.jpg" alt="">
                        <h3>john deo</h3>
                        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Ipsa adipisci quisquam sunt nesciunt fugiat odit minus illum asperiores dolorum enim sint quod ipsam distinctio molestias consectetur ducimus beatae, reprehenderit exercitationem!</p>
                        <div class="stars">
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="far fa-star"></i>
                        </div>
                    </div>
                </div>
                <div class="swiper-slide">
                    <div class="box">
                        <img src="images/coach5.jpg" alt="">
                        <h3>john deo</h3>
                        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Ipsa adipisci quisquam sunt nesciunt fugiat odit minus illum asperiores dolorum enim sint quod ipsam distinctio molestias consectetur ducimus beatae, reprehenderit exercitationem!</p>
                        <div class="stars">
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="far fa-star"></i>
                        </div>
                    </div>
                </div>

            </div>

        </div>

    </section>

    <!-- review section ends -->
    <!-- brand section  -->
    <section class="brand-container">

        <div class="swiper-container brand-slider">
            <div class="swiper-wrapper">
                <div class="swiper-slide"><img src="images/1.jpg" alt=""></div>
                <div class="swiper-slide"><img src="images/2.jpg" alt=""></div>
                <div class="swiper-slide"><img src="images/3.jpg" alt=""></div>
                <div class="swiper-slide"><img src="images/4.jpg" alt=""></div>
                <div class="swiper-slide"><img src="images/5.jpg" alt=""></div>
                <div class="swiper-slide"><img src="images/6.jpg" alt=""></div>
            </div>
        </div>

    </section>

    <%@include file="include/helpButton.html"%>
    <%@include file="include/footer.jsp"%>
    
    

    <!-- custom js file link  -->
    <script src="js/script.js"></script>

</body>
</html>