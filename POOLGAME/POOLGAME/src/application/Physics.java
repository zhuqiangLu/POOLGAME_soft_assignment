package application;

import AbstractProduct.Ball;
import AbstractProduct.Table;
/**
 * 
 * @auahor zhuqianglu
 * The physics class is responsible to detect and handle the collisions between balls and table boundary.
 *  
 */
public interface Physics {
	
	/**
	 * This method verifies if ballA collides with ballB, if yes then return true, otherwise false.
	 * @param Ball ballA
	 * @param Ball ballB
	 * @return Boolean value
	 */
	public boolean collision(Ball ballA, Ball ballB);
	
	/**
	 * This method is to verifiy whether a ball has reached the boundary of the table
	 * @param Ball ball
	 * @param Table table
	 * @return Boolean
	 */
	public boolean collision(Ball ball, Table table);
	
	/**
	 * This method simply change the direction of the velocity of a ball as it has reached the boundary of the table and get bounced off.
	 * @param ballA
	 */
	public void bounceOff(Ball ballA);
	
	/**
	 *  This method calculate the velocity of ballA and ballB after the collision
	 * @param Ball ballA
	 * @param Ball ballB
	 */
	public void collide(Ball ballA, Ball ballB);
}
