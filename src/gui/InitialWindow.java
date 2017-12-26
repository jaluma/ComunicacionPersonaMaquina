package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JSpinnerDateEditor;

import event.FocusTextFieldEvent;
import event.NumberTextFieldFormatEvent;
import fileUtil.StringUtil;
import internationalization.Internationalization;
import logic.ListProduct;

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
	private JPanel panelPeopleCount;
	private JComboBox<String> comboBox;
	private MainWindow mainWindow;
	private JPanel contentPane;
	private DefaultComboBoxModel<String> modelPlace;
	JSpinnerDateEditor spinnerDateEditor;
	JSpinnerDateEditor spinnerDateEditor_1;
	

	public InitialWindow(MainWindow mainWindow, JPanel contentPane) {
		this.mainWindow = mainWindow;
		this.contentPane = contentPane;
		modelPlace = new DefaultComboBoxModel<String>(ListProduct.loadPlaces());
		this.contentPane.setLayout(new BorderLayout(0, 0));
		this.contentPane.add(getPanelNorth(), BorderLayout.NORTH);
		this.contentPane.add(getPanelCenter(), BorderLayout.CENTER);
		this.contentPane.add(getPanelSouth(), BorderLayout.SOUTH);
		this.mainWindow.setResizable(false);
		comboBox.requestFocus();
		mainWindow.getRootPane().setDefaultButton(btnSearch);
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
			panelCenter.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 50));
			panelCenter.add(getPanelSearch());
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
			lblCode = new JLabel(Internationalization.getString("date"));
			lblCode.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblCode.setBackground(Color.WHITE);
			lblCode.setLabelFor(txtCode);
			lblCode.setDisplayedMnemonic(Internationalization.getMnemonic("code"));
			lblCode.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblCode;
	}

	private JLabel getLblDni() {
		if (lblDni == null) {
			lblDni = new JLabel(Internationalization.getString("dni"));
			lblDni.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblDni.setBackground(Color.WHITE);
			lblDni.setLabelFor(txtDni);
			lblDni.setDisplayedMnemonic(Internationalization.getMnemonic("date"));
			lblDni.setHorizontalAlignment(SwingConstants.CENTER);
			
		}
		return lblDni;
	}

	private JTextField getTxtCode() {
		if (txtCode == null) {
			txtCode = new JTextField();
			txtCode.setBackground(Color.WHITE);
			txtCode.setHorizontalAlignment(SwingConstants.CENTER);
			txtCode.setForeground(Color.DARK_GRAY);
			txtCode.setText(Internationalization.getString("date").toLowerCase());
			txtCode.setColumns(10);
			txtCode.addFocusListener(new FocusTextFieldEvent("date"));
		}
		return txtCode;
	}

	private JTextField getTxtDni() {
		if (txtDni == null) {
			txtDni = new JTextField();
			txtDni.setBackground(Color.WHITE);
			txtDni.setHorizontalAlignment(SwingConstants.CENTER);
			txtDni.setForeground(Color.DARK_GRAY);
			txtDni.setText(Internationalization.getString("dni").toLowerCase());
			txtDni.setColumns(10);
			txtDni.addFocusListener(new FocusTextFieldEvent("dni"));
		}
		return txtDni;
	}

	private JLabel getLblLogUp() {
		if (lblLogUp == null) {
			lblLogUp = new JLabel(Internationalization.getString("log_in"));
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
			btnRestoreinfo = new JButton(Internationalization.getString("restore_info"));
			btnRestoreinfo.setMnemonic(Internationalization.getMnemonic("restore_info"));
			btnRestoreinfo.setHorizontalAlignment(SwingConstants.RIGHT);
			btnRestoreinfo.setFont(new Font("Tahoma", Font.BOLD, 16));
			btnRestoreinfo.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					//check dnio
					String text = txtDni.getText();
					try {
					// no dni length
					if (text.length() != 9)
						throw new NumberFormatException();
					// first 8 chracters no Digit
					Integer.parseInt(text.substring(0, text.length() - 1));
					} catch(NumberFormatException exc) {
						JOptionPane.showMessageDialog(mainWindow, Internationalization.getString("error_dni"),
								Internationalization.getString("error_dni_title"), JOptionPane.WARNING_MESSAGE);
					}
					
//					Order order = ListOrders.search(new Date(txtCode.getText()), txtDni.getText());
//					if (order != null) {
//						JDialog dialog = new CartWindow(InitialWindow.this.mainWindow);
//						dialog.setVisible(true);
//					} else {
//						JOptionPane.showMessageDialog(mainWindow, Internationalization.getString("error_search_order"),
//								Internationalization.getString("error_search_order_title"),
//								JOptionPane.WARNING_MESSAGE);
//					}
					
				}
			});
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
			panelPlace.add(getComboBox());
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
			spinnerDateEditor = new JSpinnerDateEditor();
			spinnerDateEditor.setFont(new Font("Tahoma", Font.BOLD, 14));
			JDateChooser jd = new JDateChooser(null, new Date(), null, spinnerDateEditor);
			getLblStart().setLabelFor(jd);
			jd.setMinSelectableDate(new Date(System.currentTimeMillis() - 86400000));
			panelDate.add(jd);
			spinnerDateEditor_1 = new JSpinnerDateEditor();
			spinnerDateEditor_1.setFont(new Font("Tahoma", Font.BOLD, 14));
			JDateChooser jdN = new JDateChooser(null, new Date(System.currentTimeMillis() + 86400000), null, spinnerDateEditor_1);
			getLblFinish().setLabelFor(jdN);
			jdN.setMinSelectableDate(new Date(System.currentTimeMillis()));
			panelDate.add(jdN);
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
			lblPlace = new JLabel(Internationalization.getString("place"));
			lblPlace.setDisplayedMnemonic(Internationalization.getMnemonic("place"));
			lblPlace.setLabelFor(getComboBox());
			lblPlace.setBackground(Color.WHITE);
			lblPlace.setHorizontalAlignment(SwingConstants.CENTER);
			lblPlace.setFont(new Font("Tahoma", Font.BOLD, 14));
		}
		return lblPlace;
	}

	private JLabel getLblStart() {
		if (lblStart == null) {
			lblStart = new JLabel(Internationalization.getString("start"));
			lblStart.setDisplayedMnemonic(Internationalization.getMnemonic("start"));
			lblStart.setBackground(UIManager.getColor("Button.background"));
			lblStart.setHorizontalAlignment(SwingConstants.CENTER);
			lblStart.setFont(new Font("Tahoma", Font.BOLD, 14));
		}
		return lblStart;
	}

	private JLabel getLblFinish() {
		if (lblFinish == null) {
			lblFinish = new JLabel(Internationalization.getString("finish"));
			lblFinish.setDisplayedMnemonic(Internationalization.getMnemonic("finish"));
			lblFinish.setBackground(UIManager.getColor("Button.background"));
			lblFinish.setHorizontalAlignment(SwingConstants.CENTER);
			lblFinish.setFont(new Font("Tahoma", Font.BOLD, 14));
		}
		return lblFinish;
	}

	private JLabel getLblPeople() {
		if (lblPeople == null) {
			lblPeople = new JLabel(Internationalization.getString("number_people"));
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
			lblAdult = new JLabel(Internationalization.getString("number_adult"));
			lblAdult.setHorizontalAlignment(SwingConstants.CENTER);
			lblAdult.setDisplayedMnemonic(Internationalization.getMnemonic("number_adult"));
			lblAdult.setLabelFor(getTxtNumberadult());
			lblAdult.setFont(new Font("Tahoma", Font.BOLD, 14));
		}
		return lblAdult;
	}

	private JLabel getLblChild() {
		if (lblChild == null) {
			lblChild = new JLabel(Internationalization.getString("number_child"));
			lblChild.setHorizontalAlignment(SwingConstants.CENTER);
			lblChild.setDisplayedMnemonic(Internationalization.getMnemonic("number_child"));
			lblChild.setLabelFor(getTxtNumberchild());
			lblChild.setFont(new Font("Tahoma", Font.BOLD, 14));
		}
		return lblChild;
	}

	private JTextField getTxtNumberadult() {
		if (txtNumberadult == null) {
			txtNumberadult = new JTextField();
			txtNumberadult.setFont(new Font("Tahoma", Font.PLAIN, 13));
			txtNumberadult.setColumns(10);
			txtNumberadult.addKeyListener(new NumberTextFieldFormatEvent());
		}
		return txtNumberadult;
	}

	private JTextField getTxtNumberchild() {
		if (txtNumberchild == null) {
			txtNumberchild = new JTextField();
			txtNumberchild.setFont(new Font("Tahoma", Font.PLAIN, 13));
			txtNumberchild.setColumns(10);
			txtNumberchild.addKeyListener(new NumberTextFieldFormatEvent());
		}
		return txtNumberchild;
	}

	private JButton getBtnSearch() {
		if (btnSearch == null) {
			btnSearch = new JButton(Internationalization.getString("search"));
			btnSearch.setFont(new Font("Tahoma", Font.BOLD, 36));
			//btnSearch.setMnemonic(Internationalization.getMnemonic("search"));
			btnSearch.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					//algun campo vacio
					if (modelPlace.getIndexOf(StringUtil.formatSentece((String)comboBox.getSelectedItem())) == -1)
						JOptionPane.showMessageDialog(mainWindow, Internationalization.getString("error_place"),
								Internationalization.getString("error_place_title"),
								JOptionPane.WARNING_MESSAGE);
					else if (txtNumberadult.getText().isEmpty())
						JOptionPane.showMessageDialog(mainWindow, Internationalization.getString("error_number_adult"),
								Internationalization.getString("error_number_adult_title"),
								JOptionPane.WARNING_MESSAGE);
					else if (txtNumberchild.getText().isEmpty())
						JOptionPane.showMessageDialog(mainWindow, Internationalization.getString("error_number_child"),
								Internationalization.getString("error_number_child_title"),
								JOptionPane.WARNING_MESSAGE);
					else {	//valores correctos, pasamos a siguiente ventana
						//update values mainWindow
						mainWindow.setNumberAdult(Integer.parseInt(txtNumberadult.getText()));
						mainWindow.setNumberChild(Integer.parseInt(txtNumberchild.getText()));
						mainWindow.setDate(spinnerDateEditor.getDate());

						long diff = Math.abs(spinnerDateEditor_1.getDate().getTime() - spinnerDateEditor.getDate().getTime());
						long diffDays = diff / (24 * 60 * 60 * 1000);
						mainWindow.setDays((int)diffDays);
						mainWindow.setMinimumSize(new Dimension(1200, 650));
						mainWindow.setLocationRelativeTo(null);
						contentPane.removeAll();
						mainWindow.setProductListPanel(new ProductListWindow(InitialWindow.this.mainWindow, contentPane));
						contentPane.repaint();
						contentPane.revalidate();
						mainWindow.setResizable(true);
						mainWindow.setExtendedState(mainWindow.getExtendedState() | Frame.MAXIMIZED_BOTH);
					}
					
				}
			});
		}
		return btnSearch;
	}

	private JPanel getPanelPeopleCount() {
		if (panelPeopleCount == null) {
			panelPeopleCount = new JPanel();
			panelPeopleCount.setBackground(Color.WHITE);
			panelPeopleCount.setLayout(new GridLayout(0, 2, 0, 0));
			panelPeopleCount.add(getPanelAdult());
			panelPeopleCount.add(getPanelChild());
		}
		return panelPeopleCount;
	}
	private JComboBox<String> getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox<String>();
			comboBox.setFont(new Font("Tahoma", Font.BOLD, 13));
			comboBox.setEditable(true);
			comboBox.setBackground(Color.WHITE);
			comboBox.setModel(modelPlace);
		}
		return comboBox;
	}
	
	public String getSelectedItem() {
		return String.valueOf(comboBox.getSelectedItem());
	}
}
