package aiexample;

import simpleNetworking.*;
import org.bson.*;

import aiexample.logic.example.AILogicExample;

public class AIExample 
{
	static final int LISTEN_PORT = 9898;
	static final int REMOTE_PORT = 9899;
	
	public static void main(String args[]) throws Exception
	{
		UDPListenerThread listener = new UDPListenerThread(LISTEN_PORT);		
		UDPSender sender = new UDPSender("localhost", REMOTE_PORT);
		
		// Create the AI itself
		AILogicExample ai = new AILogicExample();
		
		// The AIBsonDeserialiser will take bson updates and interpret them for the AI
		AIBsonDeserialiser aiBson = new AIBsonDeserialiser(ai);
		
		BsonOutputInterface aiBsonOutput = new BsonOutputInterface(sender);
		ai.SetOutputInterface(aiBsonOutput);

		BSONListener bsonListener = new BSONListener(listener);
		
		listener.start();
		
		while (true)
		{
			BsonDocument obj = bsonListener.GetNextBsonObject();
			if (obj != null) 
			{
				// Pass information to the AI
				aiBson.Deserialise(obj);
				
				// For this example, the ai just 'ticks' after it gets an update
				// Your AI could do this or tick on it's own thread, up to you
				ai.ReasoningTick();
			}
		}
		
	}
}
