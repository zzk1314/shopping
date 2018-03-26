package com.entity;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity //注解为hibernate实体
@Table(name="comments") //注解对应表名
public class Comments implements Serializable{

	@Id //注解主键
	@GeneratedValue //id生成策略  默认auto 相当于hibernate的native - 自增字段
	private int id;
	@ManyToOne()
	@NotFound(action=NotFoundAction.IGNORE)
	private Product product;
	@ManyToOne()
	@NotFound(action=NotFoundAction.IGNORE)
	private Users user;
	private String content;
	private String reply;
	private Date texttime;
	private Date replytime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public Date gettexttime() {
		return texttime;
	}
	public void settexttime(Date texttime) {
		this.texttime = texttime;
	}
	public Date getreplytime() {
		return replytime;
	}
	public void setreplytime(Date replytime) {
		this.replytime = replytime;
	}
	
	
}
