package task5;

public class RectState extends State{
	private StateManager stateManager;
	private MyRectangle rect;

	public RectState(StateManager stateManager) {
		this.stateManager = stateManager;
	}

	public void mouseDown(int x, int y) {
		rect = new MyRectangle(x,y,0,0);
		stateManager.addDrawing(rect);
	}

	public void mouseUp(int x, int y) {
		rect.setSize(x - rect.getX(), y - rect.getY());
		if(rect.getW() < 10 && rect.getW() > -10 && rect.getH()<10 && rect.getH() >-10) {
			stateManager.removeDrawing();
		}else {
			stateManager.getCanvas().getMediator().record();
		}
	}
	public void mouseDrag(int x, int y) {
		rect.setSize(x - rect.getX(), y - rect.getY());
		stateManager.updateDrawing();
	}
}
