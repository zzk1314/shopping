<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="com.entity.Users"%>
<%@page import="com.service.UserService"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">

<title>魅侑抽奖大乐盘</title>

<%
	   Users form=(Users)session.getAttribute("form");
	   UserService service = new UserService();
		Users a= service.get(1);
		int id=a.getId();
	%>


<link href="files/activity-style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="files/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#inner").click(function() {
			lottery();
		});
	});
	var s=<%=a.getMeidou()%>;
	function lottery() {
	
	
	
	if(s>0){
	
		$.ajax({
			type : 'POST',
			url : "AwardServlet?id=<%=form.getId()%>",
			dataType : 'json',
			cache : false,
			error : function() {
				alert('出错了！');
				return false;
			},

			success : function(json) {
				$("#inner").unbind('click').css("cursor", "default");
				var angle = parseInt(json.angle); //角度 
				var msg = json.msg; //提示信息
				$("#outer").rotate({ //inner内部指针转动，outer外部转盘转动
					duration : 5000, //转动时间 
					angle : 0, //开始角度 
					animateTo : 3600 + angle, //转动角度 
					easing : $.easing.easeOutSine, //动画扩展 
					callback : function() {
						var con = confirm(msg+ '\n剩余：'+s+'魅豆'+'\n还要再来一次吗？');
						if (con) {
							lottery();
						} else {
							return false;
						}
					}
				});
			}
		});
		s=s-200;
		}else{
		
				alert('您的魅豆剩余：0\n请返回魅侑网上服装店');
				
			
		}
	}
</script>
</head>

<body class="activity-lottery-winning">

	<div class="main">
		<script type="text/javascript">
			var loadingObj = new loading(document.getElementById('loading'), {
				radius : 20,
				circleLineWidth : 8
			});
			loadingObj.show();
		</script>

		<div id="outercont">
			<div id="outer-cont" style="overflow:hidden;">
				<div id="outer">
					<img src="files/activity-lottery-1.png" width="310px">
				</div>
			</div>
			<div id="inner-cont">
				<div id="inner">
					<img src="files/activity-lottery-2.png">
				</div>
			</div>
		</div>
	</div>
	<div><font><center>每抽奖一次，扣除200魅豆</center></font></div>
<div><font><center>一等奖：2000魅豆，二等奖：800魅豆，三等奖：400魅豆</center></font></div>
<div><font><center><a href="index.jsp">返回魅侑网上服装店</a></center></font></div>
</body>


</html>