package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import internationalization.Internationalization;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Toolkit;

public class LogUpWindow extends JDialog {
	
	private MainWindow mainWindow;
	private JPanel contentPane;
	private JPanel panelNorth;
	private JLabel lblLogo;
	private JPanel panelBottom;
	private JButton btnCancel;
	private JButton btnFinish;
	private JPanel panelCenter;
	private JLabel lblName;
	private JLabel lblSurname;
	private JTextField txName;
	private JTextField txSurname;
	private JLabel lblDni;
	private JTextField txDni;
	private JLabel lblObs;
	private JTextArea txObs;

	
	public LogUpWindow(MainWindow mainWindow) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LogUpWindow.class.getResource("/img/user.png")));
		setTitle(Internationalization.getString("LogUpWindow.this.title")); //$NON-NLS-1$
		setResizable(false);
		this.mainWindow = mainWindow;
		this.contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanelNorth(), BorderLayout.NORTH);
		contentPane.add(getPanelCenter(), BorderLayout.CENTER);
		contentPane.add(getBottomPanel(), BorderLayout.SOUTH);
		setContentPane(contentPane);
		setModal(true);
		setBounds(0,0,1028, 561);
		setLocationRelativeTo(null);
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

	private JPanel getBottomPanel() {
		if (panelBottom == null) {
			panelBottom = new JPanel();
			panelBottom.setBackground(Color.WHITE);
			FlowLayout fl_panelBottom = (FlowLayout) panelBottom.getLayout();
			fl_panelBottom.setAlignment(FlowLayout.RIGHT);
			panelBottom.add(getBtnCancel());
			panelBottom.add(getBtnFinish());
		}
		return panelBottom;
	}
	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton(Internationalization.getString("log_cancel"));
			btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnCancel.setToolTipText(Internationalization.getToolTips("log_cancel"));
		}
		return btnCancel;
	}
	private JButton getBtnFinish() {
		if (btnFinish == null) {
			btnFinish = new JButton(Internationalization.getString("log_finish"));
			btnFinish.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnFinish.setToolTipText(Internationalization.getToolTips("log_finish"));
		}
		return btnFinish;
	}
	private JPanel getPanelCenter() {
		if (panelCenter == null) {
			panelCenter = new JPanel();
			panelCenter.setBackground(Color.WHITE);
			GroupLayout gl_panelCenter = new GroupLayout(panelCenter);
			gl_panelCenter.setHorizontalGroup(
				gl_panelCenter.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_panelCenter.createSequentialGroup()
						.addGap(217)
						.addGroup(gl_panelCenter.createParallelGroup(Alignment.LEADING)
							.addComponent(getLblName())
							.addComponent(getLblDni())
							.addComponent(getLblSurname())
							.addComponent(getLblObs()))
						.addGap(57)
						.addGroup(gl_panelCenter.createParallelGroup(Alignment.LEADING)
							.addComponent(getTxObs(), GroupLayout.PREFERRED_SIZE, 412, Short.MAX_VALUE)
							.addComponent(getTxSurname(), GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
							.addComponent(getTxDNI(), GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
							.addComponent(getTxName(), GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE))
						.addGap(209))
			);
			gl_panelCenter.setVerticalGroup(
				gl_panelCenter.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelCenter.createSequentialGroup()
						.addGap(58)
						.addGroup(gl_panelCenter.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_panelCenter.createSequentialGroup()
								.addGap(3)
								.addComponent(getLblName()))
							.addComponent(getTxName(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelCenter.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panelCenter.createSequentialGroup()
								.addGap(35)
								.addComponent(getLblSurname()))
							.addGroup(gl_panelCenter.createSequentialGroup()
								.addGap(32)
								.addComponent(getTxSurname(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panelCenter.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panelCenter.createSequentialGroup()
								.addGap(42)
								.addComponent(getLblDni()))
							.addGroup(gl_panelCenter.createSequentialGroup()
								.addGap(39)
								.addComponent(getTxDNI(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGap(35)
						.addGroup(gl_panelCenter.createParallelGroup(Alignment.BASELINE)
							.addComponent(getLblObs())
							.addComponent(getTxObs(), GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(150, Short.MAX_VALUE))
			);
			gl_panelCenter.setAutoCreateContainerGaps(true);
			gl_panelCenter.setAutoCreateGaps(true);
			panelCenter.setLayout(gl_panelCenter);
		}
		return panelCenter;
	}
	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel(Internationalization.getString("log_name"));
			lblName.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblName.setToolTipText(Internationalization.getToolTips("log_name"));
			lblName.setLabelFor(txName);
			lblName.setDisplayedMnemonic(Internationalization.getMnemonic("log_name"));
		}
		return lblName;
	}
	private JLabel getLblSurname() {
		if (lblSurname == null) {
			lblSurname = new JLabel(Internationalization.getString("log_surname"));
			lblSurname.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblSurname.setToolTipText(Internationalization.getToolTips("log_surname"));
			lblSurname.setLabelFor(txSurname);
			lblSurname.setDisplayedMnemonic(Internationalization.getMnemonic("log_surname"));
		}
		return lblSurname;
	}
	private JTextField getTxName() {
		if (txName == null) {
			txName = new JTextField();
			txName.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txName.setColumns(10);
		}
		return txName;
	}
	private JTextField getTxSurname() {
		if (txSurname == null) {
			txSurname = new JTextField();
			txSurname.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txSurname.setColumns(10);
		}
		return txSurname;
	}
	private JLabel getLblDni() {
		if (lblDni == null) {
			lblDni = new JLabel(Internationalization.getString("log_dni"));
			lblDni.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblDni.setToolTipText(Internationalization.getToolTips("log_dni"));
			lblDni.setLabelFor(txDni);
			lblDni.setDisplayedMnemonic(Internationalization.getMnemonic("log_dni"));
		}
		return lblDni;
	}
	private JTextField getTxDNI() {
		if (txDni == null) {
			txDni = new JTextField();
			txDni.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txDni.setColumns(10);
		}
		return txDni;
	}
	private JLabel getLblObs() {
		if (lblObs == null) {
			lblObs = new JLabel(Internationalization.getString("log_obs"));
			lblObs.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblObs.setToolTipText(Internationalization.getToolTips("log_obs"));
			lblObs.setLabelFor(txObs);
			lblObs.setDisplayedMnemonic(Internationalization.getMnemonic("log_obs"));
		}
		return lblObs;
	}
	private JTextArea getTxObs() {
		if (txObs == null) {
			txObs = new JTextArea();
			txObs.setBorder(UIManager.getBorder("TextField.border"));
			txObs.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txObs.setWrapStyleWord(true);
			txObs.setLineWrap(true);
		}
		return txObs;
	}
}
