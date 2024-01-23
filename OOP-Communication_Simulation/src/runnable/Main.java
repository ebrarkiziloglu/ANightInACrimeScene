package runnable;

import data.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;



public class Main {


	public static void main(String args[]) {

		Customer[] customers;
		Operator[] operators;

		int C, O, N;

		File inFile = new File(args[0]);  // args[0] is the input file
		File outFile = new File(args[1]);  // args[1] is the output file
		

		Scanner reader;
		try {
			reader = new Scanner(inFile);
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find input file");
			return;
		}

		C = reader.nextInt();
		O = reader.nextInt();
		N = reader.nextInt();

		customers = new Customer[C];
		operators = new Operator[O];

		

		PrintStream outstream1;
		try {
		        outstream1 = new PrintStream(outFile);
		}catch(FileNotFoundException e2) {
		        e2.printStackTrace();
				reader.close();
		        return;
		}
		String s = reader.nextLine();
		
		// following indexes are to store Customers and Operators in the order in the array and with the necessary ID numbers in classes. 
		int customerIndex = 0;
		int operatorIndex = 0;

		// reads each of N line in the file
		for(int i=1; i<=N; i++) {
			String input = reader.nextLine();
			String[] inputarray = input.split(" ");
			String type = inputarray[0];		// type of command line
			
			// according to the type of the command line, execute the code:

			switch (type) {
			case "1": 
				
				//1-Creating a new Customer
				//1 <name> <age> <ID> <limitingAmount>
				int age = Integer.parseInt(inputarray[2]);
				int operatorId = Integer.parseInt(inputarray[3]);
				Operator operator = operators[operatorId];
				double limitingAmount = Double.parseDouble(inputarray[4]);
				
				
				Customer customer1 = new Customer(customerIndex, inputarray[1], age, operator, limitingAmount);
				customers[customerIndex] = customer1;
				customerIndex++;
				
				break;
			
			case "2": 
				
				//2-Creating a new Operator
				//2<talkingCharge><messageCost><networkCharge><discountRate>
				double talkingCharge = Double.parseDouble(inputarray[1]);
				double messageCost = Double.parseDouble(inputarray[2]);
				double networkCharge = Double.parseDouble(inputarray[3]);
				int discountRate = Integer.parseInt(inputarray[4]);
				
				Operator operator1 = new Operator(operatorIndex, talkingCharge, messageCost, networkCharge, discountRate);
				operators[operatorIndex] = operator1;
				operatorIndex++;
				
				
				break;
			
			case "3": 
				//3-A customer can talk to another customer
				//3<1stCustomerID><2ndCustomerID><time>
				
				int customerId1 = Integer.parseInt(inputarray[1]);
				int customerId2 = Integer.parseInt(inputarray[2]);
				int time = Integer.parseInt(inputarray[3]);
				
				customers[customerId1].talk(time, customers[customerId2]);
				break;
			
			case "4": 
				
				//4-A customer can send message to another customer 
				//4<1stCustomerID><2ndCustomerID><quantity>
				
				customerId1 = Integer.parseInt(inputarray[1]);
				customerId2 = Integer.parseInt(inputarray[2]);
				int quantity = Integer.parseInt(inputarray[3]);
				
				
				customers[customerId1].message(quantity, customers[customerId2]);
				break;
			
			case "5": 
				
				//5-A customer can connect to the Internet
				//5<CustomerID><amount>
				
				customerId1 = Integer.parseInt(inputarray[1]);
				double amount = Double.parseDouble(inputarray[2]);
				
				customers[customerId1].connection(amount);
				break;
			
			case "6": 
				
				//6-A customer can pay his/her bills.
				//6<CustomerID><amount>
				//customer.amountPaid will be added.
				
				customerId1 = Integer.parseInt(inputarray[1]);
				amount = Double.parseDouble(inputarray[2]);
				
				customers[customerId1].getBill().pay(amount);
				break;
			
			case "7": 
				
				
				//7-A customer can change his/her operator
				//7<CustomerID><OperatorID>
				
				customerId1 = Integer.parseInt(inputarray[1]);
				operatorId = Integer.parseInt(inputarray[2]);
				
				customers[customerId1].setOperator(operators[operatorId]);
				break;
			
			case "8": 
				
				//8-A customer can change his/her Bill limit
				//8<CustomerID><amount>
				
				customerId1 = Integer.parseInt(inputarray[1]);
				amount = Double.parseDouble(inputarray[2]);
				
				customers[customerId1].getBill().changeTheLimit(amount);
				break;
			
			default:
				throw new IllegalArgumentException("Unexpected value: " + type);
			}
			
		
		
		}

		
		// we will start printing:
		
		// We first define some variables to store the superlatives:
		int mostTalking = 0;
		int mostMessages = 0;
		double mostNetwork = 0;
		int customerWithMostTalking = 0;
		int customerWithMostMessages = 0;
		int customerWithMostNetwork = 0; 
		
		
		// first, we print the necessary information for each Operators:
		// Operator <ID of the Operator> : <talking time> <n Of messages> <MBs of usage>
		for(Operator operator : operators) {
			outstream1.println("Operator " + operator.getId() + " : " 
		    + operator.getTimeOfTalking() + " " + operator.getNumOfMessages() 
		    + " " + (double) Math.round(operator.getAmountOfInternet() * 100) / 100);
		}
		 
		
		// second, we print the information of each Customer:
		// Also while traveling through the customers array, we determine the superlatives:
		// Customer <ID of the Customer> : <total Money spent> < current debt>
		for(Customer customer : customers) {
			
			// To find the customer talks the most, whenever we see a customer with more talking time 
			// than the previous ones, we store its ID and its talking time.
			if(customer.getAmountOfTalking() > mostTalking) {
				mostTalking = customer.getAmountOfTalking();
				customerWithMostTalking = customer.ID;
			}
			
			
			// To find the customer send the most messages, whenever we see a customer with 
			// more number of messages than the previous ones, we store its ID and its number of messages.
			if(customer.getNumOfMessages() > mostMessages) {
				mostMessages = customer.getNumOfMessages();
				customerWithMostMessages = customer.ID;
			}
			
			// To find the customer connects to the Internet the most, whenever we see a customer with more connection time 
			// than the previous ones, we store its ID and its connection time.
			if(customer.getAmountOfInternet() > mostNetwork) {
				mostNetwork = customer.getAmountOfInternet();
				customerWithMostNetwork = customer.ID;
			}
			
			// In the meantime, we print each customer's ID, total money paid to the bill and the current debt. 
			outstream1.println("Customer " + customer.ID + " : " 
		    + (double) Math.round(customer.getBill().getMoneyPaid() * 100) / 100 + " " + (double) Math.round(customer.getBill().getCurrentDebt() * 100) / 100);
		}
			
		// third, name of the customer that talks the most along with the amount of usage:
		outstream1.println(customers[customerWithMostTalking].name + " : " + mostTalking);
		// forth, name of the customer that sends the most messages along with the number of messages:
		outstream1.println(customers[customerWithMostMessages].name + " : " + mostMessages);
		// fifth and last, name of the customer that uses the most Internet along with the amount of usage:
		outstream1.println(customers[customerWithMostNetwork].name + " : " + (double) Math.round(mostNetwork * 100) / 100);
		
		
		reader.close();
		outstream1.close();

	} 
}

