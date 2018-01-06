package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import gui.event.ComboBoxSortEvent;
import gui.event.FocusTextFieldEvent;
import gui.event.NumberTextFieldFormatEvent;
import gui.event.RefreshItemThread;
import gui.event.SliderMouseWheelEvent;
import gui.event.SliderTextFieldEvent;
import logic.order.Order;
import logic.product.Accommodation;
import logic.product.ListProduct;
import logic.product.Package;
import logic.product.Product;
import logic.product.Ticket;
import net.miginfocom.swing.MigLayout;
import util.gui.GuiUtil;
import util.gui.ResizableImage;
import util.gui.internationalization.Internationalization;
import util.logic.LogicUtil;

public class ProductListPanel extends JPanel {

	private static final Color TRANSPARENT = new Color(240, 240, 240, 0);
	private static final long serialVersionUID = 1L;
	private MainWindow mainWindow;

	private JPanel panelNorth;
	private JLabel lblLogo;
	private JPanel panelCart;
	private JLabel lblNumberitems;
	private JButton btnCart;
	private JPanel panelFilter;
	private JPanel panelType;
	private JPanel panelCategory;
	private JPanel panelPeople;
	private JPanel panelOnlyPhotos;
	private JLabel lblType;
	private JCheckBox chOnlyAccom;
	private JCheckBox chOnlyPackage;
	private JCheckBox chOnlyTicket;
	private JLabel lblCategory;
	private JPanel panelStar;
	private JPanel panelRadioButtonType;
	private JLabel lblNumberperson;
	private JSlider sliderPerson;
	private JPanel panelSliderPerson;
	private JPanel panelPrice;
	private JLabel lblPrice;
	private JPanel panelSliderPrice;
	private JCheckBox chOnlyPhotos;
	private JPanel panelCentral;
	private JPanel panelFilterSettings;
	private JLabel lblNumElement;
	private JPanel panelNumElement;
	private JPanel panelSort;
	private JLabel lblSort;
	private JComboBox<String> comboBoxSort;
	private JScrollPane scrollPaneItem;
	private ComboBoxModel<String> modelSort;
	private JPanel panelTypeLabel;
	private List<Product> list;
	private JSlider sliderChild;
	private JPanel panelPlace;
	private JLabel lblPlace;
	private JPanel panelCombo;
	private JComboBox<String> comboBox;
	private DefaultComboBoxModel<String> modelPlace;
	private JPanel panelItem;
	private JLabel lblLblnumberelem;
	private int numberStars;
	private JTextField txtMinprice;
	private JTextField txtMaxprice;
	private JButton btnGopricefilter;
	private JPanel panelLblPrice;
	private JPanel panelBtnPrice;
	private JPanel panelTxFPrice;
	private JPanel panelTypeFlow;
	private JButton btnPricereset;
	private JPanel panelLoading;
	private JLabel lblLoading;
	private JTextField txAdult;
	private JPanel panelAdult;
	private JPanel panelChild;
	private JTextField txChild;
	private JPanel panelTxChild;
	private JPanel panelFilterDate;
	private JLabel label;
	private JPanel panelDate;
	private JDateChooser dateArrive;
	private JDateChooser dateExit;
	private ComboBoxSortEvent comboBoxSortEvent;
	private InitialPanel initialWindow;
	private static Order order = new Order();
	private JScrollPane scrollPane;

	public ProductListPanel(MainWindow mainWindow, InitialPanel initialWindow) {
		this.mainWindow = mainWindow;
		this.initialWindow = initialWindow;
		list = ListProduct.products;
		comboBoxSortEvent = new ComboBoxSortEvent(this);
		modelPlace = new DefaultComboBoxModel<String>(ListProduct.loadPlaces());
		setLayout(new BorderLayout(0, 0));
		add(getPanelNorth(), BorderLayout.NORTH);
		add(getScrollPane(), BorderLayout.WEST);
		// add(getPanelFilter(), BorderLayout.WEST);
		add(getPanelCentral(), BorderLayout.CENTER);
		updateMainWindow();
	}

	public void updateMainWindow() {
		mainWindow.setMinimumSize(new Dimension(1200, 720));
		mainWindow.setLocationRelativeTo(null);

		mainWindow.mntmFullscreen.setEnabled(true);
		mainWindow.rdbtnmntmPanelfilter.setEnabled(true);
		mainWindow.mnSort.setEnabled(true);
		mainWindow.mntmPeople.setEnabled(true);
		mainWindow.mntmPlace.setEnabled(true);
		mainWindow.mntmPrice.setEnabled(true);
		mainWindow.mntmType.setEnabled(true);
		mainWindow.mntmOnlyphotos.setEnabled(true);
		mainWindow.mntmCart.setEnabled(true);
		mainWindow.mntmStars.setEnabled(true);

		mainWindow.setResizable(true);
		mainWindow.setExtendedState(mainWindow.getExtendedState() | Frame.MAXIMIZED_BOTH);
	}

	public static Order getOrder() {
		return order;
	}

	public static void setOrder(String name, String dni, String obs) {
		order = new Order(order, name, dni, obs);
	}

	public static void setOrder(Order order2) {
		order = order2;
	}

	public List<Product> getListProduct() {
		return list;
	}

	public void setListProduct(List<Product> list) {
		this.list = list;
	}

