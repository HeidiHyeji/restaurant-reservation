/*
 * Restaurant Booking System: example code to accompany
 *
 * "Practical Object-oriented Design with UML"
 * Mark Priestley
 * McGraw-Hill (2004)
 */

package booksys.presentation;

import java.awt.*;//배치관리자
import java.awt.event.*;
import java.sql.Date;

import javax.swing.*;

class DateDialog extends JDialog {
	JTextField tf;
	boolean confirmed;
	private JLabel promptLabel;
	private JButton confirm;
	private JButton cancel;

	// 사이즈랑 위치는 안정해줌??? 왜? 기본으로 됨?
	DateDialog(JFrame owner, String title) {
		super(owner, title, true);// 모달타입->이 다이얼로그가 열리면 다른 창에서는 입력받을 수 없음

		/*
		 * //? addWindowListener(new WindowAdapter() { 
		 * public void windowClosing(WindowEvent e) { 
		 * DateDialog.this.dispose() ; } }) ;
		 */
		promptLabel = new JLabel("Enter date:", JLabel.RIGHT);
		tf = new JTextField("YYYY-MM-DD", 10);
		confirm = new JButton("Ok");
		cancel = new JButton("Cancel");

		getContentPane().setLayout(new FlowLayout());
		setSize(203,170);
		
		getContentPane().add(promptLabel);
		getContentPane().add(tf);
		getContentPane().add(confirm);
		getContentPane().add(cancel);
		
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmed = true;
				// DateDialog.this.setVisible(false);
				setVisible(false);
			}
		});

		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmed = false;
				// DateDialog.this.setVisible(false);
				setVisible(false);
			}
		});
	}

	boolean isConfirmed() {
		return confirmed;
	}

	Date getDate() {
		return Date.valueOf(tf.getText());// 텍스트 필드에 쓴 날짜를 어떻게 하는것인가
	}
}
