/*
 * Restaurant Booking System: example code to accompany
 *
 * "Practical Object-oriented Design with UML"
 * Mark Priestley
 * McGraw-Hill (2004)
 */

package booksys.presentation;

import booksys.application.domain.WalkIn;

import java.awt.*; //배치관리자

import javax.swing.JFrame;
import javax.swing.JPanel;

class WalkInDialog extends BookingDialog {
	WalkInDialog(JFrame owner, String title) {
		this(owner, title, null);
	}

	// This constructor initializes fields with data from an existing booking.
	// This is useful for completing Exercise 7.6.

	/**
	 * @wbp.parser.constructor
	 */
	WalkInDialog(JFrame owner, String title, WalkIn w) {
		super(owner, title, w);

		getContentPane().setLayout(new GridLayout(4, 1));
		setSize(200,300);
		
		JPanel[] panel=new JPanel[4];
		for(int i=0;i<panel.length;i++)
			panel[i]=new JPanel();
		
		panel[0].add(tableNumberLabel);
		panel[0].add(tableNumber);
		getContentPane().add(panel[0]);
		//getContentPane().add(tableNumberLabel);
		//getContentPane().add(tableNumber);
		panel[1].add(coversLabel);
		panel[1].add(covers);
		getContentPane().add(panel[1]);
		//getContentPane().add(coversLabel);
		//getContentPane().add(covers);
		panel[2].add(timeLabel);
		panel[2].add(time);
		getContentPane().add(panel[2]);
		//getContentPane().add(timeLabel);
		//getContentPane().add(time);
		panel[3].add(ok);
		panel[3].add(cancel);
		getContentPane().add(panel[3]);
		//getContentPane().add(ok);
		//getContentPane().add(cancel);

		//pack();
	}
}
