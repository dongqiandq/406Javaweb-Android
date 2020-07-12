package com.quietnight.user.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.quietnight.entity.User;
import com.quietnight.user.service.UserService;

@RestController
@RequestMapping(value="/user",produces="application/json; charset=utf-8")
public class UserController {
	@Resource
	private UserService userService;

	/**
	 * 
	 * 
	     * @Title: login
	     * @Description: TODO(这里用一句话描述这个方法的作用) 用户登录：根据手机号和密码查询用户
	     * @param @param request
	     * @param @return 参数:返回查询到的用户
	     * @return String 返回类型
	     * @throws
	 */
	@RequestMapping("/normallogin")
	public String login(HttpServletRequest request){
		try {
			InputStream is=request.getInputStream();
			byte[] buffer=new byte[64];
			int len=is.read(buffer);
			is.close();
			String content=new String(buffer,0,len);
			String[] array=content.split(",");
			User user=this.userService.login(array[0],array[1]);
			if(null==user){
				return "no";
			}else{
				return user.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * 
	     * @Title: resetPassword
	     * @Description: TODO(这里用一句话描述这个方法的作用) 根据用户id重置密码
	     * @param @param request
	     * @param @return 参数:修改成功或失败的标志
	     * @return String 返回类型
	     * @throws
	 */
	@RequestMapping("/resetpwd")
	public String resetPassword(HttpServletRequest request){
		try {
			InputStream is=request.getInputStream();
			byte[] buffer=new byte[64];
			int len=is.read(buffer);
			String message=new String(buffer,0,len);
			is.close();
			
			String[] array=message.split(",");
			return this.userService.resetPassword(Integer.parseInt(array[0]),array[1]);
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * 
	     * @Title: resetName
	     * @Description: TODO(这里用一句话描述这个方法的作用) 根据用户id修改用户昵称
	     * @param @param request
	     * @param @return 参数 :修改成功或失败标志
	     * @return String 返回类型
	     * @throws
	 */
	@RequestMapping("/name")
	public String resetName(HttpServletRequest request){
		try {
			InputStream is=request.getInputStream();
			byte[] buffer=new byte[64];
			int len=is.read(buffer);
			String content=new String(buffer,0,len);
			String[] array=content.split(",");
			
			return this.userService.resetName(Integer.parseInt(array[0]),array[1]);
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * 
	     * @Title: resetSex
	     * @Description: TODO(这里用一句话描述这个方法的作用) 根据用户id修改用户性别
	     * @param @param request
	     * @param @return 参数:修改成功或失败标志
	     * @return String 返回类型
	     * @throws
	 */
	@RequestMapping("/sex")
	public String resetSex(HttpServletRequest request){
		try {
			InputStream is=request.getInputStream();
			byte[] buffer=new byte[64];
			int len=is.read(buffer);
			String content=new String(buffer,0,len);
			String[] array=content.split(",");
			return this.userService.resetSex(Integer.parseInt(array[0]),Integer.parseInt(array[1]));
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
		
	}
	
	/**
	 * 
	     * @Title: resetAddress
	     * @Description: TODO(这里用一句话描述这个方法的作用) 根据用户id修改用户地址
	     * @param @param request
	     * @param @return 参数:修改成功或失败的标志
	     * @return String 返回类型
	     * @throws
	 */
	@RequestMapping("/address")
	public String resetAddress(HttpServletRequest request){
		try {
			InputStream is=request.getInputStream();
			byte[] buffer=new byte[64];
			int len=is.read(buffer);
			String content=new String(buffer,0,len);
			String[] array=content.split(",");
			
			return this.userService.resetAddress(Integer.parseInt(array[0]),array[1]);
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
}
