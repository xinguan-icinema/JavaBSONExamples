import simpleNetworking.*;
import java.util.Arrays;

import org.bson.BSON;

public class BSONJavaTest 
{
	public static void main(String args[]) throws Exception
	{
		UDPListenerThread listener = new UDPListenerThread(9898);
		listener.start();
		
		while (true)
		{
			byte[] data = listener.GetDataFromQueue();
			if (data != null) {
				System.out.println("RECEIVED: " + new String(data));
			}
			
		}

	}
	
}
