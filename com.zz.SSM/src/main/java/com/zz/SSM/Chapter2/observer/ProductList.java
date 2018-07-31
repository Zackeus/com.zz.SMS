package com.zz.SSM.Chapter2.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * 
 * @Title:ProductList
 * @Description:TODO(被观察的产品列表)
 * @Company: 
 * @author zhou.zhang
 * @date 2018年7月31日 下午3:47:59
 */
public class ProductList extends Observable {
	
	private List<String> productList = null; // 产品列表
	
	private static ProductList instance; // 类唯一实例
	
	private ProductList() {
		// 构建方法私有化
	}

	/**
	 * 
	 * @Title:getInstance
	 * @Description: TODO(取得唯一实例)
	 * @return
	 */
	public static ProductList getInstance() {
		if (instance == null) {
			instance = new ProductList();
			instance.productList = new ArrayList<>();
		}
		return instance;
	}
	
	/**
	 * 
	 * @Title:addProductListObserver
	 * @Description: TODO(增加观察者(电商接口))
	 * @param observer
	 */
	public void addProductListObserver(Observer observer) {
		this.addObserver(observer);
	}
	
	/**
	 * 
	 * @Title:addProduct
	 * @Description: TODO(新增产品)
	 * @param newProduct
	 */
	public void addProduct(String newProduct) {
		productList.add(newProduct);
		System.out.println("产品列表新增了产品：" + newProduct);
		this.setChanged(); // 设置被观察对象发生了变化
		this.notifyObservers(newProduct); // 通知观察者，并传递新产品
	}
	
	/**
	 * 
	 * @Title:main
	 * @Description: TODO(测试)
	 * @param args
	 */
	public static void main(String[] args) {
		ProductList observable = ProductList.getInstance();
		TaoBaoObserver taoBaoObserver = new TaoBaoObserver();
		JingDongObserver jingDongObserver = new JingDongObserver();
		observable.addObserver(jingDongObserver);
		observable.addObserver(taoBaoObserver);
		observable.addProduct("产品22");
	}
}
