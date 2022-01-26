package finalProject;

import java.time.LocalDate;

public class Transaction {
	private long transactionID;
	private long lastTransactionID;
	private LocalDate transactionDate;
	private TransactionType transactionType;
	private static long idCounter = 1;
	private double amount;
	
	public Transaction(LocalDate transactionDate, TransactionType transactionType, double amount) {
		
		transactionID = idCounter;
		lastTransactionID = idCounter;
		this.transactionDate = transactionDate;
		this.transactionType = transactionType;
		this.amount = amount;
		idCounter++;
	}
	public long getTransactionID() {
		return transactionID;
	}
	
	public long getLastTransactionID() {
		return lastTransactionID;
	}
	public void setLastTransactionID(long lastTransactionID) {
		this.lastTransactionID = lastTransactionID;
	}
	public LocalDate getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}
	public TransactionType getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
	public int compareTo(Transaction transaction) {
		
		if (transaction.getAmount() > getAmount()) {
			return -1;
		}
		else if (transaction.getAmount() < getAmount()) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
	@Override
	public String toString() {
		
		StringBuilder str = new StringBuilder();
		str.append("\nTransaction: \n----------");
		str.append("\nTransaction Type: " + transactionType);
		str.append("\nDate: " + transactionDate);
		str.append("\nAmount "+amount);
		return str.toString();
	}

	

}
