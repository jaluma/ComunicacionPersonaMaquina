package gui;

import javax.swing.JPanel;

import internationalization.Internationalization;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import event.NumberTextFieldFormatEvent;

import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FinishWindow extends JPanel {

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

	/**
	 * Create the panel.
	 */
	public FinishWindow(MainWindow main) {
		this.main = main;
		addPropertyChangeListener(new PropertyChangeListener() {
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
			}
		});
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		add(getPanelNorth(), BorderLayout.NORTH);
		add(getPanelCenter(), BorderLayout.CENTER);
		add(getPanelSouth(), BorderLayout.SOUTH);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{getBtnRestore()}));
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
					Window w = SwingUtilities.getWindowAncestor(FinishWindow.this);
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
