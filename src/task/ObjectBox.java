package task5;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class ObjectBox extends JComboBox{
	private StateManager stateManager;

	public ObjectBox(StateManager stateManager) {

		addItem(new Obj(new ImageIcon("./img/rect_icon.png"), 0));
		addItem(new Obj(new ImageIcon("./img/oval_icon.png"), 1));
		addItem(new Obj(new ImageIcon("./img/oct_icon.png"), 2));
		addItem(new Obj(new ImageIcon("./img/tri_icon.png"), 3));
		addItem(new Obj(new ImageIcon("./img/ponde_icon.png"), 4));

		MyCellRenderer renderer = new MyCellRenderer();
	    setRenderer(renderer);

		addActionListener(new ObjectListener());
		this.stateManager = stateManager;
	}
	class Obj{
		private int flag;
		private Icon icon;

		public Obj(Icon icon, int flag) {
			setIcon(icon);
			setFlag(flag);
		}
		public int getFlag() {
			return flag;
		}
		public Icon getIcon() {
			return icon;
		}
		public void setFlag(int flag) {
			this.flag = flag;
		}
		public void setIcon(Icon icon) {
			this.icon = icon;
		}
	}
	class ObjectListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int flag;
			JComboBox jcmbType = (JComboBox)e.getSource();
			Obj obj = (Obj)jcmbType.getSelectedItem();
			flag = obj.getFlag();
			switch(flag) {
			case 0: // Rectangle
				stateManager.setState(new RectState(stateManager));
				break;
			case 1: // Oval
				stateManager.setState(new OvalState(stateManager));
				break;
			case 2: // Octagonal
				stateManager.setState(new OctagonalState(stateManager));
				break;
			case 3: // Triangle
				stateManager.setState(new TriangleState(stateManager));
				break;
			case 4: // Pondering
				stateManager.setState(new PonderingState(stateManager));
				break;
			}
		}
	}
	class MyCellRenderer extends JLabel implements ListCellRenderer{
	    MyCellRenderer(){
	      setOpaque(true);
	    }

	    public Component getListCellRendererComponent(
	            JList list,
	            Object value,
	            int index,
	            boolean isSelected,
	            boolean cellHasFocus){

	      Obj data = (Obj)value;
	      setIcon(data.getIcon());
	      setForeground(Color.white);

	      return this;
	    }
	  }
	class RectListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new RectState(stateManager));
		}
	}
	class OvalListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new OvalState(stateManager));
		}
	}
	class OctagonalListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new OctagonalState(stateManager));
		}
	}
	class TriangleListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new TriangleState(stateManager));
		}
	}
	class PonderingListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new PonderingState(stateManager));
		}
	}
}
