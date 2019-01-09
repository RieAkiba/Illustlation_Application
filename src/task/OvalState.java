package task5;

public class OvalState extends State{
	private StateManager stateManager;
	private MyOval oval;

	public OvalState(StateManager stateManager) {
		this.stateManager = stateManager;
	}

	public void mouseDown(int x, int y) {
		oval = new MyOval(x,y,0,0);
		stateManager.addDrawing(oval);
	}

	public void mouseUp(int x, int y) {
		oval.setSize(x - oval.getX(), y - oval.getY());
		oval.setRegion();
		if(oval.getW() < 10 && oval.getW() > -10 && oval.getH()<10 && oval.getH() >-10) {
			stateManager.removeDrawing();
		}else {
			stateManager.getCanvas().getMediator().record();
		}
	}
	public void mouseDrag(int x, int y) {
		oval.setSize(x - oval.getX(), y - oval.getY());
		stateManager.updateDrawing();
	}
}
