package day20_21;

import java.io.*;

public class RandomAccessFileEx2 {
	public static void main(String[] args) {
		//				   번호, 국어, 영어, 수학 
		int[] score = {   1,  100,    90,     90, 
							    2,   70,    90,    100,
							    3,  100,   100,    100,
							    4,   70,    60,     80,
							    5,   70,    90,    100 };
		
		
		try {
			RandomAccessFile raf = new RandomAccessFile("score2.dat", "rw");
			for(int i = 0; i < score.length; i++) {
				raf.writeInt(score[i]);
			}
			
			/**
			 * 파일포인터가 파일의 끝에 도달해 있으므로 
			 * 파일 포인터를 다시 처음으로 이동 시켜야 저장된 값을 읽어올 수 있다.
			 */
			raf.seek(0);  
			while(true) {
				System.out.println(raf.readInt());
			}
			
		} catch (EOFException eof) {
			// readInt()를 호출했을 때 더 이상 읽을 내용이 없다면 EOFException이 발생한다.
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
} 
