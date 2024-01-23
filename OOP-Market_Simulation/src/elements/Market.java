package elements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

import javax.swing.event.TreeSelectionListener;
/**
 * Market class is an important part of this project.
 * It has various methods that make the actual transactions and actions in the Money market.
 * @author Ebrar Kızıloğlu
 *
 */
public class Market {
	/**
	 * sellingOrders PriorityQueue stores sorted SellingOrders according to the given rules
	 */
	private PriorityQueue<SellingOrder> sellingOrders; 
	/**
	 * buyingOrders PriorityQueue stores sorted BuyingOrders according to the given rules
	 */
	private PriorityQueue<BuyingOrder> buyingOrders; 
	/**
	 * this ArrayList stores the transactions made in the market.
	 */
	private ArrayList<Transaction> transactions; 
	/**
	 * Market stores the number of invalid entries(queries) here.
	 */
	private int numOfInvalidQueries;
	/**
	 * Marketfee is the tax taken from Sellers during the transactions.
	 */
	private int marketFee;
	/**
	 * getter method for marketFee
	 * @return
	 */
	public int getMarketFee() {
		return marketFee;
	}	
	/**
	 * getter method for number of Invalid Queries
	 * @return numOfInvalidQueries, int
	 */
	public int getNumOfInvalidQueries() {
		return numOfInvalidQueries;
	}
	/**
	 * getter method for the top of sellingOrders priority queue
	 * @return sellingOrders.peek(), SellingOrder
	 */
	public SellingOrder getTopofSellingOrder() {
		return sellingOrders.peek();
	}
	/**
	 * getter method for the top of buyingOrders priority queue
	 * @return buyingOrders.peek(), BuyingOrder
	 */
	public BuyingOrder getTopofBuyingOrder() {
		return buyingOrders.peek();
	}
	/**
	 * incrementing method for numOfInvalidQueries
	 */
	public void incrementNumOfInvalidQueries() {
		this.numOfInvalidQueries++;
	}
	/**
	 * getter method for number of transactions made.
	 * @return int, size of transactions ArrayList
	 */
	public int getNumberOfTransactions() {
		return transactions.size();
	}
	/**
	 * checks whether buyingOrders priority queue is empty
	 * @return boolean, true/false
	 */
	public boolean isBuyingOrdersEmpty() {
		if(buyingOrders.size() == 0)
			return true;
		else {
			return false;
		}		
	}
	/**
	 * checks whether sellingOrders priority queue is empty
	 * @return boolean, true/false
	 */
	public boolean isSellingOrdersEmpty() {
		if(sellingOrders.size() == 0)
			return true;
		else {
			return false;
		}		
	}
	/**
	 * Constractor method
	 * @param fee, int
	 */
	public Market(int fee) {
		this.marketFee = fee;
		this.numOfInvalidQueries = 0;
		this.sellingOrders = new PriorityQueue<SellingOrder>();
		this.buyingOrders = new PriorityQueue<BuyingOrder>();
		this.transactions = new ArrayList<Transaction>();
	}
	/**
	 * determines the total market size in terms of SellingOrders
	 * @return double, total PQoins in the market
	 */
	public double marketSellingPrice() {
		double totalPQoin = 0;
		Iterator<SellingOrder> itr = sellingOrders.iterator();
		while(itr.hasNext()) {
			SellingOrder order = itr.next();
			totalPQoin += order.getAmount();
		}
		return totalPQoin;
	}
	/**
	 * determines the total market size in terms of BuyingOrders
	 * @return double, total dollars in the market
	 */
	public double marketBuyingPrice() {
		double totalDollars = 0;
		Iterator<BuyingOrder> itr = buyingOrders.iterator();
		while(itr.hasNext()) {
			BuyingOrder order = itr.next();
			totalDollars += (order.getAmount()) * (order.getPrice());
		}
		return totalDollars;
	}
	/**
	 * gives selling Orders in the market, updates the crresponding priority queue
	 * @param order, SellingOrder
	 */
	public void giveSellOrder(SellingOrder order) {
		
		sellingOrders.add(order);
		
	}
	/**
	 * gives buying Orders in the market, updates the crresponding priority queue
	 * @param order, BuyingOrder
	 */
	public void giveBuyOrder(BuyingOrder order) {
		
		buyingOrders.add(order);
		
	}
	/**
	 * makes open market operations to balance the market prices.
	 * @param price, double
	 * @param traders, ArrayList of current traders
	 */
	public void makeOpenMarketOperation(double price, ArrayList<Trader> traders) {
		// price = given price
		double buyingPeek;
		double sellingPeek;
		if(buyingOrders.isEmpty())
			buyingPeek = -5;
		else {
			buyingPeek = buyingOrders.peek().getPrice();
		}
		if(sellingOrders.isEmpty())
			sellingPeek = Integer.MAX_VALUE;
		else {
			sellingPeek = sellingOrders.peek().getPrice();
		}
		double cost, amount;
		if(price <= buyingPeek) {
			// there is surplus of BuyingOrders
			// we have to create corresponding SellingOrders
			boolean b = true;
			while(b && !(buyingOrders.isEmpty())) {
				BuyingOrder by = buyingOrders.peek();
				if(by.getPrice() < price) {
					b = false;
					break;
				}
				else {
					cost = by.getPrice();
					amount = by.getAmount();
					SellingOrder newOrder = new SellingOrder(0, amount, cost);
					giveSellOrder(newOrder);
				}
				checkTransactions(traders);
			}
			
		} else if(price < sellingPeek) {
			// there is not any problem 
			checkTransactions(traders);
			return;
			
			
		}else if(sellingPeek <= price) {
			// there is surplus of SellingOrders
			// we have to create corresponding BuyingOrders
			boolean c = true;
			while(c && !(sellingOrders.isEmpty())) {
				SellingOrder sl = sellingOrders.peek();
				if(sl.getPrice() > price) {
					c = false;
					break;
				}
				else {
					cost = sl.getPrice();
					amount = sl.getAmount();
					BuyingOrder newOrder = new BuyingOrder(0, amount, cost);
					giveBuyOrder(newOrder);
				}
				checkTransactions(traders);
			}
		}
		return;
	}
	/**
	 * Checks if there are any transactions can be made, and if there are, makes the transactions
	 * Determines the paying price and arranges the gain of Seller, Buyer, and Market.
	 * @param traders, ArrayList of current traders
	 */
	public void checkTransactions(ArrayList<Trader> traders) {
		// using tratedIDs of orders, controls the transactions between Traders
	
		// makes transactions
		
		// final price of transaction is the one in the SellingOrder
		
		// all blockedCoins and dollars are not blocked anymore, even the money difference between initial SellingOrder and BuyingOrder prices.
		// but that buyer does not have the selling price anymore, instead she has PQoins
		// seller does not have blocked or regular PQoins anymore, instead she has dollars
		if(buyingOrders.isEmpty() || sellingOrders.isEmpty()) {
			return;
		}
		while(buyingOrders.peek().getPrice() >= sellingOrders.peek().getPrice()) {
			
			BuyingOrder buyOrder = buyingOrders.poll();
			SellingOrder sellOrder = sellingOrders.poll();
			double transactionPrice = sellOrder.getPrice();
			double buyingAmount = buyOrder.getAmount();
			double sellingAmount = sellOrder.getAmount();
			double transactionAmount = Math.min(sellingAmount, buyingAmount);
			int buyerTrader = buyOrder.getTraderID();
			int sellerTrader = sellOrder.getTraderID();
			double totalTransaction = transactionAmount * transactionPrice;
			double totalgain = totalTransaction * (1.0-(double)((double)this.marketFee/1000));
			traders.get(buyerTrader).getWallet().reduceBlockedDollars(transactionAmount * (buyOrder.getPrice()));
			traders.get(buyerTrader).getWallet().deposit(transactionAmount * (buyOrder.getPrice()) - totalTransaction);
			traders.get(sellerTrader).getWallet().deposit(totalgain);
			traders.get(sellerTrader).getWallet().reduceBlockedCoins(transactionAmount);
			traders.get(buyerTrader).getWallet().addCoins(transactionAmount);
			
			
			if(transactionAmount < buyingAmount) {
				double remainderAmount = buyingAmount - transactionAmount;
				BuyingOrder remainderOrder = new BuyingOrder(buyerTrader, remainderAmount, buyOrder.getPrice());
				giveBuyOrder(remainderOrder);
			}
			else if(transactionAmount < sellingAmount) {
				double remainderAmount = sellingAmount - transactionAmount;
				SellingOrder remainderSellingOrder = new SellingOrder(sellerTrader, remainderAmount, transactionPrice);
				giveSellOrder(remainderSellingOrder);
			}
			BuyingOrder transactionBuyingOrder = new BuyingOrder(buyerTrader, transactionAmount, transactionPrice);
			SellingOrder transactionSellingOrder = new SellingOrder(sellerTrader, transactionAmount, transactionPrice);
			Transaction newTransaction = new Transaction(transactionSellingOrder, transactionBuyingOrder);
			transactions.add(newTransaction);
			if(buyingOrders.isEmpty() || sellingOrders.isEmpty()) {
				return;
			}
		}
		
		
		
	}
	

}
