package task5;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class MyOctagonal extends MyDrawing{
	public MyOctagonal( int xpt, int ypt) {
		super(xpt,ypt);
	}
	public MyOctagonal( int xpt, int ypt, int wpt, int hpt) {
		super(xpt,ypt,wpt,hpt);
	}
	public MyOctagonal(int xpt, int ypt, int wpt, int hpt,
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
		int xPoints[] = {x+(w/2),
				(int)(x+((2+Math.sqrt(2))/4)*w),
				x+w,
				(int)(x+((2+Math.sqrt(2))/4)*w),
				x+(w/2),
				(int)(x+((2-Math.sqrt(2))/4)*w),
				x,
				(int)(x+((2-Math.sqrt(2))/4)*w)};
		int yPoints[] = {y,
				(int)(y+((2-Math.sqrt(2))/4)*h),
				y+h/2,
				(int)(y+((2+Math.sqrt(2))/4)*h),
				y+h,
				(int)(y+((2+Math.sqrt(2))/4)*h),
				y+h/2,
				(int)(y+((2-Math.sqrt(2))/4)*h)};
		int shadowX[] = {x+(w/2)+5,
				(int)(x+((2+Math.sqrt(2))/4)*w)+5,
				x+w+5,
				(int)(x+((2+Math.sqrt(2))/4)*w)+5,
				x+(w/2)+5,
				(int)(x+((2-Math.sqrt(2))/4)*w)+5,
				x+5,
				(int)(x+((2-Math.sqrt(2))/4)*w)+5};
		int shadowY[] = {y+5,
				(int)(y+((2-Math.sqrt(2))/4)*h)+5,
				y+h/2+5,
				(int)(y+((2+Math.sqrt(2))/4)*h)+5,
				y+h+5,
				(int)(y+((2+Math.sqrt(2))/4)*h)+5,
				y+h/2+5,
				(int)(y+((2-Math.sqrt(2))/4)*h)+5};
		if(getShadow()) {
			g2.setColor(Color.black);
			g2.fillPolygon(shadowX, shadowY, 8);
		}
		this.setStroke(getStrokeType());
		g2.setStroke(getStroke());
		g2.setColor(getFillColor());
		g2.fillPolygon(xPoints, yPoints, 8);
		g2.setColor(getLineColor());
		g2.drawPolygon(xPoints, yPoints, 8);
		g2.setStroke(new BasicStroke(1));
		super.draw(g);
	}
}
