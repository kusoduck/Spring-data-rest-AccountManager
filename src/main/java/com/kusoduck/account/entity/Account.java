package com.kusoduck.account.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Set this class to an entity named t_account because have a column is named same as table
//@Entity(name = "t_account")
@Entity
@Table(name = "t_account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "account_name")
	private String accountName;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "user_id")
	private int userId;

	public Account() {
	}

	public Account(String accountName, String username, String password) {
		this.accountName = accountName;
		this.username = username;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Account [account=" + accountName + ", username=" + username + ", userId=" + userId + "]";
	}

}
