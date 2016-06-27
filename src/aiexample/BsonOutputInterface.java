package aiexample;

import aiexample.logic.AIOutputInterface;
import simpleNetworking.UDPSender;

import org.bson.*;

public class BsonOutputInterface implements AIOutputInterface 
{
	BasicBSONEncoder encoder = new BasicBSONEncoder();
	UDPSender udpSender;
	
	public BsonOutputInterface(UDPSender udpSender) 
	{
		this.udpSender = udpSender;
	}
	
	@Override
	public void MoveTo(int aiId, float x, float y) 
	{
		//System.out.println( String.format("AI %d wants to move to (%f, %f)", aiId, x, y) );
		
		BasicBSONObject obj = new BasicBSONObject();
		obj.append("type", "moveTo");

		this.Send(obj);
	}
	
	void Send(BSONObject obj)
	{
		this.udpSender.Send( this.encoder.encode(obj) );
	}

}
