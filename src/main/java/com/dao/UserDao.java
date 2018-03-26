package com.dao;

import com.entity.Users;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // 注册dao层bean等同于@Component
public class UserDao extends BaseDao{

	
	/**
	 * 通过用户名查找用户
	 * @return 无记录返回null
	 */
	public Users getByUsername(String username){
		return (Users) getSession().createSQLQuery("select * from Users where username=?")
		.addEntity(Users.class).setString(0, username).uniqueResult();
	}
	
	/**
	 * 通过用户名和密码查找
	 * @param username
	 * @param password
	 * @return 无记录返回null
	 */
	public Users getByUsernameAndPassword(String username, String password){
		return (Users)getSession().createSQLQuery("select * from users where username=? and password=?")
				.addEntity(Users.class).setString(0, username).setString(1, password).uniqueResult();	// SQL语句方式
	}
	
	/**
	 * 获取列表
	 * @param page
	 * @param rows
	 * @return 无记录返回空集合
	 */
	@SuppressWarnings("unchecked")
	public List<Users> getList(int page, int rows){
		return getSession().createQuery("from Users").setFirstResult(rows*(page-1)).setMaxResults(rows).list();
	}

	/**
	 * 总数
	 * @return
	 */
	public long getTotal() {
		return (Long) getSession().createQuery("select count(*) from Users").uniqueResult();
	}
	
}
