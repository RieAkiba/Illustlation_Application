package task5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class SelectButton extends JButton{
	private StateManager stateManager;

	public SelectButton(StateManager stateManager) {
		super("Select & Move");

		addActionListener(new SelectListener());

		this.stateManager = stateManager;
	}

	class SelectListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new SelectState(stateManager));
		}
	}

}
