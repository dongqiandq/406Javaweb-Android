package com.example.dell.miss;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Article {
	private Integer id;
	private Integer tag;//推荐2，英雄3，热议4
	private String picture;
	private String title;
	private Date time;
	private String content;
	private Integer visitors;
	private Integer candle;
	private Integer commentNum;
	private List<User> users1=new ArrayList<>();//评论
	private Set<User> users2=new HashSet<>();//感动
	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTag() {
		return tag;
	}
	public void setTag(Integer tag) {
		this.tag = tag;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getVisitors() {
		return visitors;
	}
	public void setVisitors(Integer visitors) {
		this.visitors = visitors;
	}
	public Integer getCandle() {
		return candle;
	}
	public void setCandle(Integer candle) {
		this.candle = candle;
	}

	public Integer getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

	public List<User> getUsers1() {
		return users1;
	}
	public void setUsers1(List<User> users1) {
		this.users1 = users1;
	}
	public Set<User> getUsers2() {
		return users2;
	}
	public void setUsers2(Set<User> users2) {
		this.users2 = users2;
	}

	@Override
	public String toString() {
		return "Article" + id +
				"`" + tag +
				"`'" + picture + '\'' +
				"`'" + title + '\'' +
				"`" + time +
				"`'" + content + '\'' +
				"`" + visitors +
				"`" + candle +
				"`" + commentNum ;
	}
}
