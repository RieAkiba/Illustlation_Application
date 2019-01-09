package task5;

public class PonderingState extends State{
	private StateManager stateManager;
	private MyPondering ponde;

	public PonderingState(StateManager stateManager) {
		this.stateManager = stateManager;
	}

	public void mouseDown(int x, int y) {
		ponde = new MyPondering(x,y,0,0);
		stateManager.addDrawing(ponde);
	}

	public void mouseUp(int x, int y) {
		ponde.setSize(x - ponde.getX(), y - ponde.getY());
		ponde.setRegion();
		if(ponde.getW() < 10 && ponde.getW() > -10 && ponde.getH()<10 && ponde.getH() >-10) {
			stateManager.removeDrawing();
		}else {
			stateManager.getCanvas().getMediator().record();
		}
	}
	public void mouseDrag(int x, int y) {
		ponde.setSize(x - ponde.getX(), y - ponde.getY());
		stateManager.updateDrawing();
	}
}
