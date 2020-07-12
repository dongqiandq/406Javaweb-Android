package com.quietnight.article.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quietnight.article.service.ArticleService;
import com.quietnight.entity.Article;

@RestController
@RequestMapping(value="/article",produces="application/json; charset=utf-8")
public class ArticleController {

	@Resource
	private ArticleService articleService;
	
	/**
	 * 
	     * @Title: AllAtricles
	     * @Description: TODO(这里用一句话描述这个方法的作用) 故事中，查询某个tab的所有数据
	     * @param @param request
	     * @param @param response
	     * @param @return 参数：返回查询到的所在tab的所有故事
	     * @return String 返回类型
	     * @throws
	 */
	@RequestMapping(value="/list",produces="application/json; charset=utf-8")
	public String AllAtricles(HttpServletRequest request,HttpServletResponse response){
		try {
			InputStream is = request.getInputStream();
			byte[] buffer=new byte[4];
			int len=is.read(buffer);
			is.close();
			Integer tag=Integer.parseInt(new String(buffer,0,len));
			
			List<Article> articles=this.articleService.scanAll(tag);
			return articles.toString();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
			return "";
		}catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * 
	     * @Title: lightCandle
	     * @Description: TODO(这里用一句话描述这个方法的作用) 点击蜡烛时，数量加一或减一
	     * @param @param request
	     * @param @return 参数：返回修改成功或失败
	     * @return String 返回类型
	     * @throws
	 */
	@RequestMapping("/candle")
	public String lightCandle(HttpServletRequest request){
		try {
			InputStream is=request.getInputStream();
			byte[] buffer=new byte[128];
			int len=is.read(buffer);
			is.close();
			String content=new String(buffer,0,len);
			System.out.println(content);
			String[] array=content.split(",");
			return this.articleService.onclickCandle(Integer.parseInt(array[0]), Integer.parseInt(array[1]));
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
		
	}
	
	/**
	 * 
	     * @Title: Scan
	     * @Description: TODO(这里用一句话描述这个方法的作用) 点击某个故事时，浏览量加一
	     * @param @param request
	     * @param @return 参数:修改成功或失败的标志
	     * @return String 返回类型
	     * @throws
	 */
	@RequestMapping("/scan")
	public String Scan(HttpServletRequest request){
		try {
			InputStream is = request.getInputStream();
			byte[] buffer=new byte[128];
			int len=is.read(buffer);
			is.close();
			String str=new String(buffer,0,len);
			Integer id=Integer.parseInt(str);
			is.close();
			return this.articleService.scanNum(id);
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * 
	     * @Title: Comment
	     * @Description: TODO(这里用一句话描述这个方法的作用) 查询某个故事的所有评论
	     * @param @param request
	     * @param @return 参数:返回查询到的所有评论
	     * @return String 返回类型
	     * @throws
	 */
	@RequestMapping("/scomment")
	public String AllComment(HttpServletRequest request){
		try {
			InputStream is=request.getInputStream();
			byte[] buffer=new byte[8];
			int len=is.read(buffer);
			is.close();
			String content=new String(buffer,0,len);
			return this.articleService.scanCommentByAid(Integer.parseInt(content)).toString();
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * 
	     * @Title: TwoComment
	     * @Description: TODO(这里用一句话描述这个方法的作用) 查询某故事最新的两条评论
	     * @param @param request
	     * @param @return 参数:返回查询到的评论
	     * @return String 返回类型
	     * @throws
	 */
	@RequestMapping("/stwocomment")
	public String TwoComment(HttpServletRequest request){
		try {
			InputStream is=request.getInputStream();
			byte[] buffer=new byte[8];
			int len=is.read(buffer);
			is.close();
			String content=new String(buffer,0,len);
			return this.articleService.scanTwoCommentByAid(Integer.parseInt(content)).toString();
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * 
	     * @Title: addComment
	     * @Description: TODO(这里用一句话描述这个方法的作用) 为某个故事增加一条评论
	     * @param @param request
	     * @param @return 参数:修改成功或失败的标志
	     * @return String 返回类型
	     * @throws
	 */
	@RequestMapping("/icomment")
	public String addComment(HttpServletRequest request){
		try {
			InputStream is= request.getInputStream();
			byte[] buffer=new byte[64];
			int len=is.read(buffer);
			is.close();
			String content=new String(buffer,0,len);
			String[] array=content.split("`");
			return this.articleService.addComment(Integer.parseInt(array[0]), Integer.parseInt(array[1]), array[2]);
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * 
	     * @Title: selectMovingNum
	     * @Description: TODO(这里用一句话描述这个方法的作用) 查询某个文章被添加“感动”的数量
	     * @param @param request
	     * @param @return 参数:返回记录的条数
	     * @return String 返回类型
	     * @throws
	 */
	@RequestMapping("/smoving")
	public String selectMovingNum(HttpServletRequest request){
		try {
			InputStream is= request.getInputStream();
			byte[] buffer=new byte[16];
			int len=is.read(buffer);
			is.close();
			String content=new String(buffer,0,len);
			return this.articleService.selectMovingNum(Integer.parseInt(content));
		} catch (IOException e) {
			e.printStackTrace();
			return "0";
		}
	}
	
	/**
	 * 
	     * @Title: addMoving
	     * @Description: TODO(这里用一句话描述这个方法的作用) 添加感动
	     * @param @param request
	     * @param @return 参数：返回添加记录的主键id值或0
	     * @return String 返回类型
	     * @throws
	 */
	@RequestMapping("/imoving")
	public String addMoving(HttpServletRequest request){
		try {
			InputStream is= request.getInputStream();
			byte[] buffer=new byte[16];
			int len=is.read(buffer);
			is.close();
			String content=new String(buffer,0,len);
			String[] array=content.split(",");
			return this.articleService.addMoving(Integer.parseInt(array[0]), Integer.parseInt(array[1]));
		} catch (IOException e) {
			e.printStackTrace();
			return "0";
		}
	}
	
	/**
	 * 
	     * @Title: deleteMoving
	     * @Description: TODO(这里用一句话描述这个方法的作用) 删除感动
	     * @param @param request
	     * @param @return 参数:返回删除成功或失败的标志
	     * @return String 返回类型
	     * @throws
	 */
	@RequestMapping("/dmoving")
	public String deleteMoving(HttpServletRequest request){
		try {
			InputStream is= request.getInputStream();
			byte[] buffer=new byte[16];
			int len=is.read(buffer);
			is.close();
			String content=new String(buffer,0,len);
			return this.articleService.deleteMoving(Integer.parseInt(content));
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * 
	     * @Title: selectMovNumByUserId
	     * @Description: TODO(这里用一句话描述这个方法的作用) 查询某个用户的“感动”的数据条数
	     * @param @param request
	     * @param @return 参数:返回数据条数
	     * @return String 返回类型
	     * @throws
	 */
	@RequestMapping("/smovnum")
	public String selectMovNumByUserId(HttpServletRequest request){
		try {
			InputStream is= request.getInputStream();
			byte[] buffer=new byte[16];
			int len=is.read(buffer);
			is.close();
			String content=new String(buffer,0,len);
			return this.articleService.selectMovNumByUserId(Integer.parseInt(content));
		} catch (IOException e) {
			e.printStackTrace();
			return "0";
		}
	}
	
	/**
	 * 
	     * @Title: selectMineMov
	     * @Description: TODO(这里用一句话描述这个方法的作用)  查询某个用户的“感动”
	     * @param @param request
	     * @param @return 参数:返回查询到的某用户的所有“感动”
	     * @return String 返回类型
	     * @throws
	 */
	@RequestMapping("/sminemov")
	public String selectMineMov(HttpServletRequest request){
		try {
			InputStream is= request.getInputStream();
			byte[] buffer=new byte[16];
			int len=is.read(buffer);
			is.close();
			String content=new String(buffer,0,len);
			return this.articleService.selectMineMov(Integer.parseInt(content)).toString();
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * 
	     * @Title: selectTrack
	     * @Description: TODO(这里用一句话描述这个方法的作用) 查询某个用户的足迹--故事
	     * @param @param request
	     * @param @return 参数:返回查询到的所有文章
	     * @return String 返回类型
	     * @throws
	 */
	@RequestMapping("/strack")
	public String selectTrack(HttpServletRequest request){
		try {
			InputStream is= request.getInputStream();
			byte[] buffer=new byte[16];
			int len=is.read(buffer);
			is.close();
			String content=new String(buffer,0,len);
			return this.articleService.selectTrack(Integer.parseInt(content)).toString();
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * 
	     * @Title: insertTrack
	     * @Description: TODO(这里用一句话描述这个方法的作用) 阅读文章时，添加足迹
	     * @param @param request
	     * @param @return 参数
	     * @return String 返回类型
	     * @throws
	 */
	@RequestMapping("/itrack")
	public String insertTrack(HttpServletRequest request){
		try {
			InputStream is= request.getInputStream();
			byte[] buffer=new byte[16];
			int len=is.read(buffer);
			is.close();
			String content=new String(buffer,0,len);
			String[] array=content.split(",");
			return this.articleService.insertTrack(Integer.parseInt(array[0]),Integer.parseInt(array[1]));
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
}
