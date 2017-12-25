package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JSpinnerDateEditor;

public class InitialWindow extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel panelCenter;
	private JPanel panelNorth;
	private JLabel lblLogo;
	private JPanel panelData;
	private JLabel lblCode;
	private JLabel lblDni;
	private JTextField txtCode;
	private JTextField txtDni;

	private JLabel lblLogUp;
	private JPanel panelLogIn;
	private JPanel panelButtonRestoreInfo;
	private JButton btnRestoreinfo;
	private JPanel panelTextLogIn;
	private JLabel lblIcoAccount;
	private JPanel panelSearch;
	private JPanel panelSouth;
	private JPanel panelPlace;
	private JPanel panelDate;
	private JPanel panelPeople;
	private JLabel lblPlace;
	private JTextField txtPlace;
	private JLabel lblStart;
	private JLabel lblFinish;
	private JLabel lblPeople;
	private JPanel panelAdult;
	private JPanel panelChild;
	private JLabel lblAdult;
	private JLabel lblChild;
	private JTextField txtNumberadult;
	private JTextField txtNumberchild;
	private JButton btnSearch;
	private JDateChooser dateChooser;
	private JPanel panelPeopleCount;
	private MainWindow mainWindow;
	
	public InitialWindow(MainWindow mainWindow, JPanel contentPane) {
		this.mainWindow = mainWindow;
		contentPane.add(getPanelNorth(), BorderLayout.NORTH);
		contentPane.add(getPanelCenter(), BorderLayout.CENTER);
		contentPane.add(getPanelSouth(), BorderLayout.SOUTH);
	}
	
	private JPanel getPanelNorth() {
		if (panelNorth == null) {
			panelNorth = new JPanel();
			panelNorth.setBackground(Color.WHITE);
			panelNorth.setLayout(new GridLayout(1, 0, 0, 0));
			panelNorth.add(getLblLogo());
			panelNorth.add(getPanelLogIn());
		}
		return panelNorth;
	}

	private JPanel getPanelCenter() {
		if (panelCenter == null) {
			panelCenter = new JPanel();
			panelCenter.setBackground(Color.WHITE);
			GridBagLayout gbl_panelCenter = new GridBagLayout();
			gbl_panelCenter.columnWidths = new int[] { 196, 0, 596, 0, 0 };
			gbl_panelCenter.rowHeights = new int[] { 200 };
			gbl_panelCenter.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
			gbl_panelCenter.rowWeights = new double[] { 0.0 };
			panelCenter.setLayout(gbl_panelCenter);
			GridBagConstraints gbc_panelSearch = new GridBagConstraints();
			gbc_panelSearch.fill = GridBagConstraints.BOTH;
			gbc_panelSearch.gridx = 2;
			gbc_panelSearch.gridy = 0;
			panelCenter.add(getPanelSearch(), gbc_panelSearch);
		}
		return panelCenter;
	}
	
	private JPanel getPanelSouth() {
		if (panelSouth == null) {
			panelSouth = new JPanel();
			panelSouth.setBackground(Color.WHITE);
			panelSouth.add(getBtnSearch());
		}
		return panelSouth;
	}

	private JLabel getLblLogo() {
		if (lblLogo == null) {
			lblLogo = new JLabel("");
			lblLogo.setBackground(Color.WHITE);
			lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
			ResizableImage.setResizeImage(this, lblLogo, "/img/logo.png", 250, 100);
		}
		return lblLogo;
	}

	private JPanel getPanelData() {
		if (panelData == null) {
			panelData = new JPanel();
			panelData.setBackground(Color.WHITE);
			panelData.setLayout(new GridLayout(2, 2, 0, 0));
			panelData.setBorder(UIManager.getBorder("Spinner.border"));
			panelData.add(getLblCode());
			panelData.add(getTxtCode());
			panelData.add(getLblDni());
			panelData.add(getTxtDni());
		}
		return panelData;
	}

	private JLabel getLblCode() {
		if (lblCode == null) {
			lblCode = new JLabel("code");
			lblCode.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblCode.setBackground(Color.WHITE);
			lblCode.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblCode;
	}

	private JLabel getLblDni() {
		if (lblDni == null) {
			lblDni = new JLabel("dni");
			lblDni.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblDni.setBackground(Color.WHITE);
			lblDni.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblDni;
	}

	private JTextField getTxtCode() {
		if (txtCode == null) {
			txtCode = new JTextField();
			txtCode.setBackground(Color.WHITE);
			txtCode.setHorizontalAlignment(SwingConstants.CENTER);
			txtCode.setText("code");
			txtCode.setColumns(10);
		}
		return txtCode;
	}

	private JTextField getTxtDni() {
		if (txtDni == null) {
			txtDni = new JTextField();
			txtDni.setBackground(Color.WHITE);
			txtDni.setHorizontalAlignment(SwingConstants.CENTER);
			txtDni.setText("dni");
			txtDni.setColumns(10);
		}
		return txtDni;
	}

	private JLabel getLblLogUp() {
		if (lblLogUp == null) {
			lblLogUp = new JLabel("isLogIn");
			lblLogUp.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblLogUp.setBackground(Color.WHITE);
		}
		return lblLogUp;
	}

	private JPanel getPanelLogIn() {
		if (panelLogIn == null) {
			panelLogIn = new JPanel();
			panelLogIn.setBackground(Color.WHITE);
			panelLogIn.setLayout(new GridLayout(0, 1, 0, 0));
			panelLogIn.setBorder(UIManager.getBorder("Spinner.border"));
			panelLogIn.add(getPanelTextLogIn());
			panelLogIn.add(getPanelData());
			panelLogIn.add(getPanelButtonRestoreInfo());
		}
		return panelLogIn;
	}

	private JPanel getPanelButtonRestoreInfo() {
		if (panelButtonRestoreInfo == null) {
			panelButtonRestoreInfo = new JPanel();
			panelButtonRestoreInfo.setBackground(Color.WHITE);
			FlowLayout flowLayout = (FlowLayout) panelButtonRestoreInfo.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			panelButtonRestoreInfo.add(getBtnRestoreinfo());
		}
		return panelButtonRestoreInfo;
	}

	private JButton getBtnRestoreinfo() {
		if (btnRestoreinfo == null) {
			btnRestoreinfo = new JButton("restoreInfo");
			btnRestoreinfo.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return btnRestoreinfo;
	}

	private JPanel getPanelTextLogIn() {
		if (panelTextLogIn == null) {
			panelTextLogIn = new JPanel();
			panelTextLogIn.setBackground(Color.WHITE);
			panelTextLogIn.add(getLblIcoAccount());
			panelTextLogIn.add(getLblLogUp());
		}
		return panelTextLogIn;
	}

	private JLabel getLblIcoAccount() {
		if (lblIcoAccount == null) {
			lblIcoAccount = new JLabel("");
			lblIcoAccount.setBackground(Color.WHITE);
			ResizableImage.setResizeImage(this, lblIcoAccount, "/img/user.png", 20, 20);
		}
		return lblIcoAccount;
	}

	private JPanel getPanelSearch() {
		if (panelSearch == null) {
			panelSearch = new JPanel();
			panelSearch.setBorder(UIManager.getBorder("Spinner.border"));
			panelSearch.setBackground(Color.WHITE);
			panelSearch.setLayout(new GridLayout(3, 1, 95, 10));
			panelSearch.add(getPanelPlace());
			panelSearch.add(getPanelDate());
			panelSearch.add(getPanelPeople());
		}
		return panelSearch;
	}

	private JPanel getPanelPlace() {
		if (panelPlace == null) {
			panelPlace = new JPanel();
			panelPlace.setBorder(UIManager.getBorder("Spinner.border"));
			panelPlace.setBackground(Color.WHITE);
			panelPlace.setLayout(new GridLayout(2, 1, 0, 0));
			panelPlace.add(getLblPlace());
			panelPlace.add(getTxtPlace());
		}
		return panelPlace;
	}

	private JPanel getPanelDate() {
		if (panelDate == null) {
			panelDate = new JPanel();
			panelDate.setBorder(UIManager.getBorder("Spinner.border"));
			panelDate.setBackground(Color.WHITE);
			panelDate.setLayout(new GridLayout(0, 2, 10, 0));
			panelDate.add(getLblStart());
			panelDate.add(getLblFinish());
			panelDate.add(new JDateChooser(null, new Date(), null, new JSpinnerDateEditor()));
			panelDate.add(new JDateChooser(null, new Date(System.currentTimeMillis() + 86400000), null, new JSpinnerDateEditor()));
		}
		return panelDate;
	}

	private JPanel getPanelPeople() {
		if (panelPeople == null) {
			panelPeople = new JPanel();
			panelPeople.setBorder(UIManager.getBorder("Spinner.border"));
			panelPeople.setBackground(Color.WHITE);
			panelPeople.setLayout(new GridLayout(2, 1, 0, 0));
			panelPeople.add(getLblPeople());
			panelPeople.add(getPanelPeopleCount());
		}
		return panelPeople;
	}

	private JLabel getLblPlace() {
		if (lblPlace == null) {
			lblPlace = new JLabel("place");
			lblPlace.setBackground(Color.WHITE);
			lblPlace.setHorizontalAlignment(SwingConstants.CENTER);
			lblPlace.setFont(new Font("Tahoma", Font.BOLD, 14));
		}
		return lblPlace;
	}

	private JTextField getTxtPlace() {
		if (txtPlace == null) {
			txtPlace = new JTextField();
			txtPlace.setHorizontalAlignment(SwingConstants.CENTER);
			txtPlace.setFont(new Font("Tahoma", Font.PLAIN, 13));
			txtPlace.setText("place");
			txtPlace.setColumns(10);
		}
		return txtPlace;
	}

	private JLabel getLblStart() {
		if (lblStart == null) {
			lblStart = new JLabel("start");
			lblStart.setBackground(UIManager.getColor("Button.background"));
			lblStart.setHorizontalAlignment(SwingConstants.CENTER);
			lblStart.setFont(new Font("Tahoma", Font.BOLD, 14));
		}
		return lblStart;
	}

	private JLabel getLblFinish() {
		if (lblFinish == null) {
			lblFinish = new JLabel("finish");
			lblFinish.setBackground(UIManager.getColor("Button.background"));
			lblFinish.setHorizontalAlignment(SwingConstants.CENTER);
			lblFinish.setFont(new Font("Tahoma", Font.BOLD, 14));
		}
		return lblFinish;
	}

	private JLabel getLblPeople() {
		if (lblPeople == null) {
			lblPeople = new JLabel("people");
			lblPeople.setBackground(Color.WHITE);
			lblPeople.setHorizontalAlignment(SwingConstants.CENTER);
			lblPeople.setFont(new Font("Tahoma", Font.BOLD, 14));
		}
		return lblPeople;
	}

	private JPanel getPanelAdult() {
		if (panelAdult == null) {
			panelAdult = new JPanel();
			panelAdult.setBorder(UIManager.getBorder("Spinner.border"));
			panelAdult.setBackground(Color.WHITE);
			panelAdult.add(getLblAdult());
			panelAdult.add(getTxtNumberadult());
		}
		return panelAdult;
	}

	private JPanel getPanelChild() {
		if (panelChild == null) {
			panelChild = new JPanel();
			panelChild.setBorder(UIManager.getBorder("Spinner.border"));
			panelChild.setBackground(Color.WHITE);
			panelChild.add(getLblChild());
			panelChild.add(getTxtNumberchild());
		}
		return panelChild;
	}

	private JLabel getLblAdult() {
		if (lblAdult == null) {
			lblAdult = new JLabel("adult");
			lblAdult.setFont(new Font("Tahoma", Font.BOLD, 14));
		}
		return lblAdult;
	}

	private JLabel getLblChild() {
		if (lblChild == null) {
			lblChild = new JLabel("child");
			lblChild.setFont(new Font("Tahoma", Font.BOLD, 14));
		}
		return lblChild;
	}

	private JTextField getTxtNumberadult() {
		if (txtNumberadult == null) {
			txtNumberadult = new JTextField();
			txtNumberadult.setFont(new Font("Tahoma", Font.PLAIN, 13));
			txtNumberadult.setText("numberAdult");
			txtNumberadult.setColumns(10);
		}
		return txtNumberadult;
	}

	private JTextField getTxtNumberchild() {
		if (txtNumberchild == null) {
			txtNumberchild = new JTextField();
			txtNumberchild.setFont(new Font("Tahoma", Font.PLAIN, 13));
			txtNumberchild.setText("numberChild");
			txtNumberchild.setColumns(10);
		}
		return txtNumberchild;
	}

	private JButton getBtnSearch() {
		if (btnSearch == null) {
			btnSearch = new JButton("search");
			btnSearch.setFont(new Font("Tahoma", Font.BOLD, 18));
			//btnSearch.addActionListener();
		}
		return btnSearch;
	}

	private JDateChooser getDateChooser() {
		if (dateChooser == null) {
			dateChooser = new JDateChooser(null, null, null, new JSpinnerDateEditor());;
		}
		return dateChooser;
	}

	private JPanel getPanelPeopleCount() {
		if (panelPeopleCount == null) {
			panelPeopleCount = new JPanel();
			panelPeopleCount.setBackground(Color.WHITE);
			panelPeopleCount.add(getPanelAdult());
			panelPeopleCount.add(getPanelChild());
		}
		return panelPeopleCount;
	}
}
