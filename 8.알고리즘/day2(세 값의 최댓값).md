## 문항
- 세 값을 입력 받아 그 3개 값 중 최대값을 구하시오.
- 세 값을 입력 받기 위해서는 java.util.Scanner 클래스를 활용할 것

<!--
```

import java.util.Scanner;

class Max3 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("세 정수의 최대값을 구합니다.");
		System.out.print("a의 값 : ");
		int a = Integer.parseInt(sc.nextLine());
		System.out.print("b의 값 : ");
		int b = Integer.parseInt(sc.nextLine());
		System.out.print("c의 값 : ");
		int c = Integer.parseInt(sc.nextLine());
		
		int max = a;
		if (b > max) max = b;
		if (c > max) max = c;
		
		System.out.println("최대값은 " + max + "입니다.");
	}
}

```
-->
