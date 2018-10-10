package com.bookstore.user.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.bookstore.user.domain.User;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(
		urlPatterns = { 
				"/jsps/cart/*", 
				"/jsps/order/*"
		}, 
		servletNames = { 
				"CartServlet", 
				"OrderServlet"
		})
public class LoginFilter implements Filter {


	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/*
		 * 1.从session中获取用户信息
		 * 2.判断session中存在用户就放行
		 * 3.否则就保存错误信息，转发到login.jsp
		 */
		//先强转request
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		User user = (User)httpRequest.getSession().getAttribute("session_user");
		if(user != null){
		chain.doFilter(request, response);
		}else{
			httpRequest.setAttribute("msg", "请先登录");
			httpRequest.getRequestDispatcher("/jsps/user/login.jsp").forward(httpRequest, response);
		}
		
	}


	public void init(FilterConfig fConfig) throws ServletException {
	
	}

}
