
package ships;

import java.util.ArrayList;
import java.util.Collections;

import containers.Container;
import interfaces.IShip;
import ports.Port;

/**
 * This is the Ship class.
 * It implements IShip interface and Comparable interface to override compareTo method and to use sort method.
 * @author Ebrar Kiziloglu
 *
 */
public class Ship implements IShip, Comparable<Ship>{
	
	/**
	 * final and private field of Ship Id
	 * It has a getter method below.
	 */
	final private int ID;
	/**
	 * Private -but not final- method of fuel which has a getter and an addFuel methods.
	 */
	private double fuel;
	/**
	 * private field of the current Port. It has a getter method.
	 */
	private Port currentPort;
	/**
	 * this field is final because after the constructor, the value cannot be changed.
	 */
	final private int totalWeightCapacity;
	/**
	 * this field is final because after the constructor, the value cannot be changed.
	 */
	final private int maxNumberOfAllContainers;
	/**
	 * this field is final because after the constructor, the value cannot be changed.
	 */
	final private int maxNumberOfHeavyContainers;
	/**
	 * this field is final because after the constructor, the value cannot be changed.
	 */
	final private int maxNumberOfRefrigeratedContainers;
	/**
	 * this field is final because after the constructor, the value cannot be changed.
	 */
	final private int maxNumberOfLiquidContainers;
	/**
	 * this private field is to keep the current Containers of the Ship.
	 */
	private ArrayList<Container> currentContainers = new ArrayList<Container>();
	/**
	 * this field is given the value in the constructor and never used outside of this class.
	 * Here, it is used in the canSail method to calculate the necessary amount of fuel to sail a certain Port.
	 */
	private double fuelConsumptionPerKM;
	/**
	 * this field starts with the value 0 and whenever a container is loaded or unloaded, the value is updated.
	 */
	private int currentWeight;
	/**
	 * this field starts with the value 0 and whenever a container is loaded or unloaded, the value is updated.
	 */
	private int currentNumberOfAllContainers;
	/**
	 * this field starts with the value 0 and whenever a heavy container is loaded or unloaded, the value is updated.
	 */
	private int currentNumberOfHeavyContainers;
	/**
	 * this field starts with the value 0 and whenever a regrigerated container is loaded or unloaded, the value is updated.
	 */
	private int currentNumberOfRefrigeratedContainers;
	/**
	 * this field starts with the value 0 and whenever a liquid container is loaded or unloaded, the value is updated.
	 */
	private int currentNumberOfLiquidContainers;

	/**
	 * getter method for ID
	 * @return ID, int
	 */
	public int getID() {
		return ID;
	}

	/**
	 * getter method for current Fuel of the ship
	 * @return fuel, double
	 */
	public double getFuel() {
		return (double) Math.round(fuel * 100) / 100;
	}

	/**
	 * This method is used to add some fuel to the current fuel of the Ship
	 * @param fuel, double
	 */
	public void addFuel(double fuel) {
		this.fuel += fuel;
	}

	/**
	 * getter method for current Port of the Ship
	 * @return currentPort, Port
	 */
	public Port getCurrentPort() {
		return currentPort;
	}


