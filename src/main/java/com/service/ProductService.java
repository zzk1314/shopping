package com.service;

import com.dao.ProductDao;
import com.entity.Product;
import com.entity.ProductNew;
import com.entity.ProductSale;
import com.entity.ProductShow;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service	// 注解为service层spring管理bean
@Transactional	// 注解此类所有方法加入spring事务, 具体设置默认
public class ProductService {

	@Resource	
	private ProductDao productDao;
	
	
	/**
	 * 通过分类搜索
	 * @param categoryid
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Product> getCategoryList(int categoryid, int page, int size) {
		if (categoryid > 0) {
			return packProductPrice(productDao.getCategoryList(categoryid, page, size));
		}
		return packProductPrice(productDao.getProductList(page, size));
	}
	
	/**
	 * 获取数量
	 * @param categoryid
	 * @return
	 */
	public long getCategoryTotal(int categoryid){
		if (categoryid > 0) {
			return productDao.getCategoryTotal(categoryid);
		}
		return productDao.getProductTotal();
	}
	
	/**
	 * 通过分类搜索
	 * @param status
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Product> getStatusList(int status, int page, int size) {
		if (status > 0) {
			return packProductPrice(productDao.getStatusList(status, page, size));
		}
		return packProductPrice(productDao.getProductList(page, size));
	}
	
	/**
	 * 获取数量
	 * @param status
	 * @return
	 */
	public long getStatusTotal(int status){
		if (status > 0) {
			return productDao.getStatusTotal(status);
		}
		return productDao.getProductTotal();
	}
	
	/**
	 * 通过名称搜索
	 * @param categoryid
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Product> getSearchList(String search, int page, int size) {
		return packProductPrice(productDao.getSearchList(search, page, size));
	}
	
	/**
	 * 获取数量
	 * @param categoryid
	 * @return
	 */
	public long getSearchTotal(String search){
		return productDao.getSearchTotal(search);
	}
	
	/**
	 * 获取特卖列表
	 * @return 无记录返回空集合
	 */
	public List<ProductShow> getShowList(int page, int size){
		List<ProductShow> list = productDao.getShowList(page, size);
		for (ProductShow p : list) {
			p.setProduct(packProductPrice(p.getProduct()));;
		}
		return list;
	}
	
	/**
	 * 获取特卖数量
	 * @return
	 */
	public long getShowTotal(){
		return productDao.getShowTotal();
	}
	
	/**
	 * 获取促销列表
	 * @return 无记录返回空集合
	 */
	public List<ProductSale> getSaleList(int page, int size){
		List<ProductSale> list = productDao.getSaleList(page, size);
		for (ProductSale sale : list) {
			sale.setPrice(sale.getProduct().getPrice() * sale.getDiscount() / 100);
		}
		return list;
	}
	
	/**
	 * 获取促销数量
	 * @return
	 */
	public long getSaleTotal(){
		return productDao.getSaleTotal();
	}
	
	/**
	 * 获取新品列表
	 * @return 无记录返回空集合
	 */
	public List<ProductNew> getNewList(int page, int size){
		List<ProductNew> list = productDao.getNewList(page, size);
		for (ProductNew p : list) {
			p.setProduct(packProductPrice(p.getProduct()));;
		}
		return list;
	}
	
	/**
	 * 获取新品数量
	 * @return
	 */
	public long getNewTotal(){
		return productDao.getShowTotal();
	}

	/**
	 * 通过id获取
	 * @param productid
	 * @return
	 */
	public Product get(int productid) {
		return packProductPrice(productDao.get(Product.class, productid));
	}
	
	/**
	 * 通过id获取
	 * @param productid
	 * @return
	 */
	public ProductSale getSale(int productid) {
		ProductSale sale = productDao.getSale(productid);
		if (sale != null) {
			sale.setPrice(sale.getProduct().getPrice() * sale.getDiscount() / 100);
		}
		return sale;
	}
	
	/**
	 * 通过id获取
	 * @param productid
	 * @return
	 */
	public ProductShow getShow(int productid) {
		return productDao.getShow(productid);
	}
	
	/**
	 * 通过id获取
	 * @param productid
	 * @return
	 */
	public ProductNew getNew(int productid) {
		return productDao.getNew(productid);
	}

	/**
	 * 添加
	 * @param product
	 */
	public Integer add(Object product) {
		return productDao.save(product);
	}

	/**
	 * 修改
	 * @param product
	 * @return 
	 */
	public boolean update(Product product) {
		return productDao.update(product);
	}

	/**
	 * 删除
	 * @param product
	 */
	public boolean delete(int productid) {
		Product product = get(productid);
		if (product.getIsShow()) {
			delete(getShow(productid)); // 删除推荐信息
		}
		if (product.getIsSale()) {
			delete(getSale(productid)); // 删除促销信息
		}
		if (product.getIsNew()) {
			delete(getNew(productid)); // 删除新品信息
		}
		return productDao.delete(product);
	}
	
	/**
	 * 删除
	 * @param product
	 */
	public boolean delete(Object obj) {
		return productDao.delete(obj);
	}

	/**
	 * 封装价格
	 * @param product
	 * @return
	 */
	public Product packProductPrice(Product product){
		ProductSale sale = getSale(product.getId());
		if (sale != null) {
			// 将product对象移出session, 防止对象更新后自动update
			productDao.getSession().evict(product);
			product.setPrice(sale.getPrice());
			product.setSale(true);
		}
		ProductShow show = getShow(product.getId());
		if (show != null) {
			product.setShow(true);
		}
		ProductNew news = getNew(product.getId());
		if (news != null) {
			product.setNew(true);
		}
		return product;
	}

	/**
	 * 封装价格
	 * @param productList
	 * @return
	 */
	public List<Product> packProductPrice(List<Product> productList){
		for(Product product : productList){
			product = packProductPrice(product);
		}
		return productList;
	}

}
