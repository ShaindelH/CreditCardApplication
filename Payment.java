package finalProject;

import java.time.LocalDate;

public class Payment extends Transaction {
	private PaymentType paymentType;
	private BankAccount account;
	
	public Payment(LocalDate transactionDate, TransactionType transactionType, double amount, PaymentType paymentType,
			BankAccount account) {
		
		super(transactionDate,transactionType, amount);
		this.account = account;
	
	}
	
	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}
	public BankAccount getAccount() {
		return account;
	}
	
	
	
}
