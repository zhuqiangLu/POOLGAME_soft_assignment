package AbstractReader;

import org.json.simple.JSONObject;

import javafx.geometry.Point2D;
/**
 * 
 * @author zhuqianglu
 * The abstract class of the a BallReader object
 * Since the format of a JSONObject could be changed in the following stages, the implementation of each method is done in the children
 */
public abstract class BallReader {
	protected JSONObject obj;
	public BallReader(JSONObject obj) {
		this.obj = obj;
	}
	/**
	 * this method returns the colour of the ball in string
	 * @return String 
	 */
	public abstract String getColour();
	/**
	 * this method returns the position of the ball in Point2D
	 * @return Point2D
	 */
	public abstract Point2D getPosition();
	/**
	 * this method returns the velocity of the ball in Point2D
	 * @return
	 */
	public abstract Point2D getVelocity();
	/**
	 * this method retuens the mass of the ball in double
	 * @return
	 */
	public abstract double getMass();
}
