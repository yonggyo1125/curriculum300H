package day11.utils;

import java.util.*;

public class StringTokenizerEx2 {
	public static void main(String[] args) {
		String expression = "x=100+(200+100)/2";
		StringTokenizer st = new StringTokenizer(expression, "+-*/=()", true);
		
		while(st.hasMoreTokens()) {
			System.out.println(st.nextToken());
		}
	}
}
