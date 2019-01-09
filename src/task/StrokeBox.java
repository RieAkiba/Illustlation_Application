package task5;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

public class StrokeBox extends JComboBox{
	private StateManager stateManager;
	private String[] strokeType = {"solid", "dashed", "dotted"};

	public StrokeBox(StateManager stateManager) {
		addItem("solid");
		addItem("dashed");
		addItem("dotted");

		addActionListener(new StrokeListener());
		this.stateManager = stateManager;
	}

	class StrokeListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JComboBox jcmbType = (JComboBox)e.getSource();
			String cmbType = (String)jcmbType.getSelectedItem();
			String type = (String)jcmbType.getSelectedItem();
			stateManager.setStrokeTyoe(type);
			stateManager.getCanvas().getMediator().setStroke(type);
		}
	}
}
