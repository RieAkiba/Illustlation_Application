package task5;

import javax.swing.JMenuBar;

public class MyMenuBar extends JMenuBar{

	private StateManager stateManager;
	private FileMenu file;
	private EditMenu edit;

	public MyMenuBar(StateManager stateManager) {
		this.stateManager = stateManager;
		file = new FileMenu(stateManager);
		edit = new EditMenu(stateManager);

		add(file);
		add(edit);
	}
}
