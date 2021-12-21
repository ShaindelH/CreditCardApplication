package finalProject;

public class InsuffienctFundsException extends RuntimeException {

	public InsuffienctFundsException() {
		super("Card not accepted due to insuffienct balance.");
	}

	public InsuffienctFundsException(String message) {
		super(message);
	}
}
