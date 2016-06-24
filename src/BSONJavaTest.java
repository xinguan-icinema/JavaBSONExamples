import simpleNetworking.*;

import org.bson.BSONEncoder;
import org.bson.BSONObject;
import org.bson.BasicBSONEncoder;
import org.bson.BasicBSONObject;

public class BSONJavaTest 
{
	public static void main(String args[]) throws Exception
	{
		UDPListenerThread listener = new UDPListenerThread(9898);
		listener.start();
		
		UDPSender sender = new UDPSender("localhost", 9899);
		
		BSONListener bsonListener = new BSONListener(listener);
		
		while (true)
		{
			BSONObject obj = bsonListener.GetNextBsonObject();
			if (obj != null) {
				System.out.println(obj.toString());
				
				// Send a reply back
				BasicBSONObject sendObj = new BasicBSONObject();
				sendObj.append("WhatIReceived", obj);
				
				BSONEncoder encoder = new BasicBSONEncoder();
				
				sender.Send(encoder.encode(sendObj));				
			}

			/// Use this if you want to print the raw UDP data instead
//			byte[] data = listener.GetDataFromQueue();
//			if (data != null) {
//				System.out.println("RECEIVED: " + new String(data));
//			}
			
		}

	}
	
}
