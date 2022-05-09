package day01;

public class ImplicitConversion {
	public static void main(String[] args) {
		byte bNum = 10;
		int iNum = bNum; // byte형 값이 int형 변수로 대입됨
		
		System.out.println(bNum);
		System.out.println(iNum);
		
		int iNum2 = 20;
		float fNum = iNum2; // int형 값이 float형 변수로 대입됨 
		
		System.out.println(iNum);
		System.out.println(fNum);
		
		double dNum;
		dNum = fNum + iNum;
		System.out.println(dNum);
	}
}
