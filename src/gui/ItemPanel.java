package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import logic.product.Accommodation;
import logic.product.Package;
import logic.product.Product;
import logic.product.Ticket;
import logic.product.TypeHotel;
import util.gui.CopyObject;
import util.gui.ResizableImage;
import util.gui.internationalization.Internationalization;

public class ItemPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelItem;
	private JPanel panel;
	private JPanel panelPhoto;
	private JPanel panelInfo;
	private JLabel lblPhoto;
	private JEditorPane txtrInfo;
	private JButton btnAdd;
	private JLabel lblNameproduct;
	private JPanel panelSouth;

	private Product product;
	private JLabel lblSale;
	private JPanel panelPrice;
	private JLabel lblPrice;
	private JScrollPane scrollPane;
	private boolean image;
	private JCheckBox chckbxChbreakfast;
	private JPanel panelServices;
	private JLabel lblPark;
	private JLabel lblHotel;
	private JLabel lblTicket;
	private JLabel lblApartament;
	private ProductListPanel productListPanel;

	public ItemPanel(ProductListPanel plPanel, Product product) {
		setBackground(Color.WHITE);
		this.product = product;
		this.productListPanel = plPanel;
		setBorder(UIManager.getBorder("Spinner.border"));
		setMaximumSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, 230));
		setPreferredSize(new Dimension(900, 230));
		setLayout(new BorderLayout(0, 0));
		add(getPanelItem());
		setVisible(true);
	}

	public Product getProduct() {
		return product;
	}

	public boolean getImage() {
		return image;
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

	protected JLabel getLblPhoto() {
		if (lblPhoto == null) {
			lblPhoto = new JLabel("");
			String path = "";
			if (product instanceof Ticket)
				path = "/img/products/" + product.getPark().getCode() + ".jpg";
			else if (product instanceof Package)
				path = "/img/products/" + product.getCode() + ".jpg";
			else
				path = "/img/products/" + product.getCode() + ".jpg";

			image = ResizableImage.setResizeImage(this, lblPhoto, path, 300, 200);
			lblPhoto.setToolTipText(product.getName());
		}
		return lblPhoto;
	}

	private JEditorPane getTxtrInfo() {
		if (txtrInfo == null) {
			txtrInfo = new JEditorPane();
			txtrInfo.setBorder(new EmptyBorder(5, 5, 5, 5));
			txtrInfo.setContentType("text/html");
			txtrInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
			txtrInfo.setEditable(false);
			txtrInfo.setHighlighter(null);
			txtrInfo.setText("<font face=\"Tahoma\">" + product.toString3() + "</font>");
			txtrInfo.setCaretPosition(0);
		}
		return txtrInfo;
	}

	private JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton(Internationalization.getString("item_add").toUpperCase());
			btnAdd.setToolTipText(Internationalization.getToolTips("add"));
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int number = product.getNumberAdult() + product.getNumberChild();
					if (product instanceof Accommodation && ((Accommodation) product).getNum() < number
							|| product instanceof Package && ((Package) product).getAccom().getNum() < number) {
						JOptionPane.showMessageDialog(ItemPanel.this,
								Internationalization.getString("error_number_size"),
								Internationalization.getString("error_number_size_title"), JOptionPane.WARNING_MESSAGE);
						return;
					}

					ProductListPanel.getOrder().add(CopyObject.copy(product));
					productListPanel.setNumberItemsCart(ProductListPanel.getOrder().getItems());

					if (product instanceof Ticket)
						ItemPanel.this.setVisible(false);

					productListPanel.updateNumberItems();
				}
			});
			btnAdd.setFont(new Font("Tahoma", Font.BOLD, 33));
			btnAdd.setMaximumSize(new Dimension(100, 100));
		}
		return btnAdd;
	}

	private JLabel getLblNameproduct() {
		if (lblNameproduct == null) {
			String str = product.toString2();
			lblNameproduct = new JLabel(str);
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
			panelSouth.setLayout(new BorderLayout(0, 0));
			if (product.isSale())
				panelSouth.add(getLblSale(), BorderLayout.EAST);
			panelSouth.add(getPanelServices(), BorderLayout.WEST);
		}
		return panelSouth;
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
			panelPrice.add(getLblPrice(), BorderLayout.NORTH);
			panelPrice.add(getBtnAdd(), BorderLayout.CENTER);
		}
		return panelPrice;
	}

	protected JLabel getLblPrice() {
		if (lblPrice == null) {
			lblPrice = new JLabel();
			lblPrice.setBorder(new EmptyBorder(0, 0, 0, 10));
			lblPrice.setBackground(Color.WHITE);
			updatePrice();
			lblPrice.setForeground(Color.DARK_GRAY);
			lblPrice.setFont(new Font("Tahoma", Font.ITALIC, 30));
			lblPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lblPrice;
	}

	public void updatePrice() {
		lblPrice.setText(Internationalization.getCurrency(product.getTotal() - product.getDiscount()));
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportBorder(new LineBorder(new Color(64, 64, 64), 0, true));
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setViewportView(getTxtrInfo());
			scrollPane.getVerticalScrollBar().setValue(0);
		}
		return scrollPane;
	}

	private JCheckBox getChckbxChbreakfast() {
		if (chckbxChbreakfast == null) {
			chckbxChbreakfast = new JCheckBox(Internationalization.getString("breakfast"));
			chckbxChbreakfast.setBackground(Color.WHITE);
			chckbxChbreakfast.addChangeListener(new ChangeListener() {

				@Override
				public void stateChanged(ChangeEvent e) {
					if (chckbxChbreakfast.isSelected()) {
						((Accommodation) product).setBreakfast(true);
					} else {
						((Accommodation) product).setBreakfast(false);
					}
					updatePrice();
				}
			});
		}
		return chckbxChbreakfast;
	}

	private JPanel getPanelServices() {
		if (panelServices == null) {
			panelServices = new JPanel();
			panelServices.setBackground(Color.WHITE);
			panelServices.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

			if (product instanceof Ticket) {
				panelServices.add(getLblPark());
				panelServices.add(getLblTicket());
			}

			if (product instanceof Accommodation && ((Accommodation) product).getType().equals(TypeHotel.AP.toString()))
				panelServices.add(getLblApartament());
			else if (product instanceof Accommodation
					&& ((Accommodation) product).getType().equals(TypeHotel.HO.toString())) {
				panelServices.add(getLblHotel());
				panelServices.add(getChckbxChbreakfast());
			} else if (product instanceof Accommodation) {
				panelServices.add(getLblHotel());
				panelServices.add(getLblApartament());
			}

			if (product instanceof Package) {
				panelServices.add(getLblPark());
				panelServices.add(getLblTicket());
				String type = ((Package) product).getAccom().getType();
				if (type.equals(TypeHotel.HO.toString())) {
					panelServices.add(getLblHotel());
				} else if (type.equals(TypeHotel.AP.toString())) {
					panelServices.add(getLblApartament());
				} else if (type.equals(TypeHotel.AH.toString())) {
					panelServices.add(getLblHotel());
					panelServices.add(getLblApartament());
				}

			}
		}
		return panelServices;
	}

	private JLabel getLblPark() {
		if (lblPark == null) {
			lblPark = new JLabel(); // $NON-NLS-1$
			lblPark.setToolTipText(Internationalization.getString("park"));
			ResizableImage.setResizeImage(this, lblPark, "/img/park.png", 50, 50);
		}
		return lblPark;
	}

	private JLabel getLblHotel() {
		if (lblHotel == null) {
			lblHotel = new JLabel(); // $NON-NLS-1$
			lblHotel.setToolTipText(Internationalization.getString("hotel"));
			ResizableImage.setResizeImage(this, lblHotel, "/img/hotel.png", 50, 50);
		}
		return lblHotel;
	}

	private JLabel getLblTicket() {
		if (lblTicket == null) {
			lblTicket = new JLabel(); // $NON-NLS-1$
			lblTicket.setToolTipText(Internationalization.getString("ticket"));
			ResizableImage.setResizeImage(this, lblTicket, "/img/ticket.png", 50, 50);
		}
		return lblTicket;
	}

	private JLabel getLblApartament() {
		if (lblApartament == null) {
			lblApartament = new JLabel(); // $NON-NLS-1$
			lblApartament.setToolTipText(Internationalization.getString("apartament"));
			ResizableImage.setResizeImage(this, lblApartament, "/img/apartament.png", 50, 50);
		}
		return lblApartament;
	}
}
