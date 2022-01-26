package finalProject;

import java.time.LocalDate;

public class Payment extends Transaction {
	private PaymentType paymentType;
	private BankAccount account;
	
	public Payment(LocalDate transactionDate, double amount, PaymentType paymentType, BankAccount account) {
		
		super(transactionDate,TransactionType.PAYMENT, amount);
		this.account = account;
	}
	
	public Payment(LocalDate transactionDate, double amount, PaymentType paymentType,String bankName, String accountID) {
		
		super(transactionDate,TransactionType.PAYMENT, amount);
		this.account = new BankAccount(bankName, accountID);
	}
	
	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}
	public BankAccount getAccount() {
		return account;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(super.toString());
		str.append("\nPayment Type: "+paymentType);
		str.append("\nBank Account: "+account);
		return str.toString();
	}
}
