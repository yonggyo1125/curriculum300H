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
특정 범위에 속하는 값을 문자열로 변환해준다.

#### day12/format/ChoiceFormatEx1.java
```
package day12.format;

import java.text.*;

public class ChoiceFormatEx1 {
	public static void main(String[] args) {
		double[] limits = {60, 70, 80, 90}; // 낮은 값부터 큰 값의 순서로 적어야한다.
		// limits, grades간의 순서와 개수를 맞추어야 한다.
		String[] grades = {"D","C", "B", "A"};
		
		int[] scores = { 100, 95, 88, 70, 52, 60, 60 };
		
		ChoiceFormat form = new ChoiceFormat(limits, grades);
		
		for(int i = 0; i < scores.length; i++) {
			System.out.println(scores[i] + ":" + form.format(scores[i]));
		}
	}
}

실행결과
100:A
95:A
88:B
70:C
52:D
60:D
60:D
```

#### day12/format/ChoiceFormatEx2.java
```
package day12.format;

import java.text.*;

public class ChoiceFormatEx2 {
	public static void main(String[] args) {
		String pattern = "60#D|70#C|80<B|90#A";
		int[] scores = { 91, 90, 80, 88, 70, 52, 60 };
		
		ChoiceFormat form = new ChoiceFormat(pattern);
		
		for(int i=0; i<scores.length; i++) {
			System.out.println(scores[i]+":"+form.format(scores[i]));
		}
	}
}

실행결과
91:A
90:A
80:C
88:B
70:C
52:D
60:D
```

## MessageFormat
- 데이터를 정해진 양식에 맞게 출력할 수 있도록 도와준다.(format)
- 지정된 양식에서 필요한 데이터만 손쉽게 추출할 수 있다.(parse)

#### day12/format/MessageFormatEx1.java
```
package day12.format;

import java.text.*;

public class MessageFormatEx1 {
	public static void main(String[] args) {
		String msg = "Name: {0} \nTel: {1} \nAge: {2} \nBirthday: {3}";
		
		Object[] arguments = {
			"고애신", "02-123-1234", "27", "07-09"
		};
		
		String result = MessageFormat.format(msg,  arguments);
		System.out.println(result);
	}
}

실행결과
Name: 고애신 
Tel: 02-123-1234 
Age: 27 
Birthday: 07-09
```

