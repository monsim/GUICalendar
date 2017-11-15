package hw4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MyCalendarTester {
	static int currentDate;
	public static void main(String[] args) {
		/*
		 * should create new myCalendar object
		 */
		MyCalendar myCal = new MyCalendar();
		GregorianCalendar cal = new GregorianCalendar();
		String toDisplay = myCal.displayMainMenu(cal);
	
		
		String month = myCal.getMonthString();
		month += "\n";
		JLabel daysOfWeek = new JLabel("Su         Mo       Tu       We       Th       Fr      Sa");
		System.out.println("month: " + month);
		JLabel monthTextArea = new JLabel(month); 
		monthTextArea.setHorizontalAlignment(JLabel.CENTER);
		monthTextArea.setVerticalAlignment(JLabel.CENTER);
		JPanel calendarPanel = new JPanel();		
		JPanel leftPanel = new JPanel();	
		JFrame overarchingFrame = new JFrame();
		JPanel rightPanel = new JPanel();
		rightPanel.setMinimumSize(new Dimension(340, 340));
		rightPanel.setPreferredSize(new Dimension(340, 340));
		rightPanel.setMaximumSize(new Dimension(340, 340));
		
		JLabel createButton = new JLabel("CREATE");
		createButton.setMinimumSize(new Dimension(20, 30));
		createButton.setPreferredSize(new Dimension(20, 30));
		createButton.setMaximumSize(new Dimension(20, 30));
		createButton.setHorizontalAlignment(JLabel.CENTER);
		createButton.setVerticalAlignment(JLabel.CENTER);
		createButton.setOpaque(true);
		createButton.setBackground(Color.RED);
		createButton.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent event) {
				System.out.println("create clicked");
				JDialog createDialog = new JDialog();
				createDialog.setLayout(new BorderLayout());
				createDialog.add(new JTextArea("Create an event"), BorderLayout.NORTH);
				createDialog.setMinimumSize(new Dimension(250, 250));
				createDialog.setPreferredSize(new Dimension(250, 250));
				createDialog.setMaximumSize(new Dimension(250, 250));
				createDialog.setVisible(true);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JPanel prevNextCreatePanel = new JPanel();
		prevNextCreatePanel.setLayout(new BorderLayout());
		JPanel createAndMonthPanel = new JPanel();
		createAndMonthPanel.setLayout(new BorderLayout());
		createAndMonthPanel.add(createButton, BorderLayout.NORTH);
		createAndMonthPanel.add(daysOfWeek, BorderLayout.SOUTH);
		createAndMonthPanel.add(monthTextArea, BorderLayout.CENTER);
		
		for (int i = 0; i < myCal.getSpaceCounter(); i++) {  	//add spaces so month starts at the right day
			JButton button = new JButton();
			button.setEnabled(false);
			button.setBorder(BorderFactory.createEmptyBorder());
			calendarPanel.add(button);
		}
		for (int i = 0; i < myCal.listOfButtons.size(); i++) {
			JButton button = myCal.listOfButtons.get(i);
			calendarPanel.add(button);
			button.setName(Integer.toString(i+1));
			if (button.getName().equals(Integer.toString(myCal.getTodayDate()))) { //todays date
				button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				currentDate = myCal.getTodayDate();
			} else {
				button.setBorder(BorderFactory.createEmptyBorder());
			}
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("button pressed");
					System.out.println("name: " + button.getName());
					JTextArea dayText = new JTextArea();
					rightPanel.removeAll();
					dayText.setText("well let's see " + button.getName());
					rightPanel.add(dayText);
					rightPanel.setVisible(true);
				}
			});
		}
		
		JPanel previousNextQuitPanel = new JPanel();
		JButton previousButton = new JButton("<");
		previousButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("previous button");
				if (currentDate > 1) {
					currentDate--;
					System.out.println("current: " + currentDate);
					JButton b = myCal.listOfButtons.get(currentDate-1);
					b.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					JButton prevButton = myCal.listOfButtons.get(currentDate);
					prevButton.setBorder(BorderFactory.createEmptyBorder());
				}
			}
		});
		
		JButton nextButton = new JButton(">"); 
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("next button");
				currentDate++;
				System.out.println("current: " + currentDate);
				JButton b = myCal.listOfButtons.get(currentDate-1);
				b.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				JButton prevButton = myCal.listOfButtons.get(currentDate-2);
				prevButton.setBorder(BorderFactory.createEmptyBorder());
			}
		});
		
		
		JButton quitButton = new JButton("Quit");
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("quit button");
				
			}
		});
		
	
		
		
		previousNextQuitPanel.setLayout(new FlowLayout());
		previousNextQuitPanel.add(previousButton);
		previousNextQuitPanel.add(nextButton);
		previousNextQuitPanel.add(quitButton);
		
		
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
		leftPanel.setLayout(new BorderLayout());
		leftPanel.add(calendarPanel, BorderLayout.SOUTH);
//		frame.add(previousNextQuitPanel, BorderLayout.SOUTH);
		leftPanel.add(prevNextCreatePanel, BorderLayout.NORTH);
		calendarPanel.setPreferredSize(new Dimension(300,230));
		leftPanel.setVisible(true);
		
		overarchingFrame.setLayout(new BorderLayout());
		overarchingFrame.add(leftPanel, BorderLayout.WEST);
		overarchingFrame.add(rightPanel, BorderLayout.EAST);
		overarchingFrame.pack();
		overarchingFrame.setVisible(true);
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
