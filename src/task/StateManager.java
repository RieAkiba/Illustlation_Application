package task5;

import java.awt.Color;

public class StateManager {
	private State state;
	private MyCanvas canvas;
	private boolean isShadow;
	private String strokeType;
	private Color fillColor;
	private int fR, fG, fB;
	private Color lineColor;
	private int lR, lG, lB;
	private float lineWidth;
	private int clearDegree;
	private int fontSize;

	public StateManager() {
		state = new RectState(this);
		isShadow = false;
		strokeType = "solid";
		fillColor = Color.white;
		fR = fG = fB = 255;
		lineColor = Color.black;
		lR = lG = lB = 0;
		lineWidth = 1;
		clearDegree = 255;
		fontSize = 12;
	}

	public StateManager(MyCanvas c) {
		this();
		this.canvas = c;
	}

	public MyCanvas getCanvas() {
		return this.canvas;
	}

	public void addDrawing(MyDrawing d) {
		d.setShadow(isShadow);
		d.setStroke(strokeType);
		d.setClearDegree(clearDegree);
		d.setFillR(fR);
		d.setFillG(fG);
		d.setFillB(fB);
		d.setFillColor();
		d.setLineR(lR);
		d.setLineG(lG);
		d.setLineB(lB);
		d.setLineColor();
		d.setLineWidth(lineWidth);
		d.setFontSize(fontSize);
		canvas.getMediator().addDrawing(d);
		// キャンバスの再描画命令
		canvas.repaint();
	}

	public void removeDrawing() {
		canvas.getMediator().removeDrawing();
		// キャンバスの再描画命令
		canvas.repaint();
	}

	public void updateDrawing() {
		// 更新処理
		// キャンバスの再描画命令
		canvas.repaint();
	}

	// FileMenu
	// 新しいファイルを開く時
	public void newFile() {
		canvas.getMediator().newFile();
	}
	// すでにあるファイルを読み込む
	public void Open() {
		canvas.getMediator().open();
	}
	// 上書き保存
	public void save() {
		canvas.getMediator().save();
	}
	// 名前をつけて保存
	public void saveAs() {
		canvas.getMediator().saveAs();
	}

	// EditMenu
	// Undo
	public void undo() {
		canvas.getMediator().undo();
	}
	// Redo
	public void redo() {
		canvas.getMediator().redo();
	}
	// Cut
	public void cut() {
		canvas.getMediator().cut();
	}
	// Copy
	public void copy() {
		canvas.getMediator().copy();
	}
	// Paste
	public void paste() {
		canvas.getMediator().paste();
	}
	// Delete
	public void delete() {
		canvas.getMediator().delete();
	}

	// state のメソッド
	public void setState(State state) {
		this.state = state;
		if(state instanceof SelectState) {
			canvas.getMediator().setRegions();
		}
	}
	public State getState() {
		return state;
	}

	// shadow のメソッド
	public void setShadow(boolean b) {
		this.isShadow = b;
	}
	public boolean getIsShadow() {
		return this.isShadow;
	}

	// stroke のメソッド
	public void setStrokeTyoe(String type) {
		this.strokeType = type;
	}
	public String getStrokeType() {
		return this.strokeType;
	}

	// lineWidth のメソッド
	public void setLineWidth(float lineWidth) {
		this.lineWidth = lineWidth;
	}
	public float getLineWidth() {
		return lineWidth;
	}

	// fontSize のメソッド
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
	public int getFontSize() {
		return fontSize;
	}

	// color のメソッド
	public void setFillColor(int r, int g, int b) {
		setFillR(r);
		setFillG(g);
		setFillB(b);
	}
	public Color getFillColor() {
		return this.fillColor;
	}

	public void setFillR(int fR) {
		this.fR = fR;
	}
	public void setFillG(int fG) {
		this.fG = fG;
	}
	public void setFillB(int fB) {
		this.fB = fB;
	}
	public int getFillR() {
		return fR;
	}
	public int getFillG() {
		return fG;
	}
	public int getFillB() {
		return fB;
	}

	public void setLineColor(int r, int g, int b) {
		setLineR(r);
		setLineG(g);
		setLineB(b);
	}
	public Color getLineColor() {
		return this.lineColor;
	}

	public void setLineR(int lR) {
		this.lR = lR;
	}
	public void setLineG(int lG) {
		this.lG = lG;
	}
	public void setLineB(int lB) {
		this.lB = lB;
	}
	public int getLineR() {
		return lR;
	}
	public int getLineG() {
		return lG;
	}
	public int getLineB() {
		return lB;
	}

	// 透明度のメソッド
	public void setClearDegree(int clearDegree) {
		this.clearDegree = clearDegree;
	}
	public int getClearDegree() {
		return clearDegree;
	}

	// マウスのメソッド
	public void mouseDown(int x, int y) {
		this.state.mouseDown(x, y);
	}
	public void mouseUp(int x, int y) {
		this.state.mouseUp(x,y);
	}
	public void mouseDrag(int x, int y) {
		this.state.mouseDrag(x,y);
	}
	public void mouseMove(int x, int y) {
		this.state.mouseMove(x,y);
	}

	// キーのメソッド
	public void keyPress(int key, int mod) {
		if(key==8) {
			canvas.getMediator().delete();
		}
		if(mod == 256) {
			switch(key) {
			case 86:
				// ctrl+v ペースト
				canvas.getMediator().paste();
				break;
			case 67:
				// ctrl+c コピー
				canvas.getMediator().copy();
				break;
			case 88:
				// ctrl+x カット
				canvas.getMediator().cut();
				break;
			case 83:
				// ctrl+s セーブ
				canvas.getMediator().save();
				break;
			case 90:
				// ctrl+z undo
				canvas.getMediator().undo();
				break;
			case 82:
				// ctrl+r redo
				canvas.getMediator().redo();
				break;
			}
		}
	}
}