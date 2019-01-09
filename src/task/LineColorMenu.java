package task5;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;
import javax.swing.JComboBox;

public class LineColorMenu extends JComboBox{
	private StateManager stateManager;

	public LineColorMenu(StateManager stateManager) {
		addItem(new ColorObject("Black", Color.black));
		addItem(new ColorObject("Red", Color.red));
		addItem(new ColorObject("Pink", Color.pink));
		addItem(new ColorObject("Cyan", Color.cyan));
		addItem(new ColorObject("Blue", Color.blue));
		addItem(new ColorObject("Green", Color.green));
		addItem(new ColorObject("Yellow",Color.yellow));
		addItem(new ColorObject("Orange", Color.orange));
		addItem(new ColorObject("White", Color.white));
		addItem(new ColorObject("Other", null));

		addActionListener(new LineColorListener(this));
		this.stateManager = stateManager;
	}

	class LineColorListener implements ActionListener {
		private LineColorMenu lineColorMenu;
		public LineColorListener(LineColorMenu lineColorMenu) {
			this.lineColorMenu = lineColorMenu;
		}
		public void actionPerformed(ActionEvent e) {
			Color color;
			int r, g, b;
			JComboBox jcmbType = (JComboBox)e.getSource();
			ColorObject colorObj = (ColorObject)jcmbType.getSelectedItem();
			if(colorObj.toString() == "Other") {
				JColorChooser lineColorChooser = new JColorChooser();
				color = lineColorChooser.showDialog(lineColorMenu, "LineColor", Color.black);
				r = color.getRed();
				g = color.getGreen();
				b = color.getBlue();
				if(color != null) {
					stateManager.setLineColor(r,g,b);
					stateManager.getCanvas().getMediator().setLineColor(r,g,b);
				}
			}else {
				color = colorObj.getColor();
				r = color.getRed();
				g = color.getGreen();
				b = color.getBlue();
				stateManager.setLineColor(r,g,b);
				stateManager.getCanvas().getMediator().setLineColor(r,g,b);
			}
		}
	}
}