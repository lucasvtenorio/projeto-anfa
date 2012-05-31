package br.ufpe.cin.anfa.net;

import br.ufpe.cin.anfa.util.Config;

public class PFHeader {
	
	private int index;
	
	private byte[] headerInBytes;
	private String destinationIP;
	private short destinationPort;
	
	
	private void writeByte(byte toWrite){
		this.headerInBytes[index] = toWrite;
		index++;
	}
	
	private void writeShortToHeader(short toWrite){
		byte[] temp = new byte[2];
		
		temp[0] = (byte) ((toWrite>>8) & 0xFF);
		temp[1] = (byte) ((toWrite) & 0xFF);
		
		for (int i =0; i < 2; i++){ // Escrevendo os 2 bytes no array principal do header
			this.writeByte(temp[i]);
		}
	}
	
	private void debugByte(byte test){
		for (int i  =  7; i >= 0; i--){
			byte temp = (byte) ((test >> i) & 0x00000001);
			if (temp == 1){
				System.out.println("true");
			}else if (temp == 0){
				System.out.println("false");
			}else{
				System.out.println("fail :"+temp);
			}
		}
	}
	
	private void writeIntToHeader(int toWrite){
		byte[] temp = new byte[4];
		
		temp[0] = (byte) ((toWrite>>24) & 0xFF); // Dividindo o int em 4 bytes 
		temp[1] = (byte) ((toWrite>>16) & 0xFF);
		temp[2] = (byte) ((toWrite>>8) & 0xFF);
		temp[3] = (byte) ((toWrite) & 0xFF);
		 
		for (int i =0; i < 4; i++){ // Escrevendo os 4 bytes no array principal do header
			this.writeByte(temp[i]);
		}
	}
	/*
	 * Grava em um byte, até 8 flags booleanas, de forma que cada posição do array
	 * corresponde a um bit no byte que será gravado no header.
	 * 
	 * Se somente a primeira posicao do array for verdadeira, o byte final será:
	 * [10000000]
	 * 
	 */
	private void writeBooleanArrayToHeader(boolean[] flags){
		byte finalByte = 0;
		int size = flags.length;
		
		for (int i = 0; i < size; i++){
			boolean temp = flags[i];
			if (temp) {
				finalByte += (byte) (1 << (7 - i));
			}
		}
		this.debugByte(finalByte);
		this.writeByte(finalByte);
	}
	
	public PFHeader(String ip,int seqNum, int ackNum, short windowSize,boolean fin, boolean syn ,boolean rst){
		
		this.headerInBytes = new byte[Config.PACKET_HEADER_SIZE];
		
		this.writeIntToHeader(seqNum);
		this.writeIntToHeader(ackNum);
		this.writeShortToHeader(windowSize);
		boolean[] flags = {fin, syn, rst};
		this.writeBooleanArrayToHeader(flags);
		
	}
	
	public byte[] getBytes() {
		return this.headerInBytes;
	}
	
	public String getDestinationIP() {
		return destinationIP;
	}
	public short getDestinationPort() {
		return destinationPort;
	}
	
	public static void main(String[] args) {
		PFHeader p = new PFHeader("127.0.0.1", 0, 2, (short)4, true, false, true);
	}
}
