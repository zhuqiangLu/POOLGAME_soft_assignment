package ConcreteReaders;

import org.json.simple.JSONObject;

import AbstractReader.TableReader;
/**
 * The concrete table reader of abstract class TableReader
 * @author zhuqianglu
 *
 */
public class StageOneTableReader extends TableReader {
	
	
	public StageOneTableReader(JSONObject obj) {
		super(obj);
	}
	
	/**
	 * returns the colour of the table according to the given JSONObject
	 * @return String colour
	 */
	@Override
	public String getColour() {
		String colour = (String) super.obj.get("colour");
		return colour;
	}
	
	/**
	 * returns the size of the table in long array according to the JSONObject 
	 * @return long[] size
	 */
	@Override
	public long[] getSize() {
		//the size of the table is stored in another JSONObject, to obtain the size of the table,
		//first retrieve the JSONObject
		JSONObject size = (JSONObject)super.obj.get("size");
		long lx = (long)size.get("x");
		long ly = (long)size.get("y");
		long[] tableSize  = {lx, ly};
		
		return tableSize;
	}
	
	/**
	 * returns the friction of the table according to the JSONOject
	 * @return double friction
	 */
	@Override
	public double getFriction() {
		
		double friction = (double)super.obj.get("friction");
		return friction;
	}

}
