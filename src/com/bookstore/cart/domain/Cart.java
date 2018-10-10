package com.bookstore.cart.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
/**
 * 购物车类
 * @author Administrator
 *
 */
public class Cart {
	private Map<String,CartItem> map = new LinkedHashMap();
	
	public double getTotal(){
		//合计=所有小计之和，用BigDecimal是为了免去二进制运算所带来的误差
		BigDecimal total = new BigDecimal("0");
		for(CartItem cartItem : map.values()){
			BigDecimal subtatal = new BigDecimal(""+cartItem.getSubtotal());
			total = total.add(subtatal);
		}
		return total.doubleValue();
	}
	
	/**
	 * 添加条目到车中
	 * @param cartitem
	 */
	public void add(CartItem cartItem){
		if(map.containsKey(cartItem.getBook().getBid())){
			//获取原有条目
			CartItem _carCartItem = map.get(cartItem.getBook().getBid());
			//在原有条目的基础上加上新条目数量
			_carCartItem.setCount(_carCartItem.getCount()+cartItem.getCount());
			//保存回去
			map.put(cartItem.getBook().getBid(), _carCartItem);
		}else{
			map.put(cartItem.getBook().getBid(), cartItem);
		}
	}
	/**
	 * 清空所有条目
	 */
	public void clear(){
		map.clear();
	}
	/**
	 * 删除指定条目
	 * @param bid
	 */
	public void delete(String bid){
		map.remove(bid);
	}
	/**
	 * 获取所以条目
	 * @return
	 */
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
}