	private JPanel getPanelNorth() {
		if (panelNorth == null) {
			panelNorth = new JPanel();
			panelNorth.setBackground(Color.WHITE);
			panelNorth.setLayout(new GridLayout(1, 2, 0, 0));
			panelNorth.add(getLblLogo());
			panelNorth.add(getPanelCart());
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

	private JPanel getPanelCart() {
		if (panelCart == null) {
			panelCart = new JPanel();
			panelCart.setBackground(Color.WHITE);
			// panelCart.setLayout(new MigLayout("", "[63px,right]", "[30px][14px]"));
			panelCart.setLayout(new MigLayout("", "[][][353.00]push[140.00]", "[139.00][]"));
			panelCart.add(getBtnCart(), "cell 3 0,alignx right,growy");
			panelCart.add(getLblNumberitems(), "cell 3 1,alignx right,growy");
		}
		return panelCart;
	}

	private JLabel getLblNumberitems() {
		if (lblNumberitems == null) {
			lblNumberitems = new JLabel();
			lblNumberitems.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNumberitems.setHorizontalAlignment(SwingConstants.RIGHT);
			setNumberItemsCart(order.getItems());
		}
		return lblNumberitems;
	}

	public void setNumberItemsCart(int items) {
		lblNumberitems.setText(String.format(Internationalization.getString("number_items"), items));
	}

	protected JButton getBtnCart() {
		if (btnCart == null) {
			btnCart = new JButton("");
			btnCart.setToolTipText(Internationalization.getToolTips("cart"));
			btnCart.setPreferredSize(new Dimension(400, 200));
			ResizableImage.setResizeImage(this, btnCart, "/img/cesta.png", 100, 100);
			btnCart.setBackground(Color.WHITE);
			btnCart.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					JDialog dialog = new CartDialog(mainWindow);
					dialog.setVisible(true);
				}
			});
		}
		return btnCart;
	}

	public JPanel getPanelFilter() {
		if (panelFilter == null) {
			panelFilter = new JPanel();
			panelFilter.setBackground(Color.WHITE);
			panelFilter.setBorder(null);
			GridBagLayout gbl_panelFilter = new GridBagLayout();
			gbl_panelFilter.columnWidths = new int[] { 265, 0, 0, 0 };
			gbl_panelFilter.rowHeights = new int[] { 46, 41, 110, 54, 64, 32, 0, 0 };
			gbl_panelFilter.columnWeights = new double[] { 1.0, 0.0, 0.0, Double.MIN_VALUE };
			gbl_panelFilter.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
			panelFilter.setLayout(gbl_panelFilter);
			GridBagConstraints gbc_panelFilterDate = new GridBagConstraints();
			gbc_panelFilterDate.anchor = GridBagConstraints.NORTH;
			gbc_panelFilterDate.insets = new Insets(0, 0, 5, 5);
			gbc_panelFilterDate.fill = GridBagConstraints.HORIZONTAL;
			gbc_panelFilterDate.gridx = 0;
			gbc_panelFilterDate.gridy = 0;
			panelFilter.add(getPanelFilterDate(), gbc_panelFilterDate);
			GridBagConstraints gbc_panelPlace = new GridBagConstraints();
			gbc_panelPlace.fill = GridBagConstraints.BOTH;
			gbc_panelPlace.insets = new Insets(0, 0, 5, 5);
			gbc_panelPlace.gridx = 0;
			gbc_panelPlace.gridy = 1;
			panelFilter.add(getPanelPlace(), gbc_panelPlace);
			GridBagConstraints gbc_panelType = new GridBagConstraints();
			gbc_panelType.insets = new Insets(0, 0, 5, 5);
			gbc_panelType.fill = GridBagConstraints.BOTH;
			gbc_panelType.gridx = 0;
			gbc_panelType.gridy = 2;
			panelFilter.add(getPanelType(), gbc_panelType);
			GridBagConstraints gbc_panelCategory = new GridBagConstraints();
			gbc_panelCategory.fill = GridBagConstraints.BOTH;
			gbc_panelCategory.insets = new Insets(0, 0, 5, 5);
			// gbc_panelCategory.insets = new Insets(0, 0, 15, 0);
			gbc_panelCategory.gridx = 0;
			gbc_panelCategory.gridy = 3;
			panelFilter.add(getPanelCategory(), gbc_panelCategory);
			GridBagConstraints gbc_panelPeople = new GridBagConstraints();
			gbc_panelPeople.fill = GridBagConstraints.BOTH;
			gbc_panelPeople.insets = new Insets(0, 0, 5, 5);
			gbc_panelPeople.gridx = 0;
			gbc_panelPeople.gridy = 4;
			panelFilter.add(getPanelPeople(), gbc_panelPeople);
			GridBagConstraints gbc_panelPrice = new GridBagConstraints();
			gbc_panelPrice.fill = GridBagConstraints.BOTH;
			gbc_panelPrice.insets = new Insets(0, 0, 5, 5);
			gbc_panelPrice.gridx = 0;
			gbc_panelPrice.gridy = 5;
			panelFilter.add(getPanelPrice(), gbc_panelPrice);
			GridBagConstraints gbc_panelOnlyPhotos = new GridBagConstraints();
			gbc_panelOnlyPhotos.fill = GridBagConstraints.BOTH;
			gbc_panelOnlyPhotos.insets = new Insets(0, 0, 0, 5);
			gbc_panelOnlyPhotos.gridx = 0;
			gbc_panelOnlyPhotos.gridy = 6;
			panelFilter.add(getPanelOnlyPhotos(), gbc_panelOnlyPhotos);
		}
		return panelFilter;
	}

	private JPanel getPanelType() {
		if (panelType == null) {
			panelType = new JPanel();
			panelType.setBackground(Color.WHITE);
			panelType.setBorder(UIManager.getBorder("Spinner.border"));
			panelType.setLayout(new BoxLayout(panelType, BoxLayout.Y_AXIS));
			panelType.add(getPanelTypeLabel());
			panelType.add(getPanelTypeFlow());
		}
		return panelType;
	}

	private JPanel getPanelCategory() {
		if (panelCategory == null) {
			panelCategory = new JPanel();
			panelCategory.setAlignmentX(Component.RIGHT_ALIGNMENT);
			panelCategory.setBackground(Color.WHITE);
			panelCategory.setBorder(UIManager.getBorder("Spinner.border"));
			panelCategory.setLayout(new BoxLayout(panelCategory, BoxLayout.Y_AXIS));
			panelCategory.add(getLblCategory());
			panelCategory.add(getPanelStar());
		}
		return panelCategory;
	}

	private JPanel getPanelPeople() {
		if (panelPeople == null) {
			panelPeople = new JPanel();
			panelPeople.setAlignmentX(Component.RIGHT_ALIGNMENT);
			panelPeople.setBackground(Color.WHITE);
			panelPeople.setBorder(UIManager.getBorder("Spinner.border"));
			panelPeople.setLayout(new BoxLayout(panelPeople, BoxLayout.Y_AXIS));
			panelPeople.add(getLblNumberperson());
			panelPeople.add(getPanelSliderPerson());
		}
		return panelPeople;
	}

	private JPanel getPanelOnlyPhotos() {
		if (panelOnlyPhotos == null) {
			panelOnlyPhotos = new JPanel();
			panelOnlyPhotos.setAlignmentX(Component.RIGHT_ALIGNMENT);
			panelOnlyPhotos.setBackground(Color.WHITE);
			panelOnlyPhotos.setBorder(UIManager.getBorder("Spinner.border"));
			panelOnlyPhotos.setLayout(new BoxLayout(panelOnlyPhotos, BoxLayout.X_AXIS));
			panelOnlyPhotos.add(getChOnlyPhotos());
		}
		return panelOnlyPhotos;
	}

	private JLabel getLblType() {
		if (lblType == null) {
			lblType = new JLabel(Internationalization.getString("type"));
			lblType.setBackground(Color.WHITE);
			lblType.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblType.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblType.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblType;
	}

	protected JCheckBox getChOnlyAccom() {
		if (chOnlyAccom == null) {
			chOnlyAccom = new JCheckBox(Internationalization.getString("type_only_accom"));
			chOnlyAccom.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					if (!chOnlyAccom.isSelected()) {
						resetStars();
					}
				}
			});
			chOnlyAccom.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					filtersReset();
				}
			});
			chOnlyAccom.setFont(new Font("Tahoma", Font.PLAIN, 14));
			chOnlyAccom.setBackground(Color.WHITE);
		}
		return chOnlyAccom;
	}

	protected JCheckBox getChOnlyPackage() {
		if (chOnlyPackage == null) {
			chOnlyPackage = new JCheckBox(Internationalization.getString("type_only_packages"));
			chOnlyPackage.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					resetStars();
					filtersReset();
				}
			});
			chOnlyPackage.setFont(new Font("Tahoma", Font.PLAIN, 14));
			chOnlyPackage.setBackground(Color.WHITE);
		}
		return chOnlyPackage;
	}

	protected JCheckBox getChOnlyTicket() {
		if (chOnlyTicket == null) {
			chOnlyTicket = new JCheckBox(Internationalization.getString("type_only_ticket"));
			chOnlyTicket.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					resetStars();
					filtersReset();
				}
			});
			chOnlyTicket.setFont(new Font("Tahoma", Font.PLAIN, 14));
			chOnlyTicket.setBackground(Color.WHITE);
		}
		return chOnlyTicket;
	}

	private void resetStars() {
		for (int i = 0; i < panelStar.getComponentCount(); i++) {
			((JButton) panelStar.getComponent(i)).setText("\u2606");
		}
	}

	private JLabel getLblCategory() {
		if (lblCategory == null) {
			lblCategory = new JLabel(Internationalization.getString("category"));
			lblCategory.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblCategory.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblCategory.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblCategory;
	}

	protected JPanel getPanelStar() {
		if (panelStar == null) {
			panelStar = new JPanel();
			panelStar.setAlignmentY(Component.BOTTOM_ALIGNMENT);
			panelStar.setBackground(Color.WHITE);
			panelStar.setLayout(new BoxLayout(panelStar, BoxLayout.X_AXIS));
			for (int i = 1; i <= 5; i++) {
				panelStar.add(getBtnStar(i));
			}

		}
		return panelStar;
	}

	private JButton getBtnStar(int index) {
		JButton btnStar = new JButton("\u2606");
		btnStar.setMinimumSize(new Dimension(45, 25));
		btnStar.setPreferredSize(new Dimension(45, 25));
		btnStar.setMaximumSize(new Dimension(45, 25));
		btnStar.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnStar.setActionCommand(String.valueOf(index));
		btnStar.setBackground(Color.WHITE);
		btnStar.setFont(new Font("Code2000", Font.PLAIN, 14));
		btnStar.setToolTipText(
				String.format(Internationalization.getToolTips("star_number"), btnStar.getActionCommand()));
		btnStar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton buttonPressed = (JButton) e.getSource();
				numberStars = Integer.parseInt(buttonPressed.getActionCommand());

				// update gui
				chOnlyAccom.setSelected(true);
				chOnlyPackage.setSelected(false);
				chOnlyTicket.setSelected(false);

				if (buttonPressed.getText().equals("\u2606")) {
					for (int i = 0; i < panelStar.getComponentCount(); i++) {
						if (panelStar.getComponent(i) instanceof JButton) {
							int numberButton = Integer
									.parseInt(((JButton) panelStar.getComponent(i)).getActionCommand());
							if (numberButton <= numberStars) {
								((JButton) panelStar.getComponent(i)).setText("\u2605");
							}
						}

					}
				} else {
					for (int i = numberStars; i < panelStar.getComponentCount(); i++) {
						if (panelStar.getComponent(i) instanceof JButton
								&& ((JButton) panelStar.getComponent(i)).getText().equals("\u2605")) {
							((JButton) panelStar.getComponent(i)).setText("\u2606");
						}

					}
				}

				// filter
				filtersReset();
			}
		});
		return btnStar;
	}

	protected JPanel getPanelRadioButtonType() {
		if (panelRadioButtonType == null) {
			panelRadioButtonType = new JPanel();
			panelRadioButtonType.setBackground(TRANSPARENT);
			panelRadioButtonType.setLayout(new BoxLayout(panelRadioButtonType, BoxLayout.Y_AXIS));
			panelRadioButtonType.add(getChOnlyAccom());
			panelRadioButtonType.add(getChOnlyTicket());
			panelRadioButtonType.add(getChOnlyPackage());
			getRbAll();
		}
		return panelRadioButtonType;
	}

	private JLabel getLblNumberperson() {
		if (lblNumberperson == null) {
			lblNumberperson = new JLabel(Internationalization.getString("number_person"));
			lblNumberperson.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblNumberperson.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblNumberperson.setAlignmentY(Component.TOP_ALIGNMENT);
			lblNumberperson.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNumberperson;
	}

	protected JSlider getSliderPerson() {
		if (sliderPerson == null) {
			sliderPerson = new JSlider();
			sliderPerson.setAlignmentY(Component.TOP_ALIGNMENT);
			sliderPerson.setBackground(Color.WHITE);
			sliderPerson.setPaintLabels(true);
			sliderPerson.setToolTipText(Internationalization.getToolTips("slider_adult"));
			sliderPerson.setMinimum(1);
			sliderPerson.setMinorTickSpacing(1);
			int valueMax = setMaxSlider(Integer.parseInt(initialWindow.getTxtNumberadult().getText()));
			sliderPerson.setMaximum(valueMax + 1);
			sliderPerson.setValue(Integer.parseInt(initialWindow.getTxtNumberadult().getText()));
			sliderPerson.addMouseWheelListener(new SliderMouseWheelEvent());
			sliderPerson.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					getTxAdult().setText(String.valueOf(sliderPerson.getValue()));
					for (int i = 0; i < panelItem.getComponentCount(); i++) {
						JPanel panel = (JPanel) panelItem.getComponent(i);
						if (panel instanceof ItemPanel) {
							Product product = ((ItemPanel) panel).getProduct();
							product.setNumberAdult(sliderPerson.getValue());
							((ItemPanel) panelItem.getComponent(i)).updatePrice();
						}
					}
					filtersReset();
				}
			});
		}
		return sliderPerson;
	}

	protected int setMaxSlider(int numberPerson) {
		int valueMax = 0;
		if (numberPerson * 2 <= 10) {
			valueMax = 10;
		} else {
			valueMax = numberPerson * 2;
		}
		return valueMax;
	}

	protected JSlider getSliderChild() {
		if (sliderChild == null) {
			sliderChild = new JSlider();
			sliderChild.setPaintLabels(true);
			sliderChild.setMinimum(0);
			int valueMax = setMaxSlider(Integer.parseInt(initialWindow.getTxtNumberchild().getText()));
			sliderChild.setMaximum(valueMax);
			sliderChild.setValue(Integer.parseInt(initialWindow.getTxtNumberchild().getText()));
			sliderChild.setMinorTickSpacing(1);
			sliderChild.setToolTipText(Internationalization.getToolTips("slider_child"));
			sliderChild.setBackground(Color.WHITE);
			sliderChild.setAlignmentY(0.0f);
			sliderChild.addMouseWheelListener(new SliderMouseWheelEvent());
			sliderChild.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					getTxChild().setText(String.valueOf(sliderChild.getValue()));
					txChild.setText(String.valueOf(sliderChild.getValue()));
					for (int i = 0; i < panelItem.getComponentCount(); i++) {
						JPanel panel = (JPanel) panelItem.getComponent(i);
						if (panel instanceof ItemPanel) {
							Product product = ((ItemPanel) panel).getProduct();
							product.setNumberChild(sliderChild.getValue());
							((ItemPanel) panelItem.getComponent(i)).updatePrice();
						}
					}
					filtersReset();
				}
			});
		}
		return sliderChild;
	}

	private JPanel getPanelSliderPerson() {
		if (panelSliderPerson == null) {
			panelSliderPerson = new JPanel();
			panelSliderPerson.setBackground(Color.WHITE);
			panelSliderPerson.setLayout(new BoxLayout(panelSliderPerson, BoxLayout.Y_AXIS));
			panelSliderPerson.add(getPanelAdult());
			panelSliderPerson.add(getPanelChild());
		}
		return panelSliderPerson;
	}

	private JPanel getPanelPrice() {
		if (panelPrice == null) {
			panelPrice = new JPanel();
			panelPrice.setAlignmentX(Component.RIGHT_ALIGNMENT);
			panelPrice.setBorder(UIManager.getBorder("Spinner.border"));
			panelPrice.setBackground(Color.WHITE);
			panelPrice.setLayout(new BoxLayout(panelPrice, BoxLayout.Y_AXIS));
			panelPrice.add(getPanelLblPrice());
			panelPrice.add(getPanelSliderPrice());
		}
		return panelPrice;
	}

	private JLabel getLblPrice() {
		if (lblPrice == null) {
			lblPrice = new JLabel(Internationalization.getString("price"));
			lblPrice.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblPrice.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblPrice;
	}

	private JPanel getPanelSliderPrice() {
		if (panelSliderPrice == null) {
			panelSliderPrice = new JPanel();
			panelSliderPrice.setBackground(Color.WHITE);
			panelSliderPrice.setLayout(new BorderLayout(0, 0));
			panelSliderPrice.add(getPanelTxFPrice(), BorderLayout.NORTH);
			panelSliderPrice.add(getPanelBtnPrice());
		}
		return panelSliderPrice;
	}

	protected JCheckBox getChOnlyPhotos() {
		if (chOnlyPhotos == null) {
			chOnlyPhotos = new JCheckBox(Internationalization.getString("only_photos"));
			chOnlyPhotos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					filtersReset();
				}
			});
			chOnlyPhotos.setAlignmentX(Component.CENTER_ALIGNMENT);
			chOnlyPhotos.setFont(new Font("Tahoma", Font.PLAIN, 14));
			chOnlyPhotos.setAlignmentY(Component.TOP_ALIGNMENT);
			chOnlyPhotos.setBackground(Color.WHITE);
			chOnlyPhotos.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return chOnlyPhotos;
	}

	private JPanel getPanelCentral() {
		if (panelCentral == null) {
			panelCentral = new JPanel();
			panelCentral.setBackground(Color.WHITE);
			panelCentral.setLayout(new BorderLayout(10, 10));
			panelCentral.add(getPanelFilterSettings(), BorderLayout.NORTH);
			panelCentral.add(getScrollPaneItem(), BorderLayout.CENTER);
		}
		return panelCentral;
	}

	private JPanel getPanelFilterSettings() {
		if (panelFilterSettings == null) {
			panelFilterSettings = new JPanel();
			panelFilterSettings.setBackground(Color.WHITE);
			panelFilterSettings.setBorder(UIManager.getBorder("Spinner.border"));
			panelFilterSettings.setLayout(new GridLayout(1, 2, 5, 5));
			panelFilterSettings.add(getPanelNumElement());
			panelFilterSettings.add(getPanelSort());
		}
		return panelFilterSettings;
	}

	private JLabel getLblNumElement() {
		if (lblNumElement == null) {
			lblNumElement = new JLabel(Internationalization.getString("box_num_element"));
			lblNumElement.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblNumElement;
	}

	private JPanel getPanelNumElement() {
		if (panelNumElement == null) {
			panelNumElement = new JPanel();
			panelNumElement.setBackground(Color.WHITE);
			panelNumElement.setLayout(new BoxLayout(panelNumElement, BoxLayout.X_AXIS));
			panelNumElement.add(getLblNumElement());
			panelNumElement.add(getLblLblnumberelem());
		}
		return panelNumElement;
	}

	private JPanel getPanelSort() {
		if (panelSort == null) {
			panelSort = new JPanel();
			panelSort.setBackground(Color.WHITE);
			FlowLayout flowLayout = (FlowLayout) panelSort.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			panelSort.add(getLblSort());
			panelSort.add(getComboBoxSort());
		}
		return panelSort;
	}

	private JLabel getLblSort() {
		if (lblSort == null) {
			lblSort = new JLabel(Internationalization.getString("box_sort"));
			lblSort.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblSort;
	}

	protected JComboBox<String> getComboBoxSort() {
		if (comboBoxSort == null) {
			comboBoxSort = new JComboBox<String>();
			comboBoxSort.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					comboBoxSortEvent.resetCount();
				}
			});
			comboBoxSort.setToolTipText(Internationalization.getToolTips("place"));
			comboBoxSort.setFont(new Font("Tahoma", Font.PLAIN, 14));
			modelSort = new DefaultComboBoxModel<String>(
					new String[] { Internationalization.getString("box_combo_relevance"),
							Internationalization.getString("box_combo_price_down"),
							Internationalization.getString("box_combo_price_up") });
			comboBoxSort.setModel(modelSort);
			comboBoxSort.addItemListener(comboBoxSortEvent);
		}
		return comboBoxSort;
	}

	private JScrollPane getScrollPaneItem() {
		if (scrollPaneItem == null) {
			scrollPaneItem = new JScrollPane();
			scrollPaneItem.setViewportView(getPanelItem());
			scrollPaneItem.getVerticalScrollBar().setValue(0);
			scrollPaneItem.getVerticalScrollBar().setUnitIncrement(20);
		}

		return scrollPaneItem;
	}

	private JPanel getPanelItem() {
		if (panelItem == null) {
			panelItem = new JPanel();
			panelItem.setLayout(new BoxLayout(panelItem, BoxLayout.Y_AXIS));
			panelItem.add(getPanelLoading());
			refresh();
		}
		return panelItem;
	}

	public void loadItems() {
		for (int i = 0; i < list.size(); i++) {
			Product product = ListProduct.searchProduct(list.get(i).getCode());
			int numberAdult = Integer.parseInt(getTxAdult().getText());
			int numberChild = Integer.parseInt(getTxChild().getText());
			Date date = dateArrive.getDate();
			int days = LogicUtil.getDays(date, getDateExit().getDate());
			product.loadData(numberAdult, numberChild, date, days);
			panelItem.add(new ItemPanel(this, product));
		}
	}

	private JPanel getPanelTypeLabel() {
		if (panelTypeLabel == null) {
			panelTypeLabel = new JPanel();
			panelTypeLabel.setBackground(Color.WHITE);
			panelTypeLabel.setLayout(new BorderLayout(0, 0));
			panelTypeLabel.add(getLblType());
		}
		return panelTypeLabel;
	}

	private JPanel getPanelPlace() {
		if (panelPlace == null) {
			panelPlace = new JPanel();
			panelPlace.setBorder(UIManager.getBorder("Spinner.border"));
			panelPlace.setAlignmentX(Component.RIGHT_ALIGNMENT);
			panelPlace.setBackground(Color.WHITE);
			panelPlace.setLayout(new BoxLayout(panelPlace, BoxLayout.Y_AXIS));
			panelPlace.add(getLblPlace());
			panelPlace.add(getPanel_1_1());
		}
		return panelPlace;
	}

	private JLabel getLblPlace() {
		if (lblPlace == null) {
			lblPlace = new JLabel(Internationalization.getString("place"));
			lblPlace.setAlignmentY(Component.TOP_ALIGNMENT);
			lblPlace.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblPlace.setBackground(Color.WHITE);
			lblPlace.setHorizontalAlignment(SwingConstants.CENTER);
			lblPlace.setFont(new Font("Tahoma", Font.BOLD, 16));
		}
		return lblPlace;
	}

	private JPanel getPanel_1_1() {
		if (panelCombo == null) {
			panelCombo = new JPanel();
			panelCombo.setBackground(Color.WHITE);
			panelCombo.setLayout(new BoxLayout(panelCombo, BoxLayout.Y_AXIS));
			panelCombo.add(getComboBox());
		}
		return panelCombo;
	}

	protected JComboBox<String> getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox<String>();
			comboBox.setToolTipText(Internationalization.getToolTips("place"));
			comboBox.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					getRbAll();
					filterPlaceChange();
					getLblLblnumberelem().setText(String.valueOf(GuiUtil.getVisibleChildrenCount(getPanelItem())));
				}
			});
			comboBox.setFont(new Font("Tahoma", Font.BOLD, 13));
			comboBox.setEditable(true);
			comboBox.setBackground(Color.WHITE);
			comboBox.setModel(modelPlace);
			comboBox.setSelectedIndex(mainWindow.getInitialPanel().getComboBox().getSelectedIndex());
		}
		return comboBox;
	}

	public void getRbAll() {
		getChOnlyAccom().setSelected(true);
		getChOnlyPackage().setSelected(true);
		getChOnlyTicket().setSelected(true);
	}

	private JLabel getLblLblnumberelem() {
		if (lblLblnumberelem == null) {
			lblLblnumberelem = new JLabel("0"); // $NON-NLS-1$
			lblLblnumberelem.setFont(new Font("Tahoma", Font.BOLD, 14));
		}
		return lblLblnumberelem;
	}

	protected JTextField getTxtMinprice() {
		if (txtMinprice == null) {
			txtMinprice = new JTextField();
			txtMinprice.setHorizontalAlignment(SwingConstants.CENTER);
			txtMinprice.setForeground(Color.DARK_GRAY);
			txtMinprice.setToolTipText(Internationalization.getToolTips("price_min"));
			txtMinprice.setFont(new Font("Tahoma", Font.BOLD, 13));
			txtMinprice.setText(Internationalization.getString("min_price")); //$NON-NLS-1$
			txtMinprice.setColumns(10);
			txtMinprice.addKeyListener(new NumberTextFieldFormatEvent());
			txtMinprice.addFocusListener(new FocusTextFieldEvent("min_price"));
		}
		return txtMinprice;
	}

	protected JTextField getTxtMaxprice() {
		if (txtMaxprice == null) {
			txtMaxprice = new JTextField();
			txtMaxprice.setHorizontalAlignment(SwingConstants.CENTER);
			txtMaxprice.setForeground(Color.DARK_GRAY);
			txtMaxprice.setToolTipText(Internationalization.getToolTips("price_max"));
			txtMaxprice.setFont(new Font("Tahoma", Font.BOLD, 13));
			txtMaxprice.setText(Internationalization.getString("max_price")); //$NON-NLS-1$
			txtMaxprice.setColumns(10);
			txtMaxprice.addKeyListener(new NumberTextFieldFormatEvent());
			txtMaxprice.addFocusListener(new FocusTextFieldEvent("max_price"));
		}
		return txtMaxprice;
	}

	protected JButton getBtnGopricefilter() {
		if (btnGopricefilter == null) {
			btnGopricefilter = new JButton(Internationalization.getString("filter_price_btn")); //$NON-NLS-1$
			btnGopricefilter.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnGopricefilter.setToolTipText(Internationalization.getToolTips("filter_price_btn"));
			btnGopricefilter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int min = 0;
					try {
						min = Integer.parseInt(txtMinprice.getText());

					} catch (NumberFormatException ex) {
						txtMinprice.setText("0");
					}

					int max = 0;
					try {
						max = Integer.parseInt(txtMaxprice.getText());

					} catch (NumberFormatException ex) {
						txtMaxprice.setText(getTxtMinprice().getText());
					}

					if (min > max) {
						txtMinprice.setText("0");
					}

					filtersReset();
				}
			});
		}
		return btnGopricefilter;
	}

	private JPanel getPanelLblPrice() {
		if (panelLblPrice == null) {
			panelLblPrice = new JPanel();
			panelLblPrice.setBackground(Color.WHITE);
			panelLblPrice.setLayout(new BorderLayout(0, 0));
			panelLblPrice.add(getLblPrice());
		}
		return panelLblPrice;
	}

	private JPanel getPanelBtnPrice() {
		if (panelBtnPrice == null) {
			panelBtnPrice = new JPanel();
			panelBtnPrice.setBackground(Color.WHITE);
			panelBtnPrice.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			panelBtnPrice.add(getBtnGopricefilter());
			panelBtnPrice.add(getBtnPricereset());
		}
		return panelBtnPrice;
	}

	private JPanel getPanelTxFPrice() {
		if (panelTxFPrice == null) {
			panelTxFPrice = new JPanel();
			panelTxFPrice.setBackground(Color.WHITE);
			panelTxFPrice.setLayout(new GridLayout(0, 2, 2, 2));
			panelTxFPrice.add(getTxtMinprice());
			panelTxFPrice.add(getTxtMaxprice());
		}
		return panelTxFPrice;
	}

	private JPanel getPanelTypeFlow() {
		if (panelTypeFlow == null) {
			panelTypeFlow = new JPanel();
			panelTypeFlow.setBackground(Color.WHITE);
			FlowLayout fl_panelTypeFlow = (FlowLayout) panelTypeFlow.getLayout();
			fl_panelTypeFlow.setAlignment(FlowLayout.LEFT);
			panelTypeFlow.add(getPanelRadioButtonType());
		}
		return panelTypeFlow;
	}

	private JButton getBtnPricereset() {
		if (btnPricereset == null) {
			btnPricereset = new JButton(Internationalization.getString("filter_price_reset")); //$NON-NLS-1$
			btnPricereset.setToolTipText(Internationalization.getToolTips("filter_price_reset"));
			btnPricereset.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnPricereset.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					txtMinprice.setText(Internationalization.getString("min_price"));
					txtMaxprice.setText(Internationalization.getString("max_price"));
					txtMinprice.setForeground(Color.DARK_GRAY);
					txtMaxprice.setForeground(Color.DARK_GRAY);
					filtersReset();
				}
			});
		}
		return btnPricereset;
	}

	public JPanel getPanelLoading() {
		if (panelLoading == null) {
			panelLoading = new JPanel();
			panelLoading.setLayout(new BoxLayout(panelLoading, BoxLayout.X_AXIS));
			panelLoading.add(getLblLoading());
		}
		return panelLoading;
	}

	private JLabel getLblLoading() {
		if (lblLoading == null) {
			lblLoading = new JLabel(Internationalization.getString("loading")); //$NON-NLS-1$
			lblLoading.setFont(new Font("Tahoma", Font.BOLD, 36));
		}
		return lblLoading;
	}

	private JTextField getTxAdult() {
		if (txAdult == null) {
			txAdult = new JTextField();
			txAdult.setHorizontalAlignment(SwingConstants.CENTER);
			txAdult.setForeground(Color.DARK_GRAY);
			txAdult.setFont(new Font("Tahoma", Font.BOLD, 13));
			txAdult.setText(String.valueOf(getSliderPerson().getValue())); // $NON-NLS-1$
			txAdult.setColumns(10);
			txAdult.addKeyListener(new NumberTextFieldFormatEvent());
			txAdult.addFocusListener(new FocusTextFieldEvent(String.valueOf(getSliderPerson().getValue())));
			txAdult.addFocusListener(new SliderTextFieldEvent(txAdult, sliderPerson));
		}
		return txAdult;
	}

	private JPanel getPanelAdult() {
		if (panelAdult == null) {
			panelAdult = new JPanel();
			panelAdult.setLayout(new GridLayout(0, 1, 0, 0));
			panelAdult.add(getSliderPerson());
		}
		return panelAdult;
	}

	private JPanel getPanelChild() {
		if (panelChild == null) {
			panelChild = new JPanel();
			panelChild.setLayout(new GridLayout(0, 1, 0, 0));
			panelChild.add(getSliderChild());
			panelChild.add(getPanelTxChild());
		}
		return panelChild;
	}

	private JTextField getTxChild() {
		if (txChild == null) {
			txChild = new JTextField();
			txChild.setHorizontalAlignment(SwingConstants.CENTER);
			txChild.setForeground(Color.DARK_GRAY);
			txChild.setFont(new Font("Tahoma", Font.BOLD, 13));
			txChild.setText(String.valueOf(getSliderChild().getValue())); // $NON-NLS-1$
			txChild.setColumns(10);
			txChild.addKeyListener(new NumberTextFieldFormatEvent());
			txChild.addFocusListener(new FocusTextFieldEvent(String.valueOf(getSliderChild().getValue())));
			txChild.addFocusListener(new SliderTextFieldEvent(txChild, sliderChild));
		}
		return txChild;
	}

	private JPanel getPanelTxChild() {
		if (panelTxChild == null) {
			panelTxChild = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelTxChild.getLayout();
			flowLayout.setVgap(2);
			flowLayout.setHgap(2);
			panelTxChild.setBackground(Color.WHITE);
			panelTxChild.add(getTxAdult());
			panelTxChild.add(getTxChild());
		}
		return panelTxChild;
	}

	private JPanel getPanelFilterDate() {
		if (panelFilterDate == null) {
			panelFilterDate = new JPanel();
			panelFilterDate.setBorder(UIManager.getBorder("Spinner.border"));
			panelFilterDate.setBackground(Color.WHITE);
			panelFilterDate.setLayout(new BoxLayout(panelFilterDate, BoxLayout.Y_AXIS));
			panelFilterDate.add(getLabel());
			panelFilterDate.add(getPanelDate());
		}
		return panelFilterDate;
	}

	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel(Internationalization.getString("date"));
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setFont(new Font("Tahoma", Font.BOLD, 16));
			label.setBackground(Color.WHITE);
			label.setAlignmentY(0.0f);
			label.setAlignmentX(0.5f);
		}
		return label;
	}

	private JPanel getPanelDate() {
		if (panelDate == null) {
			panelDate = new JPanel();
			panelDate.setBorder(UIManager.getBorder("Spinner.border"));
			panelDate.setBackground(Color.WHITE);
			panelDate.setLayout(new GridLayout(0, 2, 2, 2));
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
			dateArrive.setFont(new Font("Tahoma", Font.BOLD, 13));
			JTextFieldDateEditor dateEditor = (JTextFieldDateEditor) dateArrive.getDateEditor();
			dateEditor.setHorizontalAlignment(JTextField.CENTER);
			Date date = initialWindow.getDateArrive().getDate();
			dateArrive.setDate(date);
			dateArrive.setMinSelectableDate(date);
			dateArrive.setDateFormatString(
					((SimpleDateFormat) DateFormat.getDateInstance(DateFormat.SHORT, Internationalization.getLocate()))
							.toLocalizedPattern());
			dateArrive.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent arg0) {
					if (dateArrive.getDate() != null
							&& dateArrive.getDate().getTime() >= getDateExit().getDate().getTime()) {
						Date date2 = new Date(dateArrive.getDate().getTime() + 86400000);
						getDateExit().setMinSelectableDate(date2);
						getDateExit().setDate(date2);
					}
				}
			});
			dateEditor.addFocusListener(new FocusListener() {
				private Date dateC;

				@Override
				public void focusGained(FocusEvent e) {
					dateC = dateArrive.getDate();
				}

				@Override
				public void focusLost(FocusEvent e) {
					if (dateArrive.getDate() == null) {
						Date date2 = dateExit.getDate();
						dateEditor.setDate(dateC);
						getDateExit().setMinSelectableDate(date2);
						getDateExit().setDate(date2);
					}

				}

			});
		}
		return dateArrive;
	}

	private JDateChooser getDateExit() {
		if (dateExit == null) {
			dateExit = new JDateChooser();
			dateExit.setFont(new Font("Tahoma", Font.BOLD, 13));
			JTextFieldDateEditor dateEditor = (JTextFieldDateEditor) dateExit.getDateEditor();
			dateEditor.setHorizontalAlignment(JTextField.CENTER);
			Date date = initialWindow.getDateExit().getDate();
			dateExit.setDate(date);
			dateExit.setMinSelectableDate(date);
			dateExit.setDateFormatString(
					((SimpleDateFormat) DateFormat.getDateInstance(DateFormat.SHORT, Internationalization.getLocate()))
							.toLocalizedPattern());
			dateExit.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent arg0) {
					if (dateExit.getDate() != null) {
						for (int i = 0; i < panelItem.getComponentCount(); i++) {
							JPanel panel = (JPanel) panelItem.getComponent(i);
							if (panel instanceof ItemPanel) {
								Product product = ((ItemPanel) panel).getProduct();
								product.setDate(dateArrive.getDate());
								product.setDuration(LogicUtil.getDays(dateArrive.getDate(), dateExit.getDate()));
								((ItemPanel) panelItem.getComponent(i)).updatePrice();
							}
						}
					}
				}
			});
			dateEditor.addFocusListener(new FocusListener() {
				private Date dateC;

				@Override
				public void focusGained(FocusEvent e) {
					dateC = dateExit.getDate();
				}

				@Override
				public void focusLost(FocusEvent e) {
					if (dateExit.getDate() == null) {
						dateEditor.setDate(dateC);
					}

				}

			});
		}
		return dateExit;
	}

	// <----------------------------FILTERS---------------------------------->
	public void refresh() {
		getPanelLoading().setVisible(true);
		panelItem.removeAll();
		panelItem.add(getPanelLoading());
		panelItem.revalidate();
		panelItem.repaint();
		new Thread(new RefreshItemThread(this)).start();
	}

	public void filtersReset() {
		getDateExit().setEnabled(true);
		filterPlaceChange();
		filterType();
		filterOnlyPhostosCh();
		filterStars();
		filterPriceCh();
		filterSize();
		filterTicket();
		updateNumberItems();
	}

	public void updateNumberItems() {
		lblLblnumberelem.setText(String.valueOf(GuiUtil.getVisibleChildrenCount(panelItem)));
	}

	private void filterType() {
		for (int i = 0; i < getPanelItem().getComponentCount(); i++) {
			if (panelItem.getComponent(i) instanceof ItemPanel) {
				Product product = ((ItemPanel) panelItem.getComponent(i)).getProduct();
				if (panelItem.getComponent(i).isVisible()) {
					if (product instanceof Accommodation && chOnlyAccom.isSelected()) {
						panelItem.getComponent(i).setVisible(true);
					} else if (product instanceof Ticket && chOnlyTicket.isSelected()) {
						panelItem.getComponent(i).setVisible(true);
					} else if (product instanceof Package && chOnlyPackage.isSelected()) {
						panelItem.getComponent(i).setVisible(true);
					} else {
						panelItem.getComponent(i).setVisible(false);
					}
				} else {
					panelItem.getComponent(i).setVisible(false);
				}
			}
		}
	}

	public void filterPlaceChange() {
		for (int i = 0; i < getPanelItem().getComponentCount(); i++) {
			if (panelItem.getComponent(i) instanceof ItemPanel) {
				Product product = ((ItemPanel) panelItem.getComponent(i)).getProduct();
				String aux = String.valueOf(getComboBox().getSelectedItem());
				if (product.getPark().getCity().equals(aux)
						|| product.getPark().getCountry().equals(String.valueOf(getComboBox().getSelectedItem()))) {
					panelItem.getComponent(i).setVisible(true);
				} else {
					panelItem.getComponent(i).setVisible(false);
				}
			}
		}

	}

	private void filterStars() {
		if (chOnlyAccom.isSelected() && !chOnlyPackage.isSelected() && !chOnlyTicket.isSelected()) {
			for (int i = 0; i < getPanelItem().getComponentCount(); i++) {
				if (panelItem.getComponent(i) instanceof ItemPanel) {
					Product product = ((ItemPanel) panelItem.getComponent(i)).getProduct();
					if (panelItem.getComponent(i).isVisible() && product instanceof Accommodation
							&& ((Accommodation) product).getStars() >= numberStars) {
						panelItem.getComponent(i).setVisible(true);
					} else {
						panelItem.getComponent(i).setVisible(false);
					}
				}
			}
		}
	}

	private void filterOnlyPhostosCh() {
		if (getChOnlyPhotos().isSelected()) {
			for (int i = 0; i < getPanelItem().getComponentCount(); i++) {
				if (panelItem.getComponent(i) instanceof ItemPanel) {
					boolean value = ((ItemPanel) panelItem.getComponent(i)).getImage();
					if (panelItem.getComponent(i).isVisible() && value) {
						panelItem.getComponent(i).setVisible(true);
					} else {
						panelItem.getComponent(i).setVisible(false);
					}
				}
			}
		}

	}

	private void filterPriceCh() {
		try {
			double maxPrice = Double.parseDouble(txtMaxprice.getText());
			double minPrice = Double.parseDouble(txtMinprice.getText());
			for (int i = 0; i < getPanelItem().getComponentCount(); i++) {
				if (panelItem.getComponent(i) instanceof ItemPanel) {
					Product product = ((ItemPanel) panelItem.getComponent(i)).getProduct();
					if (panelItem.getComponent(i).isVisible()
							&& (product.getTotal() - product.getDiscount()) >= minPrice
							&& (product.getTotal() - product.getDiscount()) <= maxPrice) {
						panelItem.getComponent(i).setVisible(true);
					} else {
						panelItem.getComponent(i).setVisible(false);
					}
				}
			}
		} catch (NumberFormatException e) {

		}

	}

	private void filterSize() {
		for (int i = 0; i < getPanelItem().getComponentCount(); i++) {
			if (panelItem.getComponent(i) instanceof ItemPanel) {
				Product product = ((ItemPanel) panelItem.getComponent(i)).getProduct();
				int number = Integer.parseInt(getTxAdult().getText()) + Integer.parseInt(getTxChild().getText());
				if (panelItem.getComponent(i).isVisible()
						&& (product instanceof Accommodation && ((Accommodation) product).getNum() >= number
								|| product instanceof Package && ((Package) product).getAccom().getNum() >= number)) {
					panelItem.getComponent(i).setVisible(true);
				} else if (panelItem.getComponent(i).isVisible() && product instanceof Ticket) {
					panelItem.getComponent(i).setVisible(true);
				} else {
					panelItem.getComponent(i).setVisible(false);
				}
			}
		}
	}

	private void filterTicket() {
		for (int i = 0; i < getPanelItem().getComponentCount(); i++) {
			if (panelItem.getComponent(i) instanceof ItemPanel) {
				Product product = ((ItemPanel) panelItem.getComponent(i)).getProduct();
				if (product instanceof Ticket) {
					if (ProductListPanel.getOrder().getCodes().indexOf(product.getCode()) != (-1)) {
						panelItem.getComponent(i).setVisible(false);
					}
				}
			}
		}

	}

	public JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBorder(null);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setViewportView(getPanelFilter());
			scrollPane.getVerticalScrollBar().setValue(0);
		}
		return scrollPane;
	}
}
