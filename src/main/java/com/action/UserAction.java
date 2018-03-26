package com.action;

import com.entity.Comments;
import com.entity.Indent;
import com.entity.Items;
import com.entity.Product;
import com.entity.ProductSale;
import com.entity.Users;
import com.service.CommentsService;
import com.service.IndentService;
import com.service.ItemService;
import com.service.ProductService;
import com.service.UserService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Namespace("/index")
@Results({
	@Result(name="login",location="/index/login.jsp"),
	@Result(name="register",location="/index/register.jsp"),
	@Result(name="reindex",type="redirect",location="index.action"),
	@Result(name="cart",location="/index/cart.jsp"),
	@Result(name="order",location="/index/order.jsp"),
	@Result(name="userdetail",location="/index/user_list.jsp"),
})
public class UserAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	
	private Users user;
	private int productid;
	private List<Indent> indentList;
	private List<Items> itemList;
	private int paytype;
	private String address;
	private int indentid;
	private String content;
	

	@Resource
	private UserService userService;
	@Resource
	private IndentService indentService;
	@Resource
	private ProductService productService;
	@Resource
	private ItemService itemService;
	@Resource
	private CommentsService commentsService;

	
	/**
	 * 注册用户
	 * @return
	 */
	@Action("register")
	public String register(){
		boolean flag = user.getUsername().isEmpty()||user.getPassword().isEmpty()||user.getPhone().isEmpty()
				|| user.getEmail().isEmpty();
		if (flag) {
			
			addActionMessage("请填写注册信息!");
			return "register";
		}else {
			Users isUser = userService.get(user.getUsername());
			if(isUser!=null){
				addActionMessage("用户已存在，请重新输入!");
				return "register";
			}else{
				user.setMeidou(600);
				userService.add(user);
				addActionError("注册成功, 请登录!");
				return "login";
			}
		}
	}
	
	/**
	 * 用户登录
	 * @return
	 * @throws Exception
	 */
	@Action("login")
	public String login() {
		if(userService.checkUser(user.getUsername(), user.getPassword())){
			getSession().put("user", userService.get(user.getUsername()));
			return "reindex";
		} else {
			addActionError("用户名或密码错误!");
			return "login";
		}
	}
	
	/**
	 * 个人中心
	 * @return
	 * @author ccm
	 * @throws Exception
	 */
	@Action("loginUser")
	public String loginUser(){
		Users user = (Users) getSession().get("user");
		if (user != null) {
			getSession().put("user", userService.get(user.getUsername()));
			return "userdetail";
		}else {
			addActionError("请先登录!");
			return "login";
		}
	}

	/**
	 * 修改个人信息
	 * @return
	 * @author ccm
	 * 
	 */
	@Action("updateUser")
	public String updateUser(){
		Users oldUser = (Users) getSession().get("user");
		user.setId(oldUser.getId());
		if(user.getPassword().isEmpty()){
			user.setPassword(oldUser.getPassword());
		}
		if(user!=null){
			getSession().put("user", userService.get(user.getUsername()));
			userService.update(user);
			addActionMessage("修改成功！");
			return "userdetail";
		} else {
			addActionError("请先登录!");
			return "login";
		}
	}
	/**
	 * 注销登录
	 * @return
	 */
	@Action("logout")
	public String logout() {
		getSession().remove("user");
		getSession().remove("indent");
		return "login";
	}
	
	/**
	 * 查看购物车
	 * @return
	 */
	@Action("cart")
	public String cart() {
		Users user = (Users) getSession().get("user");
		float allTotal = 0;
		if (user == null) {
			addActionError("请登录后查看订单!");
			return "login";
		}
		itemList = itemService.getItemUser(user.getId());
		if(itemList!=null&&!itemList.isEmpty()){
			itemList = itemService.getItemList(user.getId(), 0, 100);
			for (Items item : itemList) {
				allTotal = allTotal+item.getAmount()*item.getPrice();
			}
			getSession().put("itemList", itemList);
			getSession().put("allTotal", (float)Math.round(allTotal*100)/100);
		}else{
			getSession().put("itemList", null);
		}
		
		
		return "cart";
	}
	
	/**
	 * 购买-->加入购物车
	 * 根据用户和宠物Id 查，如果有，则数量+1，否则创建新的宠物项目
	 * @return
	 */
	@SuppressWarnings("unused")
	@Action("buy")
	public void buy(){
		Users user = (Users) getSession().get("user");
		if(user!=null){
			List<Items> itemEx = itemService.getItemPU(user,productService.get(productid));
			Items item =  new Items() ;
			if(itemEx.size()!=0){
				//如果购物车中有此宠物
				Product pro = productService.get(productid);
				itemService.saveItem(itemEx.get(0));
				sendResponseMsg("ok");
			}else{
				Product pro = productService.get(productid);
				ProductSale ps = productService.getSale(productid);
				//如果为促销宠物，则保存促销宠物价格
				if(ps!=null){
					pro.setPrice(ps.getPrice());
				}
				itemService.crateItems(user,pro);
				sendResponseMsg("ok");
			}
		}else{
			sendResponseMsg("login");
		}
		
	}
	
	/**
	 * 减少购物车宠物数量
	 */
	@Action("lessen")
	public void lessen(){
		Users user = (Users) getSession().get("user");
		if(user!=null){
			List<Items> itemEx = itemService.getItemPU(user,productService.get(productid));
			Items item = itemEx.size()==0 ? new Items() : itemEx.get(0);
			if(item !=null){
				if(item.getAmount()-1<=0){
					itemService.deleteItem(item);
					getSession().put("itemList", null);
				}else{
					getSession().put("itemList", itemService.lessItem( item));
				}
			}
			sendResponseMsg("ok");
		}else{
			sendResponseMsg("login");
		}
	}
	
	/**
	 * 删除购物车宠物
	 */
	@Action("delete")
	public void delete(){
		Users user = (Users) getSession().get("user");
		if(user!=null){
			List<Items> itemEx = itemService.getItemPU(user,productService.get(productid));
			Items item = itemEx.size()==0 ? new Items() : itemEx.get(0);
			if(item!=null){
				itemService.deleteItem(item);
				getSession().put("itemList", null);
			}
			sendResponseMsg("ok");
		}else{
			sendResponseMsg("login");
		}
	}
	
	
	/**
	 * 提交订单
	 * @return
	 */
	@Action("save")
	public void save(){
		Users user = (Users) getSession().get("user");
		if (user == null) {
			sendResponseMsg("login");
			//return "login";
		}
		//保存支付方式，地址
		List<Items> itemEx = itemService.getItemUser(user.getId());
		if(itemEx.size()>0){
			Indent indent = indentService.createIndent(user, itemEx);
			indent.setAddress(address);
			indent.setPaytype(paytype);
			indentService.saveIndent(indent);
			sendResponseMsg("ok");
		}
	}
	
	/**
	 * 查看订单
	 * @return
	 */
	@Action("order")
	public String order(){
		Users user = (Users) getSession().get("user");
		if (user == null) {
			addActionError("请登录后查看订单!");
			return "login";
		}
		indentList = indentService.getListByUserid(user.getId());
		if (indentList!=null && !indentList.isEmpty()) {
			for(Indent indent : indentList){
				indent.setItemList(indentService.getItemList(indent.getId(), 1, 100)); // 暂不分页
			}
		}
		return "order";
	}
	
	/**
	 * 确认收货
	 */
	@Action("configIndent")
	public void configIndent(){
		Users user = (Users) getSession().get("user");
		if (user == null) {
			sendResponseMsg("login");
		}
		if(indentService.updateStatus(indentid)){
			sendResponseMsg("ok");
		}
	}
	
	/**
	 * 删除订单
	 */
	@Action("deleteIndent")
	public void deleteIndent(){
		Users user = (Users) getSession().get("user");
		if (user == null) {
			sendResponseMsg("login");
		}
		//System.out.println("删除订单"+indentid);
		itemService.deleteByIndent(indentid);
		
		indentService.delete(indentid);
		sendResponseMsg("ok");
	}
	
	/**
	 * 添加评论
	 */
	@Action("addComment")
	public void addComment(){
		Users user = (Users) getSession().get("user");
		if (user == null) {
			sendResponseMsg("login");
		}
		Comments comment = new Comments();
		comment.setUser(user);
		comment.setProduct(productService.get(productid));
		comment.setContent(content);
		comment.settexttime(new Date());
		if(comment!=null){
			int flag = commentsService.createComment(comment);
			if(flag>0){
				sendResponseMsg("ok");
			}
		}
	}
	
	
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public List<Indent> getIndentList() {
		return indentList;
	}

	public void setIndentList(List<Indent> indentList) {
		this.indentList = indentList;
	}

	public int getPaytype() {
		return paytype;
	}

	public void setPaytype(int paytype) {
		this.paytype = paytype;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getIndentid() {
		return indentid;
	}

	public void setIndentid(int indentid) {
		this.indentid = indentid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
