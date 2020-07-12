package com.quietnight.user.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.quietnight.entity.User;
import com.quietnight.user.dao.UserDao;

@Service
@Transactional(readOnly=false)
public class UserService {

	@Resource
	private UserDao userDao;
	
	//用户登录：根据手机号和密码查询用户
	@Transactional(readOnly=true)
	public User login(String telephone,String password){
		return this.userDao.queryOneUser(telephone, password);
	}
	
	//根据用户id重置密码
	public String resetPassword(Integer id,String password){
		return this.userDao.updatePassword(id, password);
	}
	
	//根据用户id修改用户昵称
	public String resetName(Integer id,String name){
		return this.userDao.updateName(id, name);
	}
	
	//根据用户id修改用户性别
	public String resetSex(Integer id,Integer sex){
		return this.userDao.updateSex(id, sex);
	}
	
	//根据用户id修改用户地址
	public String resetAddress(Integer id,String address){
		return this.userDao.updateAddress(id, address);
	}
}
