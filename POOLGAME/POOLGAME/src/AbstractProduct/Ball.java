package AbstractProduct;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * 
 * @author zhuqianglu
 * 1. This is an abstract ball class, the basic functions have already been implemented. 
 * 2. If needed, client can override any function in the following stages.
 * 3. A ball object should be created by a builder
 * 4. The ball object should create a javafx circle which represents this ball in the game
 */

public abstract class Ball {
	protected String colour;
	protected Point2D position;
	protected Point2D velocity;
	protected double mass;
	protected double radius;
	protected Circle ball;
	protected boolean visible;
	/**
	 * 
	 * @param colour
	 * @param position
	 * @param velocity
	 * @param mass
	 * @param radius
	 * A normal constructor of the a Ball object 
	 * To avoid calling method move() before the JavaFx Circle object is created
	 *  , a JavaFX Circle object is created when a Ball object is built.
	 */
	public Ball(String colour,Point2D position,Point2D velocity,double mass,double radius) {
		this.colour = colour;
		this.position = position;
		this.velocity = velocity;
		this.mass = mass;
		this.radius = radius;
		
		float x = (float)position.getX();
		float y = (float)position.getY();
		
		visible = true;
		ball = new Circle(x, y, radius);
		
		Color color = Color.valueOf(colour.toUpperCase());
		
		ball.setFill(color);
		
	}
	
	/**
	 * return colour in string
	 * @return String colour
	 */
	public  String getColour() {
		return colour;
	}
	
	/**
	 * return position in Point2D
	 * @return Point2D position
	 */
	public  Point2D getPosition() {
		return position;
	}
	
	/**
	 * return velocity in Point2D
	 * @return Point2D velocity
	 */
	public  Point2D getVelocity() {
		return velocity;
	}
	
	/**
	 * return radius in double
	 * @return double raidus
	 */
	public double getRadius() {
		return radius;
	}
	
	/**
	 * return mass in double
	 * @return double mass
	 */
	public  double getMass() {
		
		return mass;
	}
	
	/**
	 * return a JavaFX Circle object to represent the ball
	 * @return Circle ball
	 */
	public Circle getBall() { 
		return ball;
	}
	
	/**
	 * update the velocity 
	 * @param Point2D newVelocity
	 */
	public void setVelocity(Point2D newVelocity) {
		
		this.velocity = newVelocity;

	}
	
	/**
	 * return the position Y in double 
	 * @return double positionY
	 */
	public double getPositionY() {
		
		return position.getY();
	}
	
	/**
	 * return the position X in double 
	 * @return double positionX
	 */
	public double getPositionX() {
		return position.getX();
	}
	
	/**
	 * update position Y 
	 * @param double y
	 *
	 */
	public void setPositionY(double y) {
		
		position = new Point2D(position.getX(), y);
	}
	
	/**
	 * update the position x
	 * @param double x
	 */
	public void setPositionX(double x) {
		position = new Point2D(x, position.getY());
	}
	
	/**
	 * when the ball hits the pockets, this method hides the ball by setting a small radius and set it not in motion
	 */
	public void hide() { 
		visible = false;
		ball.setRadius(0.001);
		this.setVelocity(new Point2D(0.0, 0.0));
	}
	
	/**
	 * return whether the ball is still visible
	 * @return boolean visible
	 */
	public boolean getVisibility() { 
		return visible; 
	}
	
	/**
	 * this method returns boolean to indicate whether the ball is in motion or not
	 * @return boolean
	 */
	public boolean isStopped() {
		if(velocity.getX() == 0 && velocity.getY() == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * 
	 * 
	 * @param friction
	 * The method move updates the position of the Ball object and the JavaFX Circle object according
	 * to the table friction and the velocity of the ball.
	 * 
	 * 
	 */
	public void move(double friction) {
		
		friction = friction * 0.2;
		//get the deceleration done by friction
		double ax = friction * mass ;
		double ay = friction * mass ;
		
		//get the absolute x and y velocity
		double absvx = Math.abs(velocity.getX());
		double absvy = Math.abs(velocity.getY());
		
		//to calculate the angle of vx and vy, then we can calculate the decelerations on vx and vy
		//----->vx
		//|\
		//| \
		//|  \
		//vy  speed
		double angle = Math.atan2(velocity.getY(), velocity.getX());
		
		//first update the position
		Point2D displacement = velocity.multiply(0.1);
		position = position.add(displacement);
		ball.setCenterX(position.getX());
		ball.setCenterY(position.getY());
		
		//then to update the velocity according to friction
		//since the friction affects the velocity no matter the velocity is negative or position, we use absolute velocity here
		//There are two scenerios: the absolute velocity drops down to 0 or the absolute velocity is still greater than 0 
		if(absvx - ax < 0.0) {
			//vx drops down to 0 due to friction
			velocity = velocity.subtract(velocity.getX(), 0.0);
		}
		else {
			//the deceleration on vx can be calculated by deceleration * cos(angle)
			velocity = velocity.subtract(ax * Math.cos(angle) , 0.0);
		}
		
		if(absvy - ax < 0.0) {
			//vy drops down to 0 due to friction
			velocity = velocity.subtract(0.0, velocity.getY());
		}
		else {
			// the deceleration on vy can be calculated by deceleration * sin(angle)
			velocity = velocity.subtract(0.0, ay * Math.sin(angle));
		}
	
	}
}
