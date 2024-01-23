package elements;
/**
 * Order class is a parent class and stores certain fields.
 * @author Ebrar Kızıloğlu
 *
 */
public class Order {

	/**
	 * Each order stores amount
	 */
	protected double amount;
	/**
	 * Each order has a price
	 */
	protected double price;
	/**
	 * Each order belongs to a certain Trader with id
	 */
	protected int traderID;
	
	/**
	 * getter method for price
	 * @return
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * getter method for amount
	 * @return
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * getter method for traderId
	 * @return
	 */
	public int getTraderID() {
		return traderID;
	}
	/**
	 * Constractor method
	 * @param traderID
	 * @param amount
	 * @param price
	 */
	public Order(int traderID, double amount, double price) {
		this.traderID = traderID;
		this.amount = amount;
		this.price = price;
	}
}
