package containers;


/**
 * This is the abstract Container class.
 * It implements Comparable interface to override compareTo method and to use sort method.
 * @author Ebrar Kiziloglu
 *
 */
public abstract class Container implements Comparable<Container>{
	/**
	 * final and protected field of Container Id
	 * It has a getter method below.
	 */
	final protected int ID;
	/**
	 * final and protected field of Container Weight
	 * It has a getter method below.
	 */
	final protected int weight;
	/**
	 * protected field of current Ship Id
	 * It has a getter method below.
	 */
	protected int currentShipId;
	protected int type = 0;
	
	/**
	 * getter for ID
	 * @return ID, int
	 */
	public int getID() {
		return ID;
	}
	/**
	 * getter for Weight
	 * @return weight, int
	 */
	public int getWeight() {
		return weight;
	}
	/**
	 * getter for CurrentShipId
	 * @return currentShipId, int
	 */
	public int getCurrentShipId() {
		return currentShipId;
	}
	/**
	 * setter for CurrentShipId
	 * When a container is loaded to the ship, its CurrentShipId is updated. 
	 * @param currentShipId, int
	 */
	public void setCurrentShipId(int currentShipId) {
		this.currentShipId = currentShipId;
	}
	
	/**
	 * getter for Type of Container, abstract class.
	 * Basic Containers' types are 1.
	 * Heavy Containers' types are 2.
	 * Liquid Containers' types are 3.
	 * Refrigerated Containers' types are 4.
	 * @return the type of Container, as an integer
	 */
	public abstract int getType();
	
	/**
	 * Constructor takes two parameters and assigns them accordingly.
	 * Also in the Constructor, currentShipId is assigned to -1 as a begining. 
	 * @param ID, int
	 * @param weight, int
	 */
	public Container (int ID, int weight) {
		this.ID = ID;
		this.weight = weight;
		currentShipId = -1;
	}
	
	/**
	 * This method calculates the required amount of consumption of the Container when the currentShip is sailing. 
	 * @return consumption of container per KM, double
	 */
	public double consumption() {
		return weight * 2.50;
	}

	/**
	 * This method checks whether the two Containers are equal by looking to their IDs, weights and types.
	 * If they are equal, it returns true. Otherwise, it returns false.
	 * @param other, Container
	 * @return boolean
	 */
	public boolean equals(Container other) {
		
		if(other.getID() == ID) {
			if(other.getWeight() == weight) {
				if(other.getType() == this.getType())
					return true;
				else {
					return false;
				}
			}
			else {
				return false;
			}
		}
		return false;
	}
	
	
	/**
	 * This method is useful to compare Containers and sort accordingly. 
	 * The method compares the IDs of Containers.
	 * Therefore when we use Sort method, it sorts Containers in the ArrayList according to their IDs.
	 * @return int
	 */
	@Override
	public int compareTo(Container o) {
		return getID() - o.getID();
	}
	
	
	
}	
	
