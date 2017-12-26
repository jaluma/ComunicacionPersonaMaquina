package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import internationalization.Internationalization;
import net.miginfocom.swing.MigLayout;

public class ProductListWindow extends JPanel {

	private MainWindow mainWindow;
	private JPanel contentPane;

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
	private JPanel panelItem;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panelPhoto;
	private JPanel panelInfo;
	private JPanel panelInfoPrice;
	private JLabel lblPhoto;
	private JTextArea txtrInfo;
	private JLabel lblPrice_2;
	private JButton btnAdd;
	private JLabel lblNameproduct;
	private JPanel panelSouth;
	private JLabel lblDateinitial;
	private JLabel lblDays;
	private JLabel lblPlace;
	private ComboBoxModel<String> modelSort;

	public ProductListWindow(MainWindow mainWindow, JPanel contentPane) {
		this.mainWindow = mainWindow;
		this.contentPane = contentPane;
		this.contentPane.setLayout(new BorderLayout(0, 0));
		this.contentPane.add(getPanelNorth(), BorderLayout.NORTH);
		this.contentPane.add(getPanelFilter(), BorderLayout.WEST);
		this.contentPane.add(getPanelCentral(), BorderLayout.CENTER);
		contentPane = this.contentPane;
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
			ResizableImage.setResizeImage(contentPane, lblLogo, "/img/logo.png", 250, 100);
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
			lblNumberitems = new JLabel(String.format(Internationalization.getString("number_items"), 0));
			lblNumberitems.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lblNumberitems;
	}

	private JButton getBtnCart() {
		if (btnCart == null) {
			btnCart = new JButton("");
			btnCart.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			ResizableImage.setResizeImage(contentPane, btnCart, "/img/cesta.png", 50, 50);
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
			panelFilter.setLayout(new GridLayout(0, 1, 50, 25));
			panelFilter.add(getPanelType());
			panelFilter.add(getPanelCategory());
			panelFilter.add(getPanelPeople());
			panelFilter.add(getPanelPrice());
			panelFilter.add(getPanelOnlyPhotos());
		}
		return panelFilter;
	}

	private JPanel getPanelType() {
		if (panelType == null) {
			panelType = new JPanel();
			panelType.setBackground(Color.WHITE);
			panelType.setBorder(UIManager.getBorder("Spinner.border"));
			panelType.setLayout(new GridLayout(2, 1, 0, 0));
			panelType.add(getLblType());
			panelType.add(getPanelCheckBoxType());
		}
		return panelType;
	}

	private JPanel getPanelCategory() {
		if (panelCategory == null) {
			panelCategory = new JPanel();
			panelCategory.setBackground(Color.WHITE);
			panelCategory.setBorder(UIManager.getBorder("Spinner.border"));
			panelCategory.setLayout(new GridLayout(2, 1, 100, 0));
			panelCategory.add(getLblCategory());
			panelCategory.add(getPanelStar());
		}
		return panelCategory;
	}

	private JPanel getPanelPeople() {
		if (panelPeople == null) {
			panelPeople = new JPanel();
			panelPeople.setBackground(Color.WHITE);
			panelPeople.setBorder(UIManager.getBorder("Spinner.border"));
			panelPeople.setLayout(new GridLayout(2, 1, 0, 0));
			panelPeople.add(getLblNumberperson());
			panelPeople.add(getPanelSliderPerson());
		}
		return panelPeople;
	}

	private JPanel getPanelOnlyPhotos() {
		if (panelOnlyPhotos == null) {
			panelOnlyPhotos = new JPanel();
			panelOnlyPhotos.setBackground(Color.WHITE);
			panelOnlyPhotos.setBorder(UIManager.getBorder("Spinner.border"));
			panelOnlyPhotos.setLayout(new BoxLayout(panelOnlyPhotos, BoxLayout.LINE_AXIS));
			panelOnlyPhotos.add(getChOnlyPhotos());
		}
		return panelOnlyPhotos;
	}

