package task5;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class MyTextBox extends MyDrawing{
	private String text;

	public MyTextBox(String _text, int xpt, int ypt) {
		super(xpt, ypt);
		text = _text;
	}
	public void draw(Graphics g) {
		g.setFont(new Font("Dialog",Font.PLAIN,getFontSize()));
		FontMetrics fm = g.getFontMetrics();
		setSize(fm.stringWidth(text), fm.getHeight());
		g.setColor(getLineColor());
		g.drawString(text,  getX(),  getY()+fm.getAscent());
		super.draw(g);
	}
}
