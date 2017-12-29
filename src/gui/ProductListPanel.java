package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import event.ComboBoxSortEvent;
import event.FocusTextFieldEvent;
import event.NumberTextFieldFormatEvent;
import guiUtil.GuiUtil;
import guiUtil.ResizableImage;
import internationalization.Internationalization;
import net.miginfocom.swing.MigLayout;
import product.Accommodation;
import product.ListProduct;
import product.Package;
import product.Product;
import product.Ticket;

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
	private JRadioButton rbOnlyAccom;
	private JRadioButton rbOnlyPackage;
	private JRadioButton rbOnlyTicket;
	private JLabel lblCategory;
	private JPanel panelStar;
	private JPanel panelRadioButtonType;
	private JLabel lblNumberperson;
	private JSlider sliderPerson;
	private JPanel panelSliderPerson;
	private JLabel lblPersonText;
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
	private JRadioButton rbAll;
	public final ButtonGroup buttonGroup = new ButtonGroup();
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

	public ProductListPanel(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		list = ListProduct.products;
		modelPlace = new DefaultComboBoxModel<String>(ListProduct.loadPlaces());
		setLayout(new BorderLayout(0, 0));
		add(getPanelNorth(), BorderLayout.NORTH);
		add(getPanelFilter(), BorderLayout.WEST);
		add(getPanelCentral(), BorderLayout.CENTER);
		mainWindow.setMinimumSize(new Dimension(1200, 720));
		mainWindow.setLocationRelativeTo(null);
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
			setNumberItemsCart(mainWindow.getOrder().getItems());
		}
		return lblNumberitems;
	}

	public void setNumberItemsCart(int items) {
		lblNumberitems.setText(String.format(Internationalization.getString("number_items"), items));
	}

	protected JButton getBtnCart() {
		if (btnCart == null) {
			btnCart = new JButton("");
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
			gbl_panelFilter.rowHeights = new int[] { 46, 74, 50, 76, 64, 32, 0, 0 };
			gbl_panelFilter.columnWeights = new double[] { 1.0, 0.0, 0.0, Double.MIN_VALUE };
			gbl_panelFilter.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
			panelFilter.setLayout(gbl_panelFilter);
			GridBagConstraints gbc_panelPlace = new GridBagConstraints();
			gbc_panelPlace.fill = GridBagConstraints.BOTH;
			gbc_panelPlace.insets = new Insets(0, 0, 5, 5);
			gbc_panelPlace.gridx = 0;
			gbc_panelPlace.gridy = 0;
			panelFilter.add(getPanelPlace(), gbc_panelPlace);
			GridBagConstraints gbc_panelType = new GridBagConstraints();
			gbc_panelType.insets = new Insets(0, 0, 5, 5);
			gbc_panelType.fill = GridBagConstraints.BOTH;
			gbc_panelType.gridx = 0;
			gbc_panelType.gridy = 1;
			panelFilter.add(getPanelType(), gbc_panelType);
			GridBagConstraints gbc_panelCategory = new GridBagConstraints();
			gbc_panelCategory.fill = GridBagConstraints.BOTH;
			gbc_panelCategory.insets = new Insets(0, 0, 5, 5);
			// gbc_panelCategory.insets = new Insets(0, 0, 15, 0);
			gbc_panelCategory.gridx = 0;
			gbc_panelCategory.gridy = 2;
			panelFilter.add(getPanelCategory(), gbc_panelCategory);
			GridBagConstraints gbc_panelPeople = new GridBagConstraints();
			gbc_panelPeople.fill = GridBagConstraints.BOTH;
			gbc_panelPeople.insets = new Insets(0, 0, 5, 5);
			gbc_panelPeople.gridx = 0;
			gbc_panelPeople.gridy = 3;
			panelFilter.add(getPanelPeople(), gbc_panelPeople);
			GridBagConstraints gbc_panelPrice = new GridBagConstraints();
			gbc_panelPrice.fill = GridBagConstraints.BOTH;
			gbc_panelPrice.insets = new Insets(0, 0, 5, 5);
			gbc_panelPrice.gridx = 0;
			gbc_panelPrice.gridy = 4;
			panelFilter.add(getPanelPrice(), gbc_panelPrice);
			GridBagConstraints gbc_panelOnlyPhotos = new GridBagConstraints();
			gbc_panelOnlyPhotos.fill = GridBagConstraints.BOTH;
			gbc_panelOnlyPhotos.insets = new Insets(0, 0, 5, 5);
			gbc_panelOnlyPhotos.gridx = 0;
			gbc_panelOnlyPhotos.gridy = 5;
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

	private JRadioButton getRbOnlyAccom() {
		if (rbOnlyAccom == null) {
			rbOnlyAccom = new JRadioButton(Internationalization.getString("type_only_accom"));
			rbOnlyAccom.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					if (!rbOnlyAccom.isSelected()) {
						for (int i = 0; i < panelStar.getComponentCount(); i++) {
							if (panelStar.getComponent(i) instanceof JButton) {
								((JButton) panelStar.getComponent(i)).setText("\u2606");
							}

						}
					}
				}
			});
			buttonGroup.add(rbOnlyAccom);
			rbOnlyAccom.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					filtersReset();
				}
			});
			rbOnlyAccom.setFont(new Font("Tahoma", Font.PLAIN, 14));
			rbOnlyAccom.setBackground(Color.WHITE);
		}
		return rbOnlyAccom;
	}

	private JRadioButton getRbOnlyPackage() {
		if (rbOnlyPackage == null) {
			rbOnlyPackage = new JRadioButton(Internationalization.getString("type_only_packages"));
			buttonGroup.add(rbOnlyPackage);
			rbOnlyPackage.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					filtersReset();
				}
			});
			rbOnlyPackage.setFont(new Font("Tahoma", Font.PLAIN, 14));
			rbOnlyPackage.setBackground(Color.WHITE);
		}
		return rbOnlyPackage;
	}

	private JRadioButton getRbOnlyTicket() {
		if (rbOnlyTicket == null) {
			rbOnlyTicket = new JRadioButton(Internationalization.getString("type_only_ticket"));
			buttonGroup.add(rbOnlyTicket);
			rbOnlyTicket.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					filtersReset();
				}
			});
			rbOnlyTicket.setFont(new Font("Tahoma", Font.PLAIN, 14));
			rbOnlyTicket.setBackground(Color.WHITE);
		}
		return rbOnlyTicket;
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

	private JPanel getPanelStar() {
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
		JButton btnStar1 = new JButton("\u2606");
		btnStar1.setMinimumSize(new Dimension(45, 25));
		btnStar1.setPreferredSize(new Dimension(45, 25));
		btnStar1.setMaximumSize(new Dimension(45, 25));
		btnStar1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton buttonPressed = (JButton) e.getSource();
				numberStars = Integer.parseInt(buttonPressed.getActionCommand());

				// update gui
				rbOnlyAccom.setSelected(true);
				if (buttonPressed.getText().equals("\u2606")) {
					for (int i = 0; i < panelStar.getComponentCount(); i++) {
						if (panelStar.getComponent(i) instanceof JButton) {
							if (Integer
									.parseInt(((JButton) panelStar.getComponent(i)).getActionCommand()) <= numberStars)
								((JButton) panelStar.getComponent(i)).setText("\u2605");
						}

					}
				} else {
					for (int i = 0; i < panelStar.getComponentCount(); i++) {
						if (panelStar.getComponent(i) instanceof JButton) {
							((JButton) panelStar.getComponent(i)).setText("\u2606");
						}

					}
				}

				// filter
				filtersReset();
			}
		});
		btnStar1.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnStar1.setActionCommand(String.valueOf(index));
		btnStar1.setBackground(Color.WHITE);
		return btnStar1;
	}

	protected JPanel getPanelRadioButtonType() {
		if (panelRadioButtonType == null) {
			panelRadioButtonType = new JPanel();
			panelRadioButtonType.setBackground(TRANSPARENT);
			panelRadioButtonType.setLayout(new BoxLayout(panelRadioButtonType, BoxLayout.Y_AXIS));
			panelRadioButtonType.add(getRbAll());
			panelRadioButtonType.add(getRbOnlyAccom());
			panelRadioButtonType.add(getRbOnlyPackage());
			panelRadioButtonType.add(getRbOnlyTicket());
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
			sliderPerson.setValue(mainWindow.getNumberAdult());
			sliderPerson.setMinimum(1);
			int valueMax = setMaxSlider(mainWindow.getNumberAdult());
			sliderPerson.setMaximum(valueMax + 1);
			sliderPerson.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					mainWindow.setNumberAdult(sliderPerson.getValue());
					lblPersonText.setText(updateLblPerson());
					for (int i = 0; i < panelItem.getComponentCount(); i++) {
						Product product = ((ItemPanel) panelItem.getComponent(i)).getProduct();
						product.setNumberAdult(sliderPerson.getValue());
						mainWindow.setNumberAdult(sliderPerson.getValue());
						((ItemPanel) panelItem.getComponent(i)).updatePrice();
					}
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
			int valueMax = setMaxSlider(mainWindow.getNumberChild());
			sliderChild.setMaximum(valueMax);
			sliderChild.setValue(mainWindow.getNumberChild());
			sliderChild.setBackground(Color.WHITE);
			sliderChild.setAlignmentY(0.0f);
			sliderChild.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					mainWindow.setNumberChild(sliderChild.getValue());
					lblPersonText.setText(updateLblPerson());
					for (int i = 0; i < panelItem.getComponentCount(); i++) {
						Product product = ((ItemPanel) panelItem.getComponent(i)).getProduct();
						product.setNumberChild(sliderChild.getValue());
						mainWindow.setNumberChild(sliderChild.getValue());
						((ItemPanel) panelItem.getComponent(i)).updatePrice();
					}
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
			panelSliderPerson.add(getSliderPerson());
			panelSliderPerson.add(getSliderChild());
			panelSliderPerson.add(getLblPersonText());
		}
		return panelSliderPerson;
	}

	private JLabel getLblPersonText() {
		if (lblPersonText == null) {
			String str = updateLblPerson();
			lblPersonText = new JLabel(str);
			lblPersonText.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblPersonText.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblPersonText.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblPersonText;
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

	private String updateLblPerson() {
		String str = Internationalization.getString("person_adult") + mainWindow.getNumberAdult() + " | "
				+ Internationalization.getString("person_child") + mainWindow.getNumberChild();
		return str;
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
			comboBoxSort.setFont(new Font("Tahoma", Font.PLAIN, 14));
			modelSort = new DefaultComboBoxModel<String>(
					new String[] { Internationalization.getString("box_combo_relevance"),
							Internationalization.getString("box_combo_price_down"),
							Internationalization.getString("box_combo_price_up") });
			comboBoxSort.setModel(modelSort);
			comboBoxSort.addItemListener(new ComboBoxSortEvent(mainWindow, this));
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
			loadItems();
			filterPlaceChange();
		}
		return panelItem;
	}

	private void loadItems() {
		for (int i = 0; i < list.size(); i++) {
			Product product = list.get(i);
			product.loadData(mainWindow.getNumberAdult(), mainWindow.getNumberChild(), mainWindow.getDate(),
					mainWindow.getDays());
			panelItem.add(new ItemPanel(mainWindow, list.get(i)));
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
			comboBox.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					for (int i = 0; i < panelItem.getComponentCount(); i++) {
						filterPlaceChange();
						rbOnlyTicket.setSelected(false);
						rbOnlyAccom.setSelected(false);
						rbOnlyPackage.setSelected(false);
					}
					lblLblnumberelem.setText(String.valueOf(GuiUtil.getVisibleChildrenCount(getPanelItem())));
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

	private JRadioButton getRbAll() {
		if (rbAll == null) {
			rbAll = new JRadioButton(Internationalization.getString("type_all_product"));
			rbAll.setBackground(TRANSPARENT);
			rbAll.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					filtersReset();
				}
			});
			buttonGroup.add(rbAll);
			rbAll.setSelected(true);
			rbAll.setFont(new Font("Tahoma", Font.PLAIN, 14));
			rbAll.setBackground(Color.WHITE);
		}
		return rbAll;
	}

	private JLabel getLblLblnumberelem() {
		if (lblLblnumberelem == null) {
			lblLblnumberelem = new JLabel(String.valueOf(GuiUtil.getVisibleChildrenCount(getPanelItem()))); // $NON-NLS-1$
			lblLblnumberelem.setFont(new Font("Tahoma", Font.BOLD, 14));
		}
		return lblLblnumberelem;
	}

	protected JTextField getTxtMinprice() {
		if (txtMinprice == null) {
			txtMinprice = new JTextField();
			txtMinprice.setHorizontalAlignment(SwingConstants.CENTER);
			txtMinprice.setForeground(Color.DARK_GRAY);
			txtMinprice.setFont(new Font("Tahoma", Font.PLAIN, 14));
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
			txtMaxprice.setFont(new Font("Tahoma", Font.PLAIN, 14));
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
			btnGopricefilter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (Integer.parseInt(txtMinprice.getText()) > Integer.parseInt(txtMaxprice.getText())) {
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
			panelTxFPrice.setLayout(new GridLayout(0, 2, 2, 0));
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

	public void refresh() {
		panelItem.removeAll();
		panelItem.revalidate();
		panelItem.repaint();
		new Thread(new Runnable() {

			@Override
			public void run() {
				loadItems();
				filtersReset();
				panelItem.revalidate();
				panelItem.repaint();

			}
		}).start();

	}

	// <----------------------------FILTERS---------------------------------->
	protected void filtersReset() {
		filterPlaceChange();
		filterOnlyAccomCh();
		filterOnlyTicketCh();
		filterOnlyPackageCh();
		filterOnlyPhostosCh();
		filterStars();
		filterPriceCh();
		lblLblnumberelem.setText(String.valueOf(GuiUtil.getVisibleChildrenCount(getPanelItem())));
	}

	private void filterOnlyAccomCh() {
		if (rbOnlyAccom.isSelected()) {
			for (int i = 0; i < panelItem.getComponentCount(); i++) {
				if (panelItem.getComponent(i) instanceof ItemPanel) {
					Product product = ((ItemPanel) panelItem.getComponent(i)).getProduct();
					if (panelItem.getComponent(i).isVisible() && product instanceof Accommodation) {
						panelItem.getComponent(i).setVisible(true);
					} else {
						panelItem.getComponent(i).setVisible(false);
					}
				}
			}
		}
	}

	private void filterOnlyTicketCh() {
		if (rbOnlyTicket.isSelected()) {
			for (int i = 0; i < panelItem.getComponentCount(); i++) {
				if (panelItem.getComponent(i) instanceof ItemPanel) {
					Product product = ((ItemPanel) panelItem.getComponent(i)).getProduct();
					if (panelItem.getComponent(i).isVisible() && product instanceof Ticket) {
						panelItem.getComponent(i).setVisible(true);
					} else {
						panelItem.getComponent(i).setVisible(false);
					}
				}
			}
		}
	}

	private void filterOnlyPackageCh() {
		if (rbOnlyPackage.isSelected()) {
			for (int i = 0; i < panelItem.getComponentCount(); i++) {
				if (panelItem.getComponent(i) instanceof ItemPanel) {
					Product product = ((ItemPanel) panelItem.getComponent(i)).getProduct();
					if (panelItem.getComponent(i).isVisible() && product instanceof Package) {
						panelItem.getComponent(i).setVisible(true);
					} else {
						panelItem.getComponent(i).setVisible(false);
					}
				}
			}
		}
	}

	private void filterPlaceChange() {
		for (int i = 0; i < panelItem.getComponentCount(); i++) {
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
		if (rbOnlyAccom.isSelected()) {
			for (int i = 0; i < panelItem.getComponentCount(); i++) {
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
		if (chOnlyPhotos.isSelected()) {
			for (int i = 0; i < panelItem.getComponentCount(); i++) {
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
			for (int i = 0; i < panelItem.getComponentCount(); i++) {
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
}
