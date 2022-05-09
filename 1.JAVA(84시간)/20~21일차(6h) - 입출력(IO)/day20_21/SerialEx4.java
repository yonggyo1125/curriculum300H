package day20_21;

import java.io.*;
import java.util.ArrayList;

public class SerialEx4 {
	public static void main(String[] args) {
		try {
			String fileName = "UserInfo2.ser";
			FileInputStream fis = new FileInputStream(fileName);
			BufferedInputStream bis = new BufferedInputStream(fis);
			
			ObjectInputStream in = new ObjectInputStream(bis);
			
			// 객체를 읽을 때는 출력한 순서와 일치해야 한다.
			UserInfo2 u1 = (UserInfo2)in.readObject();
			UserInfo2 u2 = (UserInfo2)in.readObject();
			ArrayList<UserInfo2> list = (ArrayList<UserInfo2>)in.readObject();
			
			System.out.println(u1);
			System.out.println(u2);
			System.out.println(list);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
