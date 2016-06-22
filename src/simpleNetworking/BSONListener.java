package simpleNetworking;


import org.bson.*;

public class BSONListener 
{
	UDPListenerThread udpListener;
	
	
	public BSONListener(UDPListenerThread listener) 
	{
		this.udpListener = listener;
	}
	
	public BSONObject GetNextBsonObject()
	{
		byte[] data = this.udpListener.GetDataFromQueue();
		
		BSONObject obj = null;
		
		if (data != null) 
		{
			try {
				BSONDecoder decoder = new BasicBSONDecoder();
				obj = decoder.readObject(data);
			} catch (BsonSerializationException e) {
				System.err.println(e.toString());
			}
		}
		return obj;
	}

}
