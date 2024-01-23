package containers;

/**
 * This is the Basic Container class that extends Container super class.
 * @author Ebrar Kiziloglu
 *
 */
public class BasicContainer extends Container {

	/**
	 * Constructor
	 * @param ID, int
	 * @param weight, int
	 */
	public BasicContainer(int ID, int weight) {
		super(ID, weight);
		type = 1;
	}
	
	/**
	 * The method overrides the consumption method of super class.
	 * @return consumption of Basic Containers, double
	 */
	@ Override
	public double consumption() {
		return super.consumption();
	}
	
	/**
	 * The method overrides abstract getType method of super class.
	 * @return type = 1, int
	 */
	@Override
	public int getType() {
		return 1;
	}

}

