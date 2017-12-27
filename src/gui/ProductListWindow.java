package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import event.ChangeLisnerSliderEvent;
import event.ComboBoxSortEvent;
import internationalization.Internationalization;
import logic.ListProduct;
import net.miginfocom.swing.MigLayout;
import park.Product;

public class ProductListWindow extends JPanel {

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
	private JCheckBox chckbxOnlyaccom;
	private JCheckBox chckbxOnlyPackages;
	private JCheckBox chckbkOnlyTicket;
	private JLabel lblCategory;
	private JPanel panelStar;
	private JButton btnStar1;
	private JButton btnStar2;
	private JButton btnStar5;
	private JButton btnStar3;
	private JButton btnStar4;
	private JPanel panelCheckBoxType;
	private JLabel lblNumberperson;
	private JSlider sliderPerson;
	private JPanel panelSliderPerson;
	private JLabel lblNewLabel;
	private JPanel panelPrice;
	private JLabel lblPrice;
	private JPanel panelSliderPrice;
	private JSlider slider;
	private JLabel lblPrice_1;
	private JCheckBox chOnlyPhotos;
	private JPanel panelCentral;
	private JPanel panelFilterSettings;
	private JLabel lblNumElement;
	private JComboBox<Integer> comboBoxNumElement;
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

