package ports;

import java.util.ArrayList;
import java.util.Collections;

import containers.Container;
import interfaces.IPort;
import ships.Ship;

/**
 * This is the Port class.
 * It implements IPort interface.
 * @author Ebrar Kiziloglu
 *
 */
public class Port implements IPort {

	/**
	 * final and private field of Port Id.
	 * It has a getter method below.
	 */
	final private int ID;
	/**
	 * final and private field of Port's X coordinate.
	 * It has a getter method below.
	 */
	final private double X;
	/**
	 * final and private field of Port's Y coordinate.
	 * It has a getter method below.
	 */
	final private double Y;
	/**
	 * private field of hold Containers in the port's storage.
	 * It has a getter method below.
	 */
	private ArrayList<Container> containers = new ArrayList<Container>();
	/**
	 * private field of keep track of ships that visited the port.
	 * It has a getter method below.
	 */
	private ArrayList<Ship> history = new ArrayList<Ship>();
	/**
	 * private field of keep track of ships that are currently in the port.
	 * It has a getter method below.
	 */
 	private ArrayList<Ship> current = new ArrayList<Ship>();
		
 	/**
	 * getter method for ID
	 * @return ID, int
	 */
	public int getID() {
		return ID;
	}
	
	/**
	 * getter method for X coordinate
	 * @return X, double
	 */
	public double getX() {
		return (double) Math.round(X * 100) / 100;
	}
	
	/**
	 * getter method for Y coordinate
	 * @return Y, double
	 */
	public double getY() {
		return (double) Math.round(Y * 100) / 100;
	}
	
	/**
	 * getter method for the ArrayList of Containers in the storage.
	 * @return containers, ArrayList
	 */
	public ArrayList<Container> getContainers() {
		Collections.sort(containers);
		return containers;
	}
	
	/**
	 * Port Constructor
	 * @param ID, int
	 * @param X, double
	 * @param Y, double
	 */
	public Port(int ID, double X, double Y) {
		this.ID = ID;
		this.X = X;
		this.Y = Y;
	}

	/**
	 * This method adds the container to the storage of the Port, if it is not already in it.
	 * @param container, Container
	 */
	public void addContainer(Container container) {
		if(containers.isEmpty())
			containers.add(container);
		else if(!containers.contains(container))
			containers.add(container);
	}
	
	/**
	 * This method removes the container in the storage of the Port, if it is in the storage.
	 * @param container, Container
	 */
	public void removeContainer(Container container) {
		if(containers.contains(container))
			containers.remove(container);
	}

	/**
	 * getter method for the history ArrayList of Ships in the Port.
	 * @return history, ArrayList
	 */
	public ArrayList<Ship> getHistory() {
		Collections.sort(history);
		return history;
	}

	/**
	 * When a ship leaves the Port, this method adds Ship to the history list of the Port.
	 * Also it removes the Ship from the current list of the Port.
	 * @param ship, Ship
	 */
	public void addToHistory(Ship ship) {
		if(current.contains(ship))
			current.remove(ship);
		if(!history.contains(ship))
			history.add(ship);
	}

	/**
	 * getter method for the current list of the Port
	 * @return current, ArrayList
	 */
	public ArrayList<Ship> getCurrent() {
		Collections.sort(current);
		return current;
	}

	/**
	 * When a ship arrives to the Port, this method adds the Ship to the current list of the Port.
	 * @param ship, Ship
	 */
	public void addToCurrent(Ship ship) {
		if(current.isEmpty())
			current.add(ship);
		else if (!current.contains(ship))
			current.add(ship);
	}

	/**
	 * This method is for writing the output. 
	 * It sorts and categorizes the current Containers of the Ship, and divides them into Basic, Heavy, Liquid, and Refrigerated lists.
	 * @return an arraylist of arraylists of Container IDs.
	 */
	public ArrayList<ArrayList<Integer>> giveContainers() {
		Collections.sort(containers);
		ArrayList<Integer> basicContainerIds = new ArrayList<Integer>();
		ArrayList<Integer> heavyContainerIds = new ArrayList<Integer>();
		ArrayList<Integer> liquidContainerIds = new ArrayList<Integer>();
		ArrayList<Integer> refrigeratedContainerIds = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> allIds = new ArrayList<ArrayList<Integer>>();
		for(Container container : containers) {
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
	 * This method calculated the distance between two Ports.
	 * @param other, Port
	 * @return distance, double
	 */
	public double getDistance(Port other) {
		double xDistance = Math.abs(X - other.getX());
		double yDistance = Math.abs(Y - other.getY());
		double distance = Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
		
		return distance;
	}
	
	/**
	 * This method is called when a Ship arrives to the Port.
	 */
	@Override
	public void incomingShip(Ship s) {
		addToCurrent(s);
	}

	/**
	 * This method is called when a Ship leaves the Port.
	 */
	@Override
	public void outgoingShip(Ship s) {
		addToHistory(s);
	}
	
}

