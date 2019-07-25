package ConcreteFactory;

import org.json.simple.JSONObject;

import AbstractReader.BallReader;
import AbstractReader.TableReader;
import AbstractReaderFactory.ReaderFactory;
import ConcreteReaders.StageOneBallReader;
import ConcreteReaders.StageOneTableReader;
/**
 * The concrete factory responsible to create concrete table reader and ball reader
 * This factory is implemented as a singleton
 * @author zhuqianglu
 *
 */
public class StageOneReaderFactory implements ReaderFactory{
	
	//this factory is implemented using lazy initialization
	private static ReaderFactory factory = null;
	
	/**
	 * the private constructor of the reader factory
	 */
	private StageOneReaderFactory() {
		
	}
	
	/**
	 * returns a reader factory, if the factory is not created, then first create a reader factory
	 * @return ReaderFactory factory
	 */
	public static ReaderFactory getFactory() {
		if(factory == null) {
			factory = new StageOneReaderFactory();
		}
		return factory;
	}
	
	/**
	 * returns a BallReader object specifically for the given JSONObject
	 * @param JSONObject obj
	 * @return BallReader reader
	 */
	@Override
	public BallReader createBallReader(JSONObject obj) {
		return new StageOneBallReader(obj);
	}
	
	/**
	 * returns a TableReader object specifically for the given JSONObject
	 * @param JSONObject obj
	 * @return TableReader reader
	 */
	@Override
	public TableReader createTableReader(JSONObject obj) {
		return new StageOneTableReader(obj);
	}

}

