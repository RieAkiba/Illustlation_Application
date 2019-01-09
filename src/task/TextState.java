package task5;

import javax.swing.JTextField;

public class TextState extends State{
	private StateManager stateManager;
	private TextInputBox tiBox;
	private int x,y;

	public TextState(StateManager stateManager) {
		this.stateManager = stateManager;
		tiBox = null;
	}
	public void mouseDown(int x, int y) {
		tiBox = new TextInputBox(this);
		tiBox.setVisible(true);
		tiBox.setBounds(x,y+80,150,80);
		this.x = x;
		this.y = y;
	}
	public void mouseUp(int x, int y) {
	}
	public void mouseDrag(int x, int y) {
	}
	public void myNotify(JTextField tfield) {
		tiBox.dispose();
		MyTextBox text = new MyTextBox(tfield.getText(), x, y);
		stateManager.addDrawing(text);
	}
}
