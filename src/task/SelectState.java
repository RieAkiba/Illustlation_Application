package task5;

import java.awt.Cursor;

public class SelectState extends State{
	private StateManager stateManager;
	private int x, y;
	private boolean multSelectMode, isSelected;

	public SelectState(StateManager stateManager) {
		this.stateManager = stateManager;
		multSelectMode = false;
		isSelected = !stateManager.getCanvas().getMediator().isEmpty();
	}

	public void mouseDown(int x, int y) {
		if(!isSelected) {
			stateManager.getCanvas().getMediator().setSelected(x, y);
			multSelectMode = stateManager.getCanvas().getMediator().isEmpty();
			if(multSelectMode) {
				stateManager.getCanvas().getMediator().addSelectRect(x, y);
			}else {
				// 一つ選択
				isSelected = true;
			}
		}else {// 何かを選んでいる時
			if(stateManager.getCanvas().getMediator().contains(x, y)) {
				// 選択中の図形上でクリックしたら
			}else {
				isSelected = false;
				stateManager.getCanvas().getMediator().setSelected(x, y);
				multSelectMode = stateManager.getCanvas().getMediator().isEmpty();
				if(multSelectMode) {
					stateManager.getCanvas().getMediator().addSelectRect(x, y);
				}else {
					// 一つ選択
					isSelected = true;
				}
			}
		}
		this.x = x;
		this.y = y;
	}

	public void mouseUp(int x, int y) {
		if(multSelectMode && !isSelected) {
			stateManager.getCanvas().getMediator().setMultSelected();
			isSelected = !stateManager.getCanvas().getMediator().isEmpty();
			if(!isSelected) {
				multSelectMode =false;
			}
		} else {
			stateManager.getCanvas().getMediator().setRegions();
			stateManager.getCanvas().getMediator().record();
		}
	}

	public void mouseDrag(int x, int y) {
		int dx = x - this.x;
		int dy = y - this.y;

//		System.out.println(dx+","+dy);
		if(multSelectMode && !isSelected) {
			stateManager.getCanvas().getMediator().updateSelectRect(dx,dy);
		}else {
			// multSelect で何かを選んでいる時も
			stateManager.getCanvas().getMediator().move(dx, dy);
			this.x = x;
			this.y = y;
		}
	}
	public void mouseMove(int x, int y) {
		switch(stateManager.getCanvas().getMediator().cursorCheck(x, y)) {
		case 0: //
			stateManager.getCanvas().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			break;
		case 1: // 選択している図形の上
			stateManager.getCanvas().setCursor(new Cursor(Cursor.HAND_CURSOR));
			break;
		case 2:
			break;
		}
	}
	public boolean getMult() {
		return multSelectMode;
	}
}
