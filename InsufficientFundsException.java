package finalProject;

@SuppressWarnings("serial")
public class InsufficientFundsException extends Exception {

	public InsufficientFundsException() {
		super("Card not accepted due to insuffienct balance.");
	}

	public InsufficientFundsException(String message) {
		super(message);
	}
}
