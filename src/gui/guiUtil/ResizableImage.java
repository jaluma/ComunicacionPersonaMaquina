package gui.guiUtil;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ResizableImage {

	public static boolean setResizeImage(JPanel panel, JButton button, String path, int width, int height) {
		Image imgOriginal;
		boolean image = true;
		try {
			imgOriginal = new ImageIcon(panel.getClass().getResource(path)).getImage();
		} catch (Exception e) {
			imgOriginal = new ImageIcon(panel.getClass().getResource("/img/DE000.jpg")).getImage();
			image = false;
		}
		Image imgResize = imgOriginal.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		button.setIcon(new ImageIcon(imgResize));
		button.setDisabledIcon(new ImageIcon(imgResize));
		return image;
	}

	public static boolean setResizeImage(JPanel panel, JLabel label, String path, int width, int height) {
		Image imgOriginal;
		boolean image = true;
		try {
			imgOriginal = new ImageIcon(panel.getClass().getResource(path)).getImage();
		} catch (Exception e) {
			imgOriginal = new ImageIcon(panel.getClass().getResource("/img/DE000.jpg")).getImage();
			image = false;
		}
		Image imgResize = imgOriginal.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		label.setIcon(new ImageIcon(imgResize));
		label.setDisabledIcon(new ImageIcon(imgResize));
		return image;
	}

}
