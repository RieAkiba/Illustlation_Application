package task5;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class MyPondering extends MyDrawing{
	public MyPondering( int xpt, int ypt) {
		super(xpt,ypt);
	}
	public MyPondering( int xpt, int ypt, int wpt, int hpt) {
		super(xpt,ypt,wpt,hpt);
	}
	public MyPondering(int xpt, int ypt, int wpt, int hpt,
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

		int wPonde = w/4;
		int hPonde = h/4;

		Graphics2D g2 = (Graphics2D) g;
		if(getShadow()) {
			g2.setColor(Color.black);
			g2.fillOval(x+3*w/8+5,y+5,wPonde,hPonde);
			g2.fillOval((int)(x+((6+3*Math.sqrt(2))/16)*w)+5,(int)(y+((6-3*Math.sqrt(2))/16)*h)+5,wPonde,hPonde);
			g2.fillOval(x+3*w/4+5,y+3*h/8+5,wPonde,hPonde);
			g2.fillOval((int)(x+((6+3*Math.sqrt(2))/16)*w)+5,(int)(y+((6+3*Math.sqrt(2))/16)*h)+5,wPonde,hPonde);
			g2.fillOval(x+3*w/8+5,y+3*h/4+5,wPonde,hPonde);
			g2.fillOval((int)(x+((6-3*Math.sqrt(2))/16)*w)+5,(int)(y+((6+3*Math.sqrt(2))/16)*h)+5,wPonde,hPonde);
			g2.fillOval(x+5,y+3*h/8+5,wPonde,hPonde);
			g2.fillOval((int)(x+((6-3*Math.sqrt(2))/16)*w)+5,(int)(y+((6-3*Math.sqrt(2))/16)*h)+5,wPonde,hPonde);
		}
		this.setStroke(getStrokeType());
		g2.setStroke(getStroke());
		g2.setColor(getFillColor());
		g2.fillOval(x+3*w/8,y,wPonde,hPonde);
		g2.fillOval((int)(x+((6+3*Math.sqrt(2))/16)*w),(int)(y+((6-3*Math.sqrt(2))/16)*h),wPonde,hPonde);
		g2.fillOval(x+3*w/4,y+3*h/8,wPonde,hPonde);
		g2.fillOval((int)(x+((6+3*Math.sqrt(2))/16)*w),(int)(y+((6+3*Math.sqrt(2))/16)*h),wPonde,hPonde);
		g2.fillOval(x+3*w/8,y+3*h/4,wPonde,hPonde);
		g2.fillOval((int)(x+((6-3*Math.sqrt(2))/16)*w),(int)(y+((6+3*Math.sqrt(2))/16)*h),wPonde,hPonde);
		g2.fillOval(x,y+3*h/8,wPonde,hPonde);
		g2.fillOval((int)(x+((6-3*Math.sqrt(2))/16)*w),(int)(y+((6-3*Math.sqrt(2))/16)*h),wPonde,hPonde);
		g2.setColor(getLineColor());
		g2.drawOval(x+3*w/8,y,wPonde,hPonde);
		g2.drawOval((int)(x+((6+3*Math.sqrt(2))/16)*w),(int)(y+((6-3*Math.sqrt(2))/16)*h),wPonde,hPonde);
		g2.drawOval(x+3*w/4,y+3*h/8,wPonde,hPonde);
		g2.drawOval((int)(x+((6+3*Math.sqrt(2))/16)*w),(int)(y+((6+3*Math.sqrt(2))/16)*h),wPonde,hPonde);
		g2.drawOval(x+3*w/8,y+3*h/4,wPonde,hPonde);
		g2.drawOval((int)(x+((6-3*Math.sqrt(2))/16)*w),(int)(y+((6+3*Math.sqrt(2))/16)*h),wPonde,hPonde);
		g2.drawOval(x,y+3*h/8,wPonde,hPonde);
		g2.drawOval((int)(x+((6-3*Math.sqrt(2))/16)*w),(int)(y+((6-3*Math.sqrt(2))/16)*h),wPonde,hPonde);
		g2.setStroke(new BasicStroke(1));
		super.draw(g);
	}
}
