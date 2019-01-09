package task5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class RectButton extends JButton{
	private StateManager stateManager;

	public RectButton(StateManager stateManager) {
		super("Rectangle");

		addActionListener(new RectListener());

		this.stateManager = stateManager;
	}

	class RectListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new RectState(stateManager));
		}
	}

}
