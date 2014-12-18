package pl.rosiek.history.bean;

import java.util.Date;

public class HistoryEntry {

	private Date startDate;
	private Date endDate;
	private String account;
	private String sender;
	private String amount;
	private String saldo;
	private String transaction;
	private String title;

	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
		if (this.amount != null && this.amount.length() > 0 && !this.amount.startsWith("-"))
			this.amount = "+" + this.amount;
	}
	public String getSaldo() {
		return saldo;
	}
	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}
	public String getTransaction() {
		return transaction;
	}
	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}
	public void setTitle(String title) {
		this.title = title;
		
	}

	public String getTitle() {
		return title;
	}
}
