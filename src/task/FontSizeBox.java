package task5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

public class FontSizeBox extends JComboBox{
	private StateManager stateManager;

	public FontSizeBox(StateManager stateManager) {
		addItem(12);
		addItem(16);
		addItem(20);
		addItem(24);
		addItem(28);
		addItem(36);
		addItem(48);
		addItem(64);

		addActionListener(new FontSizeListener());
		this.stateManager = stateManager;
	}

	class FontSizeListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JComboBox jcmbType = (JComboBox)e.getSource();
			int cmbType = (int)jcmbType.getSelectedItem();
			int fontSize = (int)cmbType;
			stateManager.setFontSize(fontSize);
			stateManager.getCanvas().getMediator().setFontSize(fontSize);
		}
	}
}
