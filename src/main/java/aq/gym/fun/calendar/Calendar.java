package aq.gym.fun.calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;

public class Calendar {
	
	private static final String SUNDAY_PATTERN = "%-6s%n";
	private static final String OTHER_PATTERN = "%-6s";
	
	public void print() {
		LocalDate todayDate = LocalDate.now();
		LocalDate calendar = todayDate.minusDays(todayDate.getDayOfMonth() - 1);
		int monthValue = calendar.getMonthValue();
		printDayNames();
		printAdditionalWhiteSpases(calendar);
		while(monthValue == calendar.getMonthValue()) {
			printCalendarDays(calendar, todayDate);
			calendar = calendar.plusDays(1);
		}
	}
	
	private void printDayNames() {
		Arrays
			.asList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
			.stream()
			.forEach(day -> System.out.printf(OTHER_PATTERN, day));
		System.out.println();
	}
	
	private void printAdditionalWhiteSpases(LocalDate calendar) {
		int weekDay = calendar.getDayOfWeek().getValue();
		for(int i = 1; i < weekDay; i++) {
			System.out.printf(OTHER_PATTERN, " ");
		}
	}
	
	private void printCalendarDays(LocalDate calendar, LocalDate todayDate) {
		if(isTodayDate(calendar, todayDate)) {
			printTodayDay(calendar);
		} else {
			printOtherDay(calendar);
		}
	}
	
	private boolean isTodayDate(LocalDate calendar, LocalDate todayDate) {
		return calendar.getDayOfMonth() == todayDate.getDayOfMonth();
	}
	
	private void printTodayDay(LocalDate calendar) {
		if(calendar.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
			System.out.printf(SUNDAY_PATTERN, calendar.getDayOfMonth() + "*");
		} else {		
			System.out.printf(OTHER_PATTERN, calendar.getDayOfMonth() + "*");
		}
	}
	
	private void printOtherDay(LocalDate calendar) {
		if(calendar.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
			System.out.printf(SUNDAY_PATTERN, calendar.getDayOfMonth());
		} else {		
			System.out.printf(OTHER_PATTERN,calendar.getDayOfMonth());
		}
	}
}
