package task5;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

public class LineWidthBox extends JComboBox{
	private StateManager stateManager;

	public LineWidthBox(StateManager stateManager) {
		addItem(1);
		addItem(2);
		addItem(4);
		addItem(8);
		addItem(12);
		addItem(16);
		addItem(20);
		addItem(24);
		addItem(28);
		addItem(36);
		addItem(48);
		addItem(64);

		addActionListener(new LineWidthListener());
		this.stateManager = stateManager;
	}

	class LineWidthListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JComboBox jcmbType = (JComboBox)e.getSource();
			int cmbType = (int)jcmbType.getSelectedItem();
			float lineWidth = (float)cmbType;
			stateManager.setLineWidth(lineWidth);
			stateManager.getCanvas().getMediator().setLineWidth(lineWidth);
		}
	}
}