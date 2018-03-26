package com.dao;

import com.entity.Items;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // 注册dao层bean等同于@Component
@SuppressWarnings("unchecked")
public class ItemsDao extends BaseDao{

	/**
	 * 订单项列表
	 * @param indentid
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<Items> getItemList(int userid,int page,int rows){
		return getSession().createQuery("from Items where indent_id is null and user_id="+userid).setFirstResult((page-1)*rows).list();
	}
	
	/**
	 * 订单项总数
	 * @param indentid
	 * @return
	 */
	public long getItemTotal(int indentid){
		return (Long) getSession().createQuery("select count(*) from Items where indent_id="+indentid).uniqueResult();
	}
	
	/**
	 * 订单项列表根据用户查询
	 * @param userid
	 * @return
	 */
	public List<Items> getListByUserid(int userid){
		return getSession().createQuery("from Items where user_id="+userid+" and indent_id is null order by id").list();
	}
	
	/**
	 * 订单项列表根据用户查询
	 * @param userid
	 * @return
	 */
	public List<Items> getListByAUserid(int userid){
		return getSession().createQuery("from Items where user_id="+userid+" order by id").list();
	}
	/**
	 * 订单项列表根据宠物查询
	 * @param productid
	 * @return
	 */
	public List<Items> getListByProductId(int productid){
		return getSession().createQuery("from Items where product_id="+productid+" order by id").list();
	}
	
	/**
	 * 根据用户查找购物车中的宠物
	 * @param userid
	 * @param productid
	 * @return
	 */
	public List<Items> getListByPuId(int userid,int productid){
		return getSession().createQuery("from Items where product_id="+productid+" and user_id="+userid+" and indent_id is null order by id").list();
	}
	
	/**
	 * 根据订单id，查找订单项
	 * @param indent
	 * @return
	 */
	public List<Items> getListByIndent(int indentid){
		return getSession().createQuery("from Items where indent_id ="+indentid+" order by id").list();
	}
	
	public long getCount(int userid){
		return (Long)getSession().createQuery("select count(1) from Items where user_id="+userid).uniqueResult();
	}
}
