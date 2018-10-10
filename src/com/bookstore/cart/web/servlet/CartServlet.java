package com.bookstore.cart.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.servlet.BaseServlet;

import com.bookstore.book.domain.Book;
import com.bookstore.book.service.BookService;
import com.bookstore.cart.domain.Cart;
import com.bookstore.cart.domain.CartItem;

public class CartServlet extends BaseServlet {
	/**
	 * 添加购物条目
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 得到车，得到条目（图书和数量），设置条目，添加到车中
		 */
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		//表单传递过来bid和数量，用bid去数据库获取Book
		String bid = request.getParameter("bid");
		Book book = new BookService().load(bid);
		int count = Integer.parseInt(request.getParameter("count"));
		//创建购物车条目类，设置图书和数量
		CartItem cartItem = new CartItem();
		cartItem.setBook(book);
		cartItem.setCount(count);
		//把条目添加到车中
		cart.add(cartItem);
		return "f:/jsps/cart/list.jsp";
	}
	
	/**
	 * 清空购物条目
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String clear(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//得到车，调用它的clear方法
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		cart.clear();
		return "f:/jsps/cart/list.jsp";
	}
	/**
	 * 删除购物条目
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//得到车，调用它的clear方法
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		//得到要输出的bid
		String bid = request.getParameter("bid");
		//删除
		cart.delete(bid);
		return "f:/jsps/cart/list.jsp";
	}
}
