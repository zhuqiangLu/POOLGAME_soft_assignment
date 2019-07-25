package ConcreteReaders;

import org.json.simple.JSONObject;
import AbstractReader.BallReader;
import javafx.geometry.Point2D;


/**
 * Concrete BallReader, extends from abstract class BallReader
 * @author zhuqianglu
 *
 */
public class StageOneBallReader extends BallReader {
	/**
	 * The initialization is done by super class
	 * @param JSONObject obj
	 */
	public StageOneBallReader(JSONObject obj) {
		super(obj);
	}
	
	/**
	 * returns the colour of the ball according to the given JSONObject
	 * @return String colour
	 */
	@Override
	public String getColour() {
		String colour = (String) super.obj.get("colour");
		return colour;
	}
	

	/**
	 * returns the ball position in Point2D according to the given JSONObject
	 * @return Point2D position
	 */
	@Override
	public Point2D getPosition() {
		// the position is stored in a JSONObject which is inside another JSONObject
		//to retrieve the position JSONObject
		JSONObject position = (JSONObject)super.obj.get("position");
		double sx = (double)position.get("x");
		double sy = (double)position.get("y");
		
		return new Point2D(sx, sy);
	}
	
	
	/**
	 * returns the velocity of the ball according to the JSONObject
	 * @return Point2D velocity
	 */
	@Override
	public Point2D getVelocity() {
		// the velocity is also stored in a JSONObject which is inside the ball JSONObject
		//to retrieve the velocity JSONObject
		JSONObject velocity = (JSONObject)super.obj.get("velocity");
		double dx = (double)velocity.get("x");
		double dy = (double)velocity.get("y");
		
		return new Point2D(dx, dy);
		
	}
	
	/**
	 * returns the mass of the ball according to the JSONObject
	 * @return double mass
	 */
	@Override
	public double getMass() {
		double mass = (double)super.obj.get("mass");
		return mass;
	}
	
}
