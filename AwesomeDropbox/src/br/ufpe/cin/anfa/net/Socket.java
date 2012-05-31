package br.ufpe.cin.anfa.net;

import java.net.InetAddress;
import br.ufpe.cin.anfa.buffer.Buffer;
import br.ufpe.cin.anfa.buffer.exception.BufferException;

public class Socket {
	InetAddress destinationIP;
	int destinatonPort;
	Buffer sendBuffer;
	Buffer receiveBuffer;
	long readBease;
	
	public void sendData(byte[] data) throws BufferException {
		sendBuffer.insertData(data);
	}
	
	public byte[] receiveData(long ammount) throws BufferException {
		byte[] data = receiveBuffer.getData(readBease, readBease+ammount);
		readBease += ammount;
		return data;
	}
}
