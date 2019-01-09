package task5;

public class OctagonalState extends State{
	private StateManager stateManager;
	private MyOctagonal oct;

	public OctagonalState(StateManager stateManager) {
		this.stateManager = stateManager;
	}

	public void mouseDown(int x, int y) {
		oct = new MyOctagonal(x,y,0,0);
		stateManager.addDrawing(oct);
	}

	public void mouseUp(int x, int y) {
		oct.setSize(x - oct.getX(), y - oct.getY());
		oct.setRegion();
		if(oct.getW() < 10 && oct.getW() > -10 && oct.getH()<10 && oct.getH() >-10) {
			stateManager.removeDrawing();
		}else{
			stateManager.getCanvas().getMediator().record();
		}
	}
	public void mouseDrag(int x, int y) {
		oct.setSize(x - oct.getX(), y - oct.getY());
		stateManager.updateDrawing();
	}
}
