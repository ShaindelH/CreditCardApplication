package finalProject;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		CreditCards creditCards = new CreditCards();
		creditCards.addCard("123123", LocalDate.parse("2020-04-10"), LocalDate.parse("2023-04-10"),"Chase" , CreditCardType.valueOf("AMEX"), CreditCardStatus.valueOf("ACTIVE"), 5000, 0);

		Scanner input = new Scanner(System.in);
		int choice = 9, newChoice;
		String cardNum;

		switch (choice) {
		default:
			System.out.print("\nEnter card number that you would like to manage: ");
			cardNum = input.nextLine();

			if (!creditCards.findCard(cardNum)) {
				System.out.println("This card is not in the system.");

				do {
					System.out.println("Enter 1 to add this card.");
					System.out.println("Enter 2 to go back to main menu");

					newChoice = input.nextInt();

					if (newChoice == 1) {
						// add card method in case 1
					} else
						break;

				} while (newChoice < 1 && newChoice > 2);
			} else
				manageACard(cardNum, creditCards, input);
		}
	}

	public static void manageACard(String cardNum, CreditCards creditCards, Scanner input) {

		int choice;
		do {
			System.out.println("\nMenu Options:");
			System.out.println(
					"1. Display current balance \n" + "2. Display current credit limit \n" + "3. Add a Purchase \n"
							+ "4. Add a Payment \n" + "5. Add a Fee \n" + "6. Display Most Recent Purchase \n"
							+ "7. Display Most Recent Payment \n" + "8. Return to Main Menu \n");

			System.out.print("\nEnter action you would like to perform (1-8): ");
			choice = input.nextInt();

			if (choice < 1 && choice > 8)
				System.out.println("Invalid Entry. Enter number between 1 and 8.");

		} while (choice < 1 && choice > 8);

		switch (choice) {
		case 1:
			System.out.println("Current Balance for Card #" + cardNum + ": " + creditCards.getCardBalance(cardNum));
			break;
		case 2: /// assuming current credit limit means current avail
			System.out.println("Available Credit for Card #" + cardNum + ": " + creditCards.getCardCredit(cardNum));
			break;
		case 3:
			System.out.println("Add Purchase: ");
			System.out.print("\nEnter Purchase Date (format: yyyy-mm-dd) : ");
			LocalDate transactionDate = LocalDate.parse(input.nextLine());

			System.out.print(
					"\nEnter Purchase Type (CAR, CLOTHING, FOOD, GROCERIES, LODGING, RESTAURANT, TRAVEL, UTILITES) : ");
			PurchaseType purchaseType = PurchaseType.valueOf(input.nextLine());

			double amount = getAmount(input);

			Vendor vendor = getVendorInfo(input);

			creditCards.addPurchase(cardNum, transactionDate, purchaseType, vendor, amount);
			System.out.println("\nPurchase added to Card #" + cardNum);
			System.out.println("Your balance is now " + creditCards.getCardBalance(cardNum));
			break;

		case 4:
			System.out.println("Add Payment: ");
			System.out.print("\nEnter Purchase Date (format: yyyy-mm-dd) : ");
			transactionDate = LocalDate.parse(input.nextLine());

			System.out.print("\nEnter Bank Name: ");
			String bankName = input.nextLine();
			System.out.print("\nEnter Account ID: ");
			String accountID = input.nextLine();

			amount = getAmount(input);

			System.out.print("\nEnter Payment Type (CHECK OR ONLINE) : ");
			PaymentType paymentType = PaymentType.valueOf(input.nextLine());

			creditCards.addPayment(cardNum, transactionDate, paymentType, new BankAccount(bankName, accountID), amount);
			System.out.println("Payment Added to Card #" + cardNum);
			System.out.println("Your balance is now " + creditCards.getCardBalance(cardNum));
			break;
		case 5:
			System.out.println("Add Fee: ");
			System.out.print("\nEnter Fee Date (format: yyyy-mm-dd): ");
			transactionDate = LocalDate.parse(input.nextLine());
			
			System.out.print("\nEnter Fee Type (Late Payment or Interest): ");
			FeeType feeType = FeeType.valueOf(input.nextLine());
			
			amount = getAmount(input);
			
			creditCards.addFee(cardNum, transactionDate, feeType, amount);
			System.out.println("Fee added to Card #" + cardNum);
			System.out.println("Your balance is now " + creditCards.getCardBalance(cardNum));
			break;
		case 6:
			System.out.println("Your most recent purchase is: ");
			System.out.println(creditCards.getMostRecentPurchase(cardNum));
			break;
		case 7:
			System.out.println("Your most recent payment is: ");
			System.out.println(creditCards.getMostRecentPayment(cardNum));
			break;
		default: 
			//call menu method
		}
	}

	public static Vendor getVendorInfo(Scanner input) {

		System.out.print("\nEnter Vendor Name: ");
		String vendorName = input.nextLine();

		System.out.print("\nVendor Address");
		System.out.print("Enter Street: ");

		String street = input.nextLine();

		System.out.print("\nEnter City: ");
		String city = input.nextLine();

		System.out.print("\nEnter State: ");
		USState state = USState.valueOf(input.nextLine());

		String zipCode;

		do {
			System.out.print("\nEnter Zip Code: ");
			zipCode = input.nextLine();

			if ((zipCode.length() != 5))
				System.out.println("Invalid zip code!");

		} while (zipCode.length() != 5);

		return new Vendor(vendorName, street, city, state, zipCode);

	}

	public static double getAmount(Scanner input) {

		double amount;
		do {
			System.out.print("\nEnter Purchase Amount: ");
			amount = input.nextDouble();

			if (amount < 0)
				System.out.println("Invalid amount!");

		} while (amount < 0);
		return amount;
	}

}
