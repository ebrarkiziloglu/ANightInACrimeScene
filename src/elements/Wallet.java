package elements;

import java.util.ArrayList;

/**
 * Wallet class has fields to store current and blocked dollars and PQoins of the trader.
 * @author Ebrar Kızıloğlu
 *
 */
public class Wallet {
	/**
	 * current dollars of the trader
	 */
	private double dollars;
	/**
	 * current PQoins of the trader
	 */
	private double coins;
	/**
	 * blocked dollars of the trader
	 */
	private double blockedDollars;
	/**
	 * blocked PQoins of the trader
	 */
	private double blockedCoins;
	/**
	 * getter method for coins
	 * @return double, coins
	 */
	public double getCoins() {
		return coins;
	}
	/**
	 * getter method for dollars
	 * @return double, dollars
	 */
	public double getDollars() {
		return dollars;
	}
	/**
	 * getter method for total dollars
	 * @return double, dollars+blockedDollars
	 */
	public double getTotalDollars() {
		return dollars + blockedDollars;
	}
	/**
	 * getter method for total coins
	 * @return double, coins+blockedCoins
	 */
	public double getTotalCoins() {
		return coins + blockedCoins;
	}
	/**
	 * this method convert some coins into the blocked coins
	 * @param amount
	 */
	public void addBlockedCoins(double amount) {
		this.coins -= amount;
		this.blockedCoins += amount;
	}
	/**
	 * this method convert some dollars into the blocked dollars
	 * @param amount
	 */
	public void addBlockedDollars(double amount) {
		this.dollars -= amount;
		this.blockedDollars += amount;
	}
	/**
	 * this method increment the current coins
	 * @param amount of increment, double
	 */
	public void addCoins(double amount) {
		this.coins += amount;
	}
	/**
	 * this method reduces the amount of blocked dollars
	 * @param amount of increment, double
	 * @return boolea, true/false
	 */
	public boolean reduceBlockedDollars(double amount) {
		if(blockedDollars >= amount) {
			blockedDollars -= amount;
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * this method reduces the amount of blocked coins
	 * @param amount of increment, double
	 * @return boolea, true/false
	 */
	public boolean reduceBlockedCoins(double amount) {
		if(blockedCoins >= amount) {
			blockedCoins -= amount;
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * Constractor method
	 * @param dollars
	 * @param coins
	 */
	public Wallet(double dollars, double coins) {
		this.dollars = dollars;
		this.coins = coins;
	}
	
	/**
	 * this method increments the current dollars
	 * @param amount of increment, double
	 */
	public void deposit(double amount) {
		dollars += amount;
	}
	/**
	 * this method reduces the current dollars
	 * @param amount of reduction, double
	 */
	public void withdraw(double amount) {
		dollars -= amount;
	}

}
