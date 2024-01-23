

package containers;

/**
 * This is the Refrigerated Container class that extends HeavyContainer super class.
 * @author Ebrar Kiziloglu
 *
 */
public class RefrigeratedContainer extends HeavyContainer{

	/**
	 * Constructor
	 * @param ID, int
	 * @param weight, int
	 */
	public RefrigeratedContainer(int ID, int weight) {
		super(ID, weight);
		type =4;
	}

	/**
	 * The method overrides the consumption method of super class.
	 * @return consumption of Refrigerated Containers, double
	 */
	@Override
	public double consumption() {
		return super.weight * 5.00;
	}

	/**
	 * The method overrides the getType method of super class.
	 * @return type = 4, int
	 */
	@ Override
	public int getType() {
		return 4;
	}
}
 

