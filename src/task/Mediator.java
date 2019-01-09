package task5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Mediator {
	private MyCanvas canvas;
	private Vector<MyDrawing> drawings;
	private Vector<Vector<MyDrawing>> history;
	private Vector<MyDrawing> selectedDrawings;
	private Vector<MyDrawing> buffers; // Cut, Copy バッファ
	private Vector<MyDrawing> layerBuffers; // layerChenge用バッファ
	private MyRectangle selectRect; //選択用領域のRectangleをもつ
	private File theFile;
	private int unredo; // 現在のhistoryの参照先

	public Mediator(MyCanvas canvas) {
		this.canvas = canvas;
		drawings = new Vector<MyDrawing>();
		history = new Vector<Vector<MyDrawing>>();
		selectedDrawings = new Vector<MyDrawing>();
		buffers = new Vector<MyDrawing>();
		layerBuffers  = new Vector<MyDrawing>();
		selectRect = null;
		theFile = new File("No Name");
		unredo = 0;
	}

	public Enumeration<MyDrawing> drawingsElements(){
		return drawings.elements();
	}

	public void addDrawing(MyDrawing d) {
		drawings.addElement(d);
		setUnSelected();
		setSelectedDrawing(d);
		setRegions();
		repaint();
	}
	public void addDrawingAt(MyDrawing d, int index) {
		drawings.add(index, d);
		setUnSelected();
		setSelectedDrawing(d);
		setRegions();
		repaint();
	}

	public void removeDrawing() {
		for(int i = 0; i < selectedDrawings.size(); i ++) {
			drawings.removeElement(selectedDrawings.elementAt(i));
		}
		repaint();
	}

	public void clearDrawings() {
		drawings.removeAllElements();
		selectedDrawings.removeAllElements();
		repaint();
	}

	public Vector<MyDrawing> getSelectedDrawings() {
		return selectedDrawings;
	}

	public boolean isEmpty() {
		return selectedDrawings.isEmpty();
	}

	public void setSelectedDrawing(MyDrawing d) {
		d.setSelected(true);
		selectedDrawings.addElement(d);
		repaint();
	}

	public void move(int dx, int dy) {
		for(int i = 0; i < selectedDrawings.size(); i ++) {
			selectedDrawings.elementAt(i).move(dx, dy);
		}
		repaint();
	}

	// color のメソッド
	public void setFillColor(int r, int g, int b) {
		for(int i = 0; i < selectedDrawings.size(); i ++) {
			selectedDrawings.elementAt(i).setFillR(r);
			selectedDrawings.elementAt(i).setFillG(g);
			selectedDrawings.elementAt(i).setFillB(b);
			selectedDrawings.elementAt(i).setFillColor();
		}
		record();
		repaint();
	}
	public void setLineColor(int r, int g, int b) {
		for(int i = 0; i < selectedDrawings.size(); i ++) {
			selectedDrawings.elementAt(i).setLineR(r);
			selectedDrawings.elementAt(i).setLineG(g);
			selectedDrawings.elementAt(i).setLineB(b);
			selectedDrawings.elementAt(i).setLineColor();
		}
		record();
		repaint();
	}

	// clearDegree のメソッド
	public void setClearDegree(int clearDegree) {
		for(int i = 0; i < selectedDrawings.size(); i ++) {
			selectedDrawings.elementAt(i).setClearDegree(clearDegree);
			selectedDrawings.elementAt(i).setFillColor();
			selectedDrawings.elementAt(i).setLineColor();
		}
		repaint();
	}

	// lineWidth のメソッド
	public void setLineWidth(float lineWidth) {
		for(int i = 0; i < selectedDrawings.size(); i ++) {
			selectedDrawings.elementAt(i).setLineWidth(lineWidth);
		}
		record();
		repaint();
	}

	// fontSize のメソッド
	public void setFontSize(int fontSize) {
		for(int i = 0; i < selectedDrawings.size(); i ++) {
			selectedDrawings.elementAt(i).setFontSize(fontSize);
		}
		record();
		repaint();
	}
	// stroke のメソッド
	public void setStroke(String type) {
		for(int i = 0; i < selectedDrawings.size(); i ++) {
			selectedDrawings.elementAt(i).setStroke(type);
		}
		record();
		repaint();
	}

	// shadow のメソッド
	public void setShadow(boolean b) {
		for(int i = 0; i < selectedDrawings.size(); i ++) {
			selectedDrawings.elementAt(i).setShadow(b);
		}
		record();
		repaint();
	}

	// theFile のメソッド
	public String getFileName() {
		return theFile.getName();
	}

	public void repaint() {
		// 再描画
		canvas.repaint();
	}

	// クリック用　選択非選択
	public void setSelected(int x, int y) {
		setUnSelected();
		for(int i = drawings.size(); i>0 ; i--) {
			if(drawings.elementAt(i-1).contains(x,y)) {
				setSelectedDrawing(drawings.elementAt(i-1));
				break;
			}
		}
		repaint();
	}

	// 今選択しているものの上でクリックしたかどうか
	public boolean contains(int x, int y) {
		boolean isContained = false;
		for(int i = 0; i < selectedDrawings.size(); i++) {
			if(selectedDrawings.elementAt(i).contains(x, y)) {
				isContained = true;
			}
		}
		return isContained;
	}

	// 図形以外をクリックした時にselectRectを作る
	public void addSelectRect(int x, int y) {
		selectRect = new MyRectangle(x,y,0,0);
		selectRect.setStroke("dashed");
		selectRect.setNotFill(true);
		drawings.addElement(selectRect);
		repaint();
	}

	// マウスアップした時に領域同士の当たり判定を見る
	public void setMultSelected() {
		setUnSelected();

		int x = selectRect.getX();
		int y = selectRect.getY();
		int w = selectRect.getW();
		int h = selectRect.getH();
		// 高さや横幅が負の時のための処理
		if( w < 0 ) {
			x += w;
			w *= -1;
		}
		if (h < 0) {
			y += h;
			h *= -1;
		}
		for(int i = 0; i<drawings.size()-1; i++) {
			if(drawings.elementAt(i).intersects(x,y,w,h)) {
				setSelectedDrawing(drawings.elementAt(i));
			}
		}
		drawings.removeElement(selectRect);
		selectRect = null;
		repaint();
	}

	//ドラッグした時選択用領域の大きさを更新する
	public void updateSelectRect(int w, int h) {
		selectRect.setSize(w,h);
		repaint();
	}

	public void setUnSelected() {
		for(int i = 0; i < drawings.size(); i++) {
			drawings.elementAt(i).setSelected(false);
		}
		selectedDrawings.removeAllElements();
	}

	public void setRegions() {
		for(int i = 0; i<drawings.size(); i++) {
			drawings.elementAt(i).setRegion();
		}
		repaint();
	}

	public void clearBuffers() {
		buffers.removeAllElements();
	}
	public void delete() {
		removeDrawing();
		record();
	}
	// コピー＆カット＆ペースト
	public void copy() {
		// バッファをクリアする
		clearBuffers();
		for(int i = 0; i < selectedDrawings.size(); i ++) {
			buffers.addElement(selectedDrawings.elementAt(i).clone());
		}
	}
	public void cut() {
		// バッファをクリアする
		clearBuffers();
		for(int i = 0; i < selectedDrawings.size(); i ++) {
			buffers.addElement(selectedDrawings.elementAt(i).clone());
		}
		// drawings から selectedDrawing を削除
		removeDrawing();
	}
	public void paste() {
		for(int i = 0; i < buffers.size(); i ++) {
			MyDrawing clone = buffers.elementAt(i).clone();
			clone.setLocation(clone.getX() + 30, clone.getY() + 30);
			clone.setRegion();
			addDrawing(clone);
		}
		setUnSelected();
		int dSize = drawings.size();
		for(int i = 0; i < buffers.size(); i++) {
			setSelectedDrawing(drawings.elementAt(dSize-i-1));
		}
		record();
		repaint();
	}

	// ファイル操作
	// 新規ファイル
	public void newFile() {
		if(drawings.isEmpty()) {
			clearDrawings();
		}else {
			// 警告を出す
			int option = JOptionPane.showConfirmDialog(canvas,"保存しますか？");
			if(option == JOptionPane.YES_OPTION) {
				// 保存して新規ファイルを開く（初期化する）
				saveAs();
				clearHistory();
				clearDrawings();
				theFile = new File("No Name");
			}else if(option == JOptionPane.NO_OPTION) {
				// 保存せず新規ファイルを開く
				clearHistory();
				clearDrawings();
				theFile = new File("No Name");
			}else if(option == JOptionPane.CANCEL_OPTION) {
				// 何もしない
			}
		}
	}
	// 上書き保存
	public void save() {
		if(theFile.getName() != "No Name") {
			fileOutput(theFile);
			System.out.println("save!");
		}else {
			saveAs();
		}
	}
	// 名前をつけて保存
	public void saveAs() {
		JFileChooser fileChooser = new JFileChooser("../");
		if(theFile.getName() != "No Name") {
			fileChooser.setSelectedFile(theFile);
		}
		int selected = fileChooser.showSaveDialog(canvas);
		if(selected == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			fileOutput(file);
			System.out.println("save!");
		}else if(selected == JFileChooser.CANCEL_OPTION){
			// 何もしない
		}else if(selected == JFileChooser.ERROR_OPTION) {
			JOptionPane.showMessageDialog(canvas, "エラーがありました。");
		}
	}
	// 開く
	public void open() {
		if(drawings.isEmpty()) {
			fileChoose();
		}else {
			// 警告を出す
			int option = JOptionPane.showConfirmDialog(canvas,"保存しますか？");
			if(option == JOptionPane.YES_OPTION) {
				// 保存してファイルを選択
				saveAs();
				fileChoose();
			}else if(option == JOptionPane.NO_OPTION) {
				// 保存せずファイルを選択
				fileChoose();
			}else if(option == JOptionPane.CANCEL_OPTION) {
				// 何もしない
			}
		}
	}
	public void fileChoose() {
		JFileChooser fileChooser = new JFileChooser("../");
		int selected = fileChooser.showOpenDialog(canvas);
		if(selected == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			fileInput(file);
		}else if(selected == JFileChooser.CANCEL_OPTION){
			// 何もしない
		}else if(selected == JFileChooser.ERROR_OPTION) {
			JOptionPane.showMessageDialog(canvas, "エラーがありました。");
		}
	}

	// 読み込み
	public void fileInput(File file) {
		try {
			clearDrawings();
			FileInputStream fin = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fin);

			clearHistory();
			drawings = (Vector<MyDrawing>)in.readObject();

			in.close();
			fin.close();
			theFile = file;
			setRegions();
			repaint();
		}catch(Exception ex) {
			System.out.println(ex);
			System.out.println("input error");
		}
	}
	// 書き出し
	public void fileOutput(File file) {
		try {
			FileOutputStream fout = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fout);

			out.writeObject(drawings);
			out.flush();

			out.close();
			fout.close();
			theFile = file;
		}catch(Exception ex) {
			System.out.println(ex);
			System.out.println("output error");
		}
	}
	// historyに対する操作
	// history を消去する
	public void clearHistory() {
		history.removeAllElements();
	}
	// drawingsのcloneを作る
	public Vector<MyDrawing> myClone() {
		Vector<MyDrawing> clone = new Vector<MyDrawing>();
		for(int i = 0; i<drawings.size();i++) {
			clone.addElement(drawings.elementAt(i).clone());
		}
		return clone;
	}
	// 現在参照しているhistoryの次に現在のdrawingsを追加する
	public void record() {
		if(unredo != 0) {
			int size = history.size();
			for(int i = size-1; i > size-1+unredo; i--) {
				history.removeElementAt(i);
			}
			unredo = 0;
		}
		if(history.size() >= 30) {
			history.removeElementAt(0);
		}
		history.addElement(myClone());
	}
	// 戻る
	public void undo() {
		if(history.size()-1+unredo >= 1) {
			unredo -= 1;
		}
		if(history.size() > 0) {
			clearDrawings();
			for(int i = 0; i < history.elementAt(history.size()-1+unredo).size();i++) {
				drawings.addElement(history.elementAt(history.size()-1+unredo).elementAt(i).clone());
			}
			for(int i = 0; i< drawings.size(); i++) {
				if(drawings.elementAt(i).getSelected()) {
					selectedDrawings.addElement(drawings.elementAt(i));
				}
			}
			repaint();
		}
	}
	// 進む
	public void redo() {
		if(unredo < 0) {
			unredo += 1;
		}
		if(history.size() > 0) {
			clearDrawings();
			for(int i = 0; i < history.elementAt(history.size()-1+unredo).size();i++) {
				drawings.addElement(history.elementAt(history.size()-1+unredo).elementAt(i).clone());
			}
			for(int i = 0; i< drawings.size(); i++) {
				if(drawings.elementAt(i).getSelected()) {
					selectedDrawings.addElement(drawings.elementAt(i));
				}
			}
			repaint();
		}
	}

	// カーソル変更
	public int cursorCheck(int x, int y) {
		if(contains(x,y)) {
			return 1;
		}
		return 0;
	}

	// レイヤー変える
	public void clearLayerBuffers() {
		layerBuffers.removeAllElements();
	}
	public void layerChange(int flag) {
		clearLayerBuffers();
		for(int i = 0; i < selectedDrawings.size(); i ++) {
			layerBuffers.addElement(selectedDrawings.elementAt(i));
		}
		switch(flag) {
		case 0: // 最前面
			for(int i = 0; i < selectedDrawings.size(); i ++) {
				addDrawing(selectedDrawings.elementAt(i));
			}
			setUnSelected();
			int dSize = drawings.size();
			for(int i = 0; i < layerBuffers.size(); i++) {
				setSelectedDrawing(drawings.elementAt(dSize-i-1));
			}
			for(int i = 0; i < layerBuffers.size(); i ++) {
				drawings.removeElement(layerBuffers.elementAt(i));
			}
			repaint();
			break;
		case 1: // 最背面
			for(int i = 0; i < selectedDrawings.size(); i ++) {
				addDrawingAt(selectedDrawings.elementAt(i),i);
			}
			setUnSelected();
			for(int i = 0; i < layerBuffers.size(); i++) {
				setSelectedDrawing(drawings.elementAt(i));
			}
			for(int i = 0; i < layerBuffers.size(); i ++) {
				int index = drawings.lastIndexOf(layerBuffers.elementAt(i));
				drawings.removeElementAt(index);
			}
			repaint();
			break;
		case 2: // 一つ前面
			for(int i = 0; i < selectedDrawings.size(); i ++) {
				int index = drawings.indexOf(selectedDrawings.elementAt(i));
				if(index < drawings.size()-1 && index >= 0) {
					addDrawingAt(selectedDrawings.elementAt(i), index+2);
				}
			}
			setUnSelected();
			for(int i = 0; i < layerBuffers.size(); i ++) {
				int index = drawings.indexOf(layerBuffers.elementAt(i));
				setSelectedDrawing(drawings.elementAt(index));
			}
			for(int i = 0; i < layerBuffers.size(); i ++) {
				int index = drawings.indexOf(layerBuffers.elementAt(i));
				if(index < drawings.size()-1 && index >= 0) {
					drawings.removeElementAt(index);
				}
			}
			repaint();
			break;
		case 3: // 一つ背面
			for(int i = 0; i < selectedDrawings.size(); i ++) {
				int index = drawings.indexOf(selectedDrawings.elementAt(i));
				if(index > 0) {
					addDrawingAt(selectedDrawings.elementAt(i), index-1);
				}
			}
			setUnSelected();
			for(int i = 0; i < layerBuffers.size(); i ++) {
				int index = drawings.indexOf(layerBuffers.elementAt(i));
				setSelectedDrawing(drawings.elementAt(index));
			}
			for(int i = 0; i < layerBuffers.size(); i ++) {
				int index = drawings.lastIndexOf(layerBuffers.elementAt(i));
				if(index > 0) {
					drawings.removeElementAt(index);
				}
			}
			repaint();
			break;
		}
	}
}