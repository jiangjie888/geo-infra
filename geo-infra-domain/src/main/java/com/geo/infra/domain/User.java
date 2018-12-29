package com.geo.infra.domain;

//import javax.persistence.CascadeType;  
//import javax.persistence.Column;  
//import javax.persistence.Entity;  
//import javax.persistence.GeneratedValue;  
//import javax.persistence.Id;  
//import javax.persistence.JoinColumn;  
//import javax.persistence.ManyToOne;  
//import javax.persistence.Table;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.geo.framework.domainmodel.*;

import java.util.Date;

@Entity
@Table(name = "INFRA_SYS_USER")
public class User extends AuditedEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1180750428310920750L;

	private String username;
	private String password;
	private String name;

	private Date lastlogindate;
	// private Date updatedate;
	// private String updateby;
	// private Date createdate;
	// private String createby;

	/*
	 * @Id //配置uuid，本来jpa是不支持uuid的，但借用hibernate的方法可以实现。
	 * 
	 * @GeneratedValue(generator = "uuid")
	 * 
	 * @GenericGenerator(name = "uuid", strategy = "uuid")
	 * 
	 * @Column(name="Rkey", length=32, nullable=false) public String getRkey() {
	 * return rkey; }
	 * 
	 * public void setRkey(String rkey) { this.rkey = rkey; }
	 */

	@Column(name = "UserName", length = 20, nullable = false)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "Password", length = 30)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "Name", length = 30)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "LastLoginDate")
	public Date getLastlogindate() {
		return lastlogindate;
	}

	public void setLastlogindate(Date lastlogindate) {
		this.lastlogindate = lastlogindate;
	}

}