	public ProductListWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		list = ListProduct.products;
		modelPlace = new DefaultComboBoxModel<String>(ListProduct.loadPlaces());
		setLayout(new BorderLayout(0, 0));
		add(getPanelNorth(), BorderLayout.NORTH);
		add(getPanelFilter(), BorderLayout.WEST);
		add(getPanelCentral(), BorderLayout.CENTER);
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
			panelCart.setLayout(new MigLayout("", "[][][]push[63px,right]", "[][]"));
			panelCart.add(getBtnCart(), "cell 3 0,alignx right,growy");
			panelCart.add(getLblNumberitems(), "cell 3 1,alignx right,growy");
		}
		return panelCart;
	}

	private JLabel getLblNumberitems() {
		if (lblNumberitems == null) {
			lblNumberitems = new JLabel();
			lblNumberitems.setHorizontalAlignment(SwingConstants.RIGHT);
			setNumberItemsCart(0);
		}
		return lblNumberitems;
	}

	public void setNumberItemsCart(int items) {
		lblNumberitems.setText(String.format(Internationalization.getString("number_items"), items));
	}

	private JButton getBtnCart() {
		if (btnCart == null) {
			btnCart = new JButton("");
			ResizableImage.setResizeImage(this, btnCart, "/img/cesta.png", 50, 50);
			btnCart.setBackground(Color.WHITE);
			btnCart.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					JDialog dialog = new CartWindow(mainWindow);
					dialog.setVisible(true);
				}
			});
		}
		return btnCart;
	}

	private JPanel getPanelFilter() {
		if (panelFilter == null) {
			panelFilter = new JPanel();
			panelFilter.setBackground(Color.WHITE);
			panelFilter.setBorder(null);
			GridBagLayout gbl_panelFilter = new GridBagLayout();
			gbl_panelFilter.columnWidths = new int[] { 207, 0, 0, 0 };
			gbl_panelFilter.rowHeights = new int[] { 46, 74, 50, 76, 64, 32, 0, 0 };
			gbl_panelFilter.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
			gbl_panelFilter.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
			panelFilter.setLayout(gbl_panelFilter);
			GridBagConstraints gbc_panelPlace = new GridBagConstraints();
			gbc_panelPlace.anchor = GridBagConstraints.NORTH;
			gbc_panelPlace.fill = GridBagConstraints.HORIZONTAL;
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
			panelType.add(getPanelCheckBoxType());
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

	private JCheckBox getChckbxOnlyaccom() {
		if (chckbxOnlyaccom == null) {
			chckbxOnlyaccom = new JCheckBox(Internationalization.getString("type_only_accom"));
			chckbxOnlyaccom.setFont(new Font("Tahoma", Font.PLAIN, 14));
			chckbxOnlyaccom.setBackground(Color.WHITE);
		}
		return chckbxOnlyaccom;
	}

	private JCheckBox getChckbxOnlyPackages() {
		if (chckbxOnlyPackages == null) {
			chckbxOnlyPackages = new JCheckBox(Internationalization.getString("type_only_packages"));
			chckbxOnlyPackages.setFont(new Font("Tahoma", Font.PLAIN, 14));
			chckbxOnlyPackages.setBackground(Color.WHITE);
		}
		return chckbxOnlyPackages;
	}

	private JCheckBox getChckbkOnlyTicket() {
		if (chckbkOnlyTicket == null) {
			chckbkOnlyTicket = new JCheckBox(Internationalization.getString("type_only_ticket"));
			chckbkOnlyTicket.setFont(new Font("Tahoma", Font.PLAIN, 14));
			chckbkOnlyTicket.setBackground(Color.WHITE);
		}
		return chckbkOnlyTicket;
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
			panelStar.add(getBtnStar1());
			panelStar.add(getBtnStar2());
			panelStar.add(getBtnStar3());
			panelStar.add(getBtnStar4());
			panelStar.add(getBtnStar5());
		}
		return panelStar;
	}

	private JButton getBtnStar1() {
		if (btnStar1 == null) {
			btnStar1 = new JButton("\u2606");
			btnStar1.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnStar1.setBackground(Color.WHITE);
		}
		return btnStar1;
	}

	private JButton getBtnStar2() {
		if (btnStar2 == null) {
			btnStar2 = new JButton("\u2606");
			btnStar2.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnStar2.setBackground(Color.WHITE);
		}
		return btnStar2;
	}

	private JButton getBtnStar5() {
		if (btnStar5 == null) {
			btnStar5 = new JButton("\u2606");
			btnStar5.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnStar5.setBackground(Color.WHITE);
		}
		return btnStar5;
	}

	private JButton getBtnStar3() {
		if (btnStar3 == null) {
			btnStar3 = new JButton("\u2606");
			btnStar3.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnStar3.setBackground(Color.WHITE);
		}
		return btnStar3;
	}

	private JButton getBtnStar4() {
		if (btnStar4 == null) {
			btnStar4 = new JButton("\u2606");
			btnStar4.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnStar4.setBackground(Color.WHITE);
		}
		return btnStar4;
	}

	private JPanel getPanelCheckBoxType() {
		if (panelCheckBoxType == null) {
			panelCheckBoxType = new JPanel();
			panelCheckBoxType.setAlignmentX(Component.LEFT_ALIGNMENT);
			panelCheckBoxType.setBackground(Color.WHITE);
			panelCheckBoxType.setLayout(new BoxLayout(panelCheckBoxType, BoxLayout.PAGE_AXIS));
			panelCheckBoxType.add(getChckbxOnlyaccom());
			panelCheckBoxType.add(getChckbxOnlyPackages());
			panelCheckBoxType.add(getChckbkOnlyTicket());
		}
		return panelCheckBoxType;
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

	private JSlider getSliderPerson() {
		if (sliderPerson == null) {
			sliderPerson = new JSlider();
			sliderPerson.setAlignmentY(Component.TOP_ALIGNMENT);
			sliderPerson.setBackground(Color.WHITE);
			sliderPerson.setPaintLabels(true);
			sliderPerson.setValue(5);
			sliderPerson.setMaximum(10);
		}
		return sliderPerson;
	}

	private JPanel getPanelSliderPerson() {
		if (panelSliderPerson == null) {
			panelSliderPerson = new JPanel();
			panelSliderPerson.setBackground(Color.WHITE);
			panelSliderPerson.setLayout(new BoxLayout(panelSliderPerson, BoxLayout.Y_AXIS));
			panelSliderPerson.add(getSliderPerson());
			panelSliderPerson.add(getSliderChild());
			panelSliderPerson.add(getLblNewLabel());
		}
		return panelSliderPerson;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			String str = updateLblPrice();
			lblNewLabel = new JLabel(str);
			lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNewLabel;
	}

	private JPanel getPanelPrice() {
		if (panelPrice == null) {
			panelPrice = new JPanel();
			panelPrice.setAlignmentX(Component.RIGHT_ALIGNMENT);
			panelPrice.setBorder(UIManager.getBorder("Spinner.border"));
			panelPrice.setBackground(Color.WHITE);
			panelPrice.setLayout(new BoxLayout(panelPrice, BoxLayout.Y_AXIS));
			panelPrice.add(getLblPrice());
			panelPrice.add(getPanelSliderPrice());
		}
		return panelPrice;
	}

	private JLabel getLblPrice() {
		if (lblPrice == null) {
			lblPrice = new JLabel(Internationalization.getString("price"));
			lblPrice.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblPrice.setAlignmentY(Component.TOP_ALIGNMENT);
			lblPrice.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblPrice;
	}

	private JPanel getPanelSliderPrice() {
		if (panelSliderPrice == null) {
			panelSliderPrice = new JPanel();
			panelSliderPrice.setBackground(Color.WHITE);
			panelSliderPrice.setLayout(new BoxLayout(panelSliderPrice, BoxLayout.Y_AXIS));
			panelSliderPrice.add(getSlider());
			panelSliderPrice.add(getLblPrice_1());
		}
		return panelSliderPrice;
	}

	private JSlider getSlider() {
		if (slider == null) {
			slider = new JSlider();
			slider.setForeground(Color.BLACK);
			slider.setValue(ListProduct.getMaxPrice() / 2);
			slider.setPaintLabels(true);
			slider.setMinimum(0);
			slider.setMaximum(ListProduct.getMaxPrice());
			slider.setBackground(Color.WHITE);
			slider.addChangeListener(new ChangeLisnerSliderEvent(getLblPrice_1()));
		}
		return slider;
	}

	private JLabel getLblPrice_1() {
		if (lblPrice_1 == null) {
			lblPrice_1 = new JLabel(String.valueOf(slider.getValue()));
			lblPrice_1.setAlignmentY(Component.TOP_ALIGNMENT);
			lblPrice_1.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblPrice_1.setLabelFor(getSlider());
			lblPrice_1.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblPrice_1;
	}

	private String updateLblPrice() {
		String str = Internationalization.getString("person_adult") + mainWindow.getNumberAdult() + " | "
				+ Internationalization.getString("person_child") + mainWindow.getNumberChild();
		return str;
	}

	private JCheckBox getChOnlyPhotos() {
		if (chOnlyPhotos == null) {
			chOnlyPhotos = new JCheckBox(Internationalization.getString("only_photos"));
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
		}
		return lblNumElement;
	}

	private JComboBox<Integer> getComboBoxNumElement() {
		if (comboBoxNumElement == null) {
			comboBoxNumElement = new JComboBox<Integer>();
			comboBoxNumElement.setModel(new DefaultComboBoxModel<>(new Integer[] { 10, 20, 30, 40 }));
		}
		return comboBoxNumElement;
	}

	private JPanel getPanelNumElement() {
		if (panelNumElement == null) {
			panelNumElement = new JPanel();
			panelNumElement.setBackground(Color.WHITE);
			FlowLayout flowLayout = (FlowLayout) panelNumElement.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			panelNumElement.add(getLblNumElement());
			panelNumElement.add(getComboBoxNumElement());
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
		}
		return lblSort;
	}

	private JComboBox<String> getComboBoxSort() {
		if (comboBoxSort == null) {
			comboBoxSort = new JComboBox<String>();
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
			loadProduct();
			scrollPaneItem.getVerticalScrollBar().setValue(0);
			scrollPaneItem.getVerticalScrollBar().setUnitIncrement(20);
		}

		return scrollPaneItem;
	}

	private void loadProduct() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		for (int i = 0; i < list.size(); i++) {
			Product product = list.get(i);
			product.loadData(mainWindow.getNumberAdult(), mainWindow.getNumberChild(), mainWindow.getDate(),
					mainWindow.getDays());

			if (product.getPark().getCity().equals(mainWindow.getInitialPanel().getSelectedItem())
					|| product.getPark().getCountry().equals(mainWindow.getInitialPanel().getSelectedItem())) {
				panel.add(new ItemPanel(mainWindow, list.get(i)));
			}
			ListProduct.products = list;
		}
		scrollPaneItem.setViewportView(panel);
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

	private JSlider getSliderChild() {
		if (sliderChild == null) {
			sliderChild = new JSlider();
			sliderChild.setValue(5);
			sliderChild.setPaintLabels(true);
			sliderChild.setMaximum(10);
			sliderChild.setBackground(Color.WHITE);
			sliderChild.setAlignmentY(0.0f);
		}
		return sliderChild;
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

	private JComboBox<String> getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox<String>();
			comboBox.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {

				}
			});
			comboBox.setFont(new Font("Tahoma", Font.BOLD, 13));
			comboBox.setEditable(true);
			comboBox.setBackground(Color.WHITE);
			comboBox.setModel(modelPlace);
		}
		return comboBox;
	}

	public void refresh() {

		scrollPaneItem.revalidate();
		scrollPaneItem.repaint();

	}
}
