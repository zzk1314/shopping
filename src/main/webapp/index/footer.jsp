<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="./js/move-top.js"></script>
</head>
<body>
<div class="footer">
   	  <div class="wrapper">	
	     <div class="section group">
				<div class="col_1_of_4 span_1_of_4">
						<h4>本店介绍</h4>
						<ul>
						<li><a href="#">About Us</a></li>
						</ul>
					</div>
				<div class="col_1_of_4 span_1_of_4">
					<h4>选择我们</h4>
						<ul>
						<li><a href="#">About Us</a></li>
						<li><a href="#">Customer Service</a></li>
						<li><a href="#">Privacy Policy</a></li>
						<li><a href="#"><span>Site Map</span></a></li>
						<li><a href="#"><span>Search Terms</span></a></li>
						</ul>
				</div>
				<div class="col_1_of_4 span_1_of_4">
					<h4>新手导航</h4>
						<ul>
							<li><a href="#">Sign In</a></li>
						</ul>
				</div>
				<div class="col_1_of_4 span_1_of_4">
					<h4>Contact</h4>
						<ul>
							<li><span>+40-000-000001</span></li>
							<li><span>+40-000-888888</span></li>
						</ul>
						<div class="social-icons">
							<h4>支付平台</h4>
					   		  <ul>
							      <li class="facebook"><a href="#" target="_blank"> </a></li>
							      <div class="clear"></div>
						     </ul>
   	 					</div>
				</div>
			</div>
			<div class="copy_right">
				<!-- <p>商品店信息管理系统 © All rights  | Design by me</p> -->
		   </div>
     </div>
</div>

<script type="text/javascript">
$(document).ready(function() {
	/*
	var defaults = {
 			containerID: 'toTop', // fading element id
		containerHoverID: 'toTopHover', // fading element hover id
		scrollSpeed: 1200,
		easingType: 'linear' 
		};
	*/
	
	$().UItoTop({ easingType: 'easeOutQuart' });
	
});
</script>
<a href="#" id="toTop" style="display: block;">
	<span id="toTopHover" style="opacity: 1;"></span>
</a>
</body>
</html>