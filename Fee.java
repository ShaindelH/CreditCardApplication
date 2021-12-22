package finalProject;

import java.time.LocalDate;

public class Fee extends Transaction{
	private FeeType feeType;
	
	public Fee (LocalDate transactionDate, double amount, FeeType feeType) {
		
		super(transactionDate, TransactionType.FEE, amount);
		this.feeType = feeType;
		
	}
	
	public FeeType getFeeType() {
		return feeType;
	}

	public void setFeeType(FeeType feeType) {
		this.feeType = feeType;
	}
}
