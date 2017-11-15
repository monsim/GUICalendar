package hw4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * Event class representing an event to be used in MyCalendar
 *
 */
public class Event implements Comparable<Event> {
	// CREATE GETTERS/SETTERS FOR ALL OF THESE
	private String title;
	private String stringDate;
	private int month, day, year;
	private int intStartTime;
	private int intEndTime;
	private String startTime;
	private String endTime;

	/**
	 * makes a new Event object
	 * @param title title of the Event
	 * @param date date of the Event
	 * @param startTime startTime of the Event
	 * @param endTime endTime of the Event
	 */
	public Event(String title, String date, String startTime, String endTime) {
		this.title = title;
		this.stringDate = date;
		this.intStartTime = Integer.parseInt(startTime.replaceAll(":", ""));
		this.intEndTime = Integer.parseInt(endTime.replaceAll(":", "").replaceAll(" ", ""));
		this.startTime = startTime;
		this.endTime = endTime;
		this.month = Integer.parseInt(stringDate.substring(0, 2));
		this.day = Integer.parseInt(stringDate.substring(3, 5));
		this.year = Integer.parseInt(stringDate.substring(6, 10));
	}
	
	
	/**
	 * gets the title of the Event
	 * @return the title of the Event
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * gets the string representation of the date of the Event
	 * @return string representation of the date of the Event
	 */
	public String getStringDate() {
		return this.stringDate;
	}

	/**
	 * gets the int representation of the month of the Event
	 * @return int representation of the month of the Event
	 */
	public int getMonth() {
		return this.month;
	}

	/**
	 * gets the int representation of the day of the Event
	 * @return int representation of the day of the Event
	 */
	public int getDay() {
		return this.day;
	}

	/**
	 * gets the int representation of the year of the Event
	 * @return the int representation of the year of the Event
	 */
	public int getYear() {
		return this.year;
	}

	/**
	 * gets the int representation of the start time of the Event
	 * @return the int representation of the start time of the Event
	 */
	public int getIntStartTime() {
		return this.intStartTime;
	}

	/**
	 * gets the int representation of the end time of the Event
	 * @return the int representation of the end time of the Event
	 */
	public int getIntEndTime() {
		return this.intEndTime;
	}
	
	/**
	 * gets the String representation of the start time of the Event
	 * @return the String representation of the start time of the Event
	 */
	public String getStartTime() {
		return this.startTime;
	}

	/**
	 * gets the String representation of the end time of the Event
	 * @return the String representation of the end time of the Event
	 */
	public String getEndTime() {
		return this.endTime;
	}
	
	
	/**
	 * sets the title of the Event
	 * @param title the new title
	 * @precondition title is a valid string
	 * @postcondition this.title will be changed to title 
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * sets the string representation of the date of the Event
	 * @param date the new date
	 * @precondition date is a valid string in format
	 * @postcondition this.stringDate will be changed to date
	 */
	public void setStringDate(String date) {
		this.stringDate = date;
	}

	/**
	 * sets the int representation of the month of the Event
	 * @param month the new month
	 * @precondition month is a valid month
	 * @postcondition this.month will be changed to month
	 */
	public void setMonth(int month) {
		this.month = month;
	}

	/**
	 * sets the int representation of the day of the Event
	 * @param day the new day
	 * @precondition day is a valid day
	 * @postcondition this.day will be changed to day
	 */
	public void setDay(int day) {
		this.day = day;
	}

	/**
	 * sets the int representation of the year of the Event
	 * @param year the new year
	 * @precondition year is a valid year
	 * @postcondition this.year will be changed to year
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * sets the int representation of the start time of the Event
	 * @param start the new start time
	 * @precondition start is a valid time
	 * @postcondition this.intStartTime will be changed to start
	 */
	public void setIntStartTime(int start) {
		this.intStartTime = start;
	}

	/**
	 * sets the int representation of the end time of the Event
	 * @param end the new end time
	 * @precondition end is a valid time
	 * @postcondition this.intEndTime will be changed to start
	 */
	public void setIntEndTime(int end) {
		this.intEndTime = end;
	}
	
	/**
	 * sets the String representation of the start time of the Event
	 * @param start the new start time
	 * @precondition start is a valid time
	 * @postcondition this.startTime will be changed to start
	 */
	public void setStartTime(String start) {
		this.startTime = start;
	}

	/**
	 * sets the String representation of the end time of the Event
	 * @param end the new end time
	 * @precondition end is a valid time
	 * @postcondition this.endTime will be changed to start
	 */
	public void setEndTime(String end) {
		this.endTime = end;
	}
	
	
	/**
	 * String representation of the Event
	 * @return String representation of the Event
	 */
	public String toString() {
		// Friday March 17 13:15 - 14:00 Dentist
		MONTHS[] arrayOfMonths = MONTHS.values();
		String event = year + " " + arrayOfMonths[month - 1].toString() + " " + day + " " + startTime + " - " + endTime
				+ " " + title;
		return event;
	}

	/**
	 * compares two Event objects based on year, month, day and start time
	 * @return a negative number if this < o, a positive number if this > o and 0 if this equals o
	 */
	public int compareTo(Event o) {
		int yearCompare = this.year - o.year;
		int monthCompare = this.month - o.month;
		int dayCompare = this.day - o.day;
		int startTimeCompare = this.intStartTime - o.intStartTime; //endTime doesn't matter 
		
		if (yearCompare == 0) {
			if (monthCompare == 0) {
				if (dayCompare == 0) {
					if (startTimeCompare == 0) {
						return 0;
					} else {
						return startTimeCompare;
					}
				} else {
					return dayCompare;
				}
			} else {
				return monthCompare;
			}
		} else {
			return yearCompare;
		}	
	}

	/**
	 * checks if two events are equal according to year, month, day and start time
	 */
	public boolean equals(Object x) {
		Event that = (Event)x;
		return this.compareTo(that) == 0; 
	}
	
	/**
	 * checks if there is a conflict in timing between this Event and a given one, e
	 * @param e an Event
	 * @return true if there is a conflict false if there is not 
	 */
	public boolean conflictCheck(Event e) { //return true if there is a conflict, false if there isn't
		if ((this.getIntStartTime()) <= (e.getIntEndTime()) && (this.getIntEndTime() >= e.getIntStartTime())) {
			return true;
		}
		return false;
	}
}
