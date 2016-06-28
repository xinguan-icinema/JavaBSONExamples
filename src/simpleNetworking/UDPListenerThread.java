package simpleNetworking;

import java.io.IOException;
import java.net.*;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Listens for data on a given UDP port and adds the raw messages to a queue
 * Make sure you have something constantly consuming from the queue, otherwise 
 * it will start to fill up!
 * @author som
 *
 */
public class UDPListenerThread extends Thread
{
	final int MAX_DATA_SIZE = 4096; // Not sure what this should actually be, just a magic number right now

	int listenPort;
	DatagramSocket socket;
	
	Queue<byte[]> receivedDataQueue = new ConcurrentLinkedQueue<>();
	
	public UDPListenerThread(int listenPort) throws SocketException
	{
		this.listenPort = listenPort;
		this.socket = new DatagramSocket(listenPort);
	}
	
	@Override
	public void run() 
	{
		while (true)
		{
			try {
				byte[] receiveData = new byte[MAX_DATA_SIZE];
		        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		        this.socket.receive(receivePacket);
		        
		        this.receivedDataQueue.add(receivePacket.getData());

			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}
	}
	
	/** Returns null if there is no more data in the queue **/
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
