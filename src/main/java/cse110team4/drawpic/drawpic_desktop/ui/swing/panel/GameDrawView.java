package cse110team4.drawpic.drawpic_desktop.ui.swing.panel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Label;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JColorChooser;
import java.awt.ScrollPane;
import javax.swing.JSeparator;
import java.awt.GridLayout;
import javax.swing.JSlider;
import javax.swing.JRadioButton;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.border.EtchedBorder;

public class GameDrawView extends JPanel {
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Create the panel.
	 */
	public GameDrawView() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		add(panel, BorderLayout.CENTER);
		
		Canvas canvas = new Canvas();
		panel.add(canvas);
		
		Label label = new Label("New label");
		label.setAlignment(Label.CENTER);
		add(label, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("234px"),},
			new RowSpec[] {
				RowSpec.decode("75px"),
				RowSpec.decode("201px"),}));
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, "1, 1, left, fill");
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Pen");
		buttonGroup.add(rdbtnNewRadioButton);
		panel_4.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnEraser = new JRadioButton("Eraser");
		buttonGroup.add(rdbtnEraser);
		panel_4.add(rdbtnEraser);
		
		JPanel panel_2 = new JPanel();
		panel_3.add(panel_2);
		
		JLabel lblNewLabel = new JLabel("Size:");
		panel_2.add(lblNewLabel);
		
		JSlider slider = new JSlider();
		panel_2.add(slider);
		
		JColorChooser colorChooser = new JColorChooser();
		//panel_1.add(colorChooser);
		
		ScrollPane scrollPane = new ScrollPane();
		panel_1.add(scrollPane, "1, 2, fill, fill");
		scrollPane.add(colorChooser);

	}

}