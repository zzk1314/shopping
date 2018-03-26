<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
	function gobuy(){
		alert("gobuy");
		var type = $("[name='paytype']").val();
		var add = $("[name='address']").val();
		if(type==2){
			alert("支付宝");
		}
		$.ajax({
				dataType:"text",
				type:"post",
				url:"save.action",
				data:{"paytype":type,
				 	  "address":add},
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
	
	
	//location.href='save.action'
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
        	<h3>我的购物车</h3>
        	<s:if test="#session.itemList != null">
				<table class="cart_table">
					<tr class="cart_title">
						<td>商品图片</td>
						<td>商品名称</td>
						<td>单价</td>
						<td>数量</td>
						<td>单品总价</td>
						<td>操作</td>
					
					</tr>
					<s:iterator value="#session.itemList">
						<tr class="cart_title">
							<td>
								<a href="detail.action?productid=${product.id}">
									<img src="../${product.cover}" class="cart_thumb" border="0"/>
								</a>
							</td>
							<td>${product.name}</td>
							<td>${product.price}元<br><span style="color:red;">${price}元</span>
							<s:if test="<s:property value='price'/>!=<s:property value='product.price'/>">
								<span style="color:red;">${price}元</span>
							</s:if>
								
								</td>
							<td>x ${amount}</td>
							<td>${total}元</td>
							<td>
								<a href="javascript:buy(${product.id});">[添加]</a>
								<a href="javascript:lessen(${product.id});">[减少]</a>
								<a href="javascript:deletes(${product.id});">[删除]</a>
							</td>
						</tr>			
					</s:iterator>
					
	          		<tr class="cart_title">
			            <td colspan="4" class="cart_total"><span class="red">总价: </span></td>
			            <td><s:property value="#session.allTotal"/>元</td>
	          		</tr>
	          		<tr >
	          			<td>支付方式</td>
	          			<td>
	          				<select name="paytype">
	          					<option value="">请选择</option>
	          					<option value="1">货到付款</option>
	          					<option value="2">支付宝转账</option>
	          				</select>
	          			</td>
	          			<td></td>
	          			<td>收货地址</td>
	          			<td><input type="text" name="address"/></td>
	          		</tr>
	        	</table>
        	
	            <div class="buttons">
	            	<div style="float:right; padding-top: 20px">
	            		<button class="grey" onclick="gobuy();">提交订单</button>
	            	</div>
	            </div>
	            
            </s:if>
            
            <div class="clear"></div>
		</div>
       <div class="clear"></div>
    </div>
 </div>

<jsp:include page="footer.jsp"/>

</div>

</body>
</html>