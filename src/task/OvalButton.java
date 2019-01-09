package task5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class OvalButton extends JButton{
	private StateManager stateManager;

	public OvalButton(StateManager stateManager) {
		super("Oval");

		addActionListener(new OvalListener());

		this.stateManager = stateManager;
	}

	class OvalListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new OvalState(stateManager));
		}
	}

}
