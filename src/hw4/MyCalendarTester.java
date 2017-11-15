package hw4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MyCalendarTester {
	public static void main(String[] args) {
		/*
		 * should create new myCalendar object
		 */
		MyCalendar myCal = new MyCalendar();
		GregorianCalendar cal = new GregorianCalendar();
		String toDisplay = myCal.displayMainMenu(cal);	
		String month = myCal.getMonthString();
		JLabel monthTextArea = new JLabel(month); 
		monthTextArea.setHorizontalAlignment(JLabel.CENTER);
		monthTextArea.setVerticalAlignment(JLabel.CENTER);
		JPanel calendarPanel = new JPanel();		//left panel
		JFrame frame = new JFrame();	
		JLabel createButton = new JLabel("CREATE");
		createButton.setMinimumSize(new Dimension(20, 30));
		createButton.setPreferredSize(new Dimension(20, 30));
		createButton.setMaximumSize(new Dimension(20, 30));
		createButton.setHorizontalAlignment(JLabel.CENTER);
		createButton.setVerticalAlignment(JLabel.CENTER);
		createButton.setOpaque(true);
		createButton.setBackground(Color.RED);
		
		JPanel prevNextCreatePanel = new JPanel();
		prevNextCreatePanel.setLayout(new BorderLayout());
		JPanel createAndMonthPanel = new JPanel();
		createAndMonthPanel.setLayout(new BorderLayout());
		createAndMonthPanel.add(createButton, BorderLayout.NORTH);
		createAndMonthPanel.add(monthTextArea, BorderLayout.SOUTH);
		
		for (int i = 0; i < myCal.getSpaceCounter(); i++) {  	//add spaces so month starts at the right day
			JButton button = new JButton();
			button.setEnabled(false);
			calendarPanel.add(button);
		}
		for (int i = 0; i < myCal.listOfButtons.size(); i++) {
			JButton button = myCal.listOfButtons.get(i);
//			button.setPreferredSize(new Dimension(5,5));
			calendarPanel.add(button);
		}
		
		JPanel previousNextQuitPanel = new JPanel();
		JButton previousButton = new JButton("<");
		JButton nextButton = new JButton(">");  
		previousNextQuitPanel.setLayout(new FlowLayout());
		previousNextQuitPanel.add(previousButton);
		previousNextQuitPanel.add(nextButton);
		
		
		prevNextCreatePanel.add(previousNextQuitPanel, BorderLayout.NORTH);
		prevNextCreatePanel.add(createAndMonthPanel, BorderLayout.SOUTH);
//		addButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent event) {
//				String textToAdd = textField.getText();
//				area.addItem(textToAdd);
//				textArea.setText(area.format());
//			}
//		});
		
		
		calendarPanel.setLayout(new GridLayout(5,7));
		frame.setLayout(new BorderLayout());
		frame.add(calendarPanel, BorderLayout.SOUTH);
//		frame.add(previousNextQuitPanel, BorderLayout.SOUTH);
		frame.add(prevNextCreatePanel, BorderLayout.NORTH);
		calendarPanel.setPreferredSize(new Dimension(300,230));
		frame.pack();
		frame.setVisible(true);
		displayOptions();
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine().toLowerCase();
		
		
		
		
		while (true) {
			if (input.equals("l")) {
				myCal.load();
				myCal.displayMainMenu(cal);
				displayOptions();
				input = sc.nextLine().toLowerCase();
				// need to display calendar WITH EVENTS

			} else if (input.equals("v")) {
				myCal.viewBy();
				myCal.displayMainMenu(cal);
				displayOptions();
				input = sc.nextLine().toLowerCase();
			} else if (input.equals("c")) {
				myCal.create();
				myCal.displayMainMenu(cal);
				displayOptions();
				input = sc.nextLine().toLowerCase();
			} else if (input.equals("g")) {
				myCal.goTo();
				displayOptions();
				input = sc.nextLine().toLowerCase();
			} else if (input.equals("e")) {
				myCal.eventList();
				myCal.displayMainMenu(cal);
				displayOptions();
				input = sc.nextLine().toLowerCase();
			} else if (input.equals("d")) {
				myCal.delete();
				myCal.displayMainMenu(cal);
				displayOptions();
				input = sc.nextLine().toLowerCase();
			} else /* if (input.equals("q")) */ {
				myCal.quit();
				break;
			}

		}
	}
	
	/**
	 * method to prompt for user input for the next step 
	 */
	public static void displayOptions() {
		System.out.println("\nSelect one of the following options: \n"
				+ "[L]oad   [V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit\n");
	}
}