#### day12/format/MessageFormatEx2.java
홑따옴표(')는 MessageFormat의 양식에 escape문자로 사용되기 때문에 문자열 msg내에서 홑따옴표를 사용하려면 홑따옴표를 연속으로 두 번 사용해야 한다.

```
package day12.format;

import java.text.*;

public class MessageFormatEx2 {
	public static void main(String[] args) {
		String tableName = "CUST_INFO";
		String msg = "INSERT INTO " + tableName + " VALUES (''{0}'', ''{1}'', {2}, ''{3}'');";
		
		Object[][] arguments = {
				{"최유진", "02-123-1234", "27", "07-09"},
				{"고애신", "032-333-1234", "33", "10-07"}
		};
		
		for(int i = 0; i < arguments.length; i++) {
			String result = MessageFormat.format(msg,  arguments[i]);
			System.out.println(result);
		}
	}
}

실행결과
INSERT INTO CUST_INFO VALUES ('최유진', '02-123-1234', 27, '07-09');
INSERT INTO CUST_INFO VALUES ('고애신', '032-333-1234', 33, '10-07');
```

#### day12/format/MessageFormatEx3.java
parse(String source)를 이용해서 출력된 데이터로 부터 필요한 데이터를 추출하는 방법

```
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

실행결과
'최유진','02-123-1234',27,'07-09',
'고애신','032-1234-1234',33,'10-07',
```

#### day12/format/MessageFormatEx4.java

```
package day12.format;

import java.util.*;
import java.text.*;
import java.io.*;

public class MessageFormatEx4 {
	public static void main(String[] args) throws ParseException, IOException {
		String tableName = "CUST_INFO";
		String fileName = "data.txt";
		String msg = "INSERT INTO " + tableName + " VALUES ({0},{1},{2},{3});";
		
		Scanner s = new Scanner(new File(fileName));
		String pattern = "{0},{1},{2},{3}";
		MessageFormat mf = new MessageFormat(pattern);
		
		while(s.hasNextLine()) {
			String line = s.nextLine();
			Object[] objs = mf.parse(line);
			System.out.println(MessageFormat.format(msg, objs));
		}
	}
}

data.txt 
'최유진','02-123-1234',27,'07-09'
'고애신','032-333-1234',33,'10-07'

실행결과
INSERT INTO CUST_INFO VALUES ('최유진','02-123-1234',27,'07-09');
INSERT INTO CUST_INFO VALUES ('고애신','032-333-1234',33,'10-07');

```

* * * 
# 날짜와 시간

## Calendar와 Date
- Date는 날짜와 시간을 다룰 목적으로 JDK1.0부터 제공되어온 클래스이다. 
- Date클래스의 기능이 부족했기 때문에 Calendar라는 새로운 클래스를 그 다음 버전인 JDK1.1부터 제공하기 시작하였다.
- Calendar는 Date보다는 낳았지만 몇가지 단점들이 발견 되어 JDK1.8부터 **java.time 패키지**로 기존의 단점을 개선한 새로운 클래스들이 추가 되었다.


### Calendar와 GregorianCalendar
- Calendar는 추상 클래스이기 때문에 직접 객체를 생성할 수 없고, 메서드를 통해서 완전히 구현된 클래스의 인스턴스를 얻어야 한다.
```
Calendar cal = new Calendar(); // 에러, 추상클래스는 인스턴스를 생성할 수 없다.

Calendar cal = Calendar.getInstance(); // OK, getInstance() 메서드는 Calendar 클래스를 구현한 클래스의 인스턴스를 반환한다.
```

- Calendar를 상속받아 완전히 구현한 클래스로는 GregorianCalendar와 BuddhistCalendar가 있다.
- getInstance()는 시스템의 국가와 지역설정을 확인해서 태국인 경우에는 BuddhistCalendar의 인스턴스를 반환하고, 그 외에는 GregorianCalendar와 인스턴스를 반환한다.

- GregorianCalendar는 Calendar를 상속받아 오늘날 전세계 공통으로 사용하고 있는 그레고리력에 맞게 구현한 것으로 태국을 제외한 나머지 국가에서는 GregorianCalendar를 사용하면 된다. 

- 인스턴스를 직접 생성해서 사용하지 않고 메소드를 통해서 인스턴스를 반환받게 하는 이유는 최소한의 변경으로 프로그램이 동작할 수 있도록 하기 위한 것이다.
```
class MyApplication {
	public static void main(String[] args) {
		Calendar cal = new GregorianCalendar(); // 경우에 따라 이 부분을 변경해야 한다.
		...
	}
}
```
- 상기 코드와 같이 특정 인스턴스를 생성하도록 프로그램이 작성되어 있다면, 다른 종류의 역법(calendar)을 사용하는 국가에서 실행되거나, 새로운 역법이 추가된다면, 즉 다른 종류의 인스턴스를 필요로 하는 경우에 MyApplication을 변경해야 한다.

```
class MyApplication {
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		...
	}
}
```
- getIntance()를 사용하면 구현되는 내용은 달라지겠지만 MyApplication이 변경되지 않아도 된다. 
- getInstance()메서드가 static인 이유는 메서드 내의 코드에서  인스턴스 변수를 사용하거나 인스턴스 메서드를 호출하지 않기 때문   
- Calenda는 추상 클래스 이므로 객체를 생성할 수 없다, 따라서 객체를 생성하기 위한 static 메서드은 getInstance()가 필요하다.


### Date와 Calendar간의 변환
- Calendar가 새로 추가되면서 Date는 대부분의 메서드가 'dreprecated' 되었으므로 잘 사용되지 않는다.
- 그러나 여전히 Date를 필요로 하는 메서드들이 있기 때문에 Calendar를 Date로 또는 그 반대로 변환할 일이 있다.
	- Calendar를 Date로 변환 
	```
	Calendar cal = Calendar.getInstance();
	...
	Date d = new Date(cal.getTimeInMillis()); // Date(long date)
	```
	
	- Date를 Calendar로 변환
	```
	Date d = new Date();
	...
	Calendar cal = Calendar.getInstance();
	cal.setTime(d);
	```
#### day12/date_calendar/CalendarEx1.java
```
package day12.date_calendar;

import java.util.*;

public class CalendarEx1 {
	public static void main(String[] args) {
		// 기본적으로 현재날짜와 시간으로 설정된다.
		Calendar today = Calendar.getInstance();
		
		System.out.println("이 해의 년도 : " + today.get(Calendar.YEAR));
		System.out.println("월(0~11, 0:1월) : " + today.get(Calendar.MONTH));
		System.out.println("이 해의 몇 째 주 : " + today.get(Calendar.WEEK_OF_YEAR));
		System.out.println("이 달의 몇 째 주 : " + today.get(Calendar.WEEK_OF_MONTH));
		
		// DATE와 DAY_OF_MONTH와는 같다.
		System.out.println("이 달의 몇 일 : "+ today.get(Calendar.DATE));
		System.out.println("이 달의 몇 일 : "+ today.get(Calendar.DAY_OF_MONTH));
		System.out.println("이 해의 몇 일 : "+ today.get(Calendar.DAY_OF_YEAR));
		System.out.println("요일(1~7), 1:일요일  : "+ today.get(Calendar.DAY_OF_WEEK)); // 1. 일요일, 2. 월요일 ... 7.토요일 
		System.out.println("이 달의 몇 째 요일 : "+ today.get(Calendar.DAY_OF_WEEK_IN_MONTH));
		System.out.println("오전_오후(0:오전, 1:오후) : "+ today.get(Calendar.AM_PM));
		System.out.println("시간(0~11) : "+ today.get(Calendar.HOUR));
		System.out.println("시간(0~23) : "+ today.get(Calendar.HOUR_OF_DAY));
		System.out.println("분(0~59) : "+ today.get(Calendar.MINUTE));
		System.out.println("초(0~59) : "+ today.get(Calendar.SECOND));
		System.out.println("1000분의 1초(0~999)  : "+ today.get(Calendar.MILLISECOND));
		
		System.out.println("TimeZone(~12~+12): " + (today.get(Calendar.ZONE_OFFSET) / (60 * 60 * 1000)));
		System.out.println("이 달의 마지막 날:" + today.getActualMaximum(Calendar.DATE));
	}
}

실행결과
이 해의 년도 : 2022
월(0~11, 0:1월) : 4
이 해의 몇 째 주 : 19
이 달의 몇 째 주 : 1
이 달의 몇 일 : 5
이 달의 몇 일 : 5
이 해의 몇 일 : 125
요일(1~7), 1:일요일  : 5
이 달의 몇 째 요일 : 1
오전_오후(0:오전, 1:오후) : 0
시간(0~11) : 9
시간(0~23) : 9
분(0~59) : 56
초(0~59) : 58
1000분의 1초(0~999)  : 207
TimeZone(~12~+12): 9
이 달의 마지막 날:31
```
- getInstance(),를 통해서 얻은 인스턴스는 기본적으로 현재 시스템의 날짜와 시간에 대한 정보를 담고 있다.
- 원하는 날짜나 시간으로 설정하려면 **set 메서드 ** 사용
- 원하는 필드의 값을 얻어오려면 **int get(int field)**를 사용
- get(Calendar.MONTH)로 얻어오는 값의 범위는 0~11이다. 즉 0은 1월, 11은 12월을 의미한다.

#### day12/date_calendar/CalendarEx2.java
```
package day12.date_calendar;

import java.util.Calendar;

public class CalendarEx2 {
	public static void main(String[] args) {
		// 요일은 1부터 시작하기 때문에, DAY_OF_WEEK[0]은 비워두었다.
		final String[] DAY_OF_WEEK = {"", "일", "월", "화", "수", "목", "금", "토" };
		
		Calendar date1 = Calendar.getInstance();
		Calendar date2 = Calendar.getInstance();
		
		// month의 경우 0부터 시작하기 때문에 8월인 경우 7로 지정해야 한다.
		// date1.set(2021, Calendar.AUGUST, 15); 와 같이 할 수 도 있다.
		date1.set(2021, 7, 15); // 2021년 8월 15일
		System.out.println("date1은 " + toString(date1) +  DAY_OF_WEEK[date1.get(Calendar.DAY_OF_WEEK)] + "요일 이고, ");
		System.out.println("오늘(date2)은 " + toString(date2) +  DAY_OF_WEEK[date2.get(Calendar.DAY_OF_WEEK)] + "요일 이고, ");
		
		// 두 날짜 사이의 차이를 얻으려면, getTimeInMillis() 천분의 일초 단위로 변환해야 한다.
		long difference = (date2.getTimeInMillis() - date1.getTimeInMillis()) / 1000;
		System.out.println("그 날(date1)부터 지금(date2)까지 " + difference + "초가 지났습니다.");
		System.out.println("월(day)로 계산하면 " + difference / (24*60 * 60) + "일 입니다."); // 1일 - 24 * 60 * 60
	}
	
	public static String toString(Calendar date) {
		return date.get(Calendar.YEAR) + "년 " + (date.get(Calendar.MONTH) + 1) + "월 " + date.get(Calendar.DATE) + "일";
	}
}

실행결과
date1은 2021년 8월 15일일요일 이고, 
오늘(date2)은 2022년 5월 5일목요일 이고, 
그 날(date1)부터 지금(date2)까지 22723200초가 지났습니다.
월(day)로 계산하면 263일 입니다.

```
- 날짜와 시간을 원하는 값으로 변경하려면 set메서드를 사용
```
void set (int field, int value)
void set(int year, int month, int date)
void set(int year, int month, int date, int hourOfDay, in minute)
void set(int year, int month, int date, int hourOfDay, in minute, int second)
```

- 시간상 전후를 알고 싶을 때는 두 날짜간의 차이가 양수인지 음수인질을 판단하면 된다. 
- 또는 간단히 **boolean after(Object when)**과 **boolean before(Object when)**을 사용해도 된다.

#### day12/date_calendar/CalendarEx3.java

#### day12/date_calendar/CalendarEx4.java

* * *
# java.time 패키지

## java.time 패키지의 핵심 클래스

### Period와 Duration

### 객체 생성하기 - now(), of()

### Temporal과 TemporalAmount

### TemporalUnit과 TemporalField


## LocalDate와 LocalTime

### 특정 필드의 값 가져오기 - get(), getXXX()

### 필드의 값 변경하기 - with(), plus(), minus()

### 날짜와 시간의 비교 - isAfter(), isBefore(), isEqual()

## Instant


## LocalDateTime과 ZonedDateTime

### LocalDate와 LocalTime으로 LocalDateTime 만들기

### LocalDateTime의 변환

### LocalDateTime으로 ZonedDateTime 만들기

### ZonedOffset

### OffsetDateTime

### ZonedDateTime의 변환


## TemporalAdjusters

### TemporalAdjuster 직접 구현하기

## Period와 Duration

### between()

### between()과 until() 

### of(), with()

### 사칙연산, 비교연산, 기타 메서드

### 다른 단위로 변환 - toTotalMonths(), toDays(), toHours(), toMinutes()

## 파싱과 포맷

### 로케일에 종속된 형식화

### 출력 형식 직접 정의하기

### 문자열을 날짜와 시간으로 파싱하기




