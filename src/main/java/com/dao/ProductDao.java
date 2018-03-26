package com.dao;

import com.entity.Category;
import com.entity.Product;
import com.entity.ProductNew;
import com.entity.ProductSale;
import com.entity.ProductShow;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // 注册dao层bean等同于@Component
@SuppressWarnings("unchecked")
public class ProductDao extends BaseDao{

	
	/**
	 * 	获取列表
	 * @param category
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Product> getProductList(int page, int size){
		return getSession().createQuery("from Product order by id desc").setFirstResult((page-1)*size).setMaxResults(size).list();
	}
	
	/**
	 * 	获取总数
	 * @param category
	 * @return
	 */
	public long getProductTotal(){
		return (Long) getSession().createQuery("select count(*) from Product").uniqueResult();
	}
	
	/**
	 * 	获取列表
	 * @param category
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Product> getCategoryList(int categoryid, int page, int size){
		return getSession().createQuery("from Product where category=:category order by id desc").setInteger("category", categoryid)
				.setFirstResult((page-1)*size).setMaxResults(size).list();
	}
	
	/**
	 * 	获取总数
	 * @param category
	 * @return
	 */
	public long getCategoryTotal(int categoryid){
		return (Long) getSession().createQuery("select count(*) from Product where category=:categoryid")
				.setInteger("categoryid", categoryid).uniqueResult();
	}
	
	/**
	 * 	获取列表
	 * @param category
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Product> getStatusList(int status, int page, int size) {
		return getSession().createQuery("from Product where id in ("+packProductids(status)+") order by id desc")
				.setFirstResult((page-1)*size).setMaxResults(size).list();
	}
	
	/**
	 * 	获取总数
	 * @param category
	 * @return
	 */
	public long getStatusTotal(int status) {
		return (Long) getSession().createQuery("select count(*) from Product where id in ("+packProductids(status)+")").uniqueResult();
	}
	
	/**
	 * 封装productids
	 * @param status
	 * @return
	 */
	private String packProductids(int status) {
		String productids = "";
		switch (status) {
		case 1:
			List<ProductShow> showList = getShowList();
			for (ProductShow show : showList) {
				productids += show.getProduct().getId() + ",";
			}
			break;
		case 2:
			List<ProductSale> saleList = getSaleList();
			for (ProductSale sale : saleList) {
				productids += sale.getProduct().getId() + ",";
			}
			break;
		case 3:
			List<ProductNew> newList = getNewList();
			for (ProductNew news : newList) {
				productids += news.getProduct().getId() + ",";
			}
			break;
		}
		return productids.substring(0, productids.length()-1);
	}

	/**
	 * 	获取列表
	 * @param category
	 * @param page
	 * @param size
	 * @return
	 */
	/*public List<Product> getSearchList(String search, int page, int size){
		return getSession().createQuery("from Product where name like :search order by id desc")
				.setString("search", "%"+search+"%").setFirstResult((page-1)*size).setMaxResults(size).list();
	}*/
	
	public List<Product> getSearchList(String search, int page, int size){
		
		//CategoryDao cDao = new  CategoryDao();
		//List<Category> categories= cDao.getList(search);
		
		
		List<Category> categories= getSession()
				.createQuery("from Category where name like :search")
				.setString("search", "%" + search + "%")
				.setFirstResult(0).setMaxResults(10).list();
		
		String category_id ="";
		String hqlString="from Product where name like :search ";
		if(categories!=null && categories.size()>0){
			category_id = categories.get(0).getId()+"";
			hqlString+=" or  category_id = " + category_id;
		}
		hqlString+=" order by id desc";
		
		List<Product> list = getSession().createQuery(hqlString)
				.setString("search", "%"+search+"%").setFirstResult((page-1)*size).setMaxResults(size).list();
		
		
		return list;
	}
	
	/**
	 * 	获取总数
	 * @param category
	 * @return
	 */
	public long getSearchTotal(String search){
		return (Long) getSession().createQuery("select count(*) from Product where name like :search")
				.setString("search", "%"+search+"%").uniqueResult();
	}
	
	/**
	 * 	获取特卖列表
	 * @param page
	 * @param size
	 * @return
	 */
	public List<ProductShow> getShowList(){
		return getSession().createQuery("from ProductShow order by id desc").list();
	}
	
	
	/**
	 * 	获取特卖列表
	 * @param page
	 * @param size
	 * @return
	 */
	public List<ProductShow> getShowList(int page, int size){
		return getSession().createQuery("from ProductShow order by id desc")
				.setFirstResult((page-1)*size).setMaxResults(size).list();
	}
	
	/**
	 * 	获取特卖总数
	 * @return
	 */
	public long getShowTotal(){
		return (Long) getSession().createQuery("select count(*) from ProductShow").uniqueResult();
	}
	
	/**
	 * 	获取促销列表
	 * @param page
	 * @param size
	 * @return
	 */
	public List<ProductSale> getSaleList(){
		return getSession().createQuery("from ProductSale order by id desc").list();
	}
	
	/**
	 * 	获取促销列表
	 * @param page
	 * @param size
	 * @return
	 */
	public List<ProductSale> getSaleList(int page, int size){
		return getSession().createQuery("from ProductSale order by id desc")
				.setFirstResult((page-1)*size).setMaxResults(size).list();
	}
	
	/**
	 * 	获取促销总数
	 * @return
	 */
	public long getSaleTotal(){
		return (Long) getSession().createQuery("select count(*) from ProductSale").uniqueResult();
	}
	
	/**
	 * 	获取新品列表
	 * @param page
	 * @param size
	 * @return
	 */
	public List<ProductNew> getNewList(){
		return getSession().createQuery("from ProductNew order by id desc").list();
	}
	
	/**
	 * 	获取新品列表
	 * @param page
	 * @param size
	 * @return
	 */
	public List<ProductNew> getNewList(int page, int size){
		return getSession().createQuery("from ProductNew order by id desc")
				.setFirstResult((page-1)*size).setMaxResults(size).list();
	}
	
	/**
	 * 	获取新品总数
	 * @return
	 */
	public long getNewTotal(){
		return (Long) getSession().createQuery("select count(*) from ProductNew").uniqueResult();
	}
	
	/**
	 * 获取
	 * @param productid
	 * @return
	 */
	public ProductSale getSale(int productid) {
		return (ProductSale) getSession().createQuery("from ProductSale where product_id=?").setInteger(0, productid).uniqueResult();
	}
	
	/**
	 * 获取
	 * @param productid
	 * @return
	 */
	public ProductShow getShow(int productid) {
		return (ProductShow) getSession().createQuery("from ProductShow where product_id=?").setInteger(0, productid).uniqueResult();
	}
	
	/**
	 * 获取
	 * @param productid
	 * @return
	 */
	public ProductNew getNew(int productid) {
		return (ProductNew) getSession().createQuery("from ProductNew where product_id=?").setInteger(0, productid).uniqueResult();
	}
	
}
