package task5;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class MyTriangle extends MyDrawing{
	public MyTriangle( int xpt, int ypt) {
		super(xpt,ypt);
	}
	public MyTriangle( int xpt, int ypt, int wpt, int hpt) {
		super(xpt,ypt,wpt,hpt);
	}
	public MyTriangle(int xpt, int ypt, int wpt, int hpt,
			int fR, int fG, int fB, int lR, int lG, int lB,
			int lineWidth, String strokeType){
		super(xpt, ypt, wpt, hpt, fR, fG, fB, lR, lG, lB, lineWidth, strokeType);
	}

	public void draw( Graphics g) {
		int x = getX();
		int y = getY();
		int w = getW();
		int h = getH();

		// 高さや横幅が負の時のための処理
		if( w < 0 ) {
			x += w;
			w *= -1;
		}
		if (h<0) {
			y += h;
			h *= -1;
		}

		Graphics2D g2 = (Graphics2D) g;
		// 座標の設定
		int xPoints[] = {x, x + w/2, x+w};
		int yPoints[] = {y+h, y, y+h};
		int shadowX[] = {x+5, x + w/2+5, x+w+5};
		int shadowY[] = {y+h+5, y+5, y+h+5};
		if(getShadow()) {
			g2.setColor(Color.black);
			g2.fillPolygon(shadowX, shadowY,3);
		}
		this.setStroke(getStrokeType());
		g2.setStroke(getStroke());
		g2.setColor(getFillColor());
		g2.fillPolygon(xPoints, yPoints,3);
		g2.setColor(getLineColor());
		g2.drawPolygon(xPoints, yPoints, 3);
		g2.setStroke(new BasicStroke(1));
		super.draw(g);
	}
}
