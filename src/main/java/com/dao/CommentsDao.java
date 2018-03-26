package com.dao;

import com.entity.Comments;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //注册dao层bean
@SuppressWarnings("unchecked")
public class CommentsDao extends BaseDao {

	/**
	 * 评论列表
	 * @param commentid
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<Comments> getCommentsList(int page, int rows){
		return getSession().createQuery("from Comments ")
				.setFirstResult((page-1)*rows).setMaxResults(rows).list();
	}
	
	/**
	 * 评论列表
	 * @param commentid
	 * @return
	 */
	public List<Comments> getCommentsList(int commentid){
		return getSession().createQuery("from Comments where id = "+commentid).list();
	}
	
	/**
	 * 总数
	 * @return
	 */
	public long getTotal() {
		return (Long) getSession().createQuery("select count(*) from Comments").uniqueResult();
	}
	
	public List<Comments> getListByPro(int productid){
		System.out.println("dao"+productid);
		return getSession().createQuery("from Comments where product_id="+productid).list();
	}
}
