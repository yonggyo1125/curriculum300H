# 형식화 클래스

## DecimalFormat
- 형식화 클래스 중에서 숫자를 형식화 하는데 사용된다.
- 숫자 데이터를 정수, 부동소숫점, 금액 등의 다양한 형식으로 표현할 수 있으며, 반대로 일정한 형식의 텍스트 데이터를 숫자로 쉽게 변환할 수 있다.
- 형식화 클래스에서는 원하는 형식으로 표현 또는 변환하기 위해서 패턴을 정의한다.

### DateFormat의 패턴에서 사용되는 기호
|기호|의미|패턴|결과|
|---|-----|-----|-----|
|0|10진수(값이 없을 때는 0)|0<br>0.0<br>0000000000.0000|1234568<br>1234567.9<br>1234567.8900|
|#|10진수|#<br>#.#<br>##########.####|1234568<br>1234567.9<br>1234567.89|
|.|소수점|#.#|1234567.9|
|-|음수부호|#.#-<br>-#.#|1234567.9-<br>-1234567.9|
|,|단위 구분자|#,###.##|1,124,567.89<br>123,4567.89|
|;|패턴구분자|#,###.##+;#,###.##-|1,234,567.89+(양수일 때)<br>1,234,567.89-(음수일 때)|
|%|퍼센트|#.#%|123456789%|
|\u2030|퍼밀(퍼센트 x 10)|#.#\u2030|1234567890‰|
|\u00A4|통화|\u00A4 #,###|₩ 1,234,568|
|'|escape문자|\'#'#,##<br>\'#.###|#1,234,568<br>\'1,234,568|

#### day12/format/DecimalFormatEx1.java  - 숫자를 형식화된 문자열로 변환
```
package day12.format;

import java.text.*;

public class DecimalFormatEx1 {
	public static void main(String[] args) {
		double number = 1234567.89;
		String[] pattern = {
			"0",
			"#",
			"0.0",
			"0000000000.0000",
			"##########.####",
			"#.#-",
			"-#.#",
			"#,###.##",
			"#,####.##",
			"#,###.##+;#,###.##-",
			"#.#%",
			"#.#\u2030",
			"\u00A4 #,###",
			"'#'#.###",
			"''#,###"
		};
		
		for (int i = 0; i < pattern.length; i++) {
			DecimalFormat df = new DecimalFormat(pattern[i]);
			System.out.printf("%19s : %s\n", pattern[i], df.format(number)); 
		}
	}
}

실행결과
                  0 : 1234568
                  # : 1234568
                0.0 : 1234567.9
    0000000000.0000 : 0001234567.8900
    ##########.#### : 1234567.89
               #.#- : 1234567.9-
               -#.# : -1234567.9
           #,###.## : 1,234,567.89
          #,####.## : 123,4567.89
#,###.##+;#,###.##- : 1,234,567.89+
               #.#% : 123456789%
               #.#‰ : 1234567890‰
            ¤ #,### : ? 1,234,568
            '#'#.### : #1234567.89
             ''#,### : '1,234,568
```
#### day12/format/DecimalFormatEx2.java  - 패턴이 있는 문자열을 숫자로 변환
```
package day12.format;

import java.text.*;

public class DecimalFormatEx2 {
	public static void main(String[] args) {
		DecimalFormat df = new DecimalFormat("#,###.##");
		DecimalFormat df2 = new DecimalFormat("0.0000");
		
		try {
			Number num = df.parse("1,234,567.89");
			System.out.print("1,234,567.89 -> ");
			
			double d = num.doubleValue();
			System.out.print(d + " -> ");
			
			System.out.println(df2.format(num));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
}

실행결과
1,234,567.89 -> 1234567.89 -> 1234567.8900
```

## SimpleDateFormat
- Date와 Calendar를 사용해서 날짜 데이터를 원하는 형태로 다양하게 출력하는 것은 불편하고 복잡하나, SimpleDateFormat을 사용하면 이러한 문제들이 간단하게 해결된다. 
- DateFormat은 추상클래스로 SimpleDateFormat의 조상이다. DateFormat는 추상클래스이므로 인스턴스를 생성하기 위해서는 getDateInstance()와 같은 static메서드를 이용해야 한다. getDateInstance()에 의해서 반환되는 것은 DateFormat을 상속받아 완전하게 구현한 SimpleDateFormat인스턴스이다.

