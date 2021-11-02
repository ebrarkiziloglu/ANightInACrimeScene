package data;


public class Bill {

	//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

	private double limitingAmount;
	private double currentDebt = 0;
	
	
	// following field will store the information for the money paid so far.
	private double moneyPaid;
	
	//first, constructor and getter-setter methods:
	public Bill(double limitingAmount) {
		this.limitingAmount = limitingAmount;
	}
	public double getLimitingAmount() {
		return limitingAmount;
	}
	public void setLimitingAmount(double limitingAmounts) {
		this.limitingAmount = limitingAmounts;
	}
	public double getCurrentDebt() {
		return currentDebt;
	}
	public void setCurrentDebt(double currentDebt) {
		this.currentDebt = currentDebt;
	}
	public double getMoneyPaid() {
		return moneyPaid;
	}
	public void setMoneyPaid(double moneyPaid) {
		this.moneyPaid = moneyPaid;
	}
	
	
	// Bill operations to check whether the action exceeds the limit of the bill, to add charge to the bill, to pay the bill, and to change the limit:
	public boolean check(double amount) {
		if (amount + currentDebt > limitingAmount)
			return false;
		else
			return true;
	}
	public void add(double amount) {
		currentDebt += amount;
	}
	
	// if a costumer pays more than her debt, the following method will act like she pays all of her debt and ends up with no debt.
	public void pay(double amount) {
		if (amount >  currentDebt)
			amount = currentDebt;
		moneyPaid += amount;
		currentDebt -= amount;
	}
	// following method first checks if the new limiting amount is more than the current debt, then fulfills the change action.
	public void changeTheLimit(double amount) {
		if (amount >= currentDebt)
			limitingAmount = amount;
	}
	

	//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
}

