package finalProject;

public class BankAccount {
	private String bankName;
	private String accountID;
	public BankAccount(String bankName, String accountID) {
		this.bankName = bankName;
		this.accountID = accountID;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAccountID() {
		return AccountID;
	}
	public void setAccountID(String accountID) {
		AccountID = accountID;
	}
	private String AccountID;

}
