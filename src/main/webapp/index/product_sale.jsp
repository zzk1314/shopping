<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>特价促销</title>
</head>
<body>

<div class="wrap">

<s:action name="header" executeResult="true"/>

	<div class="main">
		<div class="content">
		
			<div class="content_top">
			
				<div class="heading">
					<h3>特价促销</h3>
				</div>
				
				<div class="page-no">${pageTool}</div>
				
				<div class="clear"></div>
				
			</div>
			
			<div class="section group">
			
				<s:iterator value="saleList">
				
					<div class="grid_1_of_4 images_1_of_4">
						 <a href="detail.action?productid=<s:property value="product.id"/>">
						 	<img src="../<s:property value="product.cover"/>" alt="<s:property value="product.name"/>" width="212px" height="212px"/>
						 </a>
						 <div class="discount">
						 	<span class="percentage"><s:property value="discount"/>%</span>
						 </div>
						 <h2><a href="detail.action?productid=<s:property value="product.id"/>"><s:property value="product.name"/></a></h2>
						 <p>
							 <span class="strike"><s:property value="product.price"/>元</span>
							 <span class="price"><s:property value="price"/>元</span>
						 </p>
					     <div class="button">
						     <span>
							     <img src="./images/cart.jpg" alt="" />
							     <a href="javascript:buy(<s:property value="product.id"/>);" class="cart-button" >加入购物车</a>
						     </span> 
					     </div>
					     <div class="button">
					     	<span><a href="detail.action?productid=<s:property value="product.id"/>" class="details">查看详情</a></span>
					     </div>
					</div>
				
				</s:iterator>

			</div>
			
    	</div>
	</div>

<jsp:include page="footer.jsp"/>

</div>

</body>
</html>