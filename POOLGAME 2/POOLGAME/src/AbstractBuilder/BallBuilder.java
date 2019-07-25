package AbstractBuilder;

import AbstractProduct.Ball;
import javafx.geometry.Point2D;
/**
 * 
 * @author zhuqianglu
 * This is a BallBuilder interface which specifies a ball builder should has at least the following functions
 * 
 */
public interface BallBuilder {
	/**
	 * this method sets the colour of a ball
	 * @param colour
	 */
	public void setColour(String colour);
	/**
	 * this method sets the position of the ball
	 * @param position
	 */
	public void setPosition(Point2D position);
	/**
	 * set the velocity of the ball
	 * @param velocity
	 */
	public void setVelocity(Point2D velocity);
	/**
	 * set the mass of the ball
	 * @param mass
	 */
	public void setMass(double mass);
	/**
	 * To construct a Ball object after setting all of the attributes
	 * @return Ball
	 */
	public Ball getBall();
}