	/**
	 * Constructor takes number of parameters and assigns them.
	 * Also the constructor assigns fuel, current weight and number of different types of Containers to 0 (zero).
	 * @param ID, int
	 * @param p, Port
	 * @param totalWeightCapacity, int
	 * @param maxNumberOfAllContainers, int
	 * @param maxNumberOfHeavyContainers, int
	 * @param maxNumberOfRefrigeratedContainers, int
	 * @param maxNumberOfLiquidContainers, int
	 * @param fuelConsumptionPerKM, double
	 */
	public Ship(int ID, Port p, int totalWeightCapacity, int maxNumberOfAllContainers, int maxNumberOfHeavyContainers, 
			int maxNumberOfRefrigeratedContainers, int maxNumberOfLiquidContainers, double fuelConsumptionPerKM){
		this.ID = ID;
		this.currentPort = p;
		this.fuelConsumptionPerKM = fuelConsumptionPerKM;
		this.totalWeightCapacity = totalWeightCapacity;
		this.maxNumberOfAllContainers = maxNumberOfAllContainers;
		this.maxNumberOfHeavyContainers = maxNumberOfHeavyContainers;
		this.maxNumberOfRefrigeratedContainers = maxNumberOfRefrigeratedContainers;
		this.maxNumberOfLiquidContainers = maxNumberOfLiquidContainers;
		this.fuel = 0;
		currentWeight = 0;
		currentNumberOfAllContainers = 0;
		currentNumberOfHeavyContainers = 0;
		currentNumberOfRefrigeratedContainers = 0;
		currentNumberOfLiquidContainers = 0;
		this.currentPort.addToCurrent(this);
	}
	
	/**
	 * This method returns the currentContainers ArrayList of the Ship.
	 * It also sorts the Container elements in the ArrayList.
	 * It is used in the next method, when we need to separate different types of Containers in the ship.
	 * @return currentContainers, ArrayList
	 */
	public ArrayList<Container> getCurrentContainers(){
		Collections.sort(currentContainers);
		return currentContainers;
	}
	
	/**
	 * This method separates the different kinds of current Containers in the Ship and returns their IDs in one ArrayList.
	 * @return IDs of allcurrentContainers according to their types
	 */
	public ArrayList<ArrayList<Integer>> giveContainers() {
		getCurrentContainers();
		ArrayList<Integer> basicContainerIds = new ArrayList<Integer>();
		ArrayList<Integer> heavyContainerIds = new ArrayList<Integer>();
		ArrayList<Integer> liquidContainerIds = new ArrayList<Integer>();
		ArrayList<Integer> refrigeratedContainerIds = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> allIds = new ArrayList<ArrayList<Integer>>();
		
		basicContainerIds.add(-1);
		heavyContainerIds.add(-1);
		liquidContainerIds.add(-1);
		refrigeratedContainerIds.add(-1);
		for(Container container : currentContainers) {
			switch (container.getType()) {
			case 1:
				basicContainerIds.add(container.getID());
				break;
			case 2:
				heavyContainerIds.add(container.getID());
				break;
			case 3:
				liquidContainerIds.add(container.getID());
				break;
			case 4:
				refrigeratedContainerIds.add(container.getID());
				break;
			default:
				break;
			}
		}
		allIds.add(basicContainerIds);
		allIds.add(heavyContainerIds);
		allIds.add(refrigeratedContainerIds);
		allIds.add(liquidContainerIds);
		return allIds;
	}
	
	/**
	 * This method checks whether a certain Container can be added to Ship when they are in the same Port. 
	 * @param cont, Container
	 * @return boolean
	 */
	public boolean canContainerBeAdded(Container cont) {
		/**
		 * We check the limits of specific types of Containers, Basic, Heavy, Refrigerated, and Liquid:
		 */
		int typeOfContainer = cont.getType();
		if(maxNumberOfAllContainers < currentNumberOfAllContainers +1)
			return false;
		if(totalWeightCapacity < currentWeight + cont.getWeight())
			return false;
		switch (typeOfContainer) {
		case 1:
			/**
			 * It is a Basic Container. There is no further requirement.
			 */
			return true;
			
		case 2:
			/**
			 * It is a Heavy Container, we need to check maxNumberOfHeavyContainers. 
			 */
			if(maxNumberOfHeavyContainers < currentNumberOfHeavyContainers + 1)
				return false;
			else {
				currentNumberOfHeavyContainers++;
				return true;
			}
			
		case 3:
			/**
			 * It is a Liquid Container, we need to check maxNumberOfLiquidContainers.
			 */
			if(maxNumberOfLiquidContainers < 1 + currentNumberOfLiquidContainers)
				return false;
			else if(maxNumberOfHeavyContainers < currentNumberOfHeavyContainers + 1)
				return false;
			else {
				currentNumberOfLiquidContainers++;
				currentNumberOfHeavyContainers++;
				return true;
			}
			
		case 4:
			/**
			 * It is a Refrigerated Container, we need to check maxNumberOfRefrigeratedContainers.
			 */
			if(maxNumberOfRefrigeratedContainers < 1 + currentNumberOfRefrigeratedContainers)
				return false;
			else if(maxNumberOfHeavyContainers < currentNumberOfHeavyContainers + 1)
				return false;
			else {
				currentNumberOfRefrigeratedContainers++;
				currentNumberOfHeavyContainers++;
				return true;
			}
			
		default:
			throw new IllegalArgumentException("Unexpected value: " + typeOfContainer);
		}
	}
	
