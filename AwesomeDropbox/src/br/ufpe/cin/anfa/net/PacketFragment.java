package br.ufpe.cin.anfa.net;

import java.net.DatagramPacket;
import java.net.InetAddress;	
import java.net.UnknownHostException;

import br.ufpe.cin.anfa.util.Config;

/**
 * @since 0.1
 * @author Anfa Team
 *
 */
public class PacketFragment {
	byte[] data;
	DatagramPacket datagram;
	
	public PacketFragment(byte[] data, PFHeader header) {
		this.data = data;
		
		try {
			datagram = new DatagramPacket(data, data.length);
			datagram.setPort(header.getDestinationPort());
			datagram.setAddress(InetAddress.getByName(header.getDestinationIP()));
		} catch (UnknownHostException e) {
			System.out.println("QUE DIABOS DE IP ƒ ESSE?");
			e.printStackTrace();
		}
	}
	
	public PacketFragment() {
		datagram = new DatagramPacket(data, (int) ((Config.PACKET_DATA_MAXSIZE + Config.PACKET_HEADER_SIZE)*1.1+0.5));
	}
}
