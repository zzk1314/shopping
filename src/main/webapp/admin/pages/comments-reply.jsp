<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery.min.js"></script>
    
    <title>回复评论</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<script type="text/javascript">
		function buttony(id){
			//alert(id);
			var text = document.getElementById("pl").value;
			alert(typeof(text));
			
			location.href="../admin!reply.action?id="+id+"&reply="+text;
			window.close(1000);
		}
	
	</script>

  </head>
  
  <body>
    <table>
    	
    	<%String id = request.getParameter("id"); %>
    		
    	<tr><td>回复</td></tr>
    	<tr><td><textarea  id = "pl" style="width:400px;height:180px; " ></textarea></td></tr>
    	<tr ><td><span id="pid"></span></td><td><button id="buttony" onclick="buttony(<%=id %>);" >确定</button></td><td></td></tr>
    </table>
  </body>
</html>
