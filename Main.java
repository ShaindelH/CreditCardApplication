package finalProject;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		CreditCards creditCards = new CreditCards();
		creditCards.addCard("123123", LocalDate.parse("2020-04-10"), LocalDate.parse("2023-04-10"), "Chase",
				CreditCardType.valueOf("AMEX"), CreditCardStatus.valueOf("ACTIVE"), 5000, 0);


		String cardNum;
		int choice, newChoice;
		Scanner keyboard = new Scanner(System.in);// change all scanners to same name and delete one of them
		
		menu(keyboard, creditCards);
	} 
	public static void menu(Scanner keyboard, CreditCards creditCards) {
		do {
			System.out.println("\tMENU");
			System.out.println("--------------------");
			System.out.println("1. Add a new credit card" + "\n2. Remove a credit card"
					+ "\n3. Display total outstanding balances" + "\n4. Display total available credit"
					+ "\n5. Display largest purchase" + "\n6. Diplay most recent payment"
					+ "\n7. Display total spent on certain category of expense"
					+ "\n8. Check which type of purchase was the most money spent on"
					+ "\n9. Manage a specific Credit Card" + "\n10. Exit");

			do {
				choice = keyboard.nextInt();
				keyboard.nextLine();

				if ((choice < 1 || choice > 10)) {
					System.out.println("Invalid Entry!\nEnter a number from 1-10.");
				}

			} while (choice < 1 || choice > 10);

			switch (choice) {
			case 1:
				// validate?
				System.out.println("Enter your card number. ");
				String cardNumber = keyboard.nextLine();
				System.out.println("Enter the expiration date. (YYYY-MM-DD)");
				Date expiration = LocalDate.parse(keyboard.nextLine());
				System.out.println("Enter the card company. ");
				String company = keyboard.nextLine();
				LocalDate date = LocalDate.now();

				do {
					System.out.println("What type of credit card do you want to add? (VISA,MASTERCARD,AMEX)");
					String typeEntry = keyboard.nextLine().toUpperCase();

					if (!typeEntry.equals("VISA") || !typeEntry.equals("MASTERCARD") || !typeEntry.equals("AMEX")) {
						System.out.println("Invalid Entry!");
					}
				} while (!typeEntry.equals("VISA") || !typeEntry.equals("MASTERCARD") || !typeEntry.equals("AMEX"));
				CreditCardType type = CreditCardType.valueOf(keyboard.nextLine());

				System.out.println("What is your credit limit? ");
				double creditLimit = keyboard.nextDouble();
				System.out.println("Enter the current balance. ");
				double balance = keyboard.nextDouble();
				creditCards.addCard(cardNumber, date, expiration, company, type, CreditCardStatus.ACTIVE, creditLimit,
						balance);
				break;
			case 2:
				System.out.println("Enter the ID of the card you would like to remove. ");
				String creditCardIDtoRemove = keyboard.nextLine();
				creditCards.removeCard(creditCardIDtoRemove);
				break;
			case 3:
				System.out
						.println("The sum total of all your outstanding balances is: $" + creditCards.totalBalances());
				break;
			case 4:
				System.out.println("Your total available credit is: $" + creditCards.totalAvailCredit());
				break;
			case 5:
				System.out.println("Your largest purchase was " + creditCards.getLargestPurchase().toString());// must make a toString!																							
				break;
			case 6:
				System.out.println("Your most recent payment was " + creditCards.getMostRecentPayment().toString());
				break;
			case 7:
				break;
			case 8:
				break;
			case 9:
				System.out.print("\nEnter card number that you would like to manage: ");
				cardNum = keyboard.nextLine();

				if (!creditCards.findCard(cardNum)) {
					System.out.println("This card is not in the system.");

					do {
						System.out.println("Enter 1 to add this card.");
						System.out.println("Enter 2 to go back to main menu");

						newChoice = keyboard.nextInt();

						if (newChoice == 1) {
							// add card method in case 1
						} else
							break;

					} while (newChoice < 1 && newChoice > 2);
				} else
					manageACard(cardNum, creditCards, keyboard);

				break;
			default:
				System.exit(0);
				break;
			}
		} while (choice != 10);
	}


	public static void manageACard(String cardNum, CreditCards creditCards, Scanner keyboard) {

		int choice;
		do {
			System.out.println("\nMenu Options:");
			System.out.println(
					"1. Display current balance \n" + "2. Display current credit limit \n" + "3. Add a Purchase \n"
							+ "4. Add a Payment \n" + "5. Add a Fee \n" + "6. Display Most Recent Purchase \n"
							+ "7. Display Most Recent Payment \n" + "8. Return to Main Menu \n");

			System.out.print("\nEnter action you would like to perform (1-8): ");
			choice = keyboard.nextInt();

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
			LocalDate transactionDate = LocalDate.parse(keyboard.nextLine());

			System.out.print(
					"\nEnter Purchase Type (CAR, CLOTHING, FOOD, GROCERIES, LODGING, RESTAURANT, TRAVEL, UTILITES) : ");
			PurchaseType purchaseType = PurchaseType.valueOf(keyboard.nextLine());

			double amount = getAmount(keyboard);

			Vendor vendor = getVendorInfo(keyboard);

			creditCards.addPurchase(cardNum, transactionDate, purchaseType, vendor, amount);
			System.out.println("\nPurchase added to Card #" + cardNum);
			System.out.println("Your balance is now " + creditCards.getCardBalance(cardNum));
			break;

		case 4:
			System.out.println("Add Payment: ");
			System.out.print("\nEnter Purchase Date (format: yyyy-mm-dd) : ");
			transactionDate = LocalDate.parse(keyboard.nextLine());

			System.out.print("\nEnter Bank Name: ");
			String bankName = keyboard.nextLine();
			System.out.print("\nEnter Account ID: ");
			String accountID = keyboard.nextLine();

			amount = getAmount(keyboard);

			System.out.print("\nEnter Payment Type (CHECK OR ONLINE) : ");
			PaymentType paymentType = PaymentType.valueOf(keyboard.nextLine());

			creditCards.addPayment(cardNum, transactionDate, paymentType, new BankAccount(bankName, accountID), amount);
			System.out.println("Payment Added to Card #" + cardNum);
			System.out.println("Your balance is now " + creditCards.getCardBalance(cardNum));
			break;
		case 5:
			System.out.println("Add Fee: ");
			System.out.print("\nEnter Fee Date (format: yyyy-mm-dd): ");
			transactionDate = LocalDate.parse(keyboard.nextLine());

			System.out.print("\nEnter Fee Type (Late Payment or Interest): ");
			FeeType feeType = FeeType.valueOf(keyboard.nextLine());

			amount = getAmount(keyboard);

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
			break;
		}
	}

	public static Vendor getVendorInfo(Scanner keyboard) {

		System.out.print("\nEnter Vendor Name: ");
		String vendorName = keyboard.nextLine();

		System.out.print("\nVendor Address");
		System.out.print("Enter Street: ");

		String street = keyboard.nextLine();

		System.out.print("\nEnter City: ");
		String city = keyboard.nextLine();

		System.out.print("\nEnter State: ");
		USState state = USState.valueOf(keyboard.nextLine());

		String zipCode;

		do {
			System.out.print("\nEnter Zip Code: ");
			zipCode = keyboard.nextLine();

			if ((zipCode.length() != 5))
				System.out.println("Invalid zip code!");

		} while (zipCode.length() != 5);

		return new Vendor(vendorName, street, city, state, zipCode);

	}

	public static double getAmount(Scanner keyboard) {

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
