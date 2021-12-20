package finalProject;

public class Purchase {

	private PurchaseType purchaseType;
	private Vendor vendor;
	
	public Purchase(PurchaseType purchaseType, Vendor vendor) {
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
