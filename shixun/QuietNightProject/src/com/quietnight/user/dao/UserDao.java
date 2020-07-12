package com.quietnight.user.dao;


import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.quietnight.entity.User;

@Repository
public class UserDao {
	
	@Resource
	private SessionFactory sessionFactory;
		
	/**
	 * 
	     * @Title: queryOneUser
	     * @Description: TODO(这里用一句话描述这个方法的作用) 用户登录：根据手机号和密码查询用户
	     * @param @param telephone
	     * @param @param password
	     * @param @return 参数：返回查询到的用户
	     * @return User 返回类型
	     * @throws
	 */
	public User queryOneUser(String telephone,String password){
		Query query=this.sessionFactory.getCurrentSession().createQuery("from User where telephone=? and password=?");
		query.setParameter(0, telephone);
		query.setParameter(1,password);
		Object o=query.uniqueResult();
		if(null==o){
			return null;
		}else{
			return (User)o;
		}

	}
	
	/**
	 * 
	     * @Title: updatePassword
	     * @Description: TODO(这里用一句话描述这个方法的作用) 根据用户id重置密码
	     * @param @param id 用户id
	     * @param @param password
	     * @param @return 参数：修改成功标志ok
	     * @return String 返回类型
	     * @throws
	 */
	public String updatePassword(Integer id,String password){
		User user=this.sessionFactory.getCurrentSession().get(User.class,new Integer(id));
		user.setPassword(password);
		this.sessionFactory.getCurrentSession().save(user);
		return "ok";
	}
	
	/**
	 * 
	     * @Title: updateName
	     * @Description: TODO(这里用一句话描述这个方法的作用) 根据用户id修改用户昵称
	     * @param @param id：用户id
	     * @param @param name：要修改成的用户昵称
	     * @param @return 参数：返回修改成功或失败
	     * @return String 返回类型
	     * @throws
	 */
	public String updateName(Integer id,String name){
		User user=this.sessionFactory.getCurrentSession().get(User.class,new Integer(id));
		user.setName(name);
		this.sessionFactory.getCurrentSession().save(user);
		return "ok";
	}
	
	/**
	 * 
	     * @Title: updateSex
	     * @Description: TODO(这里用一句话描述这个方法的作用) 根据用户id修改用户性别
	     * @param @param id：用户id
	     * @param @param sex：要修改成的性别（0：女，1：男）
	     * @param @return 参数：返回修改成功或失败
	     * @return String 返回类型
	     * @throws
	 */
	public String updateSex(Integer id,Integer sex){
		User user=this.sessionFactory.getCurrentSession().get(User.class,new Integer(id));
		user.setSex(sex);
		this.sessionFactory.getCurrentSession().save(user);
		return "ok";
	}
	
	/**
	 * 
	     * @Title: updateAddress
	     * @Description: TODO(这里用一句话描述这个方法的作用) 根据用户id修改用户地址
	     * @param @param id：用户id
	     * @param @param address：要修改成的用户地址
	     * @param @return 参数：返回修改成功或失败
	     * @return String 返回类型
	     * @throws
	 */
	public String updateAddress(Integer id,String address){
		User user=this.sessionFactory.getCurrentSession().get(User.class,new Integer(id));
		user.setArea(address);
		this.sessionFactory.getCurrentSession().save(user);
		return "ok";
	}
}
