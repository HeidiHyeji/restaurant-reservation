/*
 * Restaurant Booking System: example code to accompany
 *
 * "Practical Object-oriented Design with UML"
 * Mark Priestley
 * McGraw-Hill (2004)
 */

package booksys.presentation;

import java.awt.event.*;
import javax.swing.*;
import java.awt.BorderLayout;

public class BookingSystemApp extends JFrame {
	private StaffUI ui;

	public BookingSystemApp() {
		setTitle("FUSE Restaurant Booking System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(100,100,770,468);//원래 없었음.
		
		// 메뉴바
		JMenuBar mb = new JMenuBar();

		// 메뉴
		JMenu fileMenu = new JMenu("File");
		JMenu dateMenu = new JMenu("Date");
		JMenu bookingMenu = new JMenu("Booking");

		// 메뉴아이템
		JMenuItem quit = new JMenuItem("Quit");// File메뉴 아이템
		JMenuItem display = new JMenuItem("Display...");// Date메뉴 아이템
		JMenuItem newReservation = new JMenuItem("New Reservation...");
		JMenuItem newWalkIn = new JMenuItem("New Walk-in...");
		JMenuItem cancel = new JMenuItem("Cancel");
		JMenuItem recordArrival = new JMenuItem("Record Arrival");

		//메뉴바에 메뉴넣기
		mb.add(fileMenu);
		mb.add(dateMenu);
		mb.add(bookingMenu);

		//메뉴에 메뉴아이템 넣기
		fileMenu.add(quit);
		dateMenu.add(display);
		bookingMenu.add(newReservation);
		bookingMenu.add(newWalkIn);
		bookingMenu.add(cancel);
		bookingMenu.add(recordArrival);

		//프레임에 메뉴바 넣기
		setJMenuBar(mb);
		
		//팬에 StaffUI넣기
		ui = new StaffUI(this);
		getContentPane().add(ui, BorderLayout.CENTER);

		//this.pack() ; //프레임을 적절한 크기로 만드는 메소드
		this.show() ;

		//액션 리스너
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		display.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ui.displayDate();
			}
		});

		newReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ui.addReservation();
			}
		});

		newWalkIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ui.addWalkIn();
			}
		});

		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ui.cancel();
			}
		});

		recordArrival.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ui.recordArrival();
			}
		});

	}

	public static void main(String args[]) {
		new BookingSystemApp();
	}
}
