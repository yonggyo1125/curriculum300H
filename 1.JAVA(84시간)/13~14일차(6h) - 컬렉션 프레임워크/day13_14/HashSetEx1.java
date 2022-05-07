package day13_14;

import java.util.*;

public class HashSetEx1 {
	public static void main(String[] args) {
		Object[] objArr = {"1", Integer.valueOf(1), "2", "2", "3", "3", "4", "4", "4" };
		Set set = new HashSet();
		
		for(int i = 0; i < objArr.length; i++) {
			set.add(objArr[i]); // HashSet에 objArr의 요소를 저장한다.
		}
		
		System.out.println(set);
	}
}
