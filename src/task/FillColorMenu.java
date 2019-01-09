package task5;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;
import javax.swing.JComboBox;

public class FillColorMenu extends JComboBox{
	private StateManager stateManager;

	public FillColorMenu(StateManager stateManager) {
		addItem(new ColorObject("White", Color.white));
		addItem(new ColorObject("Red", Color.red));
		addItem(new ColorObject("Pink", Color.pink));
		addItem(new ColorObject("Cyan", Color.cyan));
		addItem(new ColorObject("Blue", Color.blue));
		addItem(new ColorObject("Green", Color.green));
		addItem(new ColorObject("Yellow",Color.yellow));
		addItem(new ColorObject("Orange", Color.orange));
		addItem(new ColorObject("Black", Color.black));
		addItem(new ColorObject("Other", null));

		addActionListener(new FillColorListener(this));
		this.stateManager = stateManager;
	}

	class FillColorListener implements ActionListener {
		private FillColorMenu fillColorMenu;
		public FillColorListener(FillColorMenu fillColorMenu) {
			this.fillColorMenu = fillColorMenu;
		}
		public void actionPerformed(ActionEvent e) {
			Color color;
			int r, g, b;
			JComboBox jcmbType = (JComboBox)e.getSource();
			ColorObject colorObj = (ColorObject)jcmbType.getSelectedItem();
			if(colorObj.toString() == "Other") {
				JColorChooser fillColorChooser = new JColorChooser();
				color = fillColorChooser.showDialog(fillColorMenu, "FillColor", Color.white);
				r = color.getRed();
				g = color.getGreen();
				b = color.getBlue();
				if(color != null) {
					stateManager.setFillColor(r,g,b);
					stateManager.getCanvas().getMediator().setFillColor(r,g,b);
				}
			}else {
				color = colorObj.getColor();
				r = color.getRed();
				g = color.getGreen();
				b = color.getBlue();
				stateManager.setFillColor(r,g,b);
				stateManager.getCanvas().getMediator().setFillColor(r,g,b);
			}
		}
	}
}