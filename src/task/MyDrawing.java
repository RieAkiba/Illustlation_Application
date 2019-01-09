package task5;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class MyDrawing implements Cloneable, Serializable{
	private int x, y, w, h; // X, Y , width, height
	private Color lineColor, fillColor; // 線の色、塗り色
	private int fR, fG, fB, lR, lG, lB; // 線の色、塗り色のRGB
	private int clearDegree;
	private float lineWidth; // 線の太さ
	transient protected BasicStroke stroke; // 線種
	private String strokeType; // 線種名
	private boolean isShadow; // 影ありかなしか
	private int fontSize; // フォントサイズ
	private boolean isSelected; // 選択されているかどうか
	private boolean isNotFill; // 塗りつぶさないかどうか
	transient protected Shape region; // 包含判定用
	private final int SIZE = 7; // 選択表示矩形に付く四角形の大きさ

	public MyDrawing() {
		x = y = 0;
		w = h = 40;
		lineColor = Color.black;
		lR = lG = lB = 0;
		fillColor = Color.white;
		fR = fG = fB = 255;
		clearDegree = 255;
		lineWidth = 1;
		strokeType = "solid";
		setStroke(strokeType);
		isShadow = false;
		isSelected = false;
		isNotFill = false;
		fontSize = 18;
	}
	public MyDrawing(int x, int y) {
		this();
		setLocation(x,y);
	}
	public MyDrawing(int x, int y, int w, int h) {
		this(x,y);
		setSize(w,h);
	}
	public MyDrawing(int x, int y, int w, int h,
			int fR, int fG, int fB, int lR, int lG, int lB,
			int lineWidth, String strokeType) {
		// 全ての要素を自由に設定できるコンストラクタ
		this(x,y,w,h);
		setFillR(fR);
		setFillG(fG);
		setFillB(fB);
		setFillColor();
		setLineR(lR);
		setLineG(lG);
		setLineB(lB);
		setLineColor();
		setLineWidth(lineWidth);
		this.strokeType = strokeType;
		setStroke(this.strokeType);
	}
	public MyDrawing(int x, int y, int w, int h,
			int fR, int fG, int fB, int lR, int lG, int lB,
			int lineWidth, String strokeType, int fontSize) {
		this(x,y,w,h,fR,fG,fB,lR,lG,lB,lineWidth,strokeType);
		setFontSize(fontSize);
	}

	// コピー・ペーストで利用する

	@Override
	public MyDrawing clone() {
		MyDrawing d = null;
        try {
            d = (MyDrawing)super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
	}

	public void draw(Graphics g) {
		// 選択状態を表す四角形を描く
		if(isSelected) {
			int rx = x;
			int ry = y;
			int rw = w;
			int rh = h;
			g.setColor(Color.black);
			//System.out.println(x+","+y+","+w+","+h);
			// 高さや横幅が負の時のための処理
			if( rw < 0 ) {
				rx += rw;
				rw *= -1;
			}
			if (rh<0) {
				ry += rh;
				rh *= -1;
			}
			g.drawRect(rx, ry, rw, rh);
			g.fillRect(x+w/2-SIZE/2, y-SIZE/2, SIZE, SIZE);
			g.fillRect(x-SIZE/2, y+h/2-SIZE/2, SIZE, SIZE);
			g.fillRect(x+w/2-SIZE/2, y+h-SIZE/2, SIZE, SIZE);
			g.fillRect(x+w-SIZE/2, y+h/2-SIZE/2, SIZE, SIZE);
			g.fillRect(x-SIZE/2, y-SIZE/2, SIZE, SIZE);
			g.fillRect(x+w-SIZE/2, y-SIZE/2, SIZE, SIZE);
			g.fillRect(x-SIZE/2, y+h-SIZE/2, SIZE, SIZE);
			g.fillRect(x+w-SIZE/2, y+h-SIZE/2, SIZE, SIZE);
		}
	}

	// 選択状態のメソッド
	public boolean getSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	// 包含判定用のメソッド
	// クリック用
	public boolean contains(int x, int y) {
		return region.contains(x, y);
	}
	// ドラッグ用
	public boolean intersects(int x, int y, int w, int h) {
		return region.intersects(x,y,w,h);
	}

	public Shape getRegion() {
		return this.region;
	}
	public void setRegion() {
		// 高さや横幅が負の時のための処理
		if( w < 0 ) {
			x += w;
			w *= -1;
		}
		if (h<0) {
			y += h;
			h *= -1;
		}
		region = new Rectangle(x,y,w,h);
	}

	// (x,y,w,h) のメソッド
	public void move(int dx, int dy) {
		// オブジェクトを移動する処理
		this.x += dx;
		this.y += dy;
	}
	public void setLocation( int x, int y) {
		// オブジェクトの位置を変更する処理
		this.x = x;
		this.y = y;
	}
	public void setSize( int w, int h) {
		// オブジェクトのサイズを変更する処理を書く
		this.w = w;
		this.h = h;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	public int getW() {
		return this.w;
	}
	public int getH() {
		return this.h;
	}

	// color のメソッド
	public void setLineColor() {
		this.lineColor  = new Color(lR,lG,lB,clearDegree);
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

	public void setFillColor () {
		this.fillColor = new Color(fR, fG, fB, clearDegree);
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

	// 透明度のメソッド
	public void setClearDegree(int clearDegree) {
		this.clearDegree = clearDegree;
	}
	public int getClearDegree() {
		return clearDegree;
	}

	// lineWidth のメソッド
	public void setLineWidth(float lw) {
		this.lineWidth = lw;
	}
	public float getLineWidth() {
		return this.lineWidth;
	}

	// fontSize のメソッド
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
	public int getFontSize() {
		return fontSize;
	}

	// stroke のメソッド
	public BasicStroke getStroke() {
		return this.stroke;
	}
	public void setStroke(String strokeType) {
		this.strokeType = strokeType;
		switch (strokeType) {
		case "solid":
			this.stroke = new BasicStroke(getLineWidth());
			break;
		case "dotted":
			this.stroke = new BasicStroke(
				      getLineWidth(),
				      BasicStroke.CAP_ROUND,
				      BasicStroke.JOIN_ROUND,
				      1f,
				      new float[] {2f},
				      0f);
			break;
		case "dashed":
			this.stroke = new BasicStroke(
					getLineWidth(),
					BasicStroke.CAP_BUTT,
					BasicStroke.JOIN_MITER,
					3.0f,
					new float[] {5.0f,1.5f},
					0.0f);
			break;
		}
	}
	public String getStrokeType() {
		return this.strokeType;
	}

	// shadow のメソッド
	public boolean getShadow() {
		return this.isShadow;
	}

	public void setShadow(boolean b) {
		this.isShadow = b;
	}

	// NotFill のメソッド
	public boolean getNotFill() {
		return isNotFill;
	}
	public void setNotFill(boolean b) {
		isNotFill = b;
	}

	public void writeObject(ObjectOutputStream out) throws IOException{
		out.writeInt(getX());
		out.writeInt(getY());
		out.writeInt(getW());
		out.writeInt(getX());
		out.writeUTF(getStrokeType());
	}
	public void readObject(ObjectInputStream in) throws IOException{
		int inx = in.readInt();
		int iny = in.readInt();
		int inw = in.readInt();
		int inh = in.readInt();
		String inst = in.readUTF();

		// 高さや横幅が負の時のための処理
		if( inw < 0 ) {
			inx += inw;
			inw *= -1;
		}
		if (inh<0) {
			iny += inh;
			inh *= -1;
		}
		region = new Rectangle(inx, iny, inw, inh);
		setStroke(inst);
	}
}
