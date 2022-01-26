package finalProject;

public enum PurchaseType {CAR, CLOTHING, FOOD, GROCERIES, LODGING, RESTAURANT, TRAVEL, UTILITIES;

	
	static boolean isValidPurchaseType(String purchaseType) {
	
	PurchaseType[] types = PurchaseType.values();
	
	String purchaseTypeCaps = purchaseType.toUpperCase();
	
	for (PurchaseType currType : types) {
		if(currType.toString().equals(purchaseTypeCaps)) {
			return true;
		}
	}
	return false;
	}
}
