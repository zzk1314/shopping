package com.service;

import com.dao.ItemsDao;
import com.entity.Items;
import com.entity.Product;
import com.entity.Users;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service	// 注解为service层spring管理bean
@Transactional	// 注解此类所有方法加入spring事务, 具体设置默认
public class ItemService {

	@Resource
	private ItemsDao itemsDao;
	
	/**
	 * 创建订单项
	 * @param product
	 * @return
	 */
	public Items crateItems(Users user, Product product){
		Items item = new Items();
		item.setProduct(product);
		item.setAmount(1);
		item.setPrice(product.getPrice());
		item.setTotal(product.getPrice());
		item.setUser(user);
		itemsDao.save(item);
		return item;
	}
	
	/**
	 * 根据用户ID查询订单项列表
	 * @param userid
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<Items> getItemList(int userid,int page,int rows){
		List<Items> itemList = itemsDao.getItemList(userid, page, rows);
		if (itemList!=null && !itemList.isEmpty()) {
			for (Items item : itemList) {
				item.setTotal(item.getPrice() * item.getAmount());
			}
		}
		return itemList;
	}
	
	/**
	 * 根据宠物ID查询订单项列表
	 * @param product
	 * @return
	 */
	public List<Items> getItemProdcut(Product product){
		List<Items> itemProdcut = itemsDao.getListByProductId(product.getId());
		return itemProdcut;
	}
	
	public List<Items> getItemPU(Users user, Product product){
		List<Items> itemPU = itemsDao.getListByPuId(user.getId(), product.getId());
		return itemPU;
	}
	/**
	 * 根据用户id查找订单项
	 * @param userid
	 * @return
	 */
	public List<Items> getItemUser(int userid){
		List<Items> itemUser = itemsDao.getListByUserid(userid);
		return itemUser;
	}
	/**
	 * 根据用户id查找订单项
	 * @param userid
	 * @return
	 */
	public List<Items> getItemAUser(int userid){
		List<Items> itemUser = itemsDao.getListByAUserid(userid);
		return itemUser;
	}
	
	/**
	 * 增加宠物数量
	 * @param pro
	 * @param item
	 * @return
	 */
	public boolean saveItem(Items item){
		item.setAmount(item.getAmount()+1);
		item.setTotal(item.getPrice()*item.getAmount());
		return itemsDao.update(item);
	}
	
	/**
	 * 减少宠物数量
	 * @param pro
	 * @param item
	 * @return
	 */
	public boolean lessItem(Items item){
		item.setAmount(item.getAmount()-1);
		item.setTotal(item.getPrice()*item.getAmount());
		return itemsDao.update(item);
	}
	
	public boolean updateItem(Items item){
		return itemsDao.update(item);
	}
	/**
	 * 删除订单项
	 * @param item
	 * @return
	 */
	public boolean deleteItem(Items item){
		return itemsDao.delete(item);
	}
	
	/**
	 * 根据订单查找宠物
	 * @param indent
	 * @return
	 */
	public List<Items> getListIndent(int indentid){
		return itemsDao.getListByIndent(indentid);
	}
	
	public boolean deleteByIndent(int indentid){
		List<Items> list = this.getListIndent(indentid);
		boolean flag = false;
		if(list.size()>0){
			for (Items items : list) {
				flag = itemsDao.delete(items);
			}
			
		}
		
		return flag;
	}
	
	public int getItemCount(int userid){
		return (int)itemsDao.getCount(userid);
	}
}
