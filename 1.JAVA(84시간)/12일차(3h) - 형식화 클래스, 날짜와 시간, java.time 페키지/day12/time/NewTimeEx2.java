package day12.time;

import java.time.*;

public class NewTimeEx2 {
	public static void main(String[] args) {
		LocalDate date = LocalDate.of(2021,  12, 31); // 2021년 12월 31일
		LocalTime time = LocalTime.of(12, 34, 56); // 12시 34분 56초
		
		// 2021년 12월 31일 12시 34분 56초
		LocalDateTime dt = LocalDateTime.of(date,  time);
		
		ZoneId zid = ZoneId.of("Asia/Seoul");
		ZonedDateTime zdt = dt.atZone(zid);
		//String strZid = zdt.getZone().getId();
		//System.out.println(strZid); // Asia/Seoul
		
		ZonedDateTime seoulTime = ZonedDateTime.now();
		ZoneId nyId = ZoneId.of("America/New_York");
		ZonedDateTime nyTime = ZonedDateTime.now().withZoneSameInstant(nyId);
		
		// ZonedDateTime -> OffsetDateTime
		OffsetDateTime odt = zdt.toOffsetDateTime();
		
		System.out.println(dt);
		System.out.println(zid);
		System.out.println(zdt);
		System.out.println(seoulTime);
		System.out.println(nyTime);
		System.out.println(odt);
	}
}
