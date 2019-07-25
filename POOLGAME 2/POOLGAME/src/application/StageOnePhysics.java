package application;

import AbstractProduct.Ball;
import AbstractProduct.Table;
import javafx.geometry.Point2D;
/**
 * the implementation of the physics interface,
 * this class is responsible to detect collision and update the velocity of a ball after collision
 * @author zhuqianglu
 *
 */
public class StageOnePhysics implements Physics {
	
	/**
	 * This method verifies if ballA collides with ballB, if yes then return true, otherwise false.
	 * @param Ball ballA
	 * @param Ball ballB
	 * @return Boolean value
	 */
	@Override
	public boolean collision(Ball ballA, Ball ballB) {
		//ballA position
		double ballAX = ballA.getPosition().getX();
		double ballAY = ballA.getPosition().getY();
		
		//ballA position
		double ballBX = ballB.getPosition().getX();
		double ballBY = ballB.getPosition().getY();
		
		//radius of each ball
		double radiusA = ballA.getRadius();
		double radiusB = ballB.getRadius();
		
		//the absolute distance of x and y components
		double absX = Math.abs(ballAX - ballBX);
        double absY = Math.abs(ballAY - ballBY);
         
        //the absolute distance of two balls
        //dist = sqrt(x^2 + y^2)
        double dist = Math.sqrt( (absX * absX) + (absY * absY) );
        
        //if the absolute distance of two balls is within the range the sum of radii
        //then two balls collide
        if( dist < radiusA + radiusB) {
           	return true;
           	
        }     
        else {
        	return false;
        }
		
	}
	
	/**
	 * This method is to verifiy whether a ball has reached the boundary of the table
	 * @param Ball ball
	 * @param Table table
	 * @return Boolean
	 */
	@Override
	public boolean collision(Ball ball, Table table) {
		boolean result = false;
		//the position of the ball
		double ballX = ball.getPosition().getX();
		double ballY = ball.getPosition().getY();
		
		//the boundary of the table
		double tableX = (double)table.getSizeX();
		double tableY = (double)table.getSizeY();
		
		//the radius of the ball
		double radius = ball.getRadius();
		
		//if the raidus is greater than ballX or ballY, the center of the ball is already too close to the left or top boundary	
		if(ballX < radius ) {
			ball.setPositionX(radius);
			result = true;
		}
		if(ballY < radius) {
			ball.setPositionY(radius);
			result = true;
		}
		//if the ballx + radius  is greater than tableX, the ball has reached bottom boundary.
		if(ballX + radius > tableX  ) {
			ball.setPositionX(  tableX - radius);
			result = true;
		}
		if(ballY + radius > tableY) {
			ball.setPositionY(  tableY - radius);
			result = true;
		}
		return result;
		
	}
	
	/**
	 * This method simply change the direction of the velocity of a ball as it has reached the boundary of the table and get bounced off.
	 * @param ballA
	 */
	@Override
	public void bounceOff(Ball ballA) {
		//When a ball bounceOff the boundary, its velocity times -1
		Point2D velocity = ballA.getVelocity();
		velocity = velocity.multiply(-1.0);
		
		ballA.setVelocity(velocity);
	}
	
	/**
	 *  This method calculate the velocity of ballA and ballB after the collision
	 * @param Ball ballA
	 * @param Ball ballB
	 */
	@Override
	public void collide(Ball ballA, Ball ballB) {
		
		Point2D positionA = ballA.getPosition();
		Point2D velocityA = ballA.getVelocity();
		double massA = ballA.getMass();
		
		Point2D positionB = ballB.getPosition();
		Point2D velocityB = ballB.getVelocity();
		double massB = ballB.getMass();
        // Find the angle of the collision - basically where is ball B relative to ball A. We aren't concerned with
        // distance here, so we reduce it to unit (1) size with normalize() - this allows for arbitrary radii
        Point2D collisionVector = positionA.subtract(positionB);
        collisionVector = collisionVector.normalize();

        // Here we determine how 'direct' or 'glancing' the collision was for each ball
        double vA = collisionVector.dotProduct(velocityA);
        double vB = collisionVector.dotProduct(velocityB);

        // If you don't detect the collision at just the right time, balls might collide again before they leave
        // each others' collision detection area, and bounce twice. This stops these secondary collisions by detecting
        // whether a ball has already begun moving away from its pair, and returns the original velocities
        if (vB <= 0 && vA >= 0) {
        	ballA.setVelocity(velocityA);
        	ballB.setVelocity(velocityB);
            return;
        }

        // This is the optimisation function described in the gamasutra link. Rather than handling the full quadratic
        // (which as we have discovered allowed for sneaky typos) this is a much simpler - and faster - way of obtaining
        // the same results.
        double optimizedP = (2.0 * (vA - vB)) / (massA + massB);

        // Now we apply that calculated function to the pair of balls to obtain their final velocities
        Point2D velAPrime = velocityA.subtract(collisionVector.multiply(optimizedP).multiply(massB));
        Point2D velBPrime = velocityB.add(collisionVector.multiply(optimizedP).multiply(massA));
        ballA.setVelocity(velAPrime);
        ballB.setVelocity(velBPrime);
        
    }

}
