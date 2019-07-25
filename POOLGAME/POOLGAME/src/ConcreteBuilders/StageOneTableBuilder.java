package ConcreteBuilders;

import AbstractBuilder.TableBuilder;
import AbstractProduct.Table;
import ConcreteBallAndTable.StageOneTable;
/**
 * A concrete table builder implementing the TableBuilder interface, this class is used to create a StageOneTable object
 */
public class StageOneTableBuilder implements TableBuilder{
	private String colour;
	private long[] size;
	private double friction;
	
	/**
	 * set the colour of the table
	 * @param String colour
	 */
	@Override
	public void setColour(String colour) {
		
		this.colour = colour;
	}
	
	/**
	 * set the size of the table
	 * @param long[] size
	 */
	@Override
	public void setSize(long[] size) {
		
		this.size = size;
	}
	
	/**
	 * set the friction of the table
	 * @param double friction
	 */
	@Override
	public void setFriction( double friction) {
		
		this.friction = friction;
	}
	
	/**
	 * returns a Table object
	 * @return Table
	 */
	public Table getTable() {
		return new StageOneTable(colour, size, friction);
	}
}
