package br.ufpe.cin.anfa.buffer.exception;

public class BufferException extends Exception{
	public BufferException(){
		super("Buffer exception");
	}
	
	public BufferException(String type){
		super("Buffer exception: " + type);
	}
}
