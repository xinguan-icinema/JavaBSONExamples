package aiexample;

import org.bson.*;
import org.bson.types.BasicBSONList;

/***
 * Example BSON message from Unity:
 * {
 *   "world": [
 *     { 
 *       "type": "player pos",
 *       "id": 2,
 *       "x": 0.512,
 *       "y": 0.0,
 *       "z": 3.3
 *     },
 *     {
 *       "type": "player action",
 *       "id": 2,
 *       "action": "waving"
 *     }
 *   ]
 * }
 * 
 */

public class AIBsonDeserialiser 
{
	AILogicInterface aiLogic;
	
	public AIBsonDeserialiser(AILogicInterface ai)
	{
		this.aiLogic = ai;
	}
	
	public void Deserialise(BsonDocument obj)
	{
		try {
			BsonArray worldArray = obj.getArray("world");
			DeserialiseWorld(worldArray);
		} catch (BSONException e) {
			e.printStackTrace();
		}
			
	}
		
	void DeserialiseWorld(BsonArray worldArray)
	{
		for (BsonValue v : worldArray)
		{
			System.out.println(v.toString());
		}
	}
}
