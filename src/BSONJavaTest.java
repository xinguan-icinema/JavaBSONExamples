import simpleNetworking.*;
import java.util.Arrays;

import org.bson.BSON;
import org.bson.BSONObject;

public class BSONJavaTest 
{
	public static void main(String args[]) throws Exception
	{
		UDPListenerThread listener = new UDPListenerThread(9898);
		listener.start();
		
		BSONListener bsonListener = new BSONListener(listener);
		
		while (true)
		{
			BSONObject obj = bsonListener.GetNextBsonObject();
			if (obj != null) {
				System.out.println(obj.toString());
			}
			
			/// Use this if you want to print the raw UDP data instead
//			byte[] data = listener.GetDataFromQueue();
//			if (data != null) {
//				System.out.println("RECEIVED: " + new String(data));
//			}
			
		}

	}
	
}
