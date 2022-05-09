package day20_21;

import java.io.*;

public class BufferedReaderEx1 {
	public static void main(String[] args) {
		try {
			FileReader fr = new FileReader("D:\\javaEx\\lecture\\src\\day20_21\\BufferedReaderEx1.java");
			BufferedReader br = new BufferedReader(fr);
			
			String line = "";
			int i = 1;
			while((line = br.readLine()) != null) {
				// ";"를 포함한 라인을 출력한다.
				if (line.indexOf(";") != -1) {
					System.out.println(i + ":" + line);
				}
				i++;
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
