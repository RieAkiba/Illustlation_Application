package task5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class MyPopup extends JPopupMenu{
	private StateManager stateManager;
	private JMenuItem mostfront, mostback, front, back;

	public MyPopup(StateManager stateManager) {
		this.stateManager = stateManager;
		mostfront = new JMenuItem("最前面に移動");
		mostfront.addActionListener(new LayerListener(0));
		mostback = new JMenuItem("最背面に移動");
		mostback.addActionListener(new LayerListener(1));
		front = new JMenuItem("一つ前面に移動");
		front.addActionListener(new LayerListener(2));
		back = new JMenuItem("一つ背面に移動");
		back.addActionListener(new LayerListener(3));

		add(mostfront);
		add(mostback);
		add(front);
		add(back);
	}

	public void setFalse() {
		if(stateManager.getState() instanceof SelectState) {
			SelectState sState = (SelectState)stateManager.getState();
			boolean mult = sState.getMult();
			mostfront.setEnabled(!mult);
			mostback.setEnabled(!mult);
			front.setEnabled(!mult);
			back.setEnabled(!mult);
		}
		if(stateManager.getCanvas().getMediator().getSelectedDrawings().size() == 0) {
			mostfront.setEnabled(false);
			mostback.setEnabled(false);
			front.setEnabled(false);
			back.setEnabled(false);
		}
	}

	class LayerListener implements ActionListener {
		private int flag;
		public LayerListener(int flag) {
			this.flag = flag;
		}
		public void actionPerformed(ActionEvent e) {
			System.out.println(flag);
			System.out.println(stateManager.getCanvas().getMediator());
			stateManager.getCanvas().getMediator().layerChange(flag);
		}
	}
}
