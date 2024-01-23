

package containers;

/**
 * This is the Liquid Container class that extends HeavyContainer super class.
 * @author Ebrar Kiziloglu
 *
 */
public class LiquidContainer extends HeavyContainer{

	/**
	 * Constructor
	 * @param ID, int
	 * @param weight, int
	 */
	public LiquidContainer(int ID, int weight) {
		super(ID, weight);
		type = 3;
	}
	
	/**
	 * The method overrides the consumption method of super class.
	 * @return consumption of Liquid Containers, double
	 */
	@Override
	public double consumption() {
		return super.weight * 4.00;
	}

	/**
	 * The method overrides the getType method of super class.
	 * @return type = 3, int
	 */
	@ Override
	public int getType() {
		return 3;
	}
}