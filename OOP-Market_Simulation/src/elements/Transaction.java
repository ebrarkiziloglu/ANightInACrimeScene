package elements;
/**
 * Transaction class is for data storage.
 * It stores the transactions made in the Market.
 * @author Ebrar Kızıloğlu
 *
 */
public class Transaction {
	/**
	 * SellingOrder field stores the sellingOrder made
	 */
	private SellingOrder sellingOrder; 
	/**
	 * BuyingOrder field stores the buyingOrder made for the SellingOrder
	 */
	private BuyingOrder buyingOrder;

	/**
	 * Constractor method for Transaction
	 * @param sellingOrder, SellingOrder
	 * @param buyingOrder, BuyingOrder
	 */
	public Transaction(SellingOrder sellingOrder, BuyingOrder buyingOrder) {
		this.buyingOrder = buyingOrder;
		this.sellingOrder = sellingOrder;
	}
}
