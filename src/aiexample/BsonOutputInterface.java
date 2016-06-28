package aiexample;

import aiexample.logic.AIOutputInterface;
import simpleNetworking.UDPSender;

import org.bson.*;

/***
 * An implementation of AIOutputInterface that sends AI commands via BSON and UDP
 * Note that in this example, each update is sent in it's own UDP datagram
 * (so you will probably have multiple UDP datagrams sent every tick)
 * @author som
 *
 */
public class BsonOutputInterface implements AIOutputInterface 
{
	BasicBSONEncoder encoder = new BasicBSONEncoder();
	UDPSender udpSender;
	
	public BsonOutputInterface(UDPSender udpSender) 
	{
		this.udpSender = udpSender;
	}
	
	/**
	 * Notice that I use BasicBSONObject here instead of BsonDocument. It's just a bit
	 * easier to manually construct a BasicBSONObject since you don't have to convert values
	 * to a BSONValue first when you .append()
	 */
	@Override
	public void MoveTo(int aiId, float x, float y) 
	{
		BasicBSONObject obj = new BasicBSONObject();
		obj.append("type", "moveTo");
		obj.append("id", aiId);
		obj.append("x", x);
		obj.append("y", y);

		this.Send(obj);
	}
	
	void Send(BSONObject obj)
	{
		this.udpSender.Send( this.encoder.encode(obj) );
	}

}
