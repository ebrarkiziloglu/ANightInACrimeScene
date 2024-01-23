package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import containers.BasicContainer;
import containers.Container;
import containers.HeavyContainer;
import containers.LiquidContainer;
import containers.RefrigeratedContainer;
import ports.Port;
import ships.Ship;

/**
 * This is the Main Class.
 * @author Ebrar Kiziloglu
 *
 */
public class Main {
	/**
	 * This is the main method that we conduct all the input and output operations. 
	 * @param args, locations of input and output files
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		/**
		 *  Main receives two arguments: path to input file and path to output file.
		 */
		Scanner in = new Scanner(new File(args[0]));
		PrintStream out = new PrintStream(new File(args[1]));
		
		/** 
		 * In order to hold all Containers, Ports, and Ships in the order of their IDs, and to facilitate the indexing process, 
		 * we have the following three ArrayLists and the following three index integers.
		 */
		ArrayList<Port> ports = new ArrayList<Port>();
		ArrayList<Container> containers = new ArrayList<Container>();
		ArrayList<Ship> ships = new ArrayList<Ship>();
		
		int containerId = 0;
		int shipId = 0;
		int portId = 0;
		
		/**
		 * Number of command lines in the input is N. 
		 * And we first read the value of N.
		 */
		int N = in.nextInt();
		
		String line = in.nextLine(); 
		
		/**
		 * For each line of command, we read the line as a string.
		 * Then, we convert it to an array of strings with .split(" ") method.
		 * In this array, the first element determines the type of the command. 
		 * Thus, we assign it to a String variable, typeOfInput and using switch-case, we act according to the type of Command Line.
		 */
		for(int i=0; i<N; i++) {
			line = in.nextLine();
			String[] linearray = line.split(" ");
			String typeOfInput = linearray[0];	 	
			// With the first element of the lineArray, we can figure out which input we get 
			// Depending on the type of the input, we will continue our methods:
			
			switch(typeOfInput) {
			case "1":
				/**
				 *  1. Creating a container
				 *  Input format:	1 <PortId> <WeightOfContainer> <R/L>
				 *  If Weight <=3000: BasicContainer
				 *  First we need to distinguish Basic and Heavy Containers
				 */
				int portsId = Integer.parseInt(linearray[1]);
				int weight  = Integer.parseInt(linearray[2]);
				Container container = null;
				if(linearray.length == 4) {
					/**
					 * The container must be either R or L Container
					 */
					if(linearray[3].equals("R")) {
						/**
						 * Refrigerated Container
						 */
						container = new RefrigeratedContainer(containerId, weight);}
					else if(linearray[3].equals("L")) {
						/**
						 * Liquid Container
						 */
						container = new LiquidContainer(containerId, weight);}
					
				}
				else if(weight > 3000) {
					container = new HeavyContainer(containerId, weight);}
				else {
					/**
					 * Basic Container
					 */
					container = new BasicContainer(containerId, weight);}
				
				ports.get(portsId).addContainer(container);
				containers.add(container);
				containerId++;
				break;
				
			case "2":
				/**
				 * 2. Creating a ship
				 * Input format:	2 <InitialPortId> <MaxWeightOfAllCont> <MaxNumOfAllCont> <MaxNumOfHeavyCont> <MaxNumOfRCont> <MaxNumOfLCont> <FuelConsumptionPerKm in doubles>
				 */
				portsId = Integer.parseInt(linearray[1]);
				int maxWeightOfAllCont = Integer.parseInt(linearray[2]);
				int maxNumberOfAllCont = Integer.parseInt(linearray[3]);
				int maxNumberOfHeavyCont = Integer.parseInt(linearray[4]);
				int maxNumberOfRCont = Integer.parseInt(linearray[5]);
				int maxNumberOfLCont = Integer.parseInt(linearray[6]);
				/**
				 * Fuel consumption per km of the ship:
				 */
				double fuelConsumptionPerKm = Double.parseDouble(linearray[7]);
				
				
				Ship ship = new Ship(shipId, ports.get(portsId), maxWeightOfAllCont, maxNumberOfAllCont, maxNumberOfHeavyCont, 
						maxNumberOfRCont, maxNumberOfLCont, fuelConsumptionPerKm);
				/**
				 * Each new Ship is added to ships ArrayList.
				 * Also the shipId value is increased by 1.
				 */
				ships.add(ship);
				shipId++;
				break;
				
			case "3":
				/**
				 *  3. Creating a port
				 *  Input format:	3 <xCoordinates> <yCoordinates> 
				 */
				double xCoordinate = Double.parseDouble(linearray[1]);
				double yCoordinate = Double.parseDouble(linearray[2]);
				Port port = new Port(portId, xCoordinate, yCoordinate);
				/**
				 * Each new Port is added to ports ArrayList.
				 * Also the portId value is increased by 1.
				 */
				ports.add(port);
				portId++;
				break;
				
			case "4":
				/** 4. Loading a container to a ship
				* Input format:	4 <ShipId> <ContainerId>
				* A container with that ID will always exist but may or may not be in the port where the ship currently is. 
				* If the container is currently in the port, it may or may not be loaded into the ship depending on the restrictions of that ship.
				* First we check whether the container and the ship are in the same port.
				*/
				Ship shipWanted = ships.get(Integer.parseInt(linearray[1]));
				Container containerWanted = containers.get(Integer.parseInt(linearray[2]));
				Port portOfShip = shipWanted.getCurrentPort();
				if(portOfShip.getContainers().contains(containerWanted)) {
					/**
					 *  This means the ship and the container are in the same port. 
					 *  Now we check other limitations of the ship in the load method of Ship Class:
					 */
					shipWanted.load(containerWanted);
				}
				break;
				
			case "5":
				/** 5. Unloading a container from a ship
				* Input format:	5 <ShipId> <ContainerId>
				*/
				shipWanted = ships.get(Integer.parseInt(linearray[1]));
				containerWanted = containers.get(Integer.parseInt(linearray[2]));
				if(shipWanted.getCurrentContainers().contains(containerWanted)) {
					if(containerWanted.getCurrentShipId() == shipWanted.getID()) {
						/**
						 *  the container is in the ship
						 */
						shipWanted.unLoad(containerWanted);
					}
				}
				break;
				
			case "6":
				/** 6. Ship sails to another port
				* Input format:	6 <ShipId> <PortId>
				* If Ship has enough fuel, it should travel to the port.
				*/
				shipWanted = ships.get(Integer.parseInt(linearray[1]));
				Port portWanted = ports.get(Integer.parseInt(linearray[2]));
				/**
				 * We first check whether the ship has enough fuel to sail:
				 */
				shipWanted.sailTo(portWanted);
				break;
			
			case "7":
				/**
				 * 7. Ship is refueled
				 * Input format:	7 <ShipId> <AmountOfFuel>
				 */
				shipWanted = ships.get(Integer.parseInt(linearray[1]));
				double amountOfFuel = Double.parseDouble(linearray[2]);
				shipWanted.addFuel(amountOfFuel);
				break;
				
			default:
				throw new IllegalArgumentException("Unexpected value: " + typeOfInput);
			}
			
			
			
		}
		
		
	
