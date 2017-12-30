package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import fileUtil.FileUtil;
import guiUtil.ResizableImage;
import internationalization.Internationalization;

public class InfoOrderPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelNorth;
	private JLabel lblLogo;
	private JScrollPane scrollPaneOrder;
	private JTextArea txtrInfoOrder;
	private JPanel panelButton;
	private JButton btnFinish;
	private JButton btnCancel;
	private JButton btnBack;
	private MainWindow main;

	public InfoOrderPanel(MainWindow main) {
		this.main = main;
		setLayout(new BorderLayout(0, 0));
		add(getPanelNorth(), BorderLayout.NORTH);
		add(getScrollPaneOrder(), BorderLayout.CENTER);
		add(getPanelSouth(), BorderLayout.SOUTH);
		setFocusTraversalPolicy(
				new FocusTraversalOnArray(new Component[] { getBtnBack(), getBtnCancel(), getBtnFinish() }));
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
			ResizableImage.setResizeImage(this, lblLogo, "/img/logo.png", 300, 150);
		}
		return lblLogo;
	}

	private JScrollPane getScrollPaneOrder() {
		if (scrollPaneOrder == null) {
			scrollPaneOrder = new JScrollPane();
			scrollPaneOrder.setViewportView(getTxtrInfoOrder());
			scrollPaneOrder.getVerticalScrollBar().setValue(0);
			scrollPaneOrder.getVerticalScrollBar().setUnitIncrement(20);
		}
		return scrollPaneOrder;
	}

	private JTextArea getTxtrInfoOrder() {
		if (txtrInfoOrder == null) {
			txtrInfoOrder = new JTextArea();
			txtrInfoOrder.setToolTipText(Internationalization.getToolTips("order_string"));
			txtrInfoOrder.setWrapStyleWord(true);
			txtrInfoOrder.setLineWrap(true);
			txtrInfoOrder.setEditable(false);
			txtrInfoOrder.setText(ProductListPanel.getOrder().toString());
		}
		return txtrInfoOrder;
	}

	private JPanel getPanelSouth() {
		if (panelButton == null) {
			panelButton = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelButton.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			panelButton.setBackground(Color.WHITE);
			panelButton.add(getBtnBack());
			panelButton.add(getBtnCancel());
			panelButton.add(getBtnFinish());
		}
		return panelButton;
	}

	private JButton getBtnFinish() {
		if (btnFinish == null) {
			btnFinish = new JButton(Internationalization.getString("info_finish"));
			btnFinish.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnFinish.setToolTipText(Internationalization.getToolTips("info_finish"));
			btnFinish.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String nameFile = "orders/";
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					nameFile = format.format(new Date(System.currentTimeMillis())) + "_"
							+ ProductListPanel.getOrder().getDni();
					nameFile += ".dat";

					try {
						FileUtil.saveToFile(nameFile, Arrays.asList(ProductListPanel.getOrder().toString()));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					removeAll();
					add(new FinishPanel(main));
					revalidate();
					repaint();

				}
			});
		}
		return btnFinish;
	}

	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton(Internationalization.getString("info_cancel"));
			btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnCancel.setToolTipText(Internationalization.getToolTips("info_cancel"));
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Window w = SwingUtilities.getWindowAncestor(InfoOrderPanel.this);
					w.dispose();
				}
			});
		}
		return btnCancel;
	}

	private JButton getBtnBack() {
		if (btnBack == null) {
			btnBack = new JButton(Internationalization.getString("back")); //$NON-NLS-1$
			btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					removeAll();
					add(new LogUpDialog(main, null).getContentPane());
					revalidate();
					repaint();
				}
			});
		}
		return btnBack;
	}
}
