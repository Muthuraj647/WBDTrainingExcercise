package com.Muthuraj647.WBDTrainingExcercise.SMS.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_info")
public class UserInfo {

	@Id
	private String user_name;
	
	private String name;
	private String email;
	private String user_password;
	
	@OneToOne
	@JoinColumn(name = "subscription_id")
	private SubscriptionInfo info;

	
	
	public UserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserInfo(String user_name, String name, String email, String user_password, SubscriptionInfo info) {
		super();
		this.user_name = user_name;
		this.name = name;
		this.email = email;
		this.user_password = user_password;
		this.info = info;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public SubscriptionInfo getInfo() {
		return info;
	}

	public void setInfo(SubscriptionInfo info) {
		this.info = info;
	}
	
	
}