		/**
		 * We start printing the Output. 
		 */
		for(int i = 0; i< ports.size(); i++) {
			Port port = ports.get(i);
			out.print("Port " + i + ": (");
			out.printf("%.2f", port.getX());
			out.print(", ");
			out.printf("%.2f", port.getY());
			out.print(")");
			out.println();
			ArrayList<ArrayList<Integer>> allContainers = port.giveContainers();
			if(!allContainers.get(0).isEmpty()) {
				out.printf("  BasicContainer:");
				for(Integer index : allContainers.get(0)) {
					out.printf(" " + index);
				}
				out.println();
			}
			if(!allContainers.get(1).isEmpty()) {
				out.printf("  HeavyContainer:");
				for(Integer index : allContainers.get(1)) {
					out.printf(" " + index);
				}
				out.println();
			}
			if(!allContainers.get(2).isEmpty()) {
				out.printf("  RefrigeratedContainer:");
				for(Integer index : allContainers.get(2)) {
					out.printf(" " + index);
				}
				out.println();
			}
			if(!allContainers.get(3).isEmpty()) {
				out.printf("  LiquidContainer:");
				for(Integer index : allContainers.get(3)) {
					out.printf(" " + index);
				}
				out.println();
			}
			/**
			 * For each ship in the port -in order according to the IDs-, we print the necessary info of the Ship.
			 */
			for(Ship ship : port.getCurrent()) {
				out.print("  Ship " + ship.getID() + ": ");
				out.printf("%.2f", ship.getFuel());
				out.println();
				ArrayList<ArrayList<Integer>> allContainers2 = ship.giveContainers();
				if(allContainers2.get(0).size() > 1 ) {
					out.printf("    BasicContainer:");
					for(Integer index : allContainers2.get(0)) {
						if(index != -1)
							out.printf(" " + index);
					}
					out.println();
				}
				if(allContainers2.get(1).size() > 1) {
					out.printf("    HeavyContainer:");
					for(Integer index : allContainers2.get(1)) {
						if(index != -1)
							out.printf(" " + index);
					}
					out.println();
				}
				if(allContainers2.get(2).size() > 1) {
					out.printf("    RefrigeratedContainer:");
					for(Integer index : allContainers2.get(2)) {
						if(index != -1)
							out.printf(" " + index);
					}
					out.println();
				}
				if(allContainers2.get(3).size() > 1) {
					out.printf("    LiquidContainer:");
					for(Integer index : allContainers2.get(3)) {
						if(index != -1)
							out.printf(" " + index);
					}
					out.println();
				}
			}
		}
		
		
		
		in.close();
		out.close();
	}
}