	private JLabel getLblType() {
		if (lblType == null) {
			lblType = new JLabel(Internationalization.getString("type"));
			lblType.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblType;
	}

	private JCheckBox getChckbxOnlyaccom() {
		if (chckbxOnlyaccom == null) {
			chckbxOnlyaccom = new JCheckBox(Internationalization.getString("type_only_accom"));
			chckbxOnlyaccom.setBackground(Color.WHITE);
		}
		return chckbxOnlyaccom;
	}

	private JCheckBox getChckbxOnlyPackages() {
		if (chckbxOnlyPackages == null) {
			chckbxOnlyPackages = new JCheckBox(Internationalization.getString("type_only_packages"));
			chckbxOnlyPackages.setBackground(Color.WHITE);
		}
		return chckbxOnlyPackages;
	}

	private JCheckBox getChckbkOnlyTicket() {
		if (chckbkOnlyTicket == null) {
			chckbkOnlyTicket = new JCheckBox(Internationalization.getString("type_only_ticket"));
			chckbkOnlyTicket.setBackground(Color.WHITE);
		}
		return chckbkOnlyTicket;
	}

	private JLabel getLblCategory() {
		if (lblCategory == null) {
			lblCategory = new JLabel(Internationalization.getString("category"));
			lblCategory.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblCategory;
	}

	private JPanel getPanelStar() {
		if (panelStar == null) {
			panelStar = new JPanel();
			panelStar.setBackground(Color.WHITE);
			panelStar.setLayout(new GridLayout(0, 5, 0, 0));
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
			btnStar1.setBackground(Color.WHITE);
		}
		return btnStar1;
	}

	private JButton getBtnStar2() {
		if (btnStar2 == null) {
			btnStar2 = new JButton("\u2606");
			btnStar2.setBackground(Color.WHITE);
		}
		return btnStar2;
	}

	private JButton getBtnStar5() {
		if (btnStar5 == null) {
			btnStar5 = new JButton("\u2606");
			btnStar5.setBackground(Color.WHITE);
		}
		return btnStar5;
	}

	private JButton getBtnStar3() {
		if (btnStar3 == null) {
			btnStar3 = new JButton("\u2606");
			btnStar3.setBackground(Color.WHITE);
		}
		return btnStar3;
	}

	private JButton getBtnStar4() {
		if (btnStar4 == null) {
			btnStar4 = new JButton("\u2606");
			btnStar4.setBackground(Color.WHITE);
		}
		return btnStar4;
	}

	private JPanel getPanelCheckBoxType() {
		if (panelCheckBoxType == null) {
			panelCheckBoxType = new JPanel();
			panelCheckBoxType.setBackground(Color.WHITE);
			FlowLayout fl_panelCheckBoxType = new FlowLayout(FlowLayout.CENTER, 0, 6);
			fl_panelCheckBoxType.setAlignOnBaseline(true);
			panelCheckBoxType.setLayout(fl_panelCheckBoxType);
			panelCheckBoxType.add(getChckbxOnlyaccom());
			panelCheckBoxType.add(getChckbxOnlyPackages());
			panelCheckBoxType.add(getChckbkOnlyTicket());
		}
		return panelCheckBoxType;
	}

	private JLabel getLblNumberperson() {
		if (lblNumberperson == null) {
			lblNumberperson = new JLabel("numberPerson");
			lblNumberperson.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNumberperson;
	}

	private JSlider getSliderPerson() {
		if (sliderPerson == null) {
			sliderPerson = new JSlider();
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
			panelSliderPerson.setLayout(new GridLayout(2, 1, 5, 0));
			panelSliderPerson.add(getSliderPerson());
			panelSliderPerson.add(getLblNewLabel());
		}
		return panelSliderPerson;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("^--> person");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNewLabel;
	}

	private JPanel getPanelPrice() {
		if (panelPrice == null) {
			panelPrice = new JPanel();
			panelPrice.setBorder(UIManager.getBorder("Spinner.border"));
			panelPrice.setBackground(Color.WHITE);
			panelPrice.setLayout(new GridLayout(2, 1, 0, 0));
			panelPrice.add(getLblPrice());
			panelPrice.add(getPanelSliderPrice());
		}
		return panelPrice;
	}

	private JLabel getLblPrice() {
		if (lblPrice == null) {
			lblPrice = new JLabel(Internationalization.getString("price"));
			lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblPrice;
	}

	private JPanel getPanelSliderPrice() {
		if (panelSliderPrice == null) {
			panelSliderPrice = new JPanel();
			panelSliderPrice.setBackground(Color.WHITE);
			panelSliderPrice.setLayout(new GridLayout(2, 1, 5, 0));
			panelSliderPrice.add(getSlider());
			panelSliderPrice.add(getLblPrice_1());
		}
		return panelSliderPrice;
	}

	private JSlider getSlider() {
		if (slider == null) {
			slider = new JSlider();
			slider.setValue(5);
			slider.setPaintLabels(true);
			slider.setMaximum(10);
			slider.setBackground(Color.WHITE);
		}
		return slider;
	}

	private JLabel getLblPrice_1() {
		if (lblPrice_1 == null) {
			lblPrice_1 = new JLabel("^--> price");
			lblPrice_1.setLabelFor(getSlider());
			lblPrice_1.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblPrice_1;
	}

	private JCheckBox getChOnlyPhotos() {
		if (chOnlyPhotos == null) {
			chOnlyPhotos = new JCheckBox(Internationalization.getString("only_photos"));
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
			comboBoxNumElement.setModel(new DefaultComboBoxModel<>(new Integer[] {10, 20, 30, 40}));
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
			modelSort = new DefaultComboBoxModel<String>(new String[] {Internationalization.getString("box_combo_price_down"), Internationalization.getString("box_combo_price_up"), Internationalization.getString("box_combo_category_down"), Internationalization.getString("box_combo_category_up")});
			comboBoxSort.setModel(modelSort);
		}
		return comboBoxSort;
	}

	private JScrollPane getScrollPaneItem() {
		if (scrollPaneItem == null) {
			scrollPaneItem = new JScrollPane();
			scrollPaneItem.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPaneItem.setViewportView(getPanelItem());
		}
		return scrollPaneItem;
	}

	private JPanel getPanelItem() {
		if (panelItem == null) {
			panelItem = new JPanel();
			panelItem.setBackground(Color.WHITE);
			panelItem.setLayout(new GridLayout(5, 1, 25, 25));
			panelItem.add(getPanel());
			panelItem.add(getPanel_1_2());
		}
		return panelItem;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(UIManager.getBorder("Spinner.border"));
			panel.setBackground(Color.WHITE);
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			panel.add(getPanelPhoto());
			panel.add(getPanelInfo());
		}
		return panel;
	}

	private JPanel getPanel_1_2() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBackground(Color.WHITE);
		}
		return panel_1;
	}

	private JPanel getPanelPhoto() {
		if (panelPhoto == null) {
			panelPhoto = new JPanel();
			panelPhoto.setBackground(Color.WHITE);
			panelPhoto.setLayout(new BoxLayout(panelPhoto, BoxLayout.X_AXIS));
			panelPhoto.add(getLblPhoto());
		}
		return panelPhoto;
	}

	private JPanel getPanelInfo() {
		if (panelInfo == null) {
			panelInfo = new JPanel();
			panelInfo.setBackground(Color.WHITE);
			panelInfo.setLayout(new BorderLayout(0, 0));
			panelInfo.add(getLblNameproduct(), BorderLayout.NORTH);
			panelInfo.add(getTxtrInfo(), BorderLayout.CENTER);
			panelInfo.add(getPanelInfoPrice(), BorderLayout.EAST);
			panelInfo.add(getPanelSouth(), BorderLayout.SOUTH);
		}
		return panelInfo;
	}

	private JPanel getPanelInfoPrice() {
		if (panelInfoPrice == null) {
			panelInfoPrice = new JPanel();
			panelInfoPrice.setBackground(Color.WHITE);
			panelInfoPrice.setLayout(new BorderLayout(0, 40));
			panelInfoPrice.add(getLblPrice_2(), BorderLayout.NORTH);
			panelInfoPrice.add(getBtnAdd(), BorderLayout.EAST);
		}
		return panelInfoPrice;
	}

	private JLabel getLblPhoto() {
		if (lblPhoto == null) {
			lblPhoto = new JLabel("\r\n\r\n");
			lblPhoto.setIcon(new ImageIcon(MainWindow.class.getResource("/img/PT001.jpg")));
		}
		return lblPhoto;
	}

	private JTextArea getTxtrInfo() {
		if (txtrInfo == null) {
			txtrInfo = new JTextArea();
			txtrInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
			txtrInfo.setEditable(false);
			txtrInfo.setWrapStyleWord(true);
			txtrInfo.setLineWrap(true);
			txtrInfo.setText(
					"szdxfctrgvbhyujnujjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj");
		}
		return txtrInfo;
	}

	private JLabel getLblPrice_2() {
		if (lblPrice_2 == null) {
			lblPrice_2 = new JLabel(Internationalization.getString("item_price").toUpperCase());
			lblPrice_2.setVerticalAlignment(SwingConstants.BOTTOM);
			lblPrice_2.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblPrice_2.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblPrice_2;
	}

	private JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton(Internationalization.getString("item_add").toUpperCase());
			btnAdd.setFont(new Font("Tahoma", Font.BOLD, 33));
		}
		return btnAdd;
	}

	private JLabel getLblNameproduct() {
		if (lblNameproduct == null) {
			lblNameproduct = new JLabel("nameProduct");
			lblNameproduct.setBackground(Color.WHITE);
			lblNameproduct.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return lblNameproduct;
	}

	private JPanel getPanelSouth() {
		if (panelSouth == null) {
			panelSouth = new JPanel();
			panelSouth.setBackground(Color.WHITE);
			panelSouth.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			panelSouth.add(getLblDateinitial());
			panelSouth.add(getLblDays());
			panelSouth.add(getLblPlace());
		}
		return panelSouth;
	}

	private JLabel getLblDateinitial() {
		if (lblDateinitial == null) {
			lblDateinitial = new JLabel("dateInitial");
		}
		return lblDateinitial;
	}

	private JLabel getLblDays() {
		if (lblDays == null) {
			lblDays = new JLabel("days");
		}
		return lblDays;
	}

	private JLabel getLblPlace() {
		if (lblPlace == null) {
			lblPlace = new JLabel("place");
		}
		return lblPlace;
	}

}
