package data;


public class Customer {
	
	//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
	

	public int ID;
	public String name;
	private int age;
	private Operator operator;
	private Bill bill;
	
	
	// following fields will store the usage for talking, messages, Internet 
	private int amountOfTalking =0;
	private int numOfMessages =0;
	private double amountOfInternet =0;
	
	//first we set the constructor and getter-setter methods
	public Customer(int ID, String name, int age, Operator operator, double limitingAmount) {
		this.ID = ID;
		this.name = name;
		this.age = age;
		this.operator = operator;
		this.bill = new Bill(limitingAmount);
		
	}	
	public int getAge() {
		return age;
	}	
	public void setAge(int age) {
		this.age = age;
	}
	public Operator getOperator() {
		return operator;
	}
	public void setOperator(Operator operator) {
		this.operator = operator;
	}
	public Bill getBill() {
		return bill;
	}
	public void setBill(Bill bill) {
		this.bill = bill;
	}
	public int getAmountOfTalking() {
		return amountOfTalking;
	}
	public void setAmountOfTalking(int amountOfTalking) {
		this.amountOfTalking = amountOfTalking;
	}
	public int getNumOfMessages() {
		return numOfMessages;
	}
	public double getAmountOfInternet() {
		return amountOfInternet;
	}
	
	
	
	
	// determining the amounts that the customer used:
	public void talk(int minute, Customer other) {
		
		// a customer cannot call herself:
		if(ID == other.ID)
			return;
		
		// we get the charge from the Operator class
		double charge = operator.calculateTalkingCost(minute, other);
		
		// checks whether we will apply discount or not:
		if (age < 18 | age> 65)
			charge -= charge * (double)operator.getDiscountRate()/100;
		
		// if the new charge does not exceed the limiting amount, then adds new charge to the bill:
		if(bill.check(charge)) {
			this.bill.add(charge);
			amountOfTalking += minute;
			other.setAmountOfTalking(other.getAmountOfTalking()+minute);
			operator.setTimeOfTalking(operator.getTimeOfTalking() + minute);
			other.operator.setTimeOfTalking(other.operator.getTimeOfTalking()+minute);
		}
	}
	
	
	
	public void message(int quantity, Customer other) {
		
		// a customer cannot send a message to herself
		if(ID == other.ID)
			return;
		
		double charge = operator.calculateMessageCost(quantity, this, other);
		
		// if the new charge does not exceed the limiting amount, then adds new charge to the bill:
		if(bill.check(charge)) {
			this.bill.add(charge);
			numOfMessages += quantity;
			operator.setNumOfMessages(operator.getNumOfMessages() + quantity);
		}
	}
	
	public void connection(double amount) {
		double charge = operator.calculateNetworkCost(amount);
		
		// if the new charge does not exceed the limiting amount, then adds new charge to the bill:
		if(bill.check(charge)) {
			this.bill.add(charge);
			amountOfInternet += amount;
			operator.setAmountOfInternet(operator.getAmountOfInternet() + amount);
		}
	}
	
	

	//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
}

