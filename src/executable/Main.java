package executable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import elements.BuyingOrder;
import elements.Market;
import elements.SellingOrder;
import elements.Trader;

/**
 * Main class is for getting the input and providing the necessary output.
 * @author Ebrar Kızıloğlu
 *
 */
public class Main {
	/**
	 * random field enables users to add random amount of coins to the traders.
	 */
	public static Random myRandom;
	/**
	 * this field stores all the Traders in the market.
	 */
	public static ArrayList<Trader> traders = new ArrayList<Trader>();

	/**
	 * main method takes the input and prepares the output.
	 * @param args
	 */
	public static void main(String[] args) {

		
		File inFile = new File(args[0]);  // args[0] is the input file
		File outFile = new File(args[1]);  // args[1] is the output file
		Scanner reader;
		try {
			reader = new Scanner(inFile);
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find input file");
			return;
		}

		PrintStream printer;
		try {
			printer = new PrintStream(outFile);
		}catch(FileNotFoundException e2) {
		        e2.printStackTrace();
				reader.close();
		        return;
		}
		
		

		
		
		// INPUT:
		int seed, B, C, D;
		String line;
		seed = reader.nextInt();	// random seed
		B = reader.nextInt();	// market fee
		C = reader.nextInt();	// number of users
		D = reader.nextInt();	// number of queries
		
		myRandom = new Random(seed);
		// Trader#0 is created immediately
		Market myMarket = new Market(B);
		line = reader.nextLine();
		for(int i=0; i< C; i++) {
			line = reader.nextLine();
			String[] inputarray = line.split(" ");
			double dollars = Double.parseDouble(inputarray[0]);
			double coins = Double.parseDouble(inputarray[1]);
			Trader trader = new Trader(dollars, coins);
			traders.add(trader);
		}
		
		for(int i=0; i< D; i++) {
			line = reader.nextLine();
			String[] inputarray = line.split(" ");
			// According to the first 
			int typeOfInput = Integer.parseInt(inputarray[0]);
			int traderId;
			Trader trader;
			double amount, price;
			switch (typeOfInput) {
			case 10:		// give buying order of specific price:
							// 10 <trader_id> <price> <amount>
				traderId = Integer.parseInt(inputarray[1]);
				trader = traders.get(traderId);
				price = Double.parseDouble(inputarray[2]);
				amount = Double.parseDouble(inputarray[3]);
				trader.buy(amount, price, myMarket);
				break;
				
			case 11:		// give buying order of market price: 
							// 11 <trader_id> <amount>
				traderId = Integer.parseInt(inputarray[1]);
				trader = traders.get(traderId);
				amount = Double.parseDouble(inputarray[2]);
				// Note: If there is no current selling price then increment the number of invalid queries.
				SellingOrder topOfSellingOrder = myMarket.getTopofSellingOrder();
				if(topOfSellingOrder.equals(null)) {
					myMarket.incrementNumOfInvalidQueries();
					break;
				}else {
					price = topOfSellingOrder.getPrice();
					trader.buy(amount, price, myMarket);
				}
				break;
				
			case 20:		// give selling order of specific price:
							// 20 <trader_id> <price> <amount>
				traderId = Integer.parseInt(inputarray[1]);
				trader = traders.get(traderId);
				price = Double.parseDouble(inputarray[2]);
				amount = Double.parseDouble(inputarray[3]);
				trader.sell(amount, price, myMarket);
				break;
	
			case 21:		// give selling order of market price: 
							// 21 <trader_id> <amount>
				traderId = Integer.parseInt(inputarray[1]);
				trader = traders.get(traderId);
				amount = Double.parseDouble(inputarray[2]);
				BuyingOrder topOfBuyingOrder = myMarket.getTopofBuyingOrder();
				if(topOfBuyingOrder.equals(null)) {
					myMarket.incrementNumOfInvalidQueries();
					break;
				}else {
					price = topOfBuyingOrder.getPrice();
					trader.sell(amount, price, myMarket);
				}
				break;
				
			case 3:		// deposit a certain amount of dollars to wallet: 
						// 3 <trader_id> <amount>
				traderId = Integer.parseInt(inputarray[1]);
				trader = traders.get(traderId);
				double amountOfDeposit = Double.parseDouble(inputarray[2]);
				trader.getWallet().deposit(amountOfDeposit);
				break;
				
			case 4:		// withdraw a certain amount of dollars from wallet: 
				 		// 4 <trader_id> <amount>
				traderId = Integer.parseInt(inputarray[1]);
				trader = traders.get(traderId);
				double amountOfWithdraw = Double.parseDouble(inputarray[2]);
				
				if(trader.getWallet().getDollars() < amountOfWithdraw) {
					myMarket.incrementNumOfInvalidQueries();
					break;
				}
				else {
					trader.getWallet().withdraw(amountOfWithdraw);
				}
				
				break;
			
			case 5:		// prints wallet status:
						// 5 <trader_id>
					// “Trader <traderID>: <trader_s_dollars>$ <trader_s_PQoins>PQ”
				traderId = Integer.parseInt(inputarray[1]);
				trader = traders.get(traderId);
				double totalDollarss = trader.getWallet().getTotalDollars();
				double totalCoinss = trader.getWallet().getTotalCoins();
				printer.printf("Trader %d: %.5f$ %.5fPQ\n", traderId, totalDollarss, totalCoinss);
				break;
				
			case 777:		// give rewards to all traders:
				
				// the system creates and distributes random amounts of PQoins to all traders.
				// for each trader add
				// myRandom.nextDouble()*10
				// coins to the trader’s wallet.
				for(Trader trader1 : traders) {
					trader1.getWallet().addCoins(myRandom.nextDouble()*10);
				}
				break;
					
			case 666:
						
				// Adds orders
				int givenPrice = Integer.parseInt(inputarray[1]);
				myMarket.makeOpenMarketOperation(givenPrice, traders);
				break;
				
			case 500:
				
				// Total price in the existing Orders in PQ
				// needs iterator
				double totalDollarsinBuiyngPQ = myMarket.marketBuyingPrice();
				double totalPQoinsinSellingPQ = myMarket.marketSellingPrice();
				printer.printf("Current market size: %.5f %.5f\n", totalDollarsinBuiyngPQ, totalPQoinsinSellingPQ);
				break;
				
			case 501:		// print number of successful transactions:
	
				printer.println("Number of successful transactions: " + myMarket.getNumberOfTransactions());
				
				break;
				
			case 502:		// print the number of invalid queries:
				printer.println("Number of invalid queries: "+ myMarket.getNumOfInvalidQueries());
				
				break;
				
			case 505:		// print the current prices:
							// Current prices: <cp_buying> <cp_selling> <cp_average>
				double cpBuying, cpSelling, cpAverage;
				if(myMarket.isBuyingOrdersEmpty()) {
					cpBuying = 0;
				}else {
					BuyingOrder topBuyingOrder = myMarket.getTopofBuyingOrder();
					cpBuying = topBuyingOrder.getPrice();
				}
				if(myMarket.isSellingOrdersEmpty()) {
					cpSelling = 0;
				}else {
					SellingOrder topSellingOrder = myMarket.getTopofSellingOrder();
					cpSelling = topSellingOrder.getPrice();
				}
				cpAverage = (cpBuying + cpSelling) / 2;
				printer.printf("Current prices: %.5f %.5f %.5f\n", cpBuying, cpSelling, cpAverage);
				break;
				
			case 555: 		// print all traders’ wallet status
				for(Trader trader2 : traders) {
					// print “<trader_s_dollars>$ <trader_s_PQoins>PQ”
					double totalDollars = trader2.getWallet().getTotalDollars();
					double totalCoins = trader2.getWallet().getTotalCoins();
					printer.print("Trader " + trader2.getId() + ": ");
					printer.printf("%.5f$ ", totalDollars);
					printer.printf("%.5fPQ\n", totalCoins);
				}
				break;

			default:
				break;
			}
			
			// Make Transactions if possible, after each query:
			myMarket.checkTransactions(traders);
			
		}
		
		reader.close();
		printer.close();
		

	}

}
