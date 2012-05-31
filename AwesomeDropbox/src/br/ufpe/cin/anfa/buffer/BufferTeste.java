package br.ufpe.cin.anfa.buffer;

import java.util.*;

public class BufferTeste {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Buffer buffer = new Buffer(8);
		Scanner in = new Scanner(System.in);
		while(true){
			int a = in.nextInt();
			
			if(a == 0){
				buffer.test();
			} else if(a > 0){
				byte[] data = new byte[a];
				for(int i = 0; i < a; i++){
					data[i] = in.nextByte();
				}
			}
		}
	}

}
