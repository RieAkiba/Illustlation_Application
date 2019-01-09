package task5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class OctagonalButton extends JButton{
	private StateManager stateManager;

	public OctagonalButton(StateManager stateManager) {
		super("Octagonal");

		addActionListener(new OctagonalListener());

		this.stateManager = stateManager;
	}

	class OctagonalListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new OctagonalState(stateManager));
		}
	}

}
