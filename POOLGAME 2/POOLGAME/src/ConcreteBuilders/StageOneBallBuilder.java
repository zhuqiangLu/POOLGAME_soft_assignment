package ConcreteBuilders;
import AbstractBuilder.BallBuilder;
import AbstractProduct.Ball;
import ConcreteBallAndTable.StageOneBall;
import javafx.geometry.Point2D;

/*
 * A concrete ball builder implementing the BallBuilder interface,
 * this class is used to create a StageOneBall object
 * Since the radius of a ball is not specified in the JSONObject, the default radius is 10
 */
public class StageOneBallBuilder implements BallBuilder{
	private String colour;
	private Point2D Position;
	private Point2D velocity;
	private double mass;
	
	/**
	 * set the colour 
	 * @param String colour
	 */
	@Override
	public void setColour(String colour) {
		this.colour = colour;
	}
	
	/**
	 * set the position of the ball
	 * @param Point2D position
	 */
	@Override
	public void setPosition(Point2D position) {
		this.Position = position;
	}
	
	/**
	 * set the velocity of the ball
	 * @param Point2D velocity
	 */
	@Override
	public void setVelocity(Point2D velocity) {
		this.velocity  = velocity;
	}
	
	/**
	 * set the mass of the ball
	 * @param double mass
	 */
	@Override
	public void setMass(double mass) {
		this.mass = mass;
	}
	
	/**
	 * This method creates a StageOneBall using default radius
	 * @return Ball
	 */
	public Ball getBall() {
		return new StageOneBall(colour, Position, velocity, mass, 10.0);
	}
	
	/**
	 * This method creates a StageOneBall with a specified radius
	 * @return Ball
	 */
	public Ball getBall(double radius) {
		return new StageOneBall(colour, Position, velocity, mass, radius);
	}
}
