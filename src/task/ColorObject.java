package task5;

import java.awt.Color;

public class ColorObject extends Object{
	private Color color;
	private String name;

	public ColorObject(String name, Color c) {
		setName(name);
		setColor(c);
	}
	public String toString() {
		return getName();
	}

	public Color getColor() {
		return color;
	}
	public void setColor(Color c) {
		this.color = c;
	}
	public void setName(String n) {
		this.name = n;
	}
	public String getName() {
		return this.name;
	}
}
