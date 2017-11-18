/**
 * MyCalendar project for CS 151
 * @author Monsi Magal
 * All rights reserved to author
 */

/**
 * MyCalendar class
 * Represents the main MyCalendar object with multiple options
 */

package hw4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.JButton;

enum MONTHS {
	January, February, March, April, May, June, July, August, September, October, November, December;
}

enum DAYS {
	Su, Mo, Tu, We, Th, Fr, Sa;
}

enum LONGDAYS {
	Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday;
}


public class MyCalendarModel {

	private static TreeMap<Calendar, TreeSet<Event>> calendarToEvent;
	private static final String EVENTSTXT = "events.txt";
	static ArrayList<JButton> listOfButtons = new ArrayList<JButton>();
	private static int spaceCounter = 0;
	private static String monthString = "";
	private static int todayDate; 
	private static int numberOfDays;
	
	/**
	 * Constructor for MyCalendar, creates a new MyCalendar object 
	 *
	 */
	public MyCalendarModel() {
		calendarToEvent = new TreeMap<Calendar, TreeSet<Event>>();
	}
	
	
	public static int getTodayDate() {
		return todayDate;
	}
	
	/**
	 * displayMainMenuWithEvents displays the calendar with the day with events highlighted with brackets ({}) around them
	 * @param c the calendar to make a MyCalendar object out of  
	 */
	public static void displayMainMenuWithEvents(Calendar c) {
		MONTHS[] arrayOfMonths = MONTHS.values(); // should be in constructor
		DAYS[] arrayOfDays = DAYS.values();
		int todayDate = c.get(Calendar.DAY_OF_MONTH);
		int todayDayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		int month = c.get(Calendar.MONTH);

		GregorianCalendar temp1 = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1);
		int firstDayOfWeek = temp1.get(Calendar.DAY_OF_WEEK);
		int numberOfDays = temp1.getActualMaximum(Calendar.DAY_OF_MONTH);
		System.out.print(arrayOfMonths[temp1.get(GregorianCalendar.MONTH)] + " " + temp1.get(GregorianCalendar.YEAR));
		System.out.println();

		for (int i = 0; i < arrayOfDays.length; i++) {
			System.out.print(arrayOfDays[i] + "  ");
		}
		System.out.println();

		for (int i = 1; i < numberOfDays + firstDayOfWeek; i++) {
			if (i < firstDayOfWeek) {
				System.out.print("    ");
			} else {
				MONTHS monthToCheck = (arrayOfMonths[temp1.get(GregorianCalendar.MONTH)]);
				int intMonth = Integer.parseInt(monthNameToNumber(monthToCheck.toString()));
				GregorianCalendar toCheck = new GregorianCalendar(temp1.get(GregorianCalendar.YEAR), intMonth,
						(i - firstDayOfWeek + 1)); // calendar or today's date, to check if there's any events that
													// match up with this day
				if (i % 7 != 1 && i != firstDayOfWeek) {

					System.out.print("  ");
					if ((i - firstDayOfWeek + 1) <= 9) {
						System.out.print(" ");
					}

				}
				if (calendarToEvent.get(toCheck) != null) {
					System.out.print("{" + (i - firstDayOfWeek + 1) + "}");
				} else {
					System.out.print(i - firstDayOfWeek + 1);
				}
				if ((i) % 7 == 0) {
					System.out.println();
				}
			}
		}
	}

	public static String getMonthString() {
		return monthString;
	}
	
	
	/**
	 * returns number of days in the current month
	 * @return
	 */
	public static int getNumberOfDays() {
		return numberOfDays;
	}
	
	
	
	/**
	 * displayMainMenu displays the calendar with the day with today highlighted with brackets ([ ]) around it
	 * @param c the calendar to use to display   
	 */
	public static String displayMainMenu(Calendar c) { 
		listOfButtons.clear();
		spaceCounter = 0;
		String toReturn = "";
		MONTHS[] arrayOfMonths = MONTHS.values(); // should be in constructor
		DAYS[] arrayOfDays = DAYS.values();
		todayDate = c.get(Calendar.DAY_OF_MONTH);
		System.out.println("todaydate: " + todayDate);
		int todayDayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		int month = c.get(Calendar.MONTH);
		
		

		GregorianCalendar temp1 = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1);
		int firstDayOfWeek = temp1.get(Calendar.DAY_OF_WEEK);
		numberOfDays = temp1.getActualMaximum(Calendar.DAY_OF_MONTH);
		monthString = (arrayOfMonths[temp1.get(GregorianCalendar.MONTH)] + " " + temp1.get(GregorianCalendar.YEAR));
		toReturn += monthString;
		toReturn += "\n";

		for (int i = 0; i < arrayOfDays.length; i++) {
			toReturn += (arrayOfDays[i] + "        ");
		}
