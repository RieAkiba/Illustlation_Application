package task5;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;

public class ShadowBox extends JCheckBox{
	private StateManager stateManager;

	public ShadowBox(StateManager stateManager) {
		super("Shadow");

		addItemListener(new ShadowListener());
		this.stateManager = stateManager;
	}

	class ShadowListener implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			int state = e.getStateChange();
			if(state == ItemEvent.SELECTED) {
				stateManager.setShadow(true);
				stateManager.getCanvas().getMediator().setShadow(true);
			}else {
				stateManager.setShadow(false);
				stateManager.getCanvas().getMediator().setShadow(false);
			}
		}
	}

}
