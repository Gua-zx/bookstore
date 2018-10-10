package com.bookstore.cart.domain;

import java.math.BigDecimal;

import org.apache.commons.fileupload.disk.DiskFileItem;

import com.bookstore.book.domain.Book;

public class CartItem {
	private Book book;//商品
	private int count;//数量
	/**
	 * 小计方法，处理了二进制运算的误差问题
	 * @return
	 */
	public double getSubtotal(){//小计 方法,但没有对应的成员
		BigDecimal d1 = new BigDecimal(book.getPrice()+"");//+""是为了转换成字符类型
		BigDecimal d2 = new BigDecimal(count+"");
		return d1.multiply(d2).doubleValue();
	}
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "CartItem [book=" + book + ", count=" + count + "]";
	}
	
}