//		System.out.println();
//		toReturn += "\n";
		
		for (int i = 1; i < numberOfDays + firstDayOfWeek; i++) {
			if (i < firstDayOfWeek) {
//				toReturn += ("    ");
				spaceCounter++;
			} else {
				if (i % 7 != 1 && i != firstDayOfWeek) {

//					toReturn += ("  ");
					if ((i - firstDayOfWeek + 1) <= 9) {
//						toReturn += (" ");
					}

				}
				if (((i - firstDayOfWeek + 1) == todayDate) && (month == temp1.get(GregorianCalendar.MONTH))
						&& (c.get(Calendar.YEAR) == temp1.get(GregorianCalendar.YEAR))) {
					JButton button = new JButton ( Integer.toString(i - firstDayOfWeek + 1) );
					listOfButtons.add(button);
				} else {
					JButton button = new JButton (Integer.toString(i - firstDayOfWeek + 1));
					listOfButtons.add(button);
				}
				if ((i) % 7 == 0) {
//					toReturn += "\n";
				}
			}
		}
		return toReturn;
	}


	// load
	/**
	 * loads events from events.txt
	 */
	public static void load() {
		File f = new File(EVENTSTXT);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(EVENTSTXT));
			String eventString = reader.readLine();
			calendarToEvent.clear();
			while (eventString != null) {
				String year = eventString.substring(1, 5);
				String month = eventString.substring(6, eventString.indexOf(" ", eventString.indexOf(" ") + 1));
				String restOfString = eventString.substring(eventString.indexOf(" ", eventString.indexOf(" ") + 1) + 1,
						eventString.length() - 1);
				String date = restOfString.substring(0, restOfString.indexOf(" "));
				String lastString = restOfString.substring(restOfString.indexOf(" ") + 1, restOfString.length() - 1);
				String startTime = restOfString.substring(restOfString.indexOf(" ") + 1, restOfString.indexOf(" ") + 6);
				String endTime = restOfString.substring(restOfString.indexOf(" ") + 9, restOfString.indexOf(" ") + 15);
				String title = restOfString.substring(restOfString.indexOf(" ") + 15, restOfString.length());
				eventString = reader.readLine();
				String numMonth = monthNameToNumber(month);
				if (date.length() == 1) {
					date = "0" + date;
				}

				String wholeDate = numMonth + "/" + date + "/" + year;
				Event toAddToMap = new Event(title, wholeDate, startTime, endTime);
				GregorianCalendar key = new GregorianCalendar(Integer.parseInt(year),
						Integer.parseInt(monthNameToNumber(month)), Integer.parseInt(date));
				// treeSET
				if (calendarToEvent.get(key) == null) { // there are no events under this date
					TreeSet treeSetForMap = new TreeSet<Event>();
					treeSetForMap.add(toAddToMap);
					calendarToEvent.put(key, treeSetForMap);
				} else { // there are already events, add event to the treeset
					calendarToEvent.get(key).add(toAddToMap);
				}
			}
			System.out.println("Events loaded successfully");
			reader.close();
		} catch (IOException e) {
			System.out.println("error");
		}

	}

	/**
	 * takes the name of a month as an input and returns what index it corresponds to 
	 * @param name the name of the month
	 * @return string representation of the index of the month in the enum
	 */
	public static String monthNameToNumber(String name) {
		MONTHS[] arrayOfMonths = MONTHS.values();
		int toReturn = -1;
		for (int i = 0; i < arrayOfMonths.length; i++) {
			if (arrayOfMonths[i].name().equals(name)) {
				toReturn = i + 1;
				break;
			}
		}
		String temp = Integer.toString(toReturn);
		if (toReturn <= 9) {
			temp = "0" + toReturn;
		}
		return temp;
	}

	// view by

	/**
	 * view's either the day or month view based on user input
	 */
	public static void viewBy() {
		MONTHS[] arrayOfMonths = MONTHS.values();
		LONGDAYS[] arrayOfLongDays = LONGDAYS.values();
		System.out.println("[D]ay view or [M]onth view ? ");
		Scanner sc = new Scanner(System.in);
		String input;
		if (sc.hasNextLine()) {
			input = sc.nextLine().toLowerCase();

			if (input.equals("d")) { //day view				
				GregorianCalendar cal1 = new GregorianCalendar();
				GregorianCalendar cal = new GregorianCalendar(cal1.get(Calendar.YEAR), cal1.get(Calendar.MONTH)+1, cal1.get(Calendar.DAY_OF_MONTH));
				System.out.print(arrayOfLongDays[cal1.get(Calendar.DAY_OF_WEEK) - 1]);
				System.out.print(", ");
				System.out.print(arrayOfMonths[cal.get(Calendar.MONTH)-1]);
				System.out.print(" ");
				System.out.print(cal.get(Calendar.DAY_OF_MONTH));
				System.out.print(", ");
				System.out.print(cal.get(Calendar.YEAR));
				String values = getValues(cal);
				if (values != "") {
					System.out.println("\n" + values);
				} else {
					System.out.println("\nThere are no events scheduled for this day");
				}
				System.out.println("\n[P]revious or [N]ext or [M]ain menu ? ");
				input = sc.nextLine().toLowerCase();
				while (input.equals("n") || input.equals("p")) {
					if (input.equals("p")) { // previous
						cal.add(Calendar.DAY_OF_MONTH, -1);
						cal1.add(Calendar.DAY_OF_MONTH, -1);
						System.out.print(arrayOfLongDays[cal1.get(Calendar.DAY_OF_WEEK) - 1]);
						System.out.print(", ");
						System.out.print(arrayOfMonths[cal.get(Calendar.MONTH)-1]);
						System.out.print(" ");
						System.out.print(cal.get(Calendar.DAY_OF_MONTH));
						System.out.print(", ");
						System.out.print(cal.get(Calendar.YEAR) + "\n");
						String value = getValues(cal);
						if (value != "") {
							System.out.println(value);
						} else {
							System.out.println("\nThere are no events scheduled for this day");
						}
						System.out.println("\n[P]revious or [N]ext or [M]ain menu ? ");
						input = sc.nextLine().toLowerCase();
					} else if (input.equals("n")) { // next
						cal.add(Calendar.DAY_OF_MONTH, 1);
						cal1.add(Calendar.DAY_OF_MONTH, 1);
						System.out.print(arrayOfLongDays[cal1.get(Calendar.DAY_OF_WEEK) - 1]);
						System.out.print(", ");
						System.out.print(arrayOfMonths[cal.get(Calendar.MONTH)-1]);
						System.out.print(" ");
						System.out.print(cal.get(Calendar.DAY_OF_MONTH));
						System.out.print(", ");
						System.out.print(cal.get(Calendar.YEAR) + "\n");
						String values1 = getValues(cal);
						if (values1 != "") {
							System.out.println(values1);
						} else {
							System.out.println("\nThere are no events scheduled for this day");
						}
						System.out.println("\n[P]revious or [N]ext or [M]ain menu ? ");
						input = sc.nextLine().toLowerCase();
					}
				}
				if (input.equals("m")) { // main menu
					System.out.println("main menu");
				} else { // user didn't input a valid option
					System.out.println("\n[P]revious or [N]ext or [M]ain menu ? ");
				}

			} else if (input.equals("m")) { // month view
				GregorianCalendar cal = new GregorianCalendar();
				displayMainMenuWithEvents(cal);
				System.out.println("[P]revious or [N]ext or [M]ain menu ? ");
				input = sc.nextLine().toLowerCase();
				while (input.equals("p") || input.equals("n")) {
					if (input.equals("p")) { // previous
						cal.add(Calendar.MONTH, -1);
						displayMainMenuWithEvents(cal);
						System.out.println("\n[P]revious or [N]ext or [M]ain menu ? ");
						input = sc.nextLine().toLowerCase();
					} else if (input.equals("n")) { // next
						cal.add(Calendar.MONTH, 1);
						displayMainMenuWithEvents(cal);
						System.out.println("\n[P]revious or [N]ext or [M]ain menu ? ");
						input = sc.nextLine().toLowerCase();
					}
				}
				if (input.equals("m")) { // main menu
					System.out.println("main menu");
				} else { // user didn't input a valid option
					System.out.println("\n[P]revious or [N]ext or [M]ain menu ? ");
				}
			}
		}
	}
	


	/**
	 * a method to get the events on a day
	 * @param calendar the date to find the events for 
	 * @return a string of all the events on the given day
	 */
	public static String getValues(Calendar calendar) {
		String toReturn = "";
		TreeSet<Event> values = calendarToEvent.get(calendar);
		if (values == null) {
			return toReturn;
		}
		for (Event e : values) {
			toReturn += e.toString() + "\n";
		}
		return toReturn;
	}
	
	public static String getValues(int month, int date, int year) {
		Calendar calendar = new GregorianCalendar((year), (month), (date));
		System.out.println("monthV: " + calendar.get(Calendar.MONTH) + "dayV: " + calendar.get(Calendar.DAY_OF_MONTH) + "yearV: " + calendar.get(Calendar.YEAR));
		String toReturn = "";
		TreeSet<Event> values = calendarToEvent.get(calendar);
		if (values == null) {
			return toReturn;
		}
		for (Event e : values) {
			toReturn += e.toString() + "\n";
		}
		return toReturn;
	}

	// create

	/**
	 * creates a new event
	 */
	public static void create() {
		Scanner sc = new Scanner(System.in);
		String title = "";
		String date = "";
		String startTime = "";
		String endTime = "";
		System.out.println("Enter the title of the event you would like to create: ");
		if (sc.hasNextLine()) {
			title = sc.nextLine().toLowerCase();
		}
		System.out.println("Enter the date on MM/DD/YYYY format: ");
		if (sc.hasNextLine()) {
			date = sc.nextLine().toLowerCase();
		}
		System.out.println("Enter the starting time in 24 hour format (ex 15:30): ");
		if (sc.hasNextLine()) {
			startTime = sc.nextLine().toLowerCase();
		}
		System.out.println("Enter the ending time: in 24 hour format (ex 15:30): "); // if there's no end time set the
																						// end time to the start time.
		if (sc.hasNextLine()) {
			endTime = sc.nextLine().toLowerCase();
			if (endTime.equals("")) {
				endTime = startTime;
			}
		}
		Event toAdd = new Event(title, date, startTime, endTime);

		GregorianCalendar calendar = new GregorianCalendar(toAdd.getYear(), toAdd.getMonth(), toAdd.getDay());
		if (calendarToEvent.get(calendar) == null) { // there are no events under this date
			TreeSet treeSetForMap = new TreeSet<Event>();
			treeSetForMap.add(toAdd);
			calendarToEvent.put(calendar, treeSetForMap);
		} else { // there are already events, add event to the treeset
			boolean canAdd = true;
			for (Event e : calendarToEvent.get(calendar)) {
				if (e.conflictCheck(toAdd)) { //check this (e.getIntEndTime()) < (toAdd.getIntStartTime()) ////return true if there is a conflict, false if there isn't
					System.out.println("Sorry can't add that event, there is a conflict");
					canAdd = false;
				}
			}
			if (canAdd) {
				calendarToEvent.get(calendar).add(toAdd); // THAT'S WHAT IT USED TO BE calendarToEvent.get(toAdd.getDate()).add(toAdd);
			}
		}
	}

	// go to
	/**
	 * goes to a user inputted specified day
	 */
	public static void goTo() {
		MONTHS[] arrayOfMonths = MONTHS.values();
		LONGDAYS[] arrayOfLongDays = LONGDAYS.values();
		Scanner sc = new Scanner(System.in);
		String date = "";
		System.out.println("Enter a date on MM/DD/YYYY format: ");
		if (sc.hasNextLine()) {
			date = sc.nextLine().toLowerCase();
		}
		int month = Integer.parseInt(date.substring(0, 2));
		int day = Integer.parseInt(date.substring(3, 5));
		int year = Integer.parseInt(date.substring(6, 10));
		GregorianCalendar toDisplay = new GregorianCalendar(year, month, day);
		String value = getValues(toDisplay);
		if (value != "") {
			System.out.println(value);
		} else {
			System.out.println("There are no events at this day");
		}
	}

	// event list
	/**
	 * displays every event in events.txt
	 */
	public static void eventList() {
		for (Map.Entry<Calendar, TreeSet<Event>> entry : calendarToEvent.entrySet()) {
			TreeSet<Event> value = entry.getValue();
			for (Event e: value) {
				System.out.println(e.toString());
			}
		}
	}

	// delete
	/**
	 * deletes either all events on a specific day, or all events depending on user input
	 * also saves to events.txt
	 */
	public static void delete() {
		System.out.println("Do you want to delete events on a [S]elected date or [A]ll events?");
		Scanner sc = new Scanner(System.in);
		String input = "";
		if (sc.hasNextLine()) {
			input = sc.nextLine().toLowerCase();
			if (input.equals("s")) {
				System.out.println("s");
				System.out.println("What day do you want to delete the events on? In MM/DD/YYYY");
				String date = sc.nextLine();
				int month = Integer.parseInt(date.substring(0, 2));
				int day = Integer.parseInt(date.substring(3, 5));
				int year = Integer.parseInt(date.substring(6, 10));
				GregorianCalendar toDelete = new GregorianCalendar(year, month, day);
				calendarToEvent.remove(toDelete);
				quit(); //to save the new treemap to the events.txt
				System.out.println("Removed");
			} else if (input.equals("a")) {
				System.out.println("a");
				System.out.println("Deleting all events");
				calendarToEvent = new TreeMap<Calendar, TreeSet<Event>>();
			}
		}
	}

	// quit
	/**
	 * quits the program, saves to events.txt
	 */
	public static void quit() {
//		System.out.println("quit");
		File f = new File(EVENTSTXT);
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(EVENTSTXT));
			String eventsString = getEventsString().replaceAll(", ", "]\n[");
			writer.write(eventsString);
			writer.close();
		} catch (IOException e) {
			System.out.println("error");
		}
	}
	
	public static String getDateString(Calendar cal) {
		String month = Integer.toString(cal.get(Calendar.MONTH)+1);
		String day = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
		String year = Integer.toString(cal.get(Calendar.YEAR));
		return month + "/" + day + "/" + year;
	}

	/**
	 * 
	 * @return a string of all the events
	 */
	public static String getEventsString() {
		String toReturn = "";
		for (Map.Entry<Calendar, TreeSet<Event>> entry : calendarToEvent.entrySet()) {
			toReturn += entry.getValue() + "\n";
		}
		return toReturn;
	}
	
	public static int getSpaceCounter() {
		return spaceCounter;
	}
}
