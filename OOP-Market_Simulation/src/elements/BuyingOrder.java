package elements;
/**
 * Buying Order class is a child class of Order class.
 * It implements Comparable interface, therefore, override compareTo method in order to be sorted in PriorityQueue of Market.
 * @author Ebrar Kızıloğlu
 *
 */
public class BuyingOrder extends Order implements Comparable<BuyingOrder>{

	/**
	 * Constractor of BuyingOrder
	 * @param traderID
	 * @param amount
	 * @param price
	 */
	public BuyingOrder(int traderID, double amount, double price) {
		super(traderID, amount, price);
	}

	/**
	 * compareTo method sorts Orders
	 */
	@Override
	public int compareTo(BuyingOrder e) {
		if(super.price < e.price)
			return 1;
		else if(super.price > e.price)
			return -1;
		else{
			// prices are the same
			if(amount > e.amount)
				return 1;
			else if(amount < e.amount)
				return -1;
			else {
				// amount are the same
				if(traderID < e.traderID)
					return 1;
				else {
					return -1;
				}
			}
				
		}
	}

}
