package br.ufpe.cin.anfa.buffer;

import br.ufpe.cin.anfa.buffer.exception.BufferException;

public class Buffer {
	
	
	private byte[] buffer;
	
	private long baseInitial;
	private long baseFinal;
	private int initialPosition;
	private int finalPosition;
	private int freeSpace;
	
	public Buffer(int size){
		this.buffer = new byte[size];
		
		this.initialPosition = 0;
		this.finalPosition = 0;
		
		this.freeSpace = size;
		
		this.baseInitial = 0;
		this.baseFinal = 0;
	}
	
	public void insertData(byte[] b) throws BufferException{
		if(b.length > freeSpace){
			throw new BufferException("Out of space!");
		}
		
		for(int i = 0; i < b.length; i++){
			this.buffer[finalPosition] = b[i];
			
			finalPosition = (finalPosition + 1)%buffer.length;
			baseFinal++;
			freeSpace--;
		}
	}
	
	public byte[] getData(long begin, long end) throws BufferException{
		if(begin < this.baseInitial || end > this.baseFinal){
			throw new BufferException("Index out of range");
		}
		
		int normalIni = (int) (begin - this.baseInitial);
		int normalEnd = (int) (end - this.baseInitial);
		
		int base = (initialPosition + normalIni)%(this.buffer.length);
		
		byte[] data = new byte[normalEnd - normalIni];
		
		for(int i = 0; i < data.length; i++){
			data[i] = this.buffer[base];
			base = (base + 1) % this.buffer.length;
		}
		
		return data;
	}
	
	public void freeData(int bytes){
		int occupied = (int) (baseFinal - baseInitial);
		if(bytes > occupied){
			this.freeSpace = this.buffer.length;
			this.baseInitial = this.baseFinal;
			this.initialPosition = this.finalPosition;
		} else{
			this.freeSpace += bytes;
			this.baseInitial += bytes;
			this.initialPosition = (this.initialPosition + bytes)%this.buffer.length;
		}
	}
	
	protected void test(){
		System.out.println("Free Space: " + this.freeSpace);
		System.out.println("Initial Position: " + this.initialPosition);
		System.out.println("Final Position: " + this.finalPosition);
		System.out.println("Base Initial: " + this.baseInitial);
		System.out.println("Base Final: " + this.baseFinal);
		for(int i = 0; i < buffer.length; i++){
			System.out.print(buffer[i] + " ");
		}
		System.out.println();
	}
}
