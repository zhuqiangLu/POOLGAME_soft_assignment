package AbstractReaderFactory;

import org.json.simple.JSONObject;

import AbstractReader.*;
/**
 * 
 * @author zhuqianglu
 * This is the abstract factory of the ball reader and table reader.
 */
public interface ReaderFactory {
	
	/**
	 * create a Ball reader specifically for a JSONObject
	 * @param JSONObjct
	 * @return BallReader
	 */
	public BallReader createBallReader(JSONObject obj);
	/**
	 * create a Table reader specifically for a JSONObject
	 * @param JSONObject
	 * @return TableReader
	 */
	public TableReader createTableReader(JSONObject obj);
}
