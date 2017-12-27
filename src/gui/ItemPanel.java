package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import internationalization.Internationalization;
import park.Package;
import park.Product;
import park.Ticket;

public class ItemPanel extends JPanel {

	private JPanel panelItem;
	private JPanel panel;
	private JPanel panelPhoto;
	private JPanel panelInfo;
	private JLabel lblPhoto;
	private JTextArea txtrInfo;
	private JButton btnAdd;
	private JLabel lblNameproduct;
	private JPanel panelSouth;
	private JLabel lblDateinitial;
	private JLabel lblDays;
	private JLabel lblPlace;

	private MainWindow mainWindow;
	private Product product;
	private JLabel lblSale;
	private JPanel panelPrice;
	private JLabel lblPrice;
	private JScrollPane scrollPane;

	public ItemPanel(MainWindow mainWindow, Product product) {
		setBackground(Color.WHITE);
		this.mainWindow = mainWindow;
		this.product = product;
		setBorder(UIManager.getBorder("Spinner.border"));
		setMaximumSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, 230));
		setPreferredSize(new Dimension(900, 230));
		setLayout(new BorderLayout(0, 0));
		add(getPanelItem());
	}

	private JPanel getPanelItem() {
		if (panelItem == null) {
			panelItem = new JPanel();
			panelItem.setBackground(Color.WHITE);
			panelItem.setLayout(new BoxLayout(panelItem, BoxLayout.Y_AXIS));
			panelItem.add(getPanel());
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

	private JPanel getPanelPhoto() {
		if (panelPhoto == null) {
			panelPhoto = new JPanel();
			panelPhoto.setAlignmentX(Component.LEFT_ALIGNMENT);
			panelPhoto.setBorder(new EmptyBorder(5, 5, 5, 15));
			panelPhoto.setBackground(Color.WHITE);
			FlowLayout fl_panelPhoto = new FlowLayout(FlowLayout.LEFT, 5, 5);
			panelPhoto.setLayout(fl_panelPhoto);
			panelPhoto.add(getLblPhoto());
			setPreferredSize(new Dimension(778, 225));
		}
		return panelPhoto;
	}

	private JPanel getPanelInfo() {
		if (panelInfo == null) {
			panelInfo = new JPanel();
			panelInfo.setBackground(Color.WHITE);
			panelInfo.setLayout(new BorderLayout(15, 0));
			panelInfo.add(getLblNameproduct(), BorderLayout.NORTH);
			panelInfo.add(getScrollPane(), BorderLayout.CENTER);
			panelInfo.add(getPanelSouth(), BorderLayout.SOUTH);
			panelInfo.add(getPanelPrice(), BorderLayout.EAST);
		}
		return panelInfo;
	}

	private JLabel getLblPhoto() {
		if (lblPhoto == null) {
			lblPhoto = new JLabel("");
			String path = "";
			if (product instanceof Ticket)
				path = "/img/" + product.getPark().getCode() + ".jpg";
			else if (product instanceof Package)
				path = "/img/" + ((Package) product).getAccom().getCode() + ".jpg";
			else
				path = "/img/" + product.getCode() + ".jpg";

			ResizableImage.setResizeImage(this, lblPhoto, path, 300, 200);
		}
		return lblPhoto;
	}

	private JTextArea getTxtrInfo() {
		if (txtrInfo == null) {
			txtrInfo = new JTextArea();
			txtrInfo.setWrapStyleWord(true);
			txtrInfo.setLineWrap(true);
			txtrInfo.setBorder(new EmptyBorder(5, 5, 5, 5));
			txtrInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
			txtrInfo.setEditable(false);
			txtrInfo.setHighlighter(null);
			txtrInfo.setText(product.getPark().getDescription());
		}
		return txtrInfo;
	}

	private JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton(Internationalization.getString("item_add").toUpperCase());
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					mainWindow.getOrder().add(product.getCode());
					mainWindow.getProductListPanel().setNumberItemsCart(mainWindow.getOrder().getItems());

				}
			});
			btnAdd.setFont(new Font("Tahoma", Font.BOLD, 33));
			btnAdd.setMaximumSize(new Dimension(100, 100));
		}
		return btnAdd;
	}

	private JLabel getLblNameproduct() {
		if (lblNameproduct == null) {
			lblNameproduct = new JLabel(Internationalization.getProduct(product.getCode()) + " ("
					+ product.getDuration() + " " + Internationalization.getString("days") + ")");
			lblNameproduct.setHorizontalAlignment(SwingConstants.CENTER);
			lblNameproduct.setBackground(Color.WHITE);
			lblNameproduct.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return lblNameproduct;
	}

	private JPanel getPanelSouth() {
		if (panelSouth == null) {
			panelSouth = new JPanel();
			panelSouth.setBackground(Color.WHITE);
			panelSouth.setLayout(new GridLayout(1, 2, 0, 0));
			panelSouth.add(getLblPlace());
			if (product.getPark().isSale())
				panelSouth.add(getLblSale());
		}
		return panelSouth;
	}

	private JLabel getLblPlace() {
		if (lblPlace == null) {
			lblPlace = new JLabel(Internationalization.getString("location") + product.getPark().getCity() + "-"
					+ product.getPark().getCountry() + " (" + product.getPark().getName() + ")");
			lblPlace.setBorder(new EmptyBorder(0, 5, 0, 0));
			lblPlace.setHorizontalAlignment(SwingConstants.LEFT);
			lblPlace.setFont(new Font("Tahoma", Font.ITALIC, 15));
		}
		return lblPlace;
	}

	private JLabel getLblSale() {
		if (lblSale == null) {
			lblSale = new JLabel(Internationalization.getString("sale"));
			lblSale.setForeground(Color.RED);
			lblSale.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblSale.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lblSale;
	}

	private JPanel getPanelPrice() {
		if (panelPrice == null) {
			panelPrice = new JPanel();
			panelPrice.setBackground(Color.WHITE);
			panelPrice.setLayout(new BorderLayout(5, 5));
			panelPrice.add(getLabel_1(), BorderLayout.NORTH);
			panelPrice.add(getBtnAdd(), BorderLayout.CENTER);
		}
		return panelPrice;
	}

	private JLabel getLabel_1() {
		if (lblPrice == null) {
			lblPrice = new JLabel(Internationalization.getCurrency(product.getTotal() - product.getDiscount()));
			lblPrice.setBorder(new EmptyBorder(0, 0, 0, 10));
			lblPrice.setBackground(Color.WHITE);
			lblPrice.setForeground(Color.DARK_GRAY);
			lblPrice.setFont(new Font("Tahoma", Font.ITALIC, 30));
			lblPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lblPrice;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportBorder(new LineBorder(new Color(64, 64, 64), 0, true));
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setWheelScrollingEnabled(false);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setViewportView(getTxtrInfo());
		}
		return scrollPane;
	}
}
