package day04.array;

public class CharArray {
	public static void main(String[] args) {
		char[] alphabets = new char[26];
		char ch = 'A';
		
		for(int i = 0; i < alphabets.length; i++, ch++) {
			alphabets[i] = ch; // 아스키 값으로 각 요소에 저장
		}
		
		for(int i = 0; i < alphabets.length; i++) {
			System.out.println(alphabets[i] + "," + (int)alphabets[i]);
		}
	}
}
