package task5;

import javax.swing.Icon;

public class MyIcon {
	private int flag;
	private Icon icon;

	public MyIcon(Icon icon, int flag) {
		setIcon(icon);
		setFlag(flag);
	}
	public int getFlag() {
		return flag;
	}
	public Icon getIcon() {
		return icon;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public void setIcon(Icon icon) {
		this.icon = icon;
	}
}
