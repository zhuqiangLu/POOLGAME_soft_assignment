package AbstractBuilder;

import AbstractProduct.Table;
/**
 * 
 * @author zhuqianglu
 *This is a Table builder interface which specifies a table builder should has the following functions
 */
public interface TableBuilder {
	/**
	 * set the colour of the table
	 * @param String colour
	 */
	public void setColour(String colour);
	/**
	 * set the size of the table
	 * @param long[] size 
	 */
	public void setSize(long[] size);
	/**
	 * set the friction of the table
	 * @param double friction
	 */
	public void setFriction(double friction);
	/**
	 * returns a Table object
	 * @return Table
	 */
	public Table getTable();
}
