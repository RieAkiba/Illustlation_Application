package task5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class EditMenu extends JMenu{
	private StateManager stateManager;

	public EditMenu(StateManager stateManager) {
		super("Edit");
		JMenuItem undo = new JMenuItem("Undo");
		undo.addActionListener(new UndoListener());
		JMenuItem redo = new JMenuItem("Redo");
		redo.addActionListener(new RedoListener());
		JMenuItem cut = new JMenuItem("Cut");
		cut.addActionListener(new CutListener());
		JMenuItem copy = new JMenuItem("Copy");
		copy.addActionListener(new CopyListener());
		JMenuItem paste = new JMenuItem("Paste");
		paste.addActionListener(new PasteListener());
		JMenuItem delete = new JMenuItem("Delete");
		delete.addActionListener(new DeleteListener());

		add(undo);
		add(redo);
		add(cut);
		add(copy);
		add(paste);
		add(delete);

		this.stateManager = stateManager;
	}
	class UndoListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.out.println("undo");
			stateManager.undo();
		}
	}
	class RedoListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.out.println("redo");
			stateManager.redo();
		}
	}
	class CutListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.out.println("cut");
			stateManager.cut();
		}
	}
	class CopyListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.out.println("copy");
			stateManager.copy();
		}
	}
	class PasteListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.out.println("paste");
			stateManager.paste();
		}
	}
	class DeleteListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.out.println("delete");
			stateManager.delete();
		}
	}
}
