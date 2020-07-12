package com.quietnight.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="galaxy")
public class Galaxy {
	private Integer id;
	private String name;
	private String header;
	private String birth;
	private String description;
	private String life;
	private String location;
	private Integer visitors;
	private Integer candle;
	private Integer flower;
	private Integer tag;//0:不公开   1:公开
	private User user;
	
	private Set<User> users1=new HashSet<>();//追忆
	private List<User> users2=new ArrayList<>();//留言
	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment",strategy="increment")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLife() {
		return life;
	}
	public void setLife(String life) {
		this.life = life;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
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
	public Integer getFlower() {
		return flower;
	}
	public void setFlower(Integer flower) {
		this.flower = flower;
	}
	public Integer getTag() {
		return tag;
	}
	public void setTag(Integer tag) {
		this.tag = tag;
	}
	@ManyToOne
	@JoinColumn(name="user_id")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@ManyToMany(mappedBy="galaxies1")
	public Set<User> getUsers1() {
		return users1;
	}
	public void setUsers1(Set<User> users1) {
		this.users1 = users1;
	}
	@ManyToMany(mappedBy="galaxies2")
	public List<User> getUsers2() {
		return users2;
	}
	public void setUsers2(List<User> users2) {
		this.users2 = users2;
	}
}
