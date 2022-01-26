package finalProject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class CreditCards {

	ArrayList<CreditCard> creditCards;

	public CreditCards() {
		creditCards = new ArrayList<>();
	}
	//return list of card numbers
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

	public double totalAvailCredit() {

		double totalCredit = 0;

		for (int i = 0; i < creditCards.size(); i++) {
			
			if (creditCards.get(i).getStatus().equals(CreditCardStatus.ACTIVE)) {
				totalCredit += creditCards.get(i).getAvailCredit();
			}
		}

		return totalCredit;
	}

	public Purchase getMostRecentPurchase(String creditCardID) {
		return creditCards.get(getCardIndex(creditCardID)).getMostRecentPurchase();
	}

	public Payment getMostRecentPayment(String creditCardID) {
		return creditCards.get(getCardIndex(creditCardID)).getMostRecentPayment();
	}
	
	public Payment getAllMostRecentPayment() {
		
		Payment recentPayement = creditCards.get(0).getMostRecentPayment();
		if (recentPayement == null) {
			
		}
		LocalDate mostRecentPaymentDate = recentPayement.getTransactionDate();
		int mostRecentPaymentIndex= 0;
		
		for(int i=1; i<creditCards.size(); i++) {
			if(creditCards.get(i).getMostRecentPayment().getTransactionDate().compareTo(mostRecentPaymentDate) > 0) {
				mostRecentPaymentDate=creditCards.get(i).getMostRecentPayment().getTransactionDate();
				mostRecentPaymentIndex=i;
			}
		}
		return creditCards.get(mostRecentPaymentIndex).getMostRecentPayment();
	}
	
	public Purchase getAllLargestPurchase() {
		double largestPurchaseAmount = creditCards.get(0).getLargestPurchase().getAmount();
		int largestPurchaseIndex = 0;
		
		for (int i=0; i<creditCards.size(); i++) {
			
			if(creditCards.get(i).getLargestPurchase().getAmount() > largestPurchaseAmount) {
				largestPurchaseAmount=creditCards.get(i).getLargestPurchase().getAmount();
				largestPurchaseIndex=i;
			}
		}
		return creditCards.get(largestPurchaseIndex).getLargestPurchase();
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

	public double getCardCredit(String creditCardID) throws NoSuchElementException {
		return creditCards.get(getCardIndex(creditCardID)).getAvailCredit();
	}

	public void removeCard(String creditCardID) throws NoSuchElementException {

		int cardIndex = getCardIndex(creditCardID);
		if (cardIndex >= 0) {
			creditCards.remove(cardIndex);
		} else
			throw new NoSuchElementException("Sorry, that card is not in the system");
	}

	public boolean findCard(String creditCardID) {

		if (getCardIndex(creditCardID) >= 0) {
			return true;
		}
		return false;
	}

	public int getCardIndex(String creditCardID) {

		for (int i = 0; i < creditCards.size(); i++) {
			if (creditCardID.compareTo(creditCards.get(i).getCreditCardID()) == 0) {
				return i;
			}
		}
		// if not found
		return -1;
	}

	public void addPurchase(String creditCardID, LocalDate transactionDate, PurchaseType purchaseType, Vendor vendor,
			double amount) throws InsufficientFundsException {

		int cardIndex = getCardIndex(creditCardID);
		if (cardIndex >= 0) {
			creditCards.get(cardIndex).addPurchase(transactionDate, amount, purchaseType, vendor);
		} else
			throw new NoSuchElementException("Sorry, that card is not in the system.");

	}

	public void addPayment(String creditCardID, LocalDate transactionDate, PaymentType paymentType, BankAccount account,
			double amount) throws InsufficientFundsException {

		int cardIndex = getCardIndex(creditCardID);
		if (cardIndex >= 0) {
			creditCards.get(cardIndex).addPayment(transactionDate, amount, paymentType, account);
		} else
			throw new NoSuchElementException();
	}

	public void addFee(String creditCardID, LocalDate transactionDate, FeeType feeType, double amount) throws InsufficientFundsException {

		int cardIndex = getCardIndex(creditCardID);
		if (cardIndex >= 0) {
			creditCards.get(cardIndex).addFee(transactionDate, amount, feeType);
		} else
			throw new NoSuchElementException();
	}

	public double getPurchaseTypeTotal(PurchaseType type) {

		double totalAmount = 0;

		for (int i = 0; i < creditCards.size(); i++) {
			totalAmount += creditCards.get(i).getPurchaseTypeTotal(type);
		}

		return totalAmount;
	}
	
	//get totals of each purchase type
	public double[] getPurchaseTypeTotals() {

		int numberOfPurchaseTypes = PurchaseType.values().length;
		double[] totalsPerType = new double[numberOfPurchaseTypes];
		ArrayList<Purchase> purchases;
		
		for (int i = 0; i < creditCards.size(); i++) {

			purchases = creditCards.get(i).getPurchases();

			for (int j = 0; j < purchases.size(); j++) {

				Purchase currPurchase = purchases.get(j);
				PurchaseType currType = currPurchase.getPurchaseType();

				switch (currType) {

				case CAR:
					totalsPerType[PurchaseType.CAR.ordinal()] += currPurchase.getAmount();
					break;
				case CLOTHING:
					totalsPerType[PurchaseType.CLOTHING.ordinal()] += currPurchase.getAmount();
					break;
				case FOOD:
					totalsPerType[PurchaseType.FOOD.ordinal()] += currPurchase.getAmount();
					break;
				case GROCERIES:
					totalsPerType[PurchaseType.GROCERIES.ordinal()] += currPurchase.getAmount();
					break;
				case LODGING:
					totalsPerType[PurchaseType.LODGING.ordinal()] += currPurchase.getAmount();
					break;
				case RESTAURANT:
					totalsPerType[PurchaseType.RESTAURANT.ordinal()] += currPurchase.getAmount();
					break;
				case TRAVEL:
					totalsPerType[PurchaseType.TRAVEL.ordinal()] += currPurchase.getAmount();
					break;
				case UTILITIES:
					totalsPerType[PurchaseType.UTILITIES.ordinal()] += currPurchase.getAmount();
					break;
				}
			}
		}
		
		return totalsPerType;
			
	}
	 
	public PurchaseType getGreatestPurchaseType(double [] totalsPerType) {
		
		double max = totalsPerType[0];
		int maxIndex = 0;
		for (int i = 1; i < totalsPerType.length; i++) {

			if (max < totalsPerType[i]) {
				max = totalsPerType[i];
				maxIndex = i;
			}
		}
		
		//if all purchases are 0
		if (max == 0.00) {
			return null; 
		}
		else {
			return PurchaseType.values()[maxIndex];
		}
	}
	
	public boolean isEmpty() {
		return creditCards.isEmpty();
	}
}
