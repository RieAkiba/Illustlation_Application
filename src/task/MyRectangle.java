package task5;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class MyRectangle extends MyDrawing{
	public MyRectangle( int xpt, int ypt) {
		super(xpt,ypt);
	}
	public MyRectangle( int xpt, int ypt, int wpt, int hpt) {
		super(xpt,ypt,wpt,hpt);
	}
	public MyRectangle(int xpt, int ypt, int wpt, int hpt,
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
		if(getShadow()) {
			g2.setColor(Color.black);
			g2.fillRect(x + 5,y + 5,w,h);
		}
		if(getNotFill()) {
			this.setStroke(getStrokeType());
			g2.setStroke(getStroke());
			g2.setColor(getLineColor());
			g2.drawRect(x, y, w, h);
			g2.setStroke(new BasicStroke(1));
			super.draw(g);
		}else {
			this.setStroke(getStrokeType());
			g2.setStroke(getStroke());
			g2.setColor(getFillColor());
			g2.fillRect(x,y,w,h);
			g2.setColor(getLineColor());
			g2.drawRect(x, y, w, h);
			g2.setStroke(new BasicStroke(1));
			super.draw(g);
		}
	}
}
