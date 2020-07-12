package com.quietnight.article.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.quietnight.entity.Article;
import com.quietnight.entity.Comment;
import com.quietnight.entity.User;

@Repository
public class ArticleDao {

	@Resource
	private SessionFactory sessionFactory;
	
	/**
	 * 
	     * @Title: queryAll
	     * @Description: TODO(这里用一句话描述这个方法的作用) 故事中，查询某个tab的所有数据
	     * @param @param tag 标志位：推荐1，生命之光2，英雄事迹3，时下热议4
	     * @param @return 参数：返回查询到的所在tab的所有故事
	     * @return List<Article> 返回类型
	     * @throws
	 */
	public List<Article> queryAll(Integer tag){
		Query query=this.sessionFactory.getCurrentSession().createQuery("from Article where tag=?");
		query.setParameter(0, tag);
		List<Article> articles=query.list();
		for(int i=0;i<articles.size();i++){
			Date date=articles.get(i).getTime();
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			int year=c.get(Calendar.YEAR);
			int month=c.get(Calendar.MONTH+1);
			int day=c.get(Calendar.DAY_OF_MONTH);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/hh");
			String s=year+"/"+month+"/"+day;
			Date date2 = null;
			try {
				date2 = sdf.parse(s);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			articles.get(i).setTime(date2);
		}
		return articles;
	}
	
	/**
	 * 
	     * @Title: updateCandle
	     * @Description: TODO(这里用一句话描述这个方法的作用) 点击蜡烛时，数量加一或减一
	     * @param @param id 对应故事的id
	     * @param @param num -1/+1
	     * @param @return 参数：修改成功标志ok
	     * @return String 返回类型
	     * @throws
	 */
	public String updateCandle(Integer id,Integer num){
		Article article=(Article)this.sessionFactory.getCurrentSession().get(Article.class,new Integer(id));
		article.setCandle(article.getCandle()+num);
		this.sessionFactory.getCurrentSession().save(article);
		return "ok";
	}
	
	/**
	 * 
	     * @Title: updateScan
	     * @Description: TODO(这里用一句话描述这个方法的作用) 点击某个故事时，浏览量加一
	     * @param @param id 要修改浏览量的故事的id值
	     * @param @return 参数 修改成功标志:ok
	     * @return String 返回类型
	     * @throws
	 */
	public String updateScan(Integer id){
		Article article=this.sessionFactory.getCurrentSession().get(Article.class,new Integer(id));
		article.setVisitors(article.getVisitors()+1);
		this.sessionFactory.getCurrentSession().save(article);
		return "ok";
	}
	
	/**
	 * 
	     * @Title: queryCommentsByAid
	     * @Description: TODO(这里用一句话描述这个方法的作用) 查询某个故事的所有评论
	     * @param @param articleId：要查询评论的故事的id值
	     * @param @return 参数：返回查询到的所有评论
	     * @return List<Comment> 返回类型
	     * @throws
	 */
	public List<Comment> queryCommentsByAid(Integer articleId){
		Query query=this.sessionFactory.getCurrentSession().createQuery("from Comment where article_id=?");
		query.setParameter(0, articleId);
		return query.list();
	}
	
	/**
	 * 
	     * @Title: queryTwoCommentByAid
	     * @Description: TODO(这里用一句话描述这个方法的作用) 查询某故事最新的两条评论
	     * @param @param articleId：要查询评论的故事的id值
	     * @param @return 参数：返回查询到的评论
	     * @return List<Comment> 返回类型
	     * @throws
	 */
	public List<Comment> queryTwoCommentByAid(Integer articleId){
		String str=this.sessionFactory.getCurrentSession().createSQLQuery("select count(*) from comment where article_id=?").setParameter(0, articleId).uniqueResult().toString();
		int size=Integer.parseInt(str);
		Query query=this.sessionFactory.getCurrentSession().createQuery("from Comment where article_id=? order by id desc");
		query.setParameter(0, articleId);
		query.setFirstResult(size-2);
		query.setMaxResults(2);
		return query.list();
	}
	
	/**
	 * 
	     * @Title: insertCommment
	     * @Description: TODO(这里用一句话描述这个方法的作用) 为某个故事增加一条评论
	     * @param @param userId：添加评论的人的id
	     * @param @param articleId：评论的故事的id
	     * @param @param content：评论的内容
	     * @param @return 参数：添加成功或失败的标志
	     * @return String 返回类型
	     * @throws
	 */
	public String insertCommment(Integer userId,Integer articleId,String content){
		String sql="insert into comment(user_id,article_id,content) values(?,?,?)";
		Query query=this.sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, userId);
		query.setParameter(1,articleId);
		query.setParameter(2,content);
		query.executeUpdate();
		
		Article article=this.sessionFactory.getCurrentSession().get(Article.class,new Integer(articleId));
		article.setCommentNum(article.getCommentNum()+1);
		this.sessionFactory.getCurrentSession().save(article);
		return "ok";
	}
	
