package finalProject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
		transactions = new ArrayList<Transaction>();
		this.creditCardID = creditCardID;
		this.issueCompany = issueCompany;
	}

	public CreditCard(String creditCardID, LocalDate issueDate, LocalDate expirationDate, String issueCompany,
			CreditCardType creditCardType, CreditCardStatus status, double creditCardLimit, double currentBalance) {
		transactions = new ArrayList<Transaction>();
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

	public void addPurchase(LocalDate transactionDate, double amount, PurchaseType purchaseType, Vendor vendor)
			throws InsufficientFundsException {

		if (amount <= getAvailCredit()) {
			Purchase newPurchase = new Purchase(transactionDate, amount, purchaseType, vendor);
			transactions.add(newPurchase);

			lastPurchase = newPurchase;

			currentBalance += amount;
		} else
			throw new InsufficientFundsException();
	}

	public void addPayment(LocalDate transactionDate, double amount, PaymentType paymentType, BankAccount account)
			throws InsufficientFundsException {

		if (amount <= getAvailCredit()) {
			Payment newPayment = new Payment(transactionDate, amount, paymentType, account);
			transactions.add(newPayment);

			lastPayment = newPayment;

			currentBalance -= amount;

		} else
			throw new InsufficientFundsException();
	}

	public void addFee(LocalDate transactionDate, double amount, FeeType feeType) throws InsufficientFundsException {

		if (amount <= getAvailCredit()) {
			transactions.add(new Fee(transactionDate, amount, feeType));
			currentBalance += amount;
		} else
			throw new InsufficientFundsException();
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

	public Purchase getLargestPurchase() throws NoSuchElementException {
		
		if (getPurchases().size() == 0) {
			throw new NoSuchElementException("Sorry, there are no purchases in the system.");
		}
		
		Iterator<Purchase> iter = getPurchases().iterator();
		Purchase largest = null;

		while (iter.hasNext()) {
			Purchase current = iter.next();

			if (largest == null) {
				largest = (Purchase) current;
			} else if (current.compareTo(largest) > 0) {
				largest = (Purchase) current;
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

	public Purchase getMostRecentPurchase() throws NoSuchElementException{

		if (lastPurchase == null) {
			throw new NoSuchElementException("Sorry, there are no purchases in the system");
		}
		return lastPurchase;
	}

	public Payment getMostRecentPayment() throws NoSuchElementException {

		if (lastPayment == null) {
			throw new NoSuchElementException("Sorry, there are no payments in the system");
		}

		return lastPayment;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Credit Card Number: " + creditCardID);
		str.append("\nIssue Date: " + issueDate);
		str.append(" Expiration: " + expirationDate);

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

	public ArrayList<Purchase> getPurchases() {

		ArrayList<Purchase> purchases = new ArrayList<>();

		for (int i = 0; i < transactions.size(); i++) {
			if (transactions.get(i) instanceof Purchase) {
				purchases.add((Purchase) transactions.get(i));
			}
		}

		return purchases;
	}

	public double getPurchaseTypeTotal(PurchaseType type) {

		double totalAmount = 0;
		ArrayList<Purchase> purchases = getPurchases();

		for (int i = 0; i < purchases.size(); i++) {

			if (purchases.get(i).getPurchaseType().equals(type)) {
				totalAmount += purchases.get(i).getAmount();
			}

		}
		return totalAmount;
	}
}
