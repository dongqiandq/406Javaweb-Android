package com.quietnight.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="user")
public class User {
	private Integer id;
	private String telephone;
	private String password;
	private String name;
	private Integer sex;//0:女   1:男
	private String area;
	private String introduction;
	private String header;
	private List<Article> articles1=new ArrayList<>();//评论
	private Set<Article> articles2=new HashSet<>();//感动
	
	private Set<Article> articles3=new  HashSet<>();//足迹
	
	private Set<Galaxy> galaxies1=new HashSet<>();//追忆
	private List<Galaxy> galaxies2=new ArrayList<>();//留言
	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment",strategy="increment")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	@ManyToMany
	@JoinTable(name="comment",
			joinColumns=@JoinColumn(name="user_id"),
			inverseJoinColumns=@JoinColumn(name="article_id"))
	public List<Article> getArticles1() {
		return articles1;
	}
	public void setArticles1(List<Article> articles1) {
		this.articles1 = articles1;
	}
	@ManyToMany
	@JoinTable(name="moving",
			joinColumns=@JoinColumn(name="user_id"),
			inverseJoinColumns=@JoinColumn(name="article_id"))
	public Set<Article> getArticles2() {
		return articles2;
	}
	public void setArticles2(Set<Article> articles2) {
		this.articles2 = articles2;
	}
	@ManyToMany
	@JoinTable(name="track",
			joinColumns=@JoinColumn(name="user_id"),
			inverseJoinColumns=@JoinColumn(name="article_id"))
	public Set<Article> getArticles3() {
		return articles3;
	}
	public void setArticles3(Set<Article> articles3) {
		this.articles3 = articles3;
	}
	@ManyToMany
	@JoinTable(name="recollection",
			joinColumns=@JoinColumn(name="user_id"),
			inverseJoinColumns=@JoinColumn(name="galaxy_id"))
	public Set<Galaxy> getGalaxies1() {
		return galaxies1;
	}
	public void setGalaxies1(Set<Galaxy> galaxies1) {
		this.galaxies1 = galaxies1;
	}
	@ManyToMany
	@JoinTable(name="words",
			joinColumns=@JoinColumn(name="user_id"),
			inverseJoinColumns=@JoinColumn(name="galaxy_id"))
	public List<Galaxy> getGalaxies2() {
		return galaxies2;
	}
	public void setGalaxies2(List<Galaxy> galaxies2) {
		this.galaxies2 = galaxies2;
	}
	@Override
	public String toString() {
		return id + "," + telephone + "," + password + "," + name + ","
				+ sex + "," + area + "," + introduction + ","
				+ header;
	}
	
}
