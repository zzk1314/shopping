package com.dao;

import com.entity.Indent;
import com.entity.Items;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // 注册dao层bean等同于@Component
@SuppressWarnings("unchecked")
public class IndentDao extends BaseDao {

	/**
	 * 获取订单列表
	 * @param page
	 * @param row
	 */
	public List<Indent> getList(int status, int page, int rows) {
		return getSession().createQuery("from Indent where status="+status+" order by id desc").setFirstResult((page-1)*rows).setMaxResults(rows).list();
	}

	/**
	 * 获取总数
	 * @return
	 */
	public long getTotal(int status) {
		return (Long) getSession().createQuery("select count(*) from Indent where status="+status).uniqueResult();
	}

	/**
	 * 订单项列表
	 * @param indentid
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<Items> getItemList(int indentid, int page, int rows) {
		return getSession().createQuery("from Items where indent_id="+indentid).setFirstResult((page-1)*rows).setMaxResults(rows).list();
	}

	/**
	 * 订单id查找订单列表
	 * @param indentid
	 * @return
	 */
	public List<Indent> getIndentList(int indentid) {
		return getSession().createQuery("from Indent where id="+indentid).list();
	}
	/**
	 * 订单项总数
	 * @return
	 */
	public long getItemTotal(int indentid) {
		return (Long) getSession().createQuery("select count(*) from Items where indent_id="+indentid).uniqueResult();
	}

	/**
	 * 获取某用户全部订单
	 * @param userid
	 */
	public List<Indent> getListByUserid(int userid) {
		return getSession().createQuery("from Indent where user_id="+userid+" order by id desc").list();
	}

}
