package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import gui.guiUtil.ResizableImage;
import internationalization.Internationalization;

public class FinishPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_TIME = 15;
	private JPanel panelNorth;
	private JPanel panelCenter;
	private JPanel panelSouth;
	private JLabel lblLogo;
	private JLabel lblThxreserve;
	private JPanel panelLabel;
	private JPanel panelRestore;
	private JLabel lblRestore;
	private JButton btnRestore;
	private MainWindow main;
	private PropertyChangeListener timer;

	/**
	 * Create the panel.
	 */
	public FinishPanel(MainWindow main) {
		this.main = main;
		timer = new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						int value = DEFAULT_TIME;
						while (value >= 0) {
							value--;
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						btnRestore.doClick();
					}
				}).start();
				removePropertyChangeListener(this);
			}
		};
		addPropertyChangeListener(timer);
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		add(getPanelNorth(), BorderLayout.NORTH);
		add(getPanelCenter(), BorderLayout.CENTER);
		add(getPanelSouth(), BorderLayout.SOUTH);
	}

	private JPanel getPanelNorth() {
		if (panelNorth == null) {
			panelNorth = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelNorth.getLayout();
			flowLayout.setVgap(25);
			panelNorth.setBackground(Color.WHITE);
			panelNorth.add(getLblLogo());
		}
		return panelNorth;
	}

	private JPanel getPanelCenter() {
		if (panelCenter == null) {
			panelCenter = new JPanel();
			panelCenter.setBackground(Color.WHITE);
			panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
			panelCenter.add(getPanelLabel());
			panelCenter.add(getPanelRestore());
		}
		return panelCenter;
	}

	private JPanel getPanelSouth() {
		if (panelSouth == null) {
			panelSouth = new JPanel();
			panelSouth.setBackground(Color.WHITE);
			panelSouth.add(getBtnRestore());
		}
		return panelSouth;
	}

	private JLabel getLblLogo() {
		if (lblLogo == null) {
			lblLogo = new JLabel("");
			ResizableImage.setResizeImage(this, lblLogo, "/img/logo.png", 400, 200);
		}
		return lblLogo;
	}

	private JLabel getLblThxreserve() {
		if (lblThxreserve == null) {
			lblThxreserve = new JLabel("<html><center>" + Internationalization.getString("thx_reserve"));
			lblThxreserve.setBackground(Color.WHITE);
			lblThxreserve.setFont(new Font("Tahoma", Font.BOLD, 30));
			lblThxreserve.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblThxreserve;
	}

	private JPanel getPanelLabel() {
		if (panelLabel == null) {
			panelLabel = new JPanel();
			panelLabel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
			panelLabel.setBackground(Color.WHITE);
			panelLabel.setLayout(new GridLayout(0, 1, 0, 0));
			panelLabel.add(getLblThxreserve());
		}
		return panelLabel;
	}

	private JPanel getPanelRestore() {
		if (panelRestore == null) {
			panelRestore = new JPanel();
			panelRestore.setBackground(Color.WHITE);
			panelRestore.setLayout(new GridLayout(0, 1, 0, 0));
			panelRestore.add(getLblRestore());
			panelRestore.setMaximumSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, 100));
			panelRestore.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, 100));

		}
		return panelRestore;
	}

	private JLabel getLblRestore() {
		if (lblRestore == null) {
			lblRestore = new JLabel(String.format(Internationalization.getString("restore_reserve"), DEFAULT_TIME));
			lblRestore.setBorder(new EmptyBorder(0, 10, 0, 0));
			lblRestore.setBackground(Color.WHITE);
			lblRestore.setHorizontalAlignment(SwingConstants.CENTER);
			lblRestore.setFont(new Font("Tahoma", Font.BOLD, 26));
		}
		return lblRestore;
	}

	private JButton getBtnRestore() {
		if (btnRestore == null) {
			btnRestore = new JButton(Internationalization.getString("restore_now")); //$NON-NLS-1$
			btnRestore.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					removePropertyChangeListener(timer);
					Window w = SwingUtilities.getWindowAncestor(FinishPanel.this);
					w.dispose();
					main.dispose();
					JFrame frame = new MainWindow();
					frame.setVisible(true);
				}
			});
			btnRestore.setBackground(Color.WHITE);
			btnRestore.setFont(new Font("Tahoma", Font.BOLD, 20));
		}
		return btnRestore;
	}
}
