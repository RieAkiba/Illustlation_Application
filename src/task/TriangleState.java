package task5;

public class TriangleState extends State{
	private StateManager stateManager;
	private MyTriangle tri;

	public TriangleState(StateManager stateManager) {
		this.stateManager = stateManager;
	}

	public void mouseDown(int x, int y) {
		tri = new MyTriangle(x,y,0,0);
		stateManager.addDrawing(tri);
	}

	public void mouseUp(int x, int y) {
		tri.setSize(x - tri.getX(), y - tri.getY());
		tri.setRegion();
		if(tri.getW() < 10 && tri.getW() > -10 && tri.getH()<10 && tri.getH() >-10) {
			stateManager.removeDrawing();
		}else {
			stateManager.getCanvas().getMediator().record();
		}
	}
	public void mouseDrag(int x, int y) {
		tri.setSize(x - tri.getX(), y - tri.getY());
		stateManager.updateDrawing();
	}
}
