package day20_21;

import java.io.*;
import java.util.ArrayList;

public class SerialEx3 {
	public static void main(String[] args) {
		try {
			String fileName = "UserInfo2.ser";
			FileOutputStream fos = new FileOutputStream(fileName);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			ObjectOutputStream out = new ObjectOutputStream(bos);
			
			UserInfo2 u1 = new UserInfo2("JavaMan", "1234", 30);
			UserInfo2 u2 = new UserInfo2("JavaWoman", "4321", 26);
			
			ArrayList<UserInfo2> list = new ArrayList<>();
			list.add(u1);
			list.add(u2);
			
			// 객체를 직렬화 한다.
			out.writeObject(u1);
			out.writeObject(u2);
			out.writeObject(list);
			out.close();
			System.out.println("직렬화가 잘 끝났습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
