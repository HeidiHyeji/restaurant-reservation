package booksys.presentation;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

class ConfirmDialog extends JDialog {
	protected JLabel messageLabel;
	protected JLabel blankLabel;
	protected boolean confirmed;
	protected JButton confirm;
	protected JButton cancel;

	ConfirmDialog(JFrame owner, String message, boolean choice) {
		super(owner, "Warning!", true); // 오류잡아주는 다이얼로그

		/*
		 * addWindowListener(new WindowAdapter() { //x누르는거 같은데.. public void
		 * windowClosing(WindowEvent e) { confirmed = false ;
		 * ConfirmDialog.this.hide() ; } }) ;
		 */

		getContentPane().setLayout(new GridLayout(0, 2));
		setSize(232,129);

		messageLabel = new JLabel(message, JLabel.RIGHT);
		blankLabel = new JLabel("", JLabel.RIGHT);
		confirm = new JButton("Ok");
		cancel = new JButton("Cancel");

		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmed = true;
				// ConfirmDialog.this.hide() ;
				setVisible(false);
			}
		});

		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmed = false;
				// ConfirmDialog.this.hide() ;
				setVisible(false);
			}
		});
	
		getContentPane().add(messageLabel);
		getContentPane().add(blankLabel);
		getContentPane().add(confirm);
		if (choice) {
			getContentPane().add(cancel);
		}
		// pack() ;
	}

	boolean isConfirmed() {
		return confirmed;
	}
}
