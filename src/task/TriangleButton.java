package task5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class TriangleButton extends JButton{
	private StateManager stateManager;

	public TriangleButton(StateManager stateManager) {
		super("Triangle");

		addActionListener(new TriangleListener());

		this.stateManager = stateManager;
	}

	class TriangleListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new TriangleState(stateManager));
		}
	}

}
