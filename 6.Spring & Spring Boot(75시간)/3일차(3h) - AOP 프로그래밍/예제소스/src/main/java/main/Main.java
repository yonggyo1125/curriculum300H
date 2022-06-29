package main;

import aopex.*;

public class Main {

	public static void main(String[] args) {
		ImpeCalculator impeCal = new ImpeCalculator();
		impeCal.factorial(4);
		
		RecCalculator recCal = new RecCalculator();
		recCal.factorial(4);
	}
}
