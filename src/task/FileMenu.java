package task5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class FileMenu extends JMenu{
	private StateManager stateManager;

	public FileMenu(StateManager stateManager) {
		super("File");
		JMenuItem newFile = new JMenuItem("New File");
		newFile.addActionListener(new NewFileListener());
		JMenuItem open = new JMenuItem("Open");
		open.addActionListener(new OpenListener());
		JMenuItem save = new JMenuItem("Save(S)");
		save.setMnemonic(KeyEvent.VK_S);
		save.addActionListener(new SaveListener());
		JMenuItem saveAs = new JMenuItem("Save As");
		saveAs.addActionListener(new SaveAsListener());
		JMenuItem close = new JMenuItem("Close(C)");
		close.setMnemonic(KeyEvent.VK_C);
		close.addActionListener(new CloseListener());

		add(newFile);
		add(open);
		add(save);
		add(saveAs);
		add(close);

		this.stateManager = stateManager;
	}
	class NewFileListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.out.println("newFile");
			stateManager.newFile();
		}
	}
	class OpenListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			stateManager.Open();
		}
	}
	class SaveAsListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			stateManager.saveAs();
		}
	}
	class SaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			stateManager.save();
		}
	}
	class CloseListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
}
