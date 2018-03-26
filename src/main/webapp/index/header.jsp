<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/style.css" rel="stylesheet" type="text/css" media="all"/>
<link href="./css/slider.css" rel="stylesheet" type="text/css" media="all"/>
<link href="./css/menu.css" rel="stylesheet" type="text/css" media="all"/>
<script type="text/javascript" src="./js/jquery.min.js"></script> 
<script type="text/javascript" src="./js/script.js"></script>
<script type="text/javascript" src="./js/nav.js"></script>
<script type="text/javascript" src="./js/easing.js"></script>
<script type="text/javascript" src="./js/nav-hover.js"></script>
<script type="text/javascript">
	//显示实时时间
	var timeInteval;//设置为全局
  $(document).ready(function($){
    $('#dc_mega-menu-orange').dcMegaMenu({rowItems:'4',speed:'fast',effect:'fade'});
    getSysTime();
	timeInteval = setInterval("getSysTime()",1000);//每隔1秒执行一次getSysTime()
	//setTimeout("getSysTime()",1000);//一秒钟后执行一次getSysTime()
  });
  	function getSysTime(){
		var datestr = "" ; 
		var mydate = new Date();
		var year = mydate.getFullYear();
		var month = mydate.getMonth()+1;
		var myday = mydate.getDate();
		var hour = mydate.getHours();
		var minutes  = mydate.getMinutes();
		var seconds = mydate.getSeconds();
		datestr = year+"-"+month+"-"+myday+" "+hour+":"+minutes+":"+seconds;
		var divobj = document.getElementById("time");
		divobj.innerHTML = "<font color='black'>现在时间："+datestr+"</font>";
	}
</script>
</head>
<body>
	<div class="header">
		<div class="header_top">
			<div class="logo">
				<a href="index.action"><img src="./images/logo2.jpg" alt="" /></a>
			</div>
			  <div class="header_top_right">
			    <div class="search_box">
				    <form action="search.action" method="post">
				    	<input type="text" name="search" value="${search}" placeholder="输入要搜索内容">
						<input type="submit" value="搜索">
				    </form>
			    </div>
			    <div class="shopping_cart">
					<div class="cart">
						<a href="cart.action" title="查看购物车" rel="nofollow">
							<strong class="opencart"> </strong>
							<span class="cart_title">购物车</span>
							<span class="no_product"></span>
						</a>
					</div>
			    </div>

				<div class="login">
					<span>
						<s:if test="#session.user==null">
							<a href="login.jsp"><img src="./images/login.png" alt="" title=""/></a>
							<a href="login.jsp" >点击登录|</a>
							<a href="register.jsp"><img src="./images/login.png" alt="" title=""/></a>
							<a href="register.jsp" >点击注册</a>
						</s:if>
						<s:else>
							<a href="order.action"><img src="./images/login.png" alt="" title=""/>&nbsp;&nbsp;${user.username} 订单</a>
							<a href="logout.action"  ><img src="./images/login.png" alt="" title=""/>&nbsp;注销</a>
						</s:else>
						
					</span>
				</div>
				<div class="clear"></div>
			</div>
			<div class="clear" align="right" ><span id="time"></span></div>
		</div>
		
		
		<div class="menu">
			<ul id="dc_mega-menu-orange" class="dc_mm-orange">
				<li><a href="index.action">首页</a></li>
				<li><a href="productList.action">分类</a>
					<ul>
						<s:iterator value="categoryList">
							<li><a href="productList.action?categoryid=<s:property value="id"/>"><s:property value="name"/></a></li>
						</s:iterator>
					</ul>
				</li>
				<li><a href="productShow.action">热销推荐</a></li>
				<li><a href="productSale.action">特价促销</a></li>
				<li><a href="productNew.action">最新上架</a></li>
				<li><a href="loginUser.action">个人中心</a></li>
				<li><a href="aboutus.jsp">关于我们</a></li>
				<li><a href="${pageContext.request.contextPath }/admin/login.jsp" target="_blank">管理员登录</a></li>
				<!-- 
				<li><a href="#">常见问题</a></li>
				<li><a href="#">关于我们</a></li>
				<li><a href="#">联系我们</a> </li> -->
				<div class="clear"></div>
			</ul>
		</div>
	</div>

</body>
</html>