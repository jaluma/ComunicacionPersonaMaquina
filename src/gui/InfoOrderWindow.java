package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class InfoOrderWindow extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private MainWindow mainWindow;
	private JPanel contentPane;
	private JPanel panelNorth;
	private JLabel lblLogo;
	private JScrollPane scrollPaneOrder;
	private JTextArea txtrInfoOrder;
	private JPanel panelButton;
	private JButton btnFinish;
	private JButton btnCancel;
	
	public InfoOrderWindow(MainWindow mainWindow, JPanel contentPane) {
		this.mainWindow = mainWindow;
		contentPane.add(getPanelNorth(), BorderLayout.NORTH);
		contentPane.add(getScrollPaneOrder(), BorderLayout.CENTER);
		contentPane.add(getPanelSouth(), BorderLayout.SOUTH);
	}
	
	private JPanel getPanelNorth() {
		if (panelNorth == null) {
			panelNorth = new JPanel();
			panelNorth.setBackground(Color.WHITE);
			FlowLayout flowLayout = (FlowLayout) panelNorth.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			panelNorth.add(getLblLogo());
		}
		return panelNorth;
	}
	private JLabel getLblLogo() {
		if (lblLogo == null) {
			lblLogo = new JLabel("");
			ResizableImage.setResizeImage(contentPane, lblLogo, "/img/logo.png", 250, 100);
		}
		return lblLogo;
	}
	private JScrollPane getScrollPaneOrder() {
		if (scrollPaneOrder == null) {
			scrollPaneOrder = new JScrollPane();
			scrollPaneOrder.setViewportView(getTxtrInfoOrder());
		}
		return scrollPaneOrder;
	}
	private JTextArea getTxtrInfoOrder() {
		if (txtrInfoOrder == null) {
			txtrInfoOrder = new JTextArea();
			txtrInfoOrder.setWrapStyleWord(true);
			txtrInfoOrder.setLineWrap(true);
			txtrInfoOrder.setEditable(false);
		}
		return txtrInfoOrder;
	}
	private JPanel getPanelSouth() {
		if (panelButton == null) {
			panelButton = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelButton.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			panelButton.setBackground(Color.WHITE);
			panelButton.add(getBtnCancel());
			panelButton.add(getBtnFinish());
		}
		return panelButton;
	}
	private JButton getBtnFinish() {
		if (btnFinish == null) {
			btnFinish = new JButton("finish");
		}
		return btnFinish;
	}
	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("cancel");
		}
		return btnCancel;
	}

}
