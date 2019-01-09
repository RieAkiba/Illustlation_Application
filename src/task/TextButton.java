package task5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class TextButton extends JButton{
	private StateManager stateManager;

	public TextButton(StateManager stateManager) {
		super("Text");

		addActionListener(new TextListener());

		this.stateManager = stateManager;
	}

	class TextListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new TextState(stateManager));
		}
	}

}
