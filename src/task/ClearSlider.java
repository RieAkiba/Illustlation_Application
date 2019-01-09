package task5;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ClearSlider extends JSlider{
	private StateManager stateManager;

	public ClearSlider(StateManager stateManager) {
		super(10,100);
		setValue(100);

		addChangeListener(new ClearListener(this));
		this.stateManager = stateManager;
	}

	class ClearListener implements ChangeListener {
		private ClearSlider cs;

		public ClearListener(ClearSlider cs) {
			this.cs = cs;
		}

		public void stateChanged(ChangeEvent e) {
			int parcent = cs.getValue();
			int clearDegree = (255 * parcent) / 100;
			stateManager.setClearDegree(clearDegree);
			stateManager.getCanvas().getMediator().setClearDegree(clearDegree);
			if(!cs.getValueIsAdjusting()) {
				stateManager.getCanvas().getMediator().record();
			}
		}
	}
}