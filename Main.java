package finalProject;

import java.time.LocalDate;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("Credit Card Managing App: \n--------------------\n");

		CreditCards creditCards = new CreditCards();

		Scanner keyboard = new Scanner(System.in);

		menu(keyboard, creditCards);
	}

	public static void menu(Scanner keyboard, CreditCards creditCards) {
		int choice = 0, newChoice;
		try {

			do {
				System.out.println("\tMENU");
				System.out.println("--------------------");
				System.out.println("1. Add a new credit card" + "\n2. Remove a credit card"
						+ "\n3. Display total outstanding balances" + "\n4. Display total available credit"
						+ "\n5. Display largest purchase" + "\n6. Diplay most recent payment"
						+ "\n7. Display total spent on certain category of expense"
						+ "\n8. Check which type of purchase the most money was spent on"
						+ "\n9. Manage a specific Credit Card" + "\n10. Exit");

				do {
					System.out.println("Enter menu option:");
					choice = keyboard.nextInt();
					keyboard.nextLine();

					if ((choice < 1 || choice > 10)) {
						System.out.println("Invalid Entry!\nEnter a number from 1-10.");
					}

				} while (choice < 1 || choice > 10);

				if (choice != 1 && creditCards.isEmpty()) {
					System.out.println("You don't have any credit cards saved. To continue, enter a card now");
					addCard(keyboard, creditCards);
				}

				switch (choice) {
				case 1:
					addCard(keyboard, creditCards);
					break;
				case 2:
					System.out.println("Enter the card number of the card you would like to remove. ");
					String creditCardIDtoRemove = keyboard.nextLine();
					creditCards.removeCard(creditCardIDtoRemove);
					System.out.println("Card #" + creditCardIDtoRemove + " was removed.");
					break;
				case 3:
					System.out.println(
							"The sum total of all your outstanding balances is: $" + creditCards.totalBalance());
					break;
				case 4:
					System.out.println("Your total available credit is: $" + creditCards.totalAvailCredit());
					break;
				case 5:
					System.out.println("Your largest purchase was " + creditCards.getAllLargestPurchase().toString());
					break;
				case 6:
					System.out.println(
							"Your most recent payment was " + creditCards.getAllMostRecentPayment().toString());
					break;
				case 7:
					// get and validate purchase type
					PurchaseType purchaseType = getPurchaseType(keyboard);
					System.out.println("The total spent for " + purchaseType + " is $"
							+ creditCards.getPurchaseTypeTotal(purchaseType));
					break;
				case 8:
					PurchaseType greatestPurchase = creditCards.getGreatestPurchaseType(creditCards.getPurchaseTypeTotals());
					
					if (greatestPurchase == null) {
						System.out.println("You didn't make any purchases yet.");
					}
					else
						System.out.println("You spent the most money on "+ greatestPurchase + " purchases.");
					break;
				case 9:
					System.out.print("\nEnter card number that you would like to manage: ");
					String cardNum = keyboard.nextLine();

					if (!creditCards.findCard(cardNum)) {
						System.out.println("This card is not in the system.");

						do {
							System.out.println("Enter 1 to add this card.");
							System.out.println("Enter 2 to go back to main menu");

							newChoice = keyboard.nextInt();
							keyboard.nextLine(); // clear buffer
							if (newChoice == 1) {
								addCard(keyboard, creditCards);
							} else
								break;

						} while (newChoice < 1 && newChoice > 2);
					} else
						manageACard(cardNum, creditCards, keyboard);
					break;
				default:
					System.out.println("Exiting...");
					System.exit(0);
					break;
				}
			} while (choice != 10);

		} catch (NullPointerException exception) {

			if (choice == 5 || choice == 8) {
				System.out.println("There are no purchases saved.");
			} else if (choice == 6) {
				System.out.println("There are no payments saved.");
			} else
				System.out.println("Error occured.");

			System.out.println("Returning to main menu...");
			menu(keyboard, creditCards);
			
		} catch (InputMismatchException ex) {
			System.out.println("Invalid entry. Please try again");
			System.out.println("Returning to main menu...");
			menu(keyboard, creditCards);
		}

		catch (NoSuchElementException nse) {
			System.out.println(nse.getMessage());
			//System.out.println("Sorry this card is not in the system.");
			System.out.println("Returning to main menu...");
			menu(keyboard, creditCards);
		}

	}

	public static void manageACard(String cardNum, CreditCards creditCards, Scanner keyboard) {

		int choice = 0;
		try {
			do {
				do {
					System.out.println("\nManage A Card Options:");
					System.out.println("1. Display current balance \n" + "2. Display current credit limit \n"
							+ "3. Add a Purchase \n" + "4. Add a Payment \n" + "5. Add a Fee \n"
							+ "6. Display Most Recent Purchase \n" + "7. Display Most Recent Payment \n"
							+ "8. Return to Main Menu \n");

					System.out.print("\nEnter action you would like to perform (1-8): ");
					choice = keyboard.nextInt();

					if (choice < 1 || choice > 8)
						System.out.println("Invalid Entry. Enter number between 1 and 8.");

				} while (choice < 1 || choice > 8);

				keyboard.nextLine(); // clear buffer

				switch (choice) {
				case 1:
					System.out.println(
							"Current Balance for Card #" + cardNum + ": " + creditCards.getCardBalance(cardNum));
					break;
				case 2: /// assuming current credit limit means current available credit
					System.out.println(
							"Available Credit for Card #" + cardNum + ": " + creditCards.getCardCredit(cardNum));
					break;
				case 3:
					System.out.println("Add Purchase: \n---------");

					LocalDate transactionDate = getDate(keyboard);

					// get and validate purchase type
					PurchaseType purchaseType = getPurchaseType(keyboard);

					// get purchase amount
					double amount = getAmount(keyboard);

					Vendor vendor = getVendorInfo(keyboard);

					creditCards.addPurchase(cardNum, transactionDate, purchaseType, vendor, amount);
					System.out.println("\nPurchase added to Card #" + cardNum);
					System.out.println("Your balance is now " + creditCards.getCardBalance(cardNum));
					break;

				case 4:
					System.out.println("Add Payment: \n--------");
					LocalDate date = getDate(keyboard);

					System.out.println("Enter Bank Name: ");
					String bankName = keyboard.nextLine();
					System.out.println("Enter Account ID: ");
					String accountID = keyboard.nextLine();

					amount = getAmount(keyboard);

					System.out.println("Enter Payment Type (CHECK OR ONLINE) : ");
					String pType = keyboard.nextLine().toUpperCase();

					while (!pType.equals("CHECK") && !pType.equals("ONLINE")) {
						System.out.println("Please enter valid type, CHECK OR ONLINE:");
						pType = keyboard.nextLine().toUpperCase();
					}

					PaymentType paymentType = PaymentType.valueOf(pType);

					creditCards.addPayment(cardNum, date, paymentType, new BankAccount(bankName, accountID), amount);
					System.out.println("Payment Added to Card #" + cardNum);
					System.out.println("Your balance is now " + creditCards.getCardBalance(cardNum));
					break;
				case 5:
					System.out.println("Add Fee: ");
					date = getDate(keyboard);

					System.out.print("\nEnter Fee Type (Late Payment or Interest): ");
					String fType = keyboard.nextLine().toUpperCase();

					while (!fType.equals("LATEPAYMENT") && !fType.equals("INTEREST")) {
						if (fType.equals("LATE PAYMENT")) {
							fType = "LATEPAYMENT";
						} else {
							System.out.println("Please enter valid type");
							fType = keyboard.nextLine().toUpperCase();
						}
					}
					FeeType feeType = FeeType.valueOf(fType);

					amount = getAmount(keyboard);

					creditCards.addFee(cardNum, date, feeType, amount);
					System.out.println("Fee added to Card #" + cardNum);
					System.out.println("Your balance is now " + creditCards.getCardBalance(cardNum));
					break;
				case 6:
					String purchase = creditCards.getMostRecentPurchase(cardNum).toString();

					System.out.println("Your most recent purchase is: " + purchase);
					break;
				case 7:
					System.out.println("Your most recent payment is: ");
					System.out.println(creditCards.getMostRecentPayment(cardNum));
					break;
				default:
					break;
				}

			} while (choice != 8);

		} catch (NullPointerException exception) {
			if (choice == 6)
				System.out.println("There are no purchases saved.");
			else if (choice == 7)
				System.out.println("There are no payments saved.");
			else
				System.out.println("Error occured.");

			System.out.println("Returning to menu...");
			manageACard(cardNum, creditCards, keyboard);
		} 
		catch (InsufficientFundsException insf) {
			insf.getMessage();
			System.out.println("Returning to menu...");
			manageACard(cardNum, creditCards, keyboard);
		}
	}

	public static void addCard(Scanner keyboard, CreditCards creditCards) {
		System.out.println("Enter your card number:");
		String cardNumber = keyboard.nextLine();

		System.out.println("Enter the card company:");
		String company = keyboard.nextLine();

		LocalDate issueDate = getDate(keyboard);

		System.out.println("Enter the expiration date (YYYY-MM-DD)");
		LocalDate expiration = LocalDate.parse(keyboard.nextLine());

		while (expiration.compareTo(issueDate) < 0) {
			System.out.println("Invalid Expiration Date. Please enter a date greater than the issue date.");

			System.out.println("Enter the expiration date. (YYYY-MM-DD)");

			expiration = LocalDate.parse(keyboard.nextLine());
		}

		String typeEntry;

		do {
			System.out.println("What type of credit card do you want to add? (VISA,MASTERCARD,AMEX)");
			typeEntry = keyboard.nextLine().toUpperCase();

			if (!typeEntry.equals("VISA") && !typeEntry.equals("MASTERCARD") && !typeEntry.equals("AMEX")) {
				System.out.println("Invalid Entry!");
			}
		} while (!typeEntry.equals("VISA") && !typeEntry.equals("MASTERCARD") && !typeEntry.equals("AMEX"));
		CreditCardType type = CreditCardType.valueOf(typeEntry);

		System.out.println("What is your credit limit? ");
		double creditLimit = keyboard.nextDouble();

		while (creditLimit < 0) {

			System.out.println("Invalid Entry! Enter valid credit limit ");
			creditLimit = keyboard.nextDouble();
		}
		keyboard.nextLine();

		// balance can be negative if got refund for example
		System.out.println("Enter the current balance: ");

		double balance = keyboard.nextDouble();
		while (balance > creditLimit) {
			System.out.println("Invalid balance. Try again. \nEnter the current balance.");

			balance = keyboard.nextDouble();
		}
		keyboard.nextLine();

		// add card
		creditCards.addCard(cardNumber, issueDate, expiration, company, type, CreditCardStatus.ACTIVE, creditLimit,
				balance);
		System.out.println("Card #" + cardNumber + " has been added.");
	}

	public static Vendor getVendorInfo(Scanner keyboard) {

		System.out.println("Enter Vendor Name: ");
		String vendorName = keyboard.nextLine();

		System.out.println("Vendor Address\n------------");
		System.out.println("Enter Street: ");

		String street = keyboard.nextLine();

		System.out.println("Enter City: ");
		String city = keyboard.nextLine();

		USState theState = getState(keyboard);

		String zipCode;
		do {
			System.out.print("Enter Zip Code: ");
			zipCode = keyboard.nextLine();

			if ((zipCode.length() != 5))
				System.out.println("Invalid zip code. Please enter zip code that is 5 characters long ");

		} while (zipCode.length() != 5);

		return new Vendor(vendorName, street, city, theState, zipCode);
	}

	public static USState getState(Scanner keyboard) {

		String state;
		do {
			System.out.println("Enter US State: ");
			state = keyboard.nextLine().toUpperCase();

			char str[] = state.toCharArray();
			int i = removeSpaces(str);
			state = (String) String.valueOf(str).subSequence(0, i);

			if (!USState.isValidState(state)) {
				System.out.println("That is not a valid U.S state. Please try again.");
			}

		} while (!USState.isValidState(state));

		return USState.valueOf(state);
	}

	public static double getAmount(Scanner keyboard) {

		double amount;
		do {
			System.out.println("Enter Amount: ");
			amount = keyboard.nextDouble();

			if (amount < 0)
				System.out.println("Invalid amount!");

		} while (amount < 0);

		keyboard.nextLine();
		return amount;
	}

	public static int removeSpaces(char[] str) {
		// To keep track of non-space character count
		int count = 0;

		// Traverse the given string.
		// If current character is not space, then place
		// it at index 'count++'
		for (int i = 0; i < str.length; i++)
			if (str[i] != ' ')
				str[count++] = str[i]; // here count is
										// incremented

		return count;
	}

	public static LocalDate getDate(Scanner keyboard) {

		System.out.println("Enter Date (format: yyyy-mm-dd) : ");
		LocalDate date = LocalDate.parse(keyboard.nextLine());

		while (date.compareTo(LocalDate.now()) > 0) {
			System.out.println("Please enter a date that is no greater than today's date.");

			date = LocalDate.parse(keyboard.nextLine());
		}

		return date;
	}

	public static PurchaseType getPurchaseType(Scanner keyboard) {
		String purchaseType;
		do {
			System.out.println("Enter Purchase Category (CAR, CLOTHING, FOOD, GROCERIES, LODGING, RESTAURANT, TRAVEL, UTILITES):");
			purchaseType = keyboard.nextLine().toUpperCase();

			if (!PurchaseType.isValidPurchaseType(purchaseType)) {
				System.out.println("That is not a valid Purchase type. Please try again.");
			}

		} while (!PurchaseType.isValidPurchaseType(purchaseType));

		return PurchaseType.valueOf(purchaseType);
	}

}
