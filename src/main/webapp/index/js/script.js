/**
 * 加入购物车
 */
function buy(productid){
	$.post("buy.action", {productid:productid}, function(data){
		if(data=="ok"){
			location.reload();
		}else if(data=="login"){
			alert("请登录后购买!");
			location.href="login.jsp";
		}else{
			alert("请求失败!");
		}
	});
}
/**
 * 购物车减去
 */
function lessen(productid){
	$.post("lessen.action", {productid:productid}, function(data){
		if(data=="ok"){
			location.reload();
		}else if(data=="login"){
			alert("请登录后操作!");
			location.href="login.jsp";
		}else{
			alert("请求失败!");
		}
	});
}
/**
 * 购物车删除
 */
function deletes(productid){
	if(confirm('确定删除此宠物吗')){
		$.post("delete.action", {productid:productid}, function(data){
			if(data=="ok"){
				location.reload();
			}else if(data=="login"){
				alert("请登录后操作!");
				location.href="login.jsp";
			}else{
				alert("请求失败!");
			}
		});
	}
}