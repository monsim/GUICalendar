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
import javax.swing.JTextField;

public class SimpleCalendar {
	
	//problem: when we go forward month it works fine, but when we go back it goes back two months, although displaying the proper month
	static int currentDate;
	private static JTextArea dayText = new JTextArea();
	private static JTextArea dayEventsText = new JTextArea();
	private static JPanel calendarPanel = new JPanel();	
	
	public static void main(String[] args) {
		/*
		 * should create new myCalendar object
		 */
		MyCalendarModel myCal = new MyCalendarModel();
		GregorianCalendar cal = new GregorianCalendar();
		MyCalendarModel.load();
		String toDisplay = MyCalendarModel.displayMainMenu(cal);
	
		
		String month = MyCalendarModel.getMonthString();
		JLabel daysOfWeek = new JLabel("Su         Mo       Tu       We       Th       Fr      Sa");
		System.out.println("month: " + month);
		JLabel monthTextArea = new JLabel(month); 
		monthTextArea.setHorizontalAlignment(JLabel.CENTER);
		monthTextArea.setVerticalAlignment(JLabel.CENTER);	
		JPanel leftPanel = new JPanel();	
		JFrame overarchingFrame = new JFrame();
		JPanel rightPanel = new JPanel();
		rightPanel.setMinimumSize(new Dimension(340, 340));
		rightPanel.setPreferredSize(new Dimension(340, 340));
		rightPanel.setMaximumSize(new Dimension(340, 340));
		rightPanel.setLayout(new BorderLayout());
		
		
		String toDisplayDate = MyCalendarModel.dateDisplay(cal);
		dayText.setText(toDisplayDate);
		rightPanel.add(dayText, BorderLayout.NORTH);
		
		JLabel createButton = new JLabel("CREATE");
		createButton.setMinimumSize(new Dimension(20, 30));
		createButton.setPreferredSize(new Dimension(20, 30));
		createButton.setMaximumSize(new Dimension(20, 30));
		createButton.setHorizontalAlignment(JLabel.CENTER);
		createButton.setVerticalAlignment(JLabel.CENTER);
		createButton.setOpaque(true);
		createButton.setBackground(Color.RED);
		createButton.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent event) {		//create dialog pop up
				System.out.println("create clicked");
				
				JDialog createDialog = new JDialog();
				createDialog.setLayout(new BorderLayout(20, 20));
				createDialog.setSize(450, 100);
				createDialog.setTitle("Create an event");
				JPanel textFieldPanel = new JPanel();

			
				JTextField eventName = new JTextField();
				eventName.setText("Untitled Event");
				eventName.setBorder(BorderFactory.createLineBorder(Color.BLACK));

				
				
				JTextField eventDate = new JTextField();
				//eventDate.setText(model.getDateDescription2(cal));
				eventDate.setText(MyCalendarModel.getDateString(cal));
				System.out.println(MyCalendarModel.getDateString(cal));
				eventDate.setBorder(BorderFactory.createLineBorder(Color.GRAY));
				eventDate.setEditable(false);

				
				JTextField eventStartTime = new JTextField();
				String time = cal.get(Calendar.HOUR_OF_DAY) + ":";
				int minute = cal.get(Calendar.MINUTE);
				if (minute < 10) 
					time += "0" + minute;
				else 
					time += minute;

				eventStartTime.setText(time);
				eventStartTime.setBorder(BorderFactory.createLineBorder(Color.GRAY));

				// EventEndTime
				JTextField eventEndTime = new JTextField();
				eventEndTime.setText("23:59");
				eventEndTime.setBorder(BorderFactory.createLineBorder(Color.GRAY));
				
				
				JButton saveButton = new JButton("SAVE");
				saveButton.addActionListener(
						new ActionListener()
						{
							public void actionPerformed(ActionEvent e)
							{
								String title = eventName.getText();
								String date = eventDate.getText();
								String startTime = eventStartTime.getText();
								String endTime = eventEndTime.getText();
								boolean added = MyCalendarModel.create(title, date, startTime, endTime);
								if (added == false) {
									JOptionPane.showMessageDialog(new JDialog(), "Sorry can't add that event, there is a conflict");
									createDialog.dispose();
								}
								dayEventsText.repaint();
								createDialog.dispose();
							}
						});
				
				textFieldPanel.add(eventDate);
				textFieldPanel.add(eventStartTime);
				textFieldPanel.add(eventEndTime);
				textFieldPanel.add(saveButton);

				createDialog.add(eventName, BorderLayout.NORTH);
				createDialog.add(textFieldPanel, BorderLayout.SOUTH);
				
				createDialog.setVisible(true);
				dayEventsText.repaint();
				
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
		
		addSpaces();
		for (int i = 0; i < MyCalendarModel.listOfButtons.size(); i++) {
			JButton button = MyCalendarModel.listOfButtons.get(i);
			calendarPanel.add(button);
			button.setName(Integer.toString(i+1));
			if (button.getName().equals(Integer.toString(MyCalendarModel.getTodayDate()))) { //todays date
				button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				currentDate = MyCalendarModel.getTodayDate();
			} else {
				button.setBorder(BorderFactory.createEmptyBorder());
			}
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("button presseddd");
					System.out.println("name: " + button.getName());
					cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(button.getName())); //change the corresponding calendar as well. fix for create
					JButton b = MyCalendarModel.listOfButtons.get(Integer.parseInt(button.getName())-1);
					b.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					JButton prevButton = MyCalendarModel.listOfButtons.get(currentDate-1);
					prevButton.setBorder(BorderFactory.createEmptyBorder());
					currentDate = Integer.parseInt(button.getName());
					rightPanel.removeAll();
					dayText.removeAll();
					String toDisplay = MyCalendarModel.dateDisplay(cal);
					dayText.setText(toDisplay);
					System.out.println("button.getName(): " + button.getName());
					rightPanel.add(dayText, BorderLayout.NORTH);
					String events = MyCalendarModel.getValues(cal.get(Calendar.MONTH)+1, cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.YEAR));
					System.out.println("events: " + events);
					rightPanel.removeAll();
					dayEventsText.setText(events);
					rightPanel.add(dayEventsText, BorderLayout.SOUTH);
					rightPanel.repaint();
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
					cal.add(Calendar.DAY_OF_MONTH, -1);
					System.out.println("current: " + currentDate);
					JButton b = MyCalendarModel.listOfButtons.get(currentDate-1);
					b.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					JButton prevButton = MyCalendarModel.listOfButtons.get(currentDate);
					prevButton.setBorder(BorderFactory.createEmptyBorder());
					cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(b.getName()));
					JTextArea dayText = new JTextArea();
					rightPanel.removeAll();
					dayText.removeAll();
					String toDisplay = MyCalendarModel.dateDisplay(cal);
					dayText.setText(toDisplay);
					System.out.println("button.getName(): " + b.getName());
					rightPanel.add(dayText, BorderLayout.NORTH);
					rightPanel.setVisible(true);
				} else { //go to previous month
					cal.add(Calendar.MONTH, -1);
					String toDisplay = MyCalendarModel.displayMainMenu(cal);
					String month = MyCalendarModel.getMonthString();
					monthTextArea.removeAll();
					calendarPanel.removeAll();
					monthTextArea.setText(month); 
					System.out.println(MyCalendarModel.getTodayDate());
					cal.set(Calendar.DAY_OF_MONTH, MyCalendarModel.getTodayDate());
					currentDate = MyCalendarModel.getNumberOfDays();
					System.out.println("currentdate: " + currentDate);
					addSpaces();
					for (int i = 0; i < MyCalendarModel.listOfButtons.size(); i++) {
						JButton button = MyCalendarModel.listOfButtons.get(i);
						calendarPanel.add(button);
						button.setName(Integer.toString(i+1));
						if (button.getName().equals(Integer.toString(currentDate))) { //todays date
							button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//							currentDate = myCal.getTodayDate();
						} else {
							button.setBorder(BorderFactory.createEmptyBorder());
						}
						button.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								System.out.println("button pressed");		//here
//								cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(button.getName()));
								System.out.println("name: " + button.getName());
								JButton b = MyCalendarModel.listOfButtons.get(Integer.parseInt(button.getName())-1);  
								b.setBorder(BorderFactory.createLineBorder(Color.BLACK));
								JButton prevButton = MyCalendarModel.listOfButtons.get(currentDate-1);
								prevButton.setBorder(BorderFactory.createEmptyBorder());
								currentDate = Integer.parseInt(button.getName());
								rightPanel.removeAll();
//								cal.add(Calendar.MONTH, 1);
								String toDisplay = MyCalendarModel.dateDisplay(cal);
								dayText.setText(toDisplay);
								System.out.println("button.getName(): " + button.getName());
								rightPanel.add(dayText, BorderLayout.NORTH);
								rightPanel.setVisible(true);
							}
						});
					}
					calendarPanel.repaint();
					monthTextArea.repaint();
				}
				String events = MyCalendarModel.getValues(cal.get(Calendar.MONTH)+1, cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.YEAR));
				System.out.println("events: " + events);
				dayEventsText.removeAll();
				dayEventsText.setText(events);
				rightPanel.add(dayEventsText, BorderLayout.SOUTH);
				rightPanel.repaint();
			}
		});
		
		JButton nextButton = new JButton(">"); 
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("next button");
				int numberOfDays = MyCalendarModel.getNumberOfDays();
				if (currentDate < numberOfDays){
					currentDate++;
					cal.add(Calendar.DAY_OF_MONTH, 1);
					System.out.println("current: " + currentDate);
					JButton b = MyCalendarModel.listOfButtons.get(currentDate-1);
					b.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					JButton prevButton = MyCalendarModel.listOfButtons.get(currentDate-2);
					prevButton.setBorder(BorderFactory.createEmptyBorder());
					JTextArea dayText = new JTextArea();
					rightPanel.removeAll();
					dayText.removeAll();
					String toDisplay = MyCalendarModel.dateDisplay(cal);
					dayText.setText(toDisplay);
					System.out.println("button.getName(): " + b.getName());
					rightPanel.add(dayText, BorderLayout.NORTH);
					rightPanel.setVisible(true);
				} else { //go to next month
					System.out.println("initial month: " + cal.get(Calendar.MONTH));
					cal.add(Calendar.MONTH, 1);
					cal.set(Calendar.DAY_OF_MONTH, 1);
					String toDisplay = MyCalendarModel.displayMainMenu(cal);
					String month = MyCalendarModel.getMonthString();
					monthTextArea.removeAll();
					calendarPanel.removeAll();
					monthTextArea.setText(month);
					currentDate = 1;
					addSpaces();
					for (int i = 0; i < MyCalendarModel.listOfButtons.size(); i++) {
						JButton button = MyCalendarModel.listOfButtons.get(i);
						calendarPanel.add(button);
						button.setName(Integer.toString(i+1));
						if (button.getName().equals(Integer.toString(MyCalendarModel.getTodayDate()))) { //todays date
							button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
							currentDate = MyCalendarModel.getTodayDate();
						} else {
							button.setBorder(BorderFactory.createEmptyBorder());
						}
						button.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								System.out.println("button pressed");
								System.out.println("name: " + button.getName());
								JButton b = MyCalendarModel.listOfButtons.get(Integer.parseInt(button.getName())-1);
								b.setBorder(BorderFactory.createLineBorder(Color.BLACK));
								JButton prevButton = MyCalendarModel.listOfButtons.get(currentDate-1);
								prevButton.setBorder(BorderFactory.createEmptyBorder());
								currentDate = Integer.parseInt(button.getName());
								rightPanel.removeAll();
								dayText.removeAll();								
								String toDisplay = MyCalendarModel.dateDisplay(cal);
								dayText.setText(toDisplay);
								System.out.println("button.getName(): " + button.getName());
								rightPanel.add(dayText, BorderLayout.NORTH);
								rightPanel.setVisible(true);
							}
						});
					}
					
					calendarPanel.repaint();
					monthTextArea.repaint();
					
				}
//				cal.add(Calendar.MONTH, 1);
				String events = MyCalendarModel.getValues(cal.get(Calendar.MONTH)+1, cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.YEAR));
				System.out.println("events: " + events);
				dayEventsText.removeAll();
				dayEventsText.setText(events);
				rightPanel.add(dayEventsText, BorderLayout.SOUTH);
				rightPanel.repaint();
			}
		});
		
		
		JButton quitButton = new JButton("Quit");
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("quit button");
				MyCalendarModel.quit();
				overarchingFrame.dispose();
				System.exit(0);
			}
		});
		
		
		previousNextQuitPanel.setLayout(new FlowLayout());
		previousNextQuitPanel.add(previousButton);
		previousNextQuitPanel.add(nextButton);
		previousNextQuitPanel.add(quitButton);
			
		prevNextCreatePanel.add(previousNextQuitPanel, BorderLayout.NORTH);
		prevNextCreatePanel.add(createAndMonthPanel, BorderLayout.SOUTH);
		
		
		calendarPanel.setLayout(new GridLayout(5,7));
		leftPanel.setLayout(new BorderLayout());
		leftPanel.add(calendarPanel, BorderLayout.SOUTH);
		leftPanel.add(prevNextCreatePanel, BorderLayout.NORTH);
		calendarPanel.setPreferredSize(new Dimension(300,230));
		leftPanel.setVisible(true);
		
		overarchingFrame.setTitle("Your Calendar");
		overarchingFrame.setLayout(new BorderLayout());
		overarchingFrame.add(leftPanel, BorderLayout.WEST);
		overarchingFrame.add(rightPanel, BorderLayout.EAST);
		overarchingFrame.pack();
		overarchingFrame.setVisible(true);
	}
	
	
	public static void addSpaces() {
		for (int i = 0; i < MyCalendarModel.getSpaceCounter(); i++) {  	//add spaces so month starts at the right day
			JButton button = new JButton();
			button.setEnabled(false);
			button.setBorder(BorderFactory.createEmptyBorder());
			calendarPanel.add(button);
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
