package finalProject;

import java.time.LocalDate;

public class Purchase extends Transaction {

	private PurchaseType purchaseType;
	private Vendor vendor;
	
	public Purchase(LocalDate transactionDate, TransactionType transactionType, double amount, PurchaseType purchaseType, Vendor vendor) {
		super(transactionDate,transactionType, amount);
		this.purchaseType = purchaseType;
		this.vendor = vendor;
	}
	
	public void setPurchaseType(PurchaseType purchaseType) {
		this.purchaseType = purchaseType;
	}
	
	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	
	public PurchaseType getPurchaseType() {
		return purchaseType;
	}
	
	public Vendor getVendor() {
		return vendor;
	}
}
