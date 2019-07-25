package ConcreteBuilders;

import AbstractBuilder.BallBuilder;
import AbstractBuilder.TableBuilder;
import AbstractProduct.Ball;
import AbstractProduct.Table;
import AbstractReader.BallReader;
import AbstractReader.TableReader;

public class Director {
	/**
	 * This method specifies how to construct a Ball object, it reads the attributes of a JSONObject to build a Ball
	 * 
	 * @param BallBuilder ballBuilder
	 * @param BallReader reader
	 * @return Ball 
	 */
	public static Ball constructBall(BallBuilder ballBuilder, BallReader reader) {
		ballBuilder.setColour(reader.getColour());
		ballBuilder.setMass(reader.getMass());
		ballBuilder.setPosition(reader.getPosition());
		ballBuilder.setVelocity(reader.getVelocity());
		return ballBuilder.getBall();
		
	}
	/**
	 * This method specifies how to construct a Table object using a table builder and a table reader
	 * it reads the attributes of a table from the TableReader and then to create a table object according to these attributes
	 * 
	 * @param TableBuilder builder
	 * @param TableReadr reader
	 * @return
	 */
	public static Table constructTable(TableBuilder builder, TableReader reader) {
		builder.setColour(reader.getColour());
		builder.setFriction(reader.getFriction());
		builder.setSize(reader.getSize());
		return builder.getTable();
	}
}
