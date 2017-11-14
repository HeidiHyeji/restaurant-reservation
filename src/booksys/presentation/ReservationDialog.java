/*
 * Restaurant Booking System: example code to accompany
 *
 * "Practical Object-oriented Design with UML"
 * Mark Priestley
 * McGraw-Hill (2004)
 */

package booksys.presentation;

import booksys.application.domain.Reservation;

import java.awt.*;//배치관리자
import java.awt.event.*;

import javax.swing.*;

class ReservationDialog extends BookingDialog {
	protected JTextField name;
	protected JTextField phone;
	protected JLabel nameLabel;
	protected JLabel phoneLabel;

	ReservationDialog(JFrame owner, String title) {
		this(owner, title, null);
	}

	// This constructor initializes fields with data from an existing booking.
	// This is useful for completing Exercise 7.6.

	/**
	 * @wbp.parser.constructor
	 */
	ReservationDialog(JFrame owner, String title, Reservation r) {
		super(owner, title, r);

		nameLabel = new JLabel("Name:", JLabel.RIGHT);
		name = new JTextField(10);
		if (r != null) {
			name.setText(r.getCustomer().getName());
		}

		phoneLabel = new JLabel("Phone no:", JLabel.RIGHT);
		phone = new JTextField(15);
		if (r != null) {
			phone.setText(r.getCustomer().getPhoneNumber());
		}

		// Lay out components in dialog

		getContentPane().setLayout(new GridLayout(6, 1));
		setSize(200, 400);
		JPanel[]panel=new JPanel[6];
		for(int i=0;i<panel.length;i++)
			panel[i]=new JPanel();
		panel[0].add(timeLabel);
		panel[0].add(time);
		getContentPane().add(panel[0]);
		//getContentPane().add(timeLabel);
		//getContentPane().add(time);
		panel[1].add(nameLabel);
		panel[1].add(name);
		getContentPane().add(panel[1]);
		//getContentPane().add(nameLabel);
		//getContentPane().add(name);
		panel[2].add(phoneLabel);
		panel[2].add(phone);
		getContentPane().add(panel[2]);
		//getContentPane().add(phoneLabel);
		//getContentPane().add(phone);
		panel[3].add(coversLabel);
		panel[3].add(covers);
		getContentPane().add(panel[3]);
		//getContentPane().add(coversLabel);
		//getContentPane().add(covers);
		panel[4].add(tableNumberLabel);
		panel[4].add(tableNumber);
		getContentPane().add(panel[4]);
		//getContentPane().add(tableNumberLabel);
		//getContentPane().add(tableNumber);
		panel[5].add(ok);
		panel[5].add(cancel);
		getContentPane().add(panel[5]);
		//getContentPane().add(ok);
		//getContentPane().add(cancel);

		// pack() ;
	}

	String getCustomerName() {
		return name.getText();
	}

	String getPhoneNumber() {
		return phone.getText();
	}
}
