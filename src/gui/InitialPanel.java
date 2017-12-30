package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.DataFormatException;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import event.FocusTextFieldEvent;
import event.NumberTextFieldFormatEvent;
import fileUtil.IncorrectOrderException;
import fileUtil.StringUtil;
import guiUtil.ResizableImage;
import internationalization.Internationalization;
import order.ListOrders;
import order.Order;
import product.ListProduct;

public class InitialPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JPanel panelCenter;
	private JPanel panelNorth;
	private JLabel lblLogo;
	private JPanel panelData;
	private JLabel lblCode;
	private JLabel lblDni;
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
	private DefaultComboBoxModel<String> modelPlace;
	private JDateChooser dateArrive;
	private JDateChooser dateExit;

	private JDateChooser dateChooser;
	private JPanel panelLblDate;
	private JPanel paneltxDate;
	private JPanel panelLblDni;
	private JPanel panelTxDni;
	private JPanel panelLblAdult;
	private JPanel panelTxNumberAdult;
	private JPanel paneLblNumberChild;
	private JPanel panelTxNumberChild;

	public InitialPanel(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		modelPlace = new DefaultComboBoxModel<String>(ListProduct.loadPlaces());
		setLayout(new BorderLayout(0, 0));
		add(getPanelNorth(), BorderLayout.NORTH);
		add(getPanelCenter(), BorderLayout.CENTER);
		add(getPanelSouth(), BorderLayout.SOUTH);
		this.mainWindow.setResizable(false);
		this.mainWindow.getRootPane().setDefaultButton(btnSearch);
		setFocusTraversalPolicy(
				new FocusTraversalOnArray(new Component[] { getDateChooser(), getTxtDni(), getBtnRestoreinfo(),
						getComboBox(), getTxtNumberadult(), getTxtNumberchild(), getDateArrive(), getDateExit() }));
		addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				getComboBox().grabFocus();
			}
		});
	}

	private JPanel getPanelNorth() {
		if (panelNorth == null) {
			panelNorth = new JPanel();
			panelNorth.setBackground(Color.WHITE);
			panelNorth.setLayout(new BorderLayout(0, 0));
			panelNorth.add(getLblLogo(), BorderLayout.WEST);
			panelNorth.add(getPanelLogIn(), BorderLayout.EAST);
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
			ResizableImage.setResizeImage(this, lblLogo, "/img/logo.png", 300, 150);
		}
		return lblLogo;
	}

	private JPanel getPanelData() {
		if (panelData == null) {
			panelData = new JPanel();
			panelData.setBackground(Color.WHITE);
			panelData.setBorder(UIManager.getBorder("Spinner.border"));
			panelData.setLayout(new GridLayout(2, 2, 2, 2));
			panelData.add(getPanelLblDate());
			panelData.add(getPaneltxDate());
			panelData.add(getPanelLblDni());
			panelData.add(getPanelTxDni());
		}
		return panelData;
	}

	private JLabel getLblCode() {
		if (lblCode == null) {
			lblCode = new JLabel(Internationalization.getString("date"));
			lblCode.setLabelFor(getDateChooser());
			lblCode.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblCode.setBackground(Color.WHITE);
			lblCode.setDisplayedMnemonic(Internationalization.getMnemonic("date"));
			lblCode.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblCode;
	}

	private JLabel getLblDni() {
		if (lblDni == null) {
			lblDni = new JLabel(Internationalization.getString("dni"));
			lblDni.setLabelFor(getTxtDni());
			lblDni.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblDni.setBackground(Color.WHITE);
			lblDni.setDisplayedMnemonic(Internationalization.getMnemonic("dni"));
			lblDni.setHorizontalAlignment(SwingConstants.CENTER);

		}
		return lblDni;
	}

	private JTextField getTxtDni() {
		if (txtDni == null) {
			txtDni = new JTextField();
			txtDni.setFont(new Font("Tahoma", Font.BOLD, 13));
			txtDni.setBackground(Color.WHITE);
			txtDni.setHorizontalAlignment(SwingConstants.CENTER);
			txtDni.setForeground(Color.DARK_GRAY);
			txtDni.setMaximumSize(new Dimension(500, 20));
			txtDni.setPreferredSize(new Dimension(500, 20));
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
			panelLogIn.setBorder(UIManager.getBorder("Spinner.border"));
			panelLogIn.setLayout(new BoxLayout(panelLogIn, BoxLayout.Y_AXIS));
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
			flowLayout.setAlignment(FlowLayout.CENTER);
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
					// check dnio
					String text = txtDni.getText();
					try {
						// no dni length
						if (text.length() != 9)
							throw new NumberFormatException();
						// first 8 chracters no Digit
						Integer.parseInt(text.substring(0, text.length() - 1));

						Order order = ListOrders.search(getDateChooser().getDate(), txtDni.getText());

						mainWindow.setOrder(order);

						CartDialog dialog = new CartDialog(mainWindow, true);
						dialog.setVisible(true);

					} catch (NumberFormatException exc) {
						JOptionPane.showMessageDialog(mainWindow, Internationalization.getString("error_dni"),
								Internationalization.getString("error_dni_title"), JOptionPane.WARNING_MESSAGE);
					} catch (IncorrectOrderException exc1) {
						JOptionPane.showMessageDialog(mainWindow, Internationalization.getString("error_search_order"),
								Internationalization.getString("error_search_order_title"),
								JOptionPane.WARNING_MESSAGE);
					} catch (DataFormatException e1) {
						JOptionPane.showMessageDialog(mainWindow, Internationalization.getString("error_date"),
								Internationalization.getString("error_date_title"), JOptionPane.WARNING_MESSAGE);
					}
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
			panelSearch.add(getPanelPeople());
			panelSearch.add(getPanelDate());
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
			panelDate.setLayout(new GridLayout(2, 2, 10, 0));
			panelDate.add(getLblStart());
			panelDate.add(getLblFinish());
			// panelDate.add(getCalendarArrive());
			// panelDate.add(getCalendarExit());
			panelDate.add(getDateArrive());
			panelDate.add(getDateExit());
		}
		return panelDate;
	}

	private JDateChooser getDateArrive() {
		if (dateArrive == null) {
			dateArrive = new JDateChooser();
			dateArrive.setToolTipText(lblStart.getToolTipText());
			dateArrive.setFont(new Font("Tahoma", Font.BOLD, 13));
			JTextFieldDateEditor dateEditor = (JTextFieldDateEditor) dateArrive.getDateEditor();
			dateEditor.setHorizontalAlignment(JTextField.CENTER);
			// dateArrive.getCalendarButton().addChangeListener(new ChangeListener() {
			// @Override
			// public void stateChanged(ChangeEvent e) {
			// getDateExit().setDate(new Date(dateArrive.getDate().getTime() + 86400000));
			// getDateExit().setMinSelectableDate(new Date(dateArrive.getDate().getTime() +
			// 86400000));
			// }
			// });
			Date date = new Date(System.currentTimeMillis());
			dateArrive.setDate(date);
			dateArrive.setMinSelectableDate(new Date());
			dateArrive.setDateFormatString(
					((SimpleDateFormat) DateFormat.getDateInstance(DateFormat.SHORT, Internationalization.getLocate()))
							.toLocalizedPattern());
			dateArrive.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent arg0) {
					if (dateArrive.getDate().getTime() >= getDateExit().getDate().getTime()) {
						Date date2 = new Date(dateArrive.getDate().getTime() + 86400000);
						getDateExit().setDate(date2);
						getDateExit().setMinSelectableDate(date2);
					}
				}
			});
		}
		return dateArrive;
	}

	private JDateChooser getDateExit() {
		if (dateExit == null) {
			dateExit = new JDateChooser();
			dateExit.setToolTipText(getLblFinish().getToolTipText());
			dateExit.setFont(new Font("Tahoma", Font.BOLD, 13));
			JTextFieldDateEditor dateEditor = (JTextFieldDateEditor) dateExit.getDateEditor();
			dateEditor.setHorizontalAlignment(JTextField.CENTER);
			Date date = new Date(dateArrive.getDate().getTime() + 86400000);
			dateExit.setDate(date);
			dateExit.setMinSelectableDate(date);
			dateExit.setDateFormatString(
					((SimpleDateFormat) DateFormat.getDateInstance(DateFormat.SHORT, Internationalization.getLocate()))
							.toLocalizedPattern());
		}
		return dateExit;
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
			lblPlace.setToolTipText(Internationalization.getToolTips("place"));
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
			lblStart.setLabelFor(getDateArrive());
			lblStart.setDisplayedMnemonic(Internationalization.getMnemonic("start"));
			lblStart.setToolTipText(Internationalization.getToolTips("start"));
			lblStart.setBackground(UIManager.getColor("Button.background"));
			lblStart.setHorizontalAlignment(SwingConstants.CENTER);
			lblStart.setFont(new Font("Tahoma", Font.BOLD, 14));
		}
		return lblStart;
	}

	private JLabel getLblFinish() {
		if (lblFinish == null) {
			lblFinish = new JLabel(Internationalization.getString("finish"));
			lblFinish.setLabelFor(getDateExit());
			lblFinish.setDisplayedMnemonic(Internationalization.getMnemonic("finish"));
			lblFinish.setToolTipText(Internationalization.getToolTips("finish"));
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
			panelAdult.setLayout(new BoxLayout(panelAdult, BoxLayout.X_AXIS));
			panelAdult.add(getPanelLblAdult());
			panelAdult.add(getPanelTxNumberAdult());
		}
		return panelAdult;
	}

	private JPanel getPanelChild() {
		if (panelChild == null) {
			panelChild = new JPanel();
			panelChild.setBorder(UIManager.getBorder("Spinner.border"));
			panelChild.setBackground(Color.WHITE);
			panelChild.setLayout(new BoxLayout(panelChild, BoxLayout.X_AXIS));
			panelChild.add(getPaneLblNumberChild());
			panelChild.add(getPanelTxNumberChild());
		}
		return panelChild;
	}

	private JLabel getLblAdult() {
		if (lblAdult == null) {
			lblAdult = new JLabel(Internationalization.getString("number_adult"));
			lblAdult.setHorizontalAlignment(SwingConstants.CENTER);
			lblAdult.setDisplayedMnemonic(Internationalization.getMnemonic("number_adult"));
			lblAdult.setToolTipText(Internationalization.getToolTips("number_adult"));
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
			lblChild.setToolTipText(Internationalization.getToolTips("number_child"));
			lblChild.setLabelFor(getTxtNumberchild());
			lblChild.setFont(new Font("Tahoma", Font.BOLD, 14));
		}
		return lblChild;
	}

	private JTextField getTxtNumberadult() {
		if (txtNumberadult == null) {
			txtNumberadult = new JTextField();
			txtNumberadult.setHorizontalAlignment(SwingConstants.CENTER);
			txtNumberadult.setText("0"); //$NON-NLS-1$
			txtNumberadult.setForeground(Color.DARK_GRAY);
			txtNumberadult.setFont(new Font("Tahoma", Font.BOLD, 13));
			txtNumberadult.setColumns(10);
			txtNumberadult.setToolTipText(getLblAdult().getToolTipText());
			txtNumberadult.addKeyListener(new NumberTextFieldFormatEvent());
			txtNumberadult.addFocusListener(new FocusTextFieldEvent("0"));
			txtNumberadult.addKeyListener(new KeyAdapter() {

				public void keyTyped(KeyEvent e) {
					if (txtNumberadult.getText().length() >= 3) {// limit textfield to 3 characters
						e.consume();
						Toolkit.getDefaultToolkit().beep();
					}
				}
			});
		}
		return txtNumberadult;
	}

	private JTextField getTxtNumberchild() {
		if (txtNumberchild == null) {
			txtNumberchild = new JTextField();
			txtNumberchild.setHorizontalAlignment(SwingConstants.CENTER);
			txtNumberchild.setText("0"); //$NON-NLS-1$
			txtNumberchild.setForeground(Color.DARK_GRAY);
			txtNumberchild.setFont(new Font("Tahoma", Font.BOLD, 13));
			txtNumberchild.setColumns(10);
			txtNumberchild.setToolTipText(getLblChild().getToolTipText());
			txtNumberchild.addKeyListener(new NumberTextFieldFormatEvent());
			txtNumberchild.addFocusListener(new FocusTextFieldEvent("0"));
			txtNumberchild.addKeyListener(new KeyAdapter() {

				public void keyTyped(KeyEvent e) {
					if (txtNumberchild.getText().length() >= 3) { // limit textfield to 3 characters
						e.consume();
						Toolkit.getDefaultToolkit().beep();
					}
				}
			});
		}
		return txtNumberchild;
	}

	private JButton getBtnSearch() {
		if (btnSearch == null) {
			btnSearch = new JButton();
			btnSearch.setText(Internationalization.getString("search"));
			btnSearch.setToolTipText(Internationalization.getToolTips("search"));
			btnSearch.setFont(new Font("Tahoma", Font.BOLD, 36));
			btnSearch.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// algun campo vacio
					if (modelPlace.getIndexOf(StringUtil.formatSentece((String) comboBox.getSelectedItem())) == -1)
						JOptionPane.showMessageDialog(mainWindow, Internationalization.getString("error_place"),
								Internationalization.getString("error_place_title"), JOptionPane.WARNING_MESSAGE);
					else if (Integer.parseInt(txtNumberadult.getText()) <= 0)
						JOptionPane.showMessageDialog(mainWindow, Internationalization.getString("error_number_adult"),
								Internationalization.getString("error_number_adult_title"),
								JOptionPane.WARNING_MESSAGE);
					else if (Integer.parseInt(txtNumberadult.getText()) <= 0
							&& Integer.parseInt(txtNumberchild.getText()) <= 0)
						JOptionPane.showMessageDialog(mainWindow, Internationalization.getString("error_number_child"),
								Internationalization.getString("error_number_child_title"),
								JOptionPane.WARNING_MESSAGE);
					else { // valores correctos, pasamos a siguiente ventana
						try {
							checkDate(dateArrive);
							checkDate(dateExit);
							// update values mainWindow
							btnSearch.setEnabled(false);
							loadListProduct();
						} catch (NullPointerException e) {
							JOptionPane.showMessageDialog(mainWindow, Internationalization.getString("error_date"),
									Internationalization.getString("error_date_title"), JOptionPane.WARNING_MESSAGE);
						}
					}

				}

				protected void checkDate(JDateChooser date) {
					long time1 = date.getDate().getTime();
					String timeA = String.valueOf(time1).substring(0, 4) + "0000000";
					time1 = Long.valueOf(timeA);
					long time2 = date.getMinSelectableDate().getTime();
					String timeB = String.valueOf(time2).substring(0, 4) + "0000000";
					time2 = Long.valueOf(timeB);

					if (time1 < time2)
						throw new NullPointerException();
				}

			});
		}
		return btnSearch;
	}

	protected void loadListProduct() {
		btnSearch.revalidate();
		btnSearch.repaint();
		mainWindow.mntmFullscreen.setEnabled(true);
		mainWindow.rdbtnmntmPanelfilter.setEnabled(true);
		mainWindow.mnSort.setEnabled(true);
		mainWindow.mntmPeople.setEnabled(true);
		mainWindow.mntmPlace.setEnabled(true);
		mainWindow.mntmPrice.setEnabled(true);
		mainWindow.mntmStars.setEnabled(true);
		mainWindow.mntmOnlyphotos.setEnabled(true);
		mainWindow.mntmCart.setEnabled(true);

		mainWindow.setNumberAdult(Integer.parseInt(txtNumberadult.getText()));
		mainWindow.setNumberChild(Integer.parseInt(txtNumberchild.getText()));
		mainWindow.setDate(dateArrive.getDate());
		mainWindow.setDateFinish(dateExit.getDate());

		removeAll();
		mainWindow.setProductListPanel(new ProductListPanel(InitialPanel.this.mainWindow));
		add(mainWindow.getProductListPanel());
		repaint();
		revalidate();
		mainWindow.setResizable(true);
		mainWindow.setExtendedState(mainWindow.getExtendedState() | Frame.MAXIMIZED_BOTH);
	}

	private JPanel getPanelPeopleCount() {
		if (panelPeopleCount == null) {
			panelPeopleCount = new JPanel();
			panelPeopleCount.setBackground(Color.WHITE);
			panelPeopleCount.setLayout(new GridLayout(0, 2, 10, 0));
			panelPeopleCount.add(getPanelAdult());
			panelPeopleCount.add(getPanelChild());
		}
		return panelPeopleCount;
	}

	protected JComboBox<String> getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox<String>();
			comboBox.setFont(new Font("Tahoma", Font.BOLD, 13));
			comboBox.setToolTipText(lblPlace.getToolTipText());
			comboBox.setEditable(true);
			comboBox.setBackground(Color.WHITE);
			comboBox.setModel(modelPlace);
		}
		return comboBox;
	}

	public String getSelectedItem() {
		return String.valueOf(comboBox.getSelectedItem());
	}

	private JDateChooser getDateChooser() {
		if (dateChooser == null) {
			dateChooser = new JDateChooser(new Date());
			dateChooser.setMaxSelectableDate(new Date(System.currentTimeMillis()));
			dateChooser.setToolTipText("Fecha de llegada al destino.");
			dateChooser.setFont(new Font("Tahoma", Font.BOLD, 13));
			JTextFieldDateEditor dateEditor = (JTextFieldDateEditor) dateChooser.getDateEditor();
			dateEditor.setHorizontalAlignment(JTextField.CENTER);
			dateChooser.setDateFormatString(
					((SimpleDateFormat) DateFormat.getDateInstance(DateFormat.SHORT, Internationalization.getLocate()))
							.toLocalizedPattern());
		}
		return dateChooser;
	}

	private JPanel getPanelLblDate() {
		if (panelLblDate == null) {
			panelLblDate = new JPanel();
			panelLblDate.setBackground(Color.WHITE);
			panelLblDate.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			panelLblDate.add(getLblCode());
		}
		return panelLblDate;
	}

	private JPanel getPaneltxDate() {
		if (paneltxDate == null) {
			paneltxDate = new JPanel();
			paneltxDate.setBackground(Color.WHITE);
			paneltxDate.setLayout(new GridLayout(0, 1, 0, 0));
			paneltxDate.add(getDateChooser());
		}
		return paneltxDate;
	}

	private JPanel getPanelLblDni() {
		if (panelLblDni == null) {
			panelLblDni = new JPanel();
			panelLblDni.setBackground(Color.WHITE);
			panelLblDni.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			panelLblDni.add(getLblDni());
		}
		return panelLblDni;
	}

	private JPanel getPanelTxDni() {
		if (panelTxDni == null) {
			panelTxDni = new JPanel();
			panelTxDni.setBackground(Color.WHITE);
			panelTxDni.setLayout(new GridLayout(0, 1, 25, 0));
			panelTxDni.add(getTxtDni());
		}
		return panelTxDni;
	}

	private JPanel getPanelLblAdult() {
		if (panelLblAdult == null) {
			panelLblAdult = new JPanel();
			panelLblAdult.setBorder(null);
			panelLblAdult.setBackground(Color.WHITE);
			panelLblAdult.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			panelLblAdult.add(getLblAdult());
		}
		return panelLblAdult;
	}

	private JPanel getPanelTxNumberAdult() {
		if (panelTxNumberAdult == null) {
			panelTxNumberAdult = new JPanel();
			panelTxNumberAdult.setBorder(null);
			panelTxNumberAdult.setBackground(Color.WHITE);
			panelTxNumberAdult.setLayout(new BoxLayout(panelTxNumberAdult, BoxLayout.X_AXIS));
			panelTxNumberAdult.add(getTxtNumberadult());
		}
		return panelTxNumberAdult;
	}

	private JPanel getPaneLblNumberChild() {
		if (paneLblNumberChild == null) {
			paneLblNumberChild = new JPanel();
			paneLblNumberChild.setBorder(null);
			paneLblNumberChild.setBackground(Color.WHITE);
			paneLblNumberChild.add(getLblChild());
		}
		return paneLblNumberChild;
	}

	private JPanel getPanelTxNumberChild() {
		if (panelTxNumberChild == null) {
			panelTxNumberChild = new JPanel();
			panelTxNumberChild.setBorder(null);
			panelTxNumberChild.setBackground(Color.WHITE);
			panelTxNumberChild.setLayout(new BoxLayout(panelTxNumberChild, BoxLayout.X_AXIS));
			panelTxNumberChild.add(getTxtNumberchild());
		}
		return panelTxNumberChild;
	}
}
