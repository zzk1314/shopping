package com.service;

import com.dao.CommentsDao;
import com.entity.Comments;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service //注解为service层spring管理bean
@Transactional  //注解此类所有方法加入spring事务，设置默认
public class CommentsService {

	@Resource
	private CommentsDao commentsDao;
	
	/**
	 * 添加评论
	 * @param comment
	 * @return
	 */
	public int createComment(Comments comment){
		return commentsDao.save(comment);
	}
	
	/**
	 * 查询所有评论
	 * @param commentid
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<Comments> getCommentsList(int page,int rows){
		System.out.println("查询评论");
		return commentsDao.getCommentsList(page, rows);
	}
	
	/**
	 * 总数
	 * @return
	 */
	public long getTotal() {
		return commentsDao.getTotal();
	}
	
	/**
	 * 删除评论
	 * @param commentid
	 * @return
	 */
	public boolean deleteComment(int commentid){
		Comments comments = new Comments();
		comments.setId(commentid);
		return commentsDao.delete(comments);
	}
	
	/**
	 * 添加回复
	 * @param commentid
	 * @param reply
	 * @return
	 */
	public boolean updateComment(int commentid,String reply){
		List<Comments> list = commentsDao.getCommentsList(commentid);
		boolean flag = false;
		if(list.size()>0){
			for (Comments comments : list) {
				comments.setReply(reply);
				comments.setreplytime(new Date());
				flag = commentsDao.update(comments);
			}
		}
		return flag;
	}
	
	public List<Comments> getListProduct(int productid){
		System.out.println("service"+productid);
		return commentsDao.getListByPro(productid);
	}
}
