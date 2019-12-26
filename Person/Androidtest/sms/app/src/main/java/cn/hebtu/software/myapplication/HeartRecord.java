package cn.hebtu.software.myapplication;

import java.sql.Timestamp;

public class HeartRecord {
	private int id;
	private int userId;
	private Timestamp time;
	private int userHeart;
	private int felling;
	private int huserBloodPressure;
	private int duserBloodPressure;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public int getUserHeart() {
		return userHeart;
	}
	public void setUserHeart(int userHeart) {
		this.userHeart = userHeart;
	}
	public int getFelling() {
		return felling;
	}
	public void setFelling(int felling) {
		this.felling = felling;
	}
	public int getHuserBloodPressure() {
		return huserBloodPressure;
	}
	public void setHuserBloodPressure(int huserBloodPressure) {
		this.huserBloodPressure = huserBloodPressure;
	}
	public int getDuserBloodPressure() {
		return duserBloodPressure;
	}
	public void setDuserBloodPressure(int duserBloodPressure) {
		this.duserBloodPressure = duserBloodPressure;
	}
	@Override
	public String toString() {
		return "HeartRecord [id=" + id + ", userId=" + userId + ", time=" + time + ", userHeart=" + userHeart
				+ ", felling=" + felling + ", huserBloodPressure=" + huserBloodPressure + ", duserBloodPressure="
				+ duserBloodPressure + "]";
	}
	public HeartRecord(int id, int userId, Timestamp timestamp, int userHeart, int felling, int huserBloodPressure,
			int duserBloodPressure) {
		super();
		this.id = id;
		this.userId = userId;
		this.time = timestamp;
		this.userHeart = userHeart;
		this.felling = felling;
		this.huserBloodPressure = huserBloodPressure;
		this.duserBloodPressure = duserBloodPressure;
	}

	public HeartRecord(int userId, int userHeart, int felling, int huserBloodPressure,
					   int duserBloodPressure) {
		super();
		this.userId = userId;
		this.userHeart = userHeart;
		this.felling = felling;
		this.huserBloodPressure = huserBloodPressure;
		this.duserBloodPressure = duserBloodPressure;
	}
	

}
