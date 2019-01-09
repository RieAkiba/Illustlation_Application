package task5;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu {
	private JPanel mp;
	private JPanel bp, lp;
	private StateManager stateManager;
	private JLabel filename;

	public Menu(JPanel mp, StateManager stateManager) {
		this.mp = mp;
		this.bp = new JPanel();
		bp.setLayout(new FlowLayout());
		this.lp = new JPanel();
		lp.setLayout(new FlowLayout());
		this.stateManager = stateManager;
		filename = new JLabel(stateManager.getCanvas().getMediator().getFileName());
	}

	public void setMenu() {
		// Object ボックス
		ObjectBox objBox = new ObjectBox(stateManager);
		bp.add(objBox);

		// Shadow チェックボックス
		ShadowBox shadowBox = new ShadowBox(stateManager);
		bp.add(shadowBox);

		// LineWidth コンボボックス
		JLabel lineWidthLabel = new JLabel("LineWidth");
		bp.add(lineWidthLabel);
		LineWidthBox lineWidthBox = new LineWidthBox(stateManager);
		bp.add(lineWidthBox);

		// Stroke コンボボックス
		StrokeBox strokeBox = new StrokeBox(stateManager);
		bp.add(strokeBox);

		// FillColorMenu コンボボックス
		JLabel fillColorLabel = new JLabel("FillColor");
		bp.add(fillColorLabel);
		FillColorMenu fillColorMenu = new FillColorMenu(stateManager);
		bp.add(fillColorMenu);

		// LineColorMenu コンボボックス
		JLabel lineColorLabel = new JLabel("LineColor");
		bp.add(lineColorLabel);
		LineColorMenu lineColorMenu = new LineColorMenu(stateManager);
		bp.add(lineColorMenu);

		// ClearSlider スライダー
		JLabel clearDegreeLabel = new JLabel("ClearDegree");
		bp.add(clearDegreeLabel);
		ClearSlider clearSlider = new ClearSlider(stateManager);
		bp.add(clearSlider);

		// Select ボタン
		SelectButton selectButton = new SelectButton(stateManager);
		bp.add(selectButton);

		// Text ボタン
		TextButton textButton = new TextButton(stateManager);
		bp.add(textButton);

		// FontSize ボックス
		JLabel fontSizeLabel = new JLabel("fontSize");
		bp.add(fontSizeLabel);
		FontSizeBox fontSizeBox = new FontSizeBox(stateManager);
		bp.add(fontSizeBox);

		// mpに全部入れる
		lp.add(filename);
		mp.add(bp);
		mp.add(lp);
	}
	public void setName() {
		filename.setText(stateManager.getCanvas().getMediator().getFileName());
	}
}
