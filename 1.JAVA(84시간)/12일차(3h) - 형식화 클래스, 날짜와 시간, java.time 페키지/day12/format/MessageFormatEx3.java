package day12.format;

import java.text.*;

public class MessageFormatEx3 {
	public static void main(String[] args) throws ParseException {
		String[] data = {
				"INSERT INTO CUST_INFO VALUES ('최유진', '02-123-1234', 27, '07-09');",
				"INSERT INTO CUST_INFO VALUES ('고애신', '032-1234-1234', 33, '10-07');"
		};
		
		String pattern = "INSERT INTO CUST_INFO VALUES ({0}, {1}, {2}, {3});";
		MessageFormat mf = new MessageFormat(pattern);
		
		for(int i = 0; i < data.length; i++) {
			Object[] objs = mf.parse(data[i]);
			for(int j = 0; j < objs.length; j++) {
				System.out.print(objs[j] + ",");
			}
			System.out.println();
		}
	}
}
