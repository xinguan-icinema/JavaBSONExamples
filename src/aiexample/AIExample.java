package aiexample;

import simpleNetworking.*;
import org.bson.*;

public class AIExample 
{
	static final int LISTEN_PORT = 9898;
	static final int REMOTE_PORT = 9899;
	
	public static void main(String args[]) throws Exception
	{
		// Create the AI itself
		AILogicExample ai = new AILogicExample();
		
		// The AIBsonDeserialiser will take bson updates and interpret them for the AI
		AIBsonDeserialiser aiBson = new AIBsonDeserialiser(ai);
				
		UDPListenerThread listener = new UDPListenerThread(LISTEN_PORT);
		listener.start();
		
		UDPSender sender = new UDPSender("localhost", REMOTE_PORT);
		
		BSONListener bsonListener = new BSONListener(listener);
		
		while (true)
		{
			BsonDocument obj = bsonListener.GetNextBsonObject();
			if (obj != null) 
			{
				// Pass information to the AI
				aiBson.Deserialise(obj);
			}
		}
	}
}
