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

/***
 * Interprets updates for the AI that have been serialised in bson
 * 
 * It's good to have this as it's own class, so the actual AI algorithms don't have to
 * worry about communication
 * @author som
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
		// Uncomment if you want to get a printout of the BSON object
		//System.out.println(obj.toString());
		
		try {
			/** TODO: interpret any other information
			* e.g you might want to send over what port Unity is listening on and 
			* automatically connect to it
			**/
			
			// Interpret information about the world sent from Unity
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
			try {
				BsonDocument doc = v.asDocument();
				DeserialiseOneUpdate(doc);
				
			} catch (BsonInvalidOperationException e) {
				e.printStackTrace();
			}
		}
	}
	
	void DeserialiseOneUpdate(BsonDocument doc) 
	{
		BsonString typeStr = doc.getString("type", null);
		BsonDocument data = doc.getDocument("data", null);
		
		if (typeStr != null && data != null) 
		{
			try {
				switch (typeStr.getValue()) 
				{
				case "player pos":
					UpdatePlayerPosition(data);
					break;
				
				case "player action":
					UpdatePlayerAction(data);
					break;
					
				}
			} catch (BsonInvalidOperationException e) {
				e.printStackTrace();
			}
		}		
	}
	
	void UpdatePlayerPosition(BsonDocument data) throws BsonInvalidOperationException
	{
		int id = data.getInt32("id").getValue();
		float x = (float)data.getDouble("x").getValue();
		float y = (float)data.getDouble("y").getValue();
		float z = (float)data.getDouble("z").getValue();
		
		this.aiLogic.UpdatePlayerPosition(id, x, y, z);
	}
	
	void UpdatePlayerAction(BsonDocument data) throws BsonInvalidOperationException
	{
		int id = data.getInt32("id").getValue();
		String action = data.getString("action").getValue();
		
		this.aiLogic.UpdatePlayerAction(id, action);
	}
}
