package task5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class TextInputBox extends JFrame{
	JTextField textField = new JTextField(10);

	public TextInputBox(TextState tstate){
		getContentPane().add(textField);
		textField.setFocusable(true);
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// テキストボックス上でリターンを押すなどした時
				tstate.myNotify(textField);
			}
		});
	}

}
