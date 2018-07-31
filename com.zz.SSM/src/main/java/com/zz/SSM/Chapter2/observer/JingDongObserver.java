package com.zz.SSM.Chapter2.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * 
 * @Title:JingDongObserver
 * @Description:TODO(京东电商接口)
 * @Company: 
 * @author zhou.zhang
 * @date 2018年7月31日 下午4:01:22
 */
public class JingDongObserver implements Observer {
	
	@Override
	public void update(Observable o, Object product) {
		String newProduct = (String) product;
		System.out.println("发送新产品【" + newProduct + "】 同步到京东商场");
	}
}