	/**
	 * 
	     * @Title: selectMovingNum
	     * @Description: TODO(这里用一句话描述这个方法的作用) 查询某个文章被添加“感动”的数量
	     * @param @param articleId
	     * @param @return 参数：返回数量
	     * @return String 返回类型
	     * @throws
	 */
	public String selectMovingNum(Integer articleId){
		Query query=this.sessionFactory.getCurrentSession().createSQLQuery("select count(*) from moving where article_id=?");
		query.setParameter(0, articleId);
		return query.uniqueResult().toString();
	}
	
	/**
	 * 
	     * @Title: insertMoving
	     * @Description: TODO(这里用一句话描述这个方法的作用) 添加感动
	     * @param @param userId
	     * @param @param articleId
	     * @param @return 参数：返回添加的一条记录的主键id值
	     * @return String 返回类型
	     * @throws
	 */
	public String insertMoving(Integer userId,Integer articleId){
		String sql="insert into moving(user_id,article_id) values(?,?)";
		Query query=this.sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, userId);
		query.setParameter(1,articleId);
		query.executeUpdate();
		
		Query query2=this.sessionFactory.getCurrentSession().createSQLQuery("select last_insert_id()");
		return query2.uniqueResult().toString();
	}
	
	/**
	 * 
	     * @Title: deleteMoving
	     * @Description: TODO(这里用一句话描述这个方法的作用) 删除感动
	     * @param @param id：要删除的“感动”记录的主键id值
	     * @param @return 参数
	     * @return String 返回类型
	     * @throws
	 */
	public String deleteMoving(Integer id){
		Query query=this.sessionFactory.getCurrentSession().createSQLQuery("delete from moving where id=?");
		query.setParameter(0, id);
		query.executeUpdate();
		return "ok";
	}
	
	/**
	 * 
	     * @Title: selectMovByUserId
	     * @Description: TODO(这里用一句话描述这个方法的作用) 查询某个用户的“感动”的数据条数
	     * @param @param userId
	     * @param @return 参数：返回数据条数
	     * @return String 返回类型
	     * @throws
	 */
	public String selectMovNumByUserId(Integer userId){
		Query query=this.sessionFactory.getCurrentSession().createSQLQuery("select count(*) from moving where user_id=?");
		query.setParameter(0, userId);
		return query.uniqueResult().toString();
	}
	
	/**
	 * 
	     * @Title: selectMineMov
	     * @Description: TODO(这里用一句话描述这个方法的作用) 查询某个用户的“感动”
	     * @param @param userId
	     * @param @return 参数：返回查询到的某用户的所有“感动”
	     * @return Set<Article> 返回类型
	     * @throws
	 */
	public Set<Article> selectMineMov(Integer userId){
		User user=this.sessionFactory.getCurrentSession().get(User.class,new Integer(userId));
		return user.getArticles2();
	}
	
	/**
	 * 
	     * @Title: selectTrack
	     * @Description: TODO(这里用一句话描述这个方法的作用) 查询某个用户的足迹--故事
	     * @param @param userId
	     * @param @return 参数：返回查询到的所有故事
	     * @return Set<Article> 返回类型
	     * @throws
	 */
	public Set<Article> selectTrack(Integer userId){
		User user=this.sessionFactory.getCurrentSession().get(User.class,new Integer(userId));
		return user.getArticles3();
	}
	
	/**
	 * 
	     * @Title: insertTrack
	     * @Description: TODO(这里用一句话描述这个方法的作用) 阅读文章时，添加足迹
	     * @param @param userId
	     * @param @param articleId
	     * @param @return 参数
	     * @return String 返回类型
	     * @throws
	 */
	public String insertTrack(Integer userId,Integer articleId){
		String sql="insert into track(user_id,article_id) values(?,?)";
		Query query=this.sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, userId).setParameter(1,articleId);
		query.executeUpdate();
		return "ok";
	}
}