### SimpleDateFormat의 패턴에 사용되는 기호
|기호|의미|보기|
|----|--------|-----|
|G|연대(BC,AD)|AD|
|y|년도|2022|
|M|월(1\~12 또는 1월\~12월)|10 또는 10월, OCT|
|w|년의 몇 번째 주(1~53)|50|
|W|월의 몇 번째 주(1~5)|4|
|D|년의 몇 번째 일(1~365)|100|
|d|월의 몇 번째 일(1~31)|15|
|F|월의 몇 번째 요일(1~5)|1|
|E|요일|월|
|a|오전/오후(AM, PM)|PM|
|H|시간(0~23)|20|
|k|시간(1~24)|13|
|K|시간(0~11)|10|
|h|시간(1~12)|11|
|m|분(0~59)|35|
|s|초(0~59)|55|
|S|천분의 일초(0~999)|253|
|z|Time zone(General time zone)|GMT+9:00|
|Z|Time zone(RFC 822 time zone)|+0900|
|\`|escape 문자(특수문자를 표현하는데 사용)|없음|

#### day12/format/DateFormatEx1.java - Date 인스턴스를 형식화된 날짜 문자열로 변환
```
package day12.format;

import java.util.*;
import java.text.*;

public class DateFormatEx1 {
	public static void main(String[] args) {
		Date today = new Date();
		
		SimpleDateFormat sdf1, sdf2, sdf3, sdf4;
		SimpleDateFormat sdf5, sdf6, sdf7, sdf8, sdf9;
		
		sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		sdf2 = new SimpleDateFormat("''yy년 MMM dd일 E요일");
		sdf3 = new SimpleDateFormat("yyyy--MM-dd HH:mm:ss.SSS");
		sdf4 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
		
		sdf5 = new SimpleDateFormat("오늘은 올 해의 D번째 날입니다.");
		sdf6 = new SimpleDateFormat("오늘은 이 달의 d번째 날입니다.");
		sdf7 = new SimpleDateFormat("오늘은 올 해의 w번째 주입니다.");
		sdf8 = new SimpleDateFormat("오늘은 이 달의 W번재 주입니다.");
		sdf9 = new SimpleDateFormat("오늘은 이 달의 F번째 E요일 입니다.");
		
		System.out.println(sdf1.format(today));
		System.out.println(sdf2.format(today));
		System.out.println(sdf3.format(today));
		System.out.println(sdf4.format(today));
		System.out.println();
		System.out.println(sdf5.format(today));
		System.out.println(sdf6.format(today));
		System.out.println(sdf7.format(today));
		System.out.println(sdf8.format(today));
		System.out.println(sdf9.format(today));
			
	}
}

실행 결과
2022-05-04
'22년 5월 04일 수요일
2022--05-04 23:17:44.147
2022-05-04 11:17:44 오후

오늘은 올 해의 124번째 날입니다.
오늘은 이 달의 4번째 날입니다.
오늘은 올 해의 19번째 주입니다.
오늘은 이 달의 1번재 주입니다.
오늘은 이 달의 1번째 수요일 입니다.
```
#### day12/format/DateFormatEx2.java - Calendar클래스를 이용한 Date인스턴스를 형식화된 날짜 문자열로 변환
```
package day12.format;

import java.util.*;
import java.text.*;

public class DateFormatEx2 {
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		cal.set(2022, 4, 4); // 2022년 5월 4일 - Month는 0~11의 범위를 갖는다.
		
		Date day = cal.getTime();
		
		SimpleDateFormat sdf1, sdf2, sdf3, sdf4;
		sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		sdf2 = new SimpleDateFormat("yy-MM-dd E요일");
		sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		sdf4 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
		
		System.out.println(sdf1.format(day));
		System.out.println(sdf2.format(day));
		System.out.println(sdf3.format(day));
		System.out.println(sdf4.format(day));
	}
}


실행결과
2022-05-04
22-05-04 수요일
2022-05-04 23:23:24.437
2022-05-04 11:23:24 오후
```

#### day12/format/DateFormatEx3.java - 형식화된 문자열을 Date 인스턴스로 변환
```
package day12.format;

import java.util.*;
import java.text.*;

public class DateFormatEx3 {
	public static void main(String[] args) {
		DateFormat df = new SimpleDateFormat("yyyy년 MM월 dd일");
		DateFormat df2 = new SimpleDateFormat("yyyy/MM/dd");
		
		try {
			Date d = df.parse("2022년 05월 04일");
			System.out.println(df2.format(d));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}

실행결과
2022/05/04
```

## ChoiceFormat


## MessageFormat



* * * 
# 날짜와 시간

* * *
# java.time 패키지



