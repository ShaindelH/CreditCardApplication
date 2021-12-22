package finalProject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class CreditCards {

	ArrayList<CreditCard> creditCards;

	public CreditCards() {
		creditCards = new ArrayList<>();
	}

	public ArrayList<String> getActiveCards() {

		ArrayList<String> activeCards = new ArrayList<>();

		for (int i = 0; i < creditCards.size(); i++) {

			if (creditCards.get(i).getStatus().equals(CreditCardStatus.ACTIVE)) {
				activeCards.add(creditCards.get(i).getCreditCardID());
			}
		}
		return activeCards;
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
	
	public Purchase getMostRecentPurchase(String creditCardID) {
		return creditCards.get(getCardIndex(creditCardID)).getMostRecentPurchase();
	}
	
	public Payment getMostRecentPayment(String creditCardID) {
		return creditCards.get(getCardIndex(creditCardID)).getMostRecentPayment();
	}

	public void addCard(String creditCardID, LocalDate issueDate, LocalDate expirationDate, String issueCompany,
			CreditCardType creditCardType, CreditCardStatus status, double creditCardLimit, double currentBalance) {

		CreditCard newCC = new CreditCard(creditCardID, issueDate, expirationDate, issueCompany, creditCardType, status,
				creditCardLimit, currentBalance);

		if (!creditCards.contains(newCC)) {
			creditCards.add(newCC);
		}
	}
	
	public double getCardBalance(String creditCardID) {
		return creditCards.get(getCardIndex(creditCardID)).getCurrentBalance();
	}
	
	public double getCardCredit(String creditCardID) {
		return creditCards.get(getCardIndex(creditCardID)).getAvailCredit();
	}
	
	public void removeCard(String creditCardID) {

		int cardIndex = getCardIndex(creditCardID);
		if (cardIndex > 0 ) {
			creditCards.remove(cardIndex);
		}
		else
			throw new NoSuchElementException();
	}

	public boolean findCard(String creditCardID) {

		if (getCardIndex(creditCardID) > 0) {
			return true;
		}
		return false;
	}
	
	public int getCardIndex(String creditCardID) {
		
		for (int i = 0; i < creditCards.size(); i++) {
			if(creditCardID.compareTo(creditCards.get(i).getCreditCardID()) == 0){
				return i;
			}
		}
		//if not found
		return -1;
	}

	public void addPurchase(String creditCardID, LocalDate transactionDate, PurchaseType purchaseType, Vendor vendor, double amount) {

		int cardIndex = getCardIndex(creditCardID);
		if (cardIndex > 0 ) {
			creditCards.get(cardIndex).addPurchase(transactionDate, amount, purchaseType, vendor);
		}
		else 
			throw new NoSuchElementException();

	}

	public void addPayment(String creditCardID, LocalDate transactionDate, PaymentType paymentType, BankAccount account, double amount) {
		
		int cardIndex = getCardIndex(creditCardID);
		if (cardIndex > 0 ) {
			creditCards.get(cardIndex).addPayment(transactionDate, amount, paymentType, account);
		}
		else 
			throw new NoSuchElementException();
	}

	public void addFee(String creditCardID, LocalDate transactionDate, FeeType feeType, double amount) {

		int cardIndex = getCardIndex(creditCardID);
		if (cardIndex > 0 ) {
			creditCards.get(cardIndex).addFee(transactionDate, amount, feeType);
		}
		else 
			throw new NoSuchElementException();
	}
	


}
