package FinalProject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class CreditCard {

	private String creditCardID;
	private LocalDate issueDate;
	private LocalDate expirationDate;
	private String issueCompany;
	private CreditCardType creditCardType;
	private CreditCardStatus status;
	private double creditCardLimit;
	private double currentBalance;
	
	private Purchase lastPurchase;
	private Payment lastPayment;

	// private ArrayList <Transaction> transactions;

	public CreditCard(String creditCardID, LocalDate issueDate, LocalDate expirationDate, String issueCompany,
			CreditCardType creditCardType, CreditCardStatus status, double creditCardLimit, double currentBalance) {

		this.creditCardID = creditCardID;
		this.issueDate = issueDate;
		this.expirationDate = expirationDate;
		this.issueCompany = issueCompany;
		this.creditCardType = creditCardType;
		this.status = status;
		this.creditCardLimit = creditCardLimit;
		this.currentBalance = currentBalance;
	}

	
	public void addPurchase(LocalDate transactionDate, TransactionType transactionType, PurchaseType purchaseType,
			Vendor vendor, double amount) {
		
		if (amount <= getAvailCredit()) {
			Purchase newPurchase = new Purchase(transactionDate, transactionType, purchaseType, vendor, amount)
			transactions.add(newPurchase);
			
			lastPurchase = newPurchase;
		}
	}

	public void addPayment(LocalDate transactionDate, TransactionType transactionType, PaymentType paymentType,
			BankAccount account, double amount) {

		if (amount <= getAvailCredit()) {
			Payment newPayment = new Payment(transactionDate, transactionDate, paymentType, account, amount);
			transactions.add(newPayment);
			
			lastPayment = newPayment;
		}
	}

	public void addFee(LocalDate transactionDate, TransactionType transactionType, FeeType feeType, double amount) {

		if (amount <= getAvailCredit()) {
			transactions.add(new Fee(transactionDate, transactionDate, feeType, amount));
		}
	}

	public double getCurrentBalance() {

		return currentBalance;
	}

	public CreditCardStatus getStatus() {
		return status;
	}

	public double getAvailCredit() {
		return creditCardLimit - currentBalance;
	}

	public Purchase getLargestPurchase() {

		Iterator<Transaction> iter = transactions.iterator();

		if (!transaction.isEmpty()) {
			Purchase largest;

			while (iter.hasNext()) {
				Transaction current = iter.next();

				if (current instanceof Purchase) {
					if (largest == null) {
						largest = (Purchase) current;
					} else if ((Purchase) current.compareTo(largest) > 0) {
						largest = current;
					}
				}
			}
			return largest;
		} else
			return null;

	}

	public double getTotalFees() {

		Iterator<Transaction> iter = Transaction.iterator();

		double totalFees = 0;

		while (iter.hasNext()) {
			Transaction current = iter.next();

			if (current instanceof Fee) {
				totalFees += Fee.getFeeAmount();
			}
		}

		return totalFees;
	}

	public String getMostRecentPurchase() {
		
		return lastPurchase.toString();	
	}

	public String getMostRecentPayment() {

		return lastPayment.toString();
	}

	// only has some of the info
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Credit Card Number: " + creditCardID);
		str.append("\nIssue Date: " + issueDate);
		str.append("Expiration: " + expirationDate);

		return str.toString();
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}

		if (o == null) {
			return false;
		}

		if (!(o instanceof CreditCard)) {
			return false;
		}

		CreditCard other = (CreditCard) o;

		if (!other.creditCardID.equals(creditCardID)) {
			return false;
		}
		return true;
	}
}
