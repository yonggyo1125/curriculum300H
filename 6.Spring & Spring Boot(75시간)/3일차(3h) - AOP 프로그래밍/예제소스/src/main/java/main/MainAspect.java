package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import aopex.Calculator;
import aopex.RecCalculator;
import config.AppCtx;

public class MainAspect {

	public static void main(String[] args) {	
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		
		//Calculator cal = ctx.getBean("calculator", Calculator.class);
		RecCalculator cal = ctx.getBean("calculator", RecCalculator.class);
		long fiveFact = cal.factorial(5);
		System.out.println("cal.factorial(5) = " + fiveFact);
		System.out.println(cal.getClass().getName());
		ctx.close();
	}
}
