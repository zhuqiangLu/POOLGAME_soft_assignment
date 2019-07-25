package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import AbstractBuilder.BallBuilder;
import AbstractBuilder.TableBuilder;
import AbstractProduct.Ball;
import AbstractProduct.Table;
import AbstractReaderFactory.ReaderFactory;
import ConcreteBuilders.Director;
import ConcreteBuilders.StageOneBallBuilder;
import ConcreteBuilders.StageOneTableBuilder;
import ConcreteFactory.StageOneReaderFactory;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

/**
 * The game engine, responsible to simulate a pool game using given CollisionHandler and Table and Balls
 * @author zhuqianglu
 *
 */
public class PoolEngine extends Application {
	
	private static Table table;
	private static ArrayList<Ball> normalBalls = new ArrayList<Ball>();
	private static Ball cueBall;
	private static Rectangle cueStick;
	private static Physics physics;
	private static Rotate rotate;
	private static int stickWidth = 10;
	//To specify the position of a cue stick
	private static double stickHeadX ;
	private static double stickHeadY;
	
	
	/**
	 * This method returns the angle of the mouse to the cue ball
	 * @param double mouseX
	 * @param double mouseY
	 * @return double angle
	 */
	static double getAngle(double mouseX, double mouseY) {
		//angle = tan ^ -1 (dx/dy)
		double dx = mouseX - cueBall.getPositionX();
		double dy = mouseY - cueBall.getPositionY();
		double angrad = Math.atan2(dx, dy);
		return 90 - Math.toDegrees(angrad);
	}
	
	/**
	 * this method is to initialize a cue stick
	 */
	static void getCueStick() {
		
		//initialize the cue stick
		cueStick = new Rectangle(0,0,0,stickWidth);
		cueStick.setFill(Color.BROWN);
		//calculate the position of cue stick
		stickHeadX = cueBall.getPositionX() + cueBall.getRadius();
		stickHeadY = cueBall.getPositionY() - stickWidth/2;
		
		//create a rotate object
		rotate = new Rotate();
		
		//set pivot point and angle
		rotate.setPivotX(cueBall.getPositionX());
		rotate.setPivotY(cueBall.getPositionY());
		rotate.setAngle(0);
		
		//Add rotate to cue stick
		cueStick.getTransforms().add(rotate);
	}
	
	
	/**
	 * this method is to relocate position of the cue stick
	 */
	static void relocateCueStick() {
		//calculate the position of the head
		stickHeadX = cueBall.getPositionX() + cueBall.getRadius();
		stickHeadY =cueBall.getPositionY() - stickWidth/2;
		//set location
		cueStick.setX(stickHeadX);
		cueStick.setY(stickHeadY);
		
		//relocate the pivot point
		rotate.setPivotX(cueBall.getPositionX());
		rotate.setPivotY(cueBall.getPositionY());
		
		//set the angle as 0
		rotate.setAngle(0);
		
		//hide the length of the cue stick
		cueStick.setWidth(0);
	}
	
	/**
	 * move every ball including the cue ball when this method is call
	 */
	static void moveBalls() {
		
		//move the cue ball in each frame 
		cueBall.move(table.getFriction());
		
		
		//detect collision between boundary and cueball
		if(physics.collision(cueBall, table) ) {
			physics.bounceOff(cueBall);
		}
		
		for(int i = 0; i < normalBalls.size(); i++) {
			
			Ball ball = normalBalls.get(i);
			//detect collision between ball and cueball
			if(physics.collision(ball, cueBall)) {
				physics.collide(ball, cueBall);
			}

		}
		
		//move the remaining balls
		for(int i = 0; i < normalBalls.size(); i++) {
			Ball ball = normalBalls.get(i);
			ball.move(table.getFriction());
			
			//detect collision between ball and table
			if(physics.collision(ball, table) ) {
				physics.bounceOff(ball);
			}
			
			//detect collision between ball and cueball
			if(physics.collision(ball, cueBall) ) {
				physics.collide(ball, cueBall);
			}
			
			//detect collision between balls
			for(int j = 0; j < normalBalls.size(); j++) {
				Ball ballB = normalBalls.get(j);
				
				if(	physics.collision(ball, ballB) 
					&& i != j //not the same ball 
					) {
					
					physics.collide(ball, ballB);
					
				}
			}
		
			
		}
	}
	/**
	 * return if the cue ball is stopped
	 * @return boolean
	 */
	static boolean isCueBallStop() {
		return cueBall.isStopped();
	}
	
