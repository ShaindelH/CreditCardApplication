package FinalProject;

import java.time.LocalDate;
import java.util.ArrayList;

public class CreditCards {

	ArrayList<CreditCard> creditCards;

	public CreditCards() {
		creditCards = new ArrayList<>();
	}

	public String getActiveCards() {

		StringBuilder str = new StringBuilder();

		for (int i = 0; i < creditCards.size(); i++) {

			if (creditCards.get(i).getStatus().equals(CreditCardStatus.ACTIVE)) {
				str.append(creditCards.get(i));
			}
		}
		return str.toString();
	}

	public double totalBalance() {

		double balance = 0;

		for (int i = 0; i < creditCards.size(); i++) {

			balance += creditCards.get(i).getCurrentBalance();
		}

		return balance;
	}

	// check status????????????????????????????????
	public double totalAvailCredit() {

		double totalCredit = 0;

		for (int i = 0; i < creditCards.size(); i++) {

			totalCredit += creditCards.get(i).getAvailCredit();
		}

		return totalCredit;
	}

	public void addCard(String creditCardID, LocalDate issueDate, LocalDate expirationDate, String issueCompany,
			CreditCardType creditCardType, CreditCardStatus status, double creditCardLimit, double currentBalance) {

		CreditCard newCC = new CreditCard(creditCardID, issueDate, expirationDate, issueCompany, creditCardType, status,
				creditCardLimit, currentBalance);

		if (!creditCards.contains(newCC)) {
			creditCards.add(newCC);
		}
	}

	public void removeCard(String creditCardID) {

		if (!creditCards.contains(creditCardID)) {
			creditCards.remove(creditCards.indexOf(creditCardID));   //or just remove???
		}
	}

	public boolean findCard(String creditCardID) {

		if (!creditCards.contains(creditCardID)) {
			return true;
		}
		return false;
	}

	//throw exception?????????????????????

	public void addPurchase(String creditCardID, LocalDate transactionDate, TransactionType transactionType, PurchaseType purchaseType,
			Vendor vendor, double amount) {
		
		if (creditCards.contains(creditCardID)) {
			creditCards.indexOf(creditCardID).addPurchase(transactionDate, transactionType, purchaseType, vendor, amount);
		}

	}

	public void addPayment(String creditCardID, LocalDate transactionDate, TransactionType transactionType, PaymentType paymentType,
			BankAccount account, double amount) {
		
		if (creditCards.contains(creditCardID)) {
			creditCards.indexOf(creditCardID).addPayment(transactionDate, transactionType, paymentType, account, amount);
		}
	}

	public void addFee(String creditCardID, LocalDate transactionDate, TransactionType transactionType, FeeType feeType, double amount) {
		
		if (creditCards.contains(creditCardID)) {
			creditCards.indexOf(creditCardID).addFee(transactionDate, transactionType, feeType, amount);
		}
	}
	

}
