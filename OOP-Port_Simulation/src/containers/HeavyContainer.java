

package containers;

/**
 * This is the Heavy Container class that extends Container super class.
 * @author Ebrar Kiziloglu
 *
 */
public class HeavyContainer extends Container{

	/**
	 * Constructor
	 * @param ID, int
	 * @param weight, int
	 */
	public HeavyContainer(int ID, int weight) {
		super(ID, weight);
		type =2;
	}
	
	/**
	 * The method overrides the consumption method of super class.
	 * @return consumption of Heavy Containers, double
	 */
	@Override
	public double consumption() {
		return super.weight * 3.00;
	}
	
	/**
	 * The method overrides abstract getType method of super class.
	 * @return type = 2, int
	 */
	@Override
	public int getType() {
		return 2;
	}

}

