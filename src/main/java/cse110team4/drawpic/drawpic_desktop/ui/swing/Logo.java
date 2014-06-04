package cse110team4.drawpic.drawpic_desktop.ui.swing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class Logo extends JComponent {
	private static final int LOGO_WIDTH_PERCENT = 80;
	private static final int LOGO_WIDTH_MAX = 600;
	private static final int LOGO_WIDTH_MIN = 300;
	private static final double LOGO_WIDTH_HEIGHT_RATIO = 609 / 120;
	
	private BufferedImage logo;
	
	public Logo() throws IOException {
		// Get logo image
		InputStream logoStream = getClass().getResourceAsStream("/graphics/logo_alpha.png");
		logo = ImageIO.read(logoStream);
		
		// Set a minimum size
		setMinimumSize(new Dimension(LOGO_WIDTH_MIN, (int) (LOGO_WIDTH_MIN / LOGO_WIDTH_HEIGHT_RATIO)));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Draw the logo centered
		int logoWidth = (LOGO_WIDTH_PERCENT * getWidth()) / 100;
		if (logoWidth > LOGO_WIDTH_MAX) {
			logoWidth = LOGO_WIDTH_MAX;
		} else if (logoWidth < LOGO_WIDTH_MIN) {
			logoWidth = LOGO_WIDTH_MIN;
		}
		int logoHeight = (int) (logoWidth / LOGO_WIDTH_HEIGHT_RATIO);
		int logoX = (getWidth() - logoWidth) / 2;
		
		g.drawImage(logo, logoX, 10, logoWidth, logoHeight, null);
	}
}
