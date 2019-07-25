package AbstractReader;

import org.json.simple.JSONObject;
/**
 * 
 * @author zhuqianglu
 * The abstract class of a TableReader object, since the format of a JSONObject could be changed in the following stages,
 * the implementation of each method is done in children
 */
public abstract class TableReader {
	protected JSONObject obj;
	public TableReader(JSONObject obj) {
		this.obj = obj;
	}
	/**
	 * this method returns the colour the table in String
	 * @return String
	 */
	public abstract String getColour();
	/**
	 * this method returns the size of the table in a long array
	 * @return long[]
	 */
	public abstract long[] getSize();
	/**
	 * this method returns the friction of a table in double
	 * @return
	 */
	public abstract double getFriction();
}
