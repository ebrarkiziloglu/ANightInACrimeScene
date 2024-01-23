package data;


public class Operator {
	//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

	private int ID;
	private double talkingCharge;
	private double messageCost;
	private double networkCharge;
	private int discountRate;
	
	// following fields store the total usage of this operator:
	private int timeOfTalking = 0;
	private int numOfMessages = 0;
	private double amountOfInternet = 0;
	
	
	// constructor:
	public Operator(int id, double talkingCharge, double messageCost, double networkCharge, int discountRate) {
		this.ID = id;
		this.talkingCharge = talkingCharge;
		this.messageCost = messageCost;
		this.networkCharge = networkCharge;
		this.discountRate = discountRate;
	}

	// getter-setter methods:
	public int getId() {
		return this.ID;
	}
	public double getTalkingCharge() {
		return talkingCharge;
	}
	public void setTalkingCharge(double talkingCharge) {
		this.talkingCharge = talkingCharge;
	}
	public double getMessageCost() {
		return messageCost;
	}
	public void setMessageCost(double messageCost) {
		this.messageCost = messageCost;
	}
	public double getNetworkCharge() {
		return networkCharge;
	}
	
	public void setNetworkCharge(double networkCharge) {
		this.networkCharge = networkCharge;
	}
	public int getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(int discountRate) {
		this.discountRate = discountRate;
	}
	public int getTimeOfTalking() {
		return timeOfTalking;
	}
	public int getNumOfMessages() {
		return numOfMessages;
	}
	public double getAmountOfInternet() {
		return amountOfInternet;
	}
	public void setTimeOfTalking(int timeOfTalking) {
		this.timeOfTalking = timeOfTalking;
	}
	public void setNumOfMessages(int numOfMessages) {
		this.numOfMessages = numOfMessages;
	}
	public void setAmountOfInternet(double amountOfInternet) {
		this.amountOfInternet = amountOfInternet;
	}
	
	
	
	// calculation Costs methods:
	double calculateTalkingCost(int minute, Customer customer) {
		double charge = minute * talkingCharge;
		return charge;
	}
	
	
	double calculateMessageCost(int quantity, Customer customer, Customer other) {

		double charge = quantity * messageCost;
		boolean isDiscount = false;
		
		// following code checks whether the discount will be applied or not:
		if (Math.abs(ID-other.getOperator().ID)<0.000001)
			isDiscount = true;
		
		if(isDiscount)
			charge -= charge * (double)discountRate/100;
		return charge;
		
	}
	
	double calculateNetworkCost(double amount) {
		return amount * networkCharge;
	}
	
	

	//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
}

