package br.ufpe.cin.anfa.net;

import br.ufpe.cin.anfa.util.Config;

public class Packet {
	String destination;
	PacketFragment[] fragments;
	
	public Packet(byte[] data, PFHeader header) {
		int lenght = data.length;
		int packetSize = Config.PACKET_DATA_MAXSIZE;
		int quantity = lenght / packetSize;
		
		if (lenght % packetSize != 0) {
			quantity++;
		}
		
		fragments = new PacketFragment[quantity];
		
		for (int i = 0; i < quantity; i++) { // Tom‡s quer um Kibe.
			byte[] fragmentData = new byte[packetSize];
			int pos = 0;
			
			if (i != quantity - 1) {
				for (int j = i * packetSize; j < (i * packetSize) + packetSize; j++) {
					fragmentData[pos] = data[j];
					pos++;
				}
			} else {
				fragmentData = new byte[lenght % packetSize];
				
				for (int j = i * packetSize; j < data.length; j++) {
					fragmentData[pos] = data[j];
					pos++;
				}
			}
			
			fragments[i] = new PacketFragment(fragmentData, header);
		}
	}
	
	public static void main(String[] args) {
		byte[] lol = new byte[201];
		for (int i = 0; i < 201; i++) {
			lol[i] = (byte)i;
		}
		
		Packet packet = new Packet(lol, null);
		
		for (int i = 0; i < packet.fragments.length; i++) {
			System.out.println("PACKOTE " + i);
			for (int j = 0; j < packet.fragments[i].data.length; j++) {
				System.out.print(packet.fragments[i].data[j] + " ");
			}
			System.out.println();
		}
	}
}
