package com.se2_project.group12.demoEBanking.Model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Table
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int accountId;

	@Column(nullable = false)
	@Size(min = 2)
	private String name;

	@Column(nullable = false)
	private String userName;

	@Column
	private Date dateOfBirth;

	@Column(nullable = false)
	@Size(min = 8)
	private String password;

	@Column
	private String role;

	@Column(updatable = false)
	@CreationTimestamp
	private LocalDateTime timeCreated;

	@Column
	private String avatar;

	@Column
	private String bio;

	@Column
	@Min(value = 0)
	private long balance;

	@Column()
	private String phoneNumber;

	@OneToMany(mappedBy = "toAccount")
	@JsonIgnoreProperties(value = { "toAccount","account" })
	private List<Gift> toGifts;

	@OneToOne(mappedBy = "account")
	@JsonIgnoreProperties(value = "account")
	private InvestType investType;

	@OneToMany(mappedBy = "account")
	@JsonIgnoreProperties(value = "account")
	private List<PaymentMethod> paymentMethods;

	@OneToMany(mappedBy = "account")
	@JsonIgnoreProperties(value = {"account", "toAccount"})
	private List<Transaction> transactions;

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public LocalDateTime getTimeCreated() {
		return timeCreated;
	}

	public void setTimeCreated(LocalDateTime timeCreated) {
		this.timeCreated = timeCreated;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public List<Gift> getToGifts() {
		return toGifts;
	}

	public void setToGifts(List<Gift> toGifts) {
		this.toGifts = toGifts;
	}

	public List<PaymentMethod> getPaymentMethods() {
		return paymentMethods;
	}

	public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
		this.paymentMethods = paymentMethods;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public InvestType getInvestType() {
		return investType;
	}

	public void setInvestType(InvestType investType) {
		this.investType = investType;
	}
	
	
}
