package finalProject;

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
	private ArrayList<Transaction> transactions;

	private Purchase lastPurchase;
	private Payment lastPayment;

	public CreditCard(String creditCardID, String issueCompany) {
		this.creditCardID = creditCardID;
		this.issueCompany = issueCompany;
	}
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

	public String getCreditCardID() {
		return creditCardID;
	}

	public void addPurchase(LocalDate transactionDate, TransactionType transactionType, double amount, PurchaseType purchaseType,
			Vendor vendor) throws InsuffienctFundsException {

		if (amount <= getAvailCredit()) {
			Purchase newPurchase = new Purchase(transactionDate, transactionType, amount, purchaseType, vendor);
			transactions.add(newPurchase);

			lastPurchase = newPurchase;
		} else
			throw new InsuffienctFundsException();
	}

	public void addPayment(LocalDate transactionDate, TransactionType transactionType, double amount, PaymentType paymentType,
			BankAccount account) throws InsuffienctFundsException {

		if (amount <= getAvailCredit()) {
			Payment newPayment = new Payment(transactionDate, transactionType, amount, paymentType, account);
			transactions.add(newPayment);

			lastPayment = newPayment;

		} else
			throw new InsuffienctFundsException();
	}

	public void addFee(LocalDate transactionDate, TransactionType transactionType, double amount, FeeType feeType)
			throws InsuffienctFundsException {

		if (amount <= getAvailCredit()) {
			transactions.add(new Fee(transactionDate, transactionType, amount, feeType));
		} else
			throw new InsuffienctFundsException();
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
		Purchase largest = null;

		while (iter.hasNext()) {
			Transaction current = iter.next();

			if (current instanceof Purchase) {
				if (largest == null) {
					largest = (Purchase) current;
				} else if (current.compareTo(largest) > 0) {
					largest = (Purchase) current;
				}
			}
		}

		return largest;
	}

	public double getTotalFees() {

		Iterator<Transaction> iter = transactions.iterator();

		double totalFees = 0;

		while (iter.hasNext()) {
			Transaction current = iter.next();

			if (current instanceof Fee) {
				totalFees += current.getAmount();
			}
		}
		return totalFees;
	}

	public Purchase getMostRecentPurchase() {

		return lastPurchase;
	}

	public Payment getMostRecentPayment() {

		return lastPayment;
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

		if (!other.creditCardID.equals(creditCardID)) {
			return false;
		}
	
		return true;
	}
}
