package com.entity;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

@Entity // 注解为hibernate实体
@Table(name="items") // 注解对应的表名
public class Items implements Serializable{
	
	@Id	// 注解主键
	@GeneratedValue //id生成策略  默认auto 相当于hibernate的native - 自增字段
	private int id;
	private float price;
	private int amount;
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	private Product product;
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	private Indent indent;
	@Transient
	private float total;
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	private Users user;
	@Transient
	private int countItem;

	
	public int getCountItem() {
		return countItem;
	}
	public void setCountItem(int countItem) {
		this.countItem = countItem;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Indent getIndent() {
		return indent;
	}
	public void setIndent(Indent indent) {
		this.indent = indent;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = (float)Math.round(total*100)/100;
	}
	
	@Override
	public String toString() {
		return "ID"+this.id+"数量"+this.amount+
				"订单"+this.indent+"单价"+this.price+"总价"+this.total;
	}
}
