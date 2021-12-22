package finalProject;

import java.time.LocalDate;

public class Purchase extends Transaction {

	private PurchaseType purchaseType;
	private Vendor vendor;
	
	public Purchase(LocalDate transactionDate, double amount, PurchaseType purchaseType, Vendor vendor) {
		super(transactionDate,TransactionType.PURCHASE, amount);
		this.purchaseType = purchaseType;
		this.vendor = vendor;
	}
	
	public Purchase(LocalDate transactionDate, double amount, PurchaseType purchaseType, String vendorName, String street, String city, USState state, String zipCode) {
		
		super(transactionDate,TransactionType.PURCHASE, amount);
		this.purchaseType = purchaseType;
		this.vendor = new Vendor (vendorName, street, city, state, zipCode);
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
