<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="js/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function namefoucs(){
		var username = $("#username").val();
		if(username.trim()==""){
			$("#mesName").show();
		}else{
			$("#mesName").hide();
		}
	}
	function pwdfoucs(){
		var pwd = $("#pwd").val();
		if(pwd.length<6){
			$("#mesPwd").show();
		}else{
			$("#mesPwd").hide();
		}
	}
	function repwdfoucs(){
		var pwd = $("#pwd").val();
		var repwd = $("#repwd").val();
		if(pwd!=repwd){
			$("#mes").show();
		}else{
			$("#mes").hide();
		}
	}
	function phonefoucs(){
		var phone = $("#phone").val();
		var regx=/^1[3|4|5|8]\d{9}$/;
		var rs=regx.test(phone);
		if(!rs){
			$("#mesPhone").show();
		}else{
			$("#mesPhone").hide();
		}
	}
	function emailfoucs(){
		var email = $("#email").val();
		var regex =  /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		var rs = regex.test(email);
		if(!rs){
			$("#mesEmail").show();
		}else{
			$("#mesEmail").hide();
		}
	}

	
</script>
<title>商城首页</title>
</head>
<body>

<div class="wrap">

<s:action name="header" executeResult="true"/>

<div class="main">
    <div class="content">
    	 <div class="login_panel">
    		<h3>注册新账户</h3>
    		<s:actionmessage style="color:red;" />
    		<form action="register.action" method="post" id="form_register" >
    		    <input name="user.username" type="text"  class="field" id="username" placeholder="用户名" onblur="namefoucs();">
    		    <label  id="mesName" hidden="true" style="color:red;">用户名不得为空</label>
            	<input name="user.password" type="password" class="field" id="pwd" placeholder="密码" onblur="pwdfoucs();">
            	<label  id="mesPwd" hidden="true" style="color:red;">密码长度不可小于6位</label>
            	<input name="repassword" type="password" class="field" id="repwd" placeholder="确认密码" onblur="repwdfoucs();">
    		    <label  id="mes" hidden="true" style="color:red;">两次密码不一致</label>
    		    <input name="user.phone" type="text"  class="field" id="phone" placeholder="电话" onblur="phonefoucs();">
    		    <label  id="mesPhone" hidden="true" style="color:red;">号码格式不正确</label>
    		    <input name="user.email" type="text" class="field" id="email" placeholder="邮箱地址" onblur="emailfoucs();">
    		    <label  id="mesEmail" hidden="true" style="color:red;">邮箱格式不正确</label>
		   	</form>
            <div class="buttons">
            	<div>
            		<button class="grey" id="conregist" onclick="$('#form_register').submit();">确认注册</button>
            		<a href="login.jsp" style="float:right; padding-top: 10px; padding-right: 50px; font-size: 14px">登录已有账户</a></div>
            </div>
            <div class="clear"></div>
    	</div>  
       <div class="clear"></div>
    </div>
 </div>

<jsp:include page="footer.jsp"/>

</div>

</body>
</html>