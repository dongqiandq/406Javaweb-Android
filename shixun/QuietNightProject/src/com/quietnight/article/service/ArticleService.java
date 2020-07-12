package com.quietnight.article.service;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quietnight.article.dao.ArticleDao;
import com.quietnight.entity.Article;
import com.quietnight.entity.Comment;

@Service
@Transactional(readOnly=false)
public class ArticleService {

	@Resource
	private ArticleDao articleDao;

	//查询所有推荐
	@Transactional(readOnly=true)
	public List<Article> scanAll(Integer tag){
		return this.articleDao.queryAll(tag);
	}
	
	//点击蜡烛时，数量加一或减一
	public String onclickCandle(Integer id,Integer num){
		return this.articleDao.updateCandle(id, num);
	}
	
	//点击某个故事时，浏览量加一
	public String scanNum(Integer id){
		return this.articleDao.updateScan(id);
	}
	
	
	
	//查询某个故事的所有评论
	@Transactional(readOnly=true)
	public List<Comment> scanCommentByAid(Integer articleId){
		return this.articleDao.queryCommentsByAid(articleId);
	}
	
	//查询某故事最新的两条评论
	@Transactional(readOnly=true)
	public List<Comment> scanTwoCommentByAid(Integer articleId){
		return this.articleDao.queryTwoCommentByAid(articleId);
	}
	
	//为某个故事增加一条评论
	public String addComment(Integer userId,Integer articleId,String content){
		return this.articleDao.insertCommment(userId, articleId, content);
	}
	
	
	
	//查询某个文章被添加“感动”的数量
	@Transactional(readOnly=true)
	public String selectMovingNum(Integer articleId){
		return this.articleDao.selectMovingNum(articleId);
	}
	
	//添加感动
	public String addMoving(Integer userId,Integer articleId){
		return this.articleDao.insertMoving(userId, articleId);
	}
	
	//删除感动
	public String deleteMoving(Integer id){
		return this.articleDao.deleteMoving(id);
	}
	
	//查询某个用户的“感动”的数据条数
	@Transactional(readOnly=true)
	public String selectMovNumByUserId(Integer userId){
		return this.articleDao.selectMovNumByUserId(userId);
	}
	
	// 查询某个用户的“感动”
	@Transactional(readOnly=true)
	public Set<Article> selectMineMov(Integer userId){
		return this.articleDao.selectMineMov(userId);
	}
	
	
	//查询某个用户的足迹--故事
	@Transactional(readOnly=true)
	public Set<Article> selectTrack(Integer userId){
		return this.articleDao.selectTrack(userId);
	}
	
	//阅读文章时，添加足迹
	public String insertTrack(Integer userId,Integer articleId){
		return this.articleDao.insertTrack(userId, articleId);
	}
}
