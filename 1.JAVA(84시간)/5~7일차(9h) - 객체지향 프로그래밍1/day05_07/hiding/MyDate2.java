package day05_07.hiding;

public class MyDate2 {
	private int day;
	private int month;
	private int year;
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public void setMonth(int month) {
		if (month < 1 || day > 12) {
			System.out.println("오류입니다.");
		} else {
			this.month = month;
		}
	}
	
	public void setDay(int day) {
		if (month == 2) {
			if (day < 1 || day > 28) {
				System.out.println("오류입니다.");
			} else {
				this.day = day;
			}
		}
	}
} 
