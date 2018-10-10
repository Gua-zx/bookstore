package com.bookstore.user.service;

import com.bookstore.user.dao.UserDao;
import com.bookstore.user.domain.User;

/**
 * User业务层
 */
public class UserService {
	private UserDao userDao = new UserDao();
	/**
	 * 注册功能
	 * @param form
	 */
	public void regist(User form) throws  UserException{
		User user = userDao.findByUsername(form.getUsername());//校验用户名
		if(user !=null) throw new UserException("用户名已存在");//创建自定义异常类
		//校验email
		user = userDao.findByUsername(form.getEmail());
		if(user !=null) throw new UserException("Email已被注册");
		//插入用户到数据库
		userDao.add(form);
	}
	public void active(String code)throws UserException{
		//使用code查询数据库，得到user
		User user = userDao.findByCode(code);
		//判断user是否存在
		if(user == null)throw new UserException("激活码无效");
		//校验用户激活状态
		if(user.isState())throw new UserException("用户已激活，请勿重复激活");
		//修改用户状态
		userDao.updateState(user.getUid(), true);
	}
	/**
	 * 登录功能
	 * @param form
	 * @return
	 * @throws UserException 
	 */
	public User login(User form) throws UserException{
		/*
		 * 1. 使用username查询，得到User
		 * 2. 如果user为null，抛出异常（用户名不存在）
		 * 3. 比较form和user的密码，若不同，抛出异常（密码错误）
		 * 4. 查看用户的状态，若为false，抛出异常（尚未激活）
		 * 5. 返回user
		 */
		User user = userDao.findByUsername(form.getUsername());
		if(user == null) throw new UserException("用户名不存在！");
		if(!user.getPassword().equals(form.getPassword()))
			throw new UserException("密码错误！");
		if(!user.isState()) throw new UserException("尚未激活！");
		
		return user;
	}
}