	/**
	 * This method checks whether the Ship can sail to the certain Port.
	 * It calculates the fuel needed and looks if the Ship has enough of the fuel.
	 * @param port, Port
	 * @return boolean
	 */
	public boolean canSail(Port port) {
		double neededFuel = fuelConsumptionPerKM;
		for(Container container : currentContainers) {
			neededFuel += container.consumption();
		}
		
		double distance = this.currentPort.getDistance(port);
		neededFuel *= distance;
		if(fuel >= neededFuel) {
			fuel -= neededFuel;
			return true;
		}
		else
			return false;
	}
	
	/**
	 * This method is used for Ship to sail to the new Port.
	 * @param p, Port
	 * @return boolean
	 */
	@Override
	public boolean sailTo(Port p) {
		if(p.getID() == this.currentPort.getID()) {
			currentPort.addToHistory(this);
			if(!currentPort.getCurrent().contains(this)) {
				currentPort.incomingShip(this);
			}
			return true;
		}
		else if(canSail(p)) {
			currentPort.outgoingShip(this);
			currentPort = p;
			p.incomingShip(this);
			return true;
		}
		else
			return false;
	}

	/**
	 * This method adds fuel to the Ship.
	 */
	@Override
	public void reFuel(double newFuel) {
		fuel += newFuel;
	}
	
	
	/**
	 * This method loads the container to the Ship, only if canContainerBeAdded method returns true. 
	 * It updated the current weight of the Ship and current number of Containers in the Ship. 
	 * It also removes the Container from the currentPort's storage.
	 * @param cont, Container
	 * @return boolean
	 */
	@Override
	public boolean load(Container cont) {
		if(canContainerBeAdded(cont)) {
			currentNumberOfAllContainers++;
			currentWeight += cont.getWeight();
			currentContainers.add(cont);
			cont.setCurrentShipId(ID);
			currentPort.removeContainer(cont);
			return true;
		}
		return false;
	}

	/**
	 * This method unloads the Container from the Ship to the currentPort and make the necessary updates. 
	 * @return boolean
	 */
	@Override
	public boolean unLoad(Container cont) {
		if(currentContainers.contains(cont)) {
			currentContainers.remove(cont);
			currentWeight -= cont.getWeight();
			currentNumberOfAllContainers--;
			switch (cont.getType()) {
			case 2:
				currentNumberOfHeavyContainers--;
				break;
				
			case 3:
				currentNumberOfHeavyContainers--;
				currentNumberOfLiquidContainers--;
				break;
	
			case 4:
				currentNumberOfHeavyContainers--;
				currentNumberOfRefrigeratedContainers--;
				break;

			default:
				break;
			}
		}
		else {
			return false;
		}
		if(!currentPort.getContainers().contains(cont)) {
			currentPort.addContainer(cont);
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * This method is useful to compare Ships and sort accordingly. 
	 * The method compares the IDs of Ships.
	 * Therefore when we use Sort method, it sorts Ships in the ArrayList according to their IDs.
	 * @return int
	 */
	@Override
	public int compareTo(Ship o) {
		return getID() - o.getID();
	}

}


