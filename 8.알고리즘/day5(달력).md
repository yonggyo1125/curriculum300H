## 문항
- 년도와 월을 입력 받아 달력을 출력하시오.
- 년도와 월의 입력은 java.util.Scanner 클래스를 사용할 것
<!--
```
import java.time.*;
import java.time.temporal.*;
import java.text.*;
import java.util.*;

public class MyCalendar {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("몇년 ?");
		int year = Integer.parseInt(sc.nextLine());
		System.out.print("몇월 ?");
		int month = Integer.parseInt(sc.nextLine());
		
		String[] yoils = {"월","화","수", "목", "금", "토", "일"}; 
		
		LocalDate startDate = LocalDate.of(year, month, 1);
		LocalDate endDate = startDate.plusMonths(1).minusDays(1);
		
		int sDay = startDate.getDayOfMonth();
		int eDay = endDate.getDayOfMonth();
		int startPos = startDate.get(ChronoField.DAY_OF_WEEK) - 1;
		
		DecimalFormat df = new DecimalFormat("00");
		
		System.out.printf("%d년 %d월%n", year, month);
		for (String yoil : yoils) {
			System.out.print(yoil + "  ");
		}
		System.out.println();
		for (int i = 1; i <= eDay + startPos ; i++) {
			if (i > startPos) {
				System.out.print(df.format(i - startPos));
			} else {
				System.out.print("    ");
			}
			System.out.print("  ");
			if (i % 7 == 0) 
				System.out.println();
		}
		
		
	}
}
```
- 실행 결과
```
몇년 ?2022
몇월 ?6
2022년 6월
월  화  수  목  금  토  일  
            01  02  03  04  05  
06  07  08  09  10  11  12  
13  14  15  16  17  18  19  
20  21  22  23  24  25  26  
27  28  29  30  
```
-->
