package elements;

/**
 * Trade class had unique id field, a wallet, and a static variable to store total number of traders.
 * @author Ebrar Kızıloğlu
 *
 */
public class Trader {
	/**
	 * Each trader has an id determined with the order of their creation in the input file.
	 */
	private int id;
	/**
	 * Each trader has her own wallet to store dollars, PQoins, and blocked money.
	 */
	private Wallet wallet;
	/**
	 * this field stores the current number of traders.
	 */
	public static int numberOfUsers = 0;
	
	/**
	 * getter method for wallet
	 * @return
	 */
	public Wallet getWallet() {
		return wallet;
	}
	/**
	 * getter method for id
	 * @return
	 */
	public int getId() {
		return id;
	}
	/**
	 * Constractor method
	 * @param dollars
	 * @param coins
	 */
	public Trader(double dollars, double coins) {
		this.wallet = new Wallet(dollars, coins);
		this.id = numberOfUsers;
		numberOfUsers++;
	}
	/**
	 * This sell method is called whenever a trader wants to create a SellingOrder
	 * The method first checks if trader can afford this selling, if not: nothing happens and -1 is returned.
	 * If trader can afford, the SellingOrder is created.
	 * @param amount of PQoins to be sold, double
	 * @param price of the sell, double
	 * @param market of selling, Market
	 * @return 1/-1, int
	 */
	public int sell(double amount, double price, Market market) {
		
		if(this.id == 0) {
			// Trader#0's wallet does not matter
			SellingOrder order = new SellingOrder(this.id, amount, price);
			market.giveSellOrder(order);
			wallet.addBlockedCoins(amount);
			return 1;
		}
		
		// if cannot aford return -1
		if(this.wallet.getCoins() < amount) {
			market.incrementNumOfInvalidQueries();
			return -1;
		}
		// Trader has enough PQoins
		SellingOrder order = new SellingOrder(this.id, amount, price);
		market.giveSellOrder(order);
		wallet.addBlockedCoins(amount);
		return 1;
		
	}
	/**
	 * This buy method is called whenever a trader wants to create a BuyingOrder
	 * The method first checks if trader can afford this buying, if not: nothing happens and -1 is returned.
	 * If trader can afford, the BuyingOrder is created.
	 * @param amount of PQoins to be bought, double
	 * @param price of the buy, double
	 * @param market of buying, Market
	 * @return 1/-1, int
	 */
	public int buy(double amount, double price, Market market) {
		
		if(this.id == 0) {
			// Trader#0's wallet does not matter
			BuyingOrder order = new BuyingOrder(this.id, amount, price);
			market.giveBuyOrder(order);
			wallet.addBlockedDollars(amount*price);
			return 1;
		}
		
		// if cannot aford return -1
		if(this.wallet.getDollars() < price*amount) {
			market.incrementNumOfInvalidQueries();
			return -1;
		}
		// Trader has enough Dollars
		BuyingOrder order = new BuyingOrder(this.id, amount, price);
		market.giveBuyOrder(order);
		wallet.addBlockedDollars(amount*price);
		return 1;
		
	}
	

}