	/**
	 * return if every ball is stopped
	 * @return boolean
	 */
	static boolean areBallsStop() {
		
		//check other balls	
		for(Ball ball: normalBalls) {
			//if one ball is still in motion then return false
			if( ball.isStopped() == false) {
				return false;
			}
		}
		//then if all other balls are not in motion, then it depends on the cue ball
		return isCueBallStop();
		
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			BorderPane root = new BorderPane();
			
			//the size of the scene is dependent of the size of table
			Scene scene = new Scene(root,table.getSizeX(),table.getSizeY());
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			//Add all component to the root
			root.getChildren().add(table.getTable());	
			root.getChildren().add(cueBall.getBall());	
			root.getChildren().add(cueStick);	
			for(int i = 0; i < normalBalls.size(); i++) {
				root.getChildren().add(normalBalls.get(i).getBall());
			}
			
			//use relocate to initialize the cue stick
			relocateCueStick();
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
			//after tests, game runs smoothest when duration is 10 millis second
			//set up the frame event
			Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10),
	                  new EventHandler<ActionEvent>() 
			{
				
				@Override
				public void handle(ActionEvent event) {
			
					//move every ball
					moveBalls();
					
					//can only select the ball when the ball is not in motion
					if( areBallsStop() ) {
						
						//set event when cue ball is selected when it is not in motion
						cueBall.getBall().setOnMousePressed(new EventHandler<MouseEvent>(){

							@Override
							public void handle(MouseEvent event) {
				
								//when the mouse is dragged, the cue stick extends from the boundary of the ball
								// the length of the cue stick represent the force that will act on the cue ball
								cueBall.getBall().setOnMouseDragged(new EventHandler<MouseEvent>() {
								
									double distance = 0;
									@Override
									public void handle(MouseEvent event) {
									
										//show the cue stick if not ball is in motion
										cueStick.setVisible( areBallsStop());
										
										//get the angle
										double angle = getAngle(event.getX(), event.getY());
										//update rotate angle when it is being dragged
										rotate.setAngle(angle);	
							
										//calculate the distance
										double distx = (cueBall.getPositionX() - event.getX());
										double disty = (cueBall.getPositionY() - event.getY());
										distance = Math.sqrt((distx*distx) + (disty* disty));
										
										//set the length of the cue stick as it is being dragged
										cueStick.setX(stickHeadX);
										cueStick.setWidth(distance);
										
									/*
									 * when the mouse is released, the cue stick shoot at the cue ball
									 * update the cue ball velocity when the mouse is released
									 */	
									cueBall.getBall().setOnMouseReleased(new EventHandler<MouseEvent>() {
										
										@Override
										public void handle(MouseEvent event) {
										
											// if cue ball is not in motion, then we can update the speed of the cue ball
											if( areBallsStop() ) {
		
												//limit the speed as the speed is too great, a ball might fly off the boundary in one frame
												if(distance >= 300) {distance = 300;}
												
												//get angle to calculate the speed of x and y
												double angle = Math.toRadians(rotate.getAngle());
												double cx = Math.cos(-angle) * -distance;
												double cy = Math.sin(-angle) * distance;
											
												//set the new velocity
												cueBall.setVelocity(new Point2D(cx/2, cy/2));
												
												//hide the cue stick
												cueStick.setVisible(false);
												
												
											}

										}	
									});
															
									
								}	
							});
								
								
							//relocate the cue stick according to the cue ball if not ball is in motion
							if( areBallsStop() ) {
								cueStick.setVisible(true);
								//relocate the cue stick
								relocateCueStick();
							}				
							
						}	
						
					});
					
					}
									
				}
			
			}));
			
			//set it as infinite loop
			timeline.setCycleCount(Timeline.INDEFINITE);
			timeline.play();
		
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
	
		JSONParser parser = new JSONParser();
		
		try {
			
			//get the reader factory
			ReaderFactory factory = StageOneReaderFactory.getFactory();
			
			//create builders
			BallBuilder ballBuilder = new StageOneBallBuilder();
			TableBuilder tableBuilder = new StageOneTableBuilder();
			
			//find the config file
			Object object = parser.parse(new FileReader(args[0]));
			
			// convert Object to JSONObject
			JSONObject jsonObject = (JSONObject) object;
						
			//get the Ball JSONArray
			JSONObject Balls = (JSONObject)jsonObject.get("Balls");
			JSONArray balls = (JSONArray)Balls.get("ball");
			
			//get the Table JSONObject
			JSONObject Table = (JSONObject)jsonObject.get("Table");
			
			//initialize the Table 
			table = Director.constructTable(tableBuilder, factory.createTableReader(Table));
			
			for(int i = 0; i < balls.size(); i++) {
				//convert to json object
				JSONObject ball = (JSONObject) balls.get(i);
				
				String color = (String) ball.get("colour");
				
				if(color.equals("white")) {

					//initialize the cue ball
					cueBall = Director.constructBall(ballBuilder, factory.createBallReader(ball));
				}
				else {
					//initialize other balls
					normalBalls.add( Director.constructBall(ballBuilder, factory.createBallReader(ball)));
					
				}
			}
			
			//initialize the cue Stick
			getCueStick();
			
			//initialize the Physics
			physics = new StageOnePhysics();
			
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		launch(args);
		
	}
}