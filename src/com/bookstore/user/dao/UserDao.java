package com.bookstore.user.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.bookstore.user.domain.User;

import cn.itcast.jdbc.TxQueryRunner;

/**
 * User持久层
 * @author Administrator
 *
 */
public class UserDao {
	private QueryRunner qr = new TxQueryRunner();
	/**
	 * 按用户名查询
	 * @param username
	 * @return
	 */
	public User findByUsername(String username){
		try{
			String sql = "SELECT * FROM tb_user WHERE username=?";
			return qr.query(sql, new BeanHandler<User>(User.class),username);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	/**
	 * 按邮箱查询
	 * @param email
	 * @return
	 */
	public User findByEmail(String email){
		try{
			String sql = "SELECT * FROM tb_user WHERE email=?";
			return qr.query(sql, new BeanHandler<User>(User.class),email);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	/**
	 * 添加用户
	 * @param user
	 */
	public void add(User user){
		try{
			String sql = "INSERT INTO tb_user value(?,?,?,?,?,?)";
			Object[] params = {user.getUid(),user.getUsername(),user.getPassword(),
					user.getEmail(),user.getCode(),user.isState()};
			qr.update(sql,params);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	/**
	 * 查询激活码
	 * @param code
	 * @return
	 */
	public User findByCode(String code){
		try{
			String sql = "SELECT * FROM tb_user WHERE code=?";
			return qr.query(sql, new BeanHandler<User>(User.class),code);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	/**
	 * 修改指定用户的指定状态
	 * @param uid
	 * @param state
	 */
	public void updateState(String uid,boolean state){
		try{
			String sql = "UPDATE tb_user SET state=? WHERE uid=?";
			qr.update(sql,state,uid);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
}
