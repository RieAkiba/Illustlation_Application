package task5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class PonderingButton extends JButton{
	private StateManager stateManager;

	public PonderingButton(StateManager stateManager) {
		super("Pondering");

		addActionListener(new PonderingListener());

		this.stateManager = stateManager;
	}

	class PonderingListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new PonderingState(stateManager));
		}
	}

}
