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
|\`|escape문자|\`#\`#,##<br>\`\`#.###|#1,234,568<br>\`1,234,568|

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
			"`#`#.###",
			"``#,###"
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
            `##.### : `1234567.89
             `#,### : `1,234,568
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
|M|월(1~12 또는 1월~12월)|10 또는 10월, OCT|
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



## ChoiceFormat

## MessageFormat



* * * 
# 날짜와 시간

* * *
# java.time 패키지



