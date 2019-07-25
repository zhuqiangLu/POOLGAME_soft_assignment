
package AbstractProduct;



import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * 
 * @author zhuqianglu
 *This is an abstract Table class, a JavaFX Rectangle object to represents the table will be created
 *when the Table object is built.
 */
public abstract class Table {
	
	protected String colour;
	protected long[] size;
	protected double friction;
	protected Rectangle table;
	
	/**
	 * the constructor, the Rectangle and pockets will be created when the object is created
	 * 
	 * @param String colour
	 * @param long[] size
	 * @param double friction
	 */
	public Table(String colour, long[] size, double friction) {
		this.colour = colour;
		this.size = size;
		this.friction = friction;
		
		float x = size[0];
		float y = size[1];
		Color color = Color.valueOf(colour.toUpperCase());
		
		//create the table
		table = new Rectangle(x,y);
		table.setFill(color);

	}

	
	/**
	 * return the colour of the table in string
	 * @return String colour
	 */
	public String getColour() {
		return colour;
	}
	
	/**
	 * return the size of the table in long array
	 * @return long[] size
	 */
	public long[] getSize() {
		return size;
	}
	
	/**
	 * return the size X of the table
	 * @return long szieX
	 */
	public long getSizeX() {
		return size[0];
	}
	
	/**
	 * return the size Y of the table
	 * @return long sizeY
	 */
	public long getSizeY() {
		return size[1];
	}
	
	/**
	 * return the friction of the table in double
	 * @return double friction
	 */
	public double getFriction() {
		return friction;
	}
	
	/**
	 * return a JavaFX Rectangle object to represent the table
	 * @return Rectangle table
	 */
	public Rectangle getTable() {
		return table;
	}
	

}
