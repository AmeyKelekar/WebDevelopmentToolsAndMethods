package com.amey.spring.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="payment", uniqueConstraints=@UniqueConstraint(columnNames={"card_type", "card_number","cvv"}))
public class Payment {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="payment_id", unique=true, nullable=false)
	private long paymentid;
	@Column(name="member_name", unique=false, nullable=false)
	private String membername;
	@Column(name="card_type", unique=false, nullable=false)
	private String card;
	@Column(name="card_number", unique=false, nullable=false)
	private String cardnumber;
	@Column(name="cvv", unique=false, nullable=false)
	private String cvv;
	@Column(name="emailid", unique=false,nullable=false)
	private String email;
	@Column(name="payment_amount", unique=false, nullable=false)
	private float amount;
	
	public long getPaymentid() {
		return paymentid;
	}
	public void setPaymentid(long paymentid) {
		this.paymentid = paymentid;
	}
	public String getMembername() {
		return membername;
	}
	public void setMembername(String membername) {
		this.membername = membername;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	public String getCardnumber() {
		return cardnumber;
	}
	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
}
