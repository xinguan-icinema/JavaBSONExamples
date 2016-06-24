package simpleNetworking;

import java.io.IOException;
import java.net.*;
public class UDPSender 
{
	DatagramSocket socket;
	String address;
	int remotePort;
	
	public UDPSender(String address, int remotePort) throws SocketException
	{
		this.socket = new DatagramSocket();
		this.address = address;
		this.remotePort = remotePort;
	}
	
	public boolean Send(byte[] data)
	{
		boolean ok = false;
		
		try {
			InetAddress ipAddress = InetAddress.getByName(this.address);
			DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, this.remotePort);
			this.socket.send(packet);
			ok = true;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return ok;
	}
	
}
