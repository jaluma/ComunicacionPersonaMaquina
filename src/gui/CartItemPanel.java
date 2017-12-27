package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import internationalization.Internationalization;
import park.Package;
import park.Product;
import park.Ticket;

public class CartItemPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLabel lblName;
	private JLabel lblPhoto;
	private JPanel panelCenterOrder;
	private JPanel panelToolsOrder;
	private JButton btnRemove;
	private JButton btnEdit;
	private JTextArea txtrDescription;
	
	private MainWindow mainWindow;
	private Product product;
	private CartWindow cartWindow;
	
	public CartItemPanel(MainWindow mainWindow, CartWindow cartWindow, Product product) {
		this.mainWindow = mainWindow;
		this.cartWindow = cartWindow;
		this.product = product;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(UIManager.getBorder("Spinner.border"));
		setMaximumSize(new Dimension( Toolkit.getDefaultToolkit().getScreenSize().width, 230));
		setPreferredSize(new Dimension(822, 225));
		add(getPanel());
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(UIManager.getBorder("Spinner.border"));
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getLblPhoto(), BorderLayout.WEST);
			panel.add(getPanelCenterOrder(), BorderLayout.CENTER);
		}
		return panel;
	}

	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel(Internationalization.getProduct(product.getCode()));
			lblName.setHorizontalAlignment(SwingConstants.CENTER);
			lblName.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return lblName;
	}

	private JLabel getLblPhoto() {
		if (lblPhoto == null) {
			lblPhoto = new JLabel("");
			lblPhoto.setBackground(Color.WHITE);
			String path = "";
			if (product instanceof Ticket)
				path = "/img/" + product.getPark().getCode() + ".jpg";
			else if (product instanceof Package)
				path = "/img/" + ((Package)product).getAccom().getCode() + ".jpg";
			else 
				path = "/img/" + product.getCode() + ".jpg";
			
			ResizableImage.setResizeImage(this, lblPhoto, path, 300, 200);
		}
		return lblPhoto;
	}

	private JPanel getPanelCenterOrder() {
		if (panelCenterOrder == null) {
			panelCenterOrder = new JPanel();
			panelCenterOrder.setBackground(Color.WHITE);
			panelCenterOrder.setLayout(new BorderLayout(0, 0));
			panelCenterOrder.add(getTxtrDescription());
			panelCenterOrder.add(getLblName(), BorderLayout.NORTH);
			panelCenterOrder.add(getPanelToolsOrder(), BorderLayout.SOUTH);
		}
		return panelCenterOrder;
	}

	private JPanel getPanelToolsOrder() {
		if (panelToolsOrder == null) {
			panelToolsOrder = new JPanel();
			panelToolsOrder.setBackground(Color.WHITE);
			FlowLayout fl_panelToolsOrder = (FlowLayout) panelToolsOrder.getLayout();
			fl_panelToolsOrder.setAlignment(FlowLayout.RIGHT);
			panelToolsOrder.add(getBtnEdit());
			panelToolsOrder.add(getBtnRemove());
		}
		return panelToolsOrder;
	}

	private JButton getBtnRemove() {
		if (btnRemove == null) {
			btnRemove = new JButton(Internationalization.getString("cart_remove"));
			btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnRemove.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					mainWindow.getOrder().remove(product.getCode());
					mainWindow.getProductListPanel().setNumberItemsCart(mainWindow.getOrder().getItems());
					cartWindow.getLblSubTotal().setText(Internationalization.getCurrency(mainWindow.getOrder().getTotal()));
					
					CartItemPanel.this.setVisible(false);
					repaint();
				}
			});
		}
		return btnRemove;
	}

	private JButton getBtnEdit() {
		if (btnEdit == null) {
			btnEdit = new JButton(Internationalization.getString("cart_edit"));
			btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return btnEdit;
	}

	private JTextArea getTxtrDescription() {
		if (txtrDescription == null) {
			txtrDescription = new JTextArea();
			txtrDescription.setEditable(false);
			txtrDescription.setLineWrap(true);
			txtrDescription.setWrapStyleWord(true);
			txtrDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtrDescription.setText(product.getPark().getDescription());
		}
		return txtrDescription;
	}

}
