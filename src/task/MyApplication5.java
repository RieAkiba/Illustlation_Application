package task5;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MyApplication5 extends JFrame{
	private StateManager stateManager;

	public MyApplication5() {
		super("Gabapaca");
		ImageIcon gabaicon = new ImageIcon("./img/gabapaca.png");
		setIconImage(gabaicon.getImage());
		MyCanvas canvas = new MyCanvas();
		canvas.setFocusable(true);

		stateManager = new StateManager(canvas);

		MyPopup popup = new MyPopup(stateManager);
		// メニューバー
		MyMenuBar menuBar = new MyMenuBar(stateManager);
		setJMenuBar(menuBar);
		// メニューのパネル
		JPanel mp = new JPanel();
		mp.setLayout(new GridLayout(2,1));
		Menu menu = new Menu(mp, stateManager);
		menu.setMenu();

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(mp, BorderLayout.NORTH);
		getContentPane().add(canvas, BorderLayout.CENTER);

		canvas.addMouseListener(new MouseAdapter() {
			// 現在の状態の mouseDown 処理の呼び出し
			public void mousePressed(MouseEvent e) {
				canvas.requestFocusInWindow();
				stateManager.mouseDown(e.getX(), e.getY());
			}
			// 現在の状態の mouseUp 処理の呼び出し
			public void mouseReleased(MouseEvent e) {
				canvas.requestFocusInWindow();
				stateManager.mouseUp(e.getX(), e.getY());
			}
			// 現在の状態の mouseClick 処理の呼び出し
			public void mouseClicked(MouseEvent e) {
				if(SwingUtilities.isRightMouseButton(e)) {
					popup.setFalse();
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		canvas.addMouseMotionListener(new MouseMotionAdapter() {
			// mouseDrag 処理の呼び出し
			public void mouseDragged(MouseEvent e) {
				canvas.requestFocusInWindow();
				stateManager.mouseDrag(e.getX(),e.getY());
			}
			// mouseMove 処理の呼び出し
			public void mouseMoved(MouseEvent e) {
				stateManager.mouseMove(e.getX(), e.getY());
			}
		});

		canvas.addKeyListener(new KeyAdapter() {
			// keyPressed 処理の呼び出し
			public void keyPressed(KeyEvent e) {
				stateManager.keyPress(e.getKeyCode(), e.getModifiersEx());
			}
		});

		this.addWindowListener(new WindowAdapter() {
			// ウインドウを閉じたら終了
			public void windowClosing(WindowEvent e) {
				System.exit(0);;
			}
		});
	}

	public static void main(String[] args) {
		MyApplication5 ma = new MyApplication5();
		ma.setSize(2000,600);
		ma.setVisible(true);
	}
}