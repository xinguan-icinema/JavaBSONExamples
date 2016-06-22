package simpleNetworking;

import java.io.IOException;
import java.net.*;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class UDPListenerThread extends Thread
{
	int listenPort;
	DatagramSocket socket;
	
	Queue<byte[]> receivedDataQueue = new ConcurrentLinkedQueue<>();
	
	public UDPListenerThread(int listenPort) throws SocketException
	{
		this.listenPort = listenPort;
		this.socket = new DatagramSocket(9898);
	}

	@Override
	public void run() 
	{
		while (true)
		{
			try {
				byte[] receiveData = new byte[2048];
		        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		        this.socket.receive(receivePacket);
		        
		        this.receivedDataQueue.add(receivePacket.getData());
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}
	}
	
	public byte[] GetDataFromQueue()
	{
		try {
			byte[] data = this.receivedDataQueue.remove();
			return data;
		} catch (NoSuchElementException e) {
			return null;
		}
	}
}
