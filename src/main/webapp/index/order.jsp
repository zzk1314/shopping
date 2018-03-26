<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
	function deleteIndent(indentid,status){
		alert(status);
		if(confirm('确定删除此商品吗')){
		$.ajax({
				dataType:"text",
				type:"post",
				url:"deleteIndent.action",
				data:{"indentid":indentid},
				success:function(msg){
					if(msg=="ok"){
						location.reload();
					}else if(msg=="login"){
						alert("请登录后操作!");
						location.href="login.jsp";
					}else{
						alert("请求失败!");
					}
				}
			});
		}
	}
	function shohuo(indentid,status){
		alert(status);
		if(status==1){
			alert("不能确认收货");
			return ;
		}else if(status==0){
			alert("已经确认收货");
			return;
		}
		if(confirm('确定已收到商品吗')){
			$.ajax({
				dataType:"text",
				type:"post",
				url:"configIndent.action",
				data:{"indentid":indentid},
				success:function(msg){
					if(msg=="ok"){
						location.reload();
					}else if(msg=="login"){
						alert("请登录后操作!");
						location.href="login.jsp";
					}else{
						alert("请求失败!");
					}
				}
			});
		}
	}

	function pinglun(productid){
		$("#pid").val(productid);
		$("#comment").fadeIn(1000);
		
	}
	function buttony(){
		var productid = $("#pid").val();
		var content = $("#pl").val();
		$.ajax({
				dataType:"text",
				type:"post",
				url:"addComment.action",
				data:{"productid":productid,
						"content":content},
				success:function(msg){
					if(msg=="ok"){
						alert("评论成功");
						location.reload();
					}else if(msg=="login"){
						alert("请登录后操作!");
						location.href="login.jsp";
					}else{
						alert("请求失败!");
					}
				}
			});
		
	}
</script>
<title>购物车</title>
</head>
<body>

<div class="wrap">

<s:action name="header" executeResult="true"/>

<div class="main">
    <div class="content">
    	<div class="login_panel"  style="width:600px">	
        	<s:actionmessage/>
        	
        	<s:if test="indentList != null">
        	
				<h3>我的订单</h3>
        		<s:iterator value="indentList">
        		
					<table class="cart_table">
					
						<tr>
		          			<td colspan="3">下单时间: <s:date name="systime" format="yyyy-MM-dd HH:mm:ss"/></td>
				            <td class="cart_total"><span class="red">总价: </span></td>
				            <td><s:property value="total"/></td>
		          		</tr>
		          		<tr>
		          			<td ></td>
		          			<td><span >订单状态：</span></td>
		          			<td><s:if test="status==1">未发货</s:if>
		          				<s:elseif test="status==2">已发货</s:elseif>
		          				<s:else>已收货</s:else>
		          			</td>
		          			<td ><button onclick="shohuo(${id },${status });">确认收货</button></td>
		          			<td><a href="javascript:deleteIndent(${id },${paytype });">删除订单</a></td>
		          		</tr>
		          		
						<s:iterator value="itemList">
							
								<tr class="cart_title">
									<td>
										<a href="detail.action?productid=${product.id}">
											<img src="../${product.cover}" class="cart_thumb" border="0"/>
										</a>
									</td>
									<td>${product.name}</td>
									<td>${product.price}</td>
									<td>x ${amount}</td>
									<td>${total}</td>
									<td><s:if test="status==0"><input type="button" value="评论商品" onclick="pinglun(${product.id});"/></s:if></td>
								</tr>	
								
						</s:iterator>
						
		          		
		        	</table><br>
	        	
	        	</s:iterator>
        	
            </s:if>
            
            <div class="clear"></div>
		</div>
		<div class="rightsidebar span_3_of_1" id="comment" style="display:none;">
			<table>
				
				<tr ><td colspan="2"><h2>商品评论</h2></td><td></td></tr>
				<tr><td colspan="2"><textarea  id = "pl" style="width:400px;height:180px; " ></textarea></td></tr>
				<tr ><td><span id="pid"></span></td>
					<td><button id="buttony" onclick="buttony()">确定</button></td><td></td></tr>
			</table>
		</div>
       <div class="clear"></div>
    </div>
 </div>

<jsp:include page="footer.jsp"/>

</div>

</body>
</html>