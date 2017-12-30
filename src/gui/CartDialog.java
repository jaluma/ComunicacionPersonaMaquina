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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import guiUtil.ResizableImage;
import internationalization.Internationalization;

public class CartDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel panelNorth;
	private JLabel lblLogo;
	private JPanel bottomPanel;
	private JButton btnAddmore;
	private JButton btnFinish;
	private JPanel contentPane;
	private JPanel panelCenter;
	private JLabel lblOrder;
	private JScrollPane scrollPane;
	private JLabel lblSubTotal;
	private JPanel panelAcount;
	private JLabel lblSubtotal;
	private JPanel panel_1;
	private JPanel panelItem;
	private boolean restoreBool;
	private MainWindow mainWindow;
	private InitialPanel ipOnlyRestore;

	/**
	 * @wbp.parser.constructor
	 */
	public CartDialog(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		setMinimumSize(new Dimension(950, 0));
		setTitle(Internationalization.getString("CartWindow.this.title")); //$NON-NLS-1$
		setIconImage(Toolkit.getDefaultToolkit().getImage(CartDialog.class.getResource("/img/cesta.png")));
		this.contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanelNorth(), BorderLayout.NORTH);
		contentPane.add(getPanelCenter(), BorderLayout.CENTER);
		contentPane.add(getBottomPanel(), BorderLayout.SOUTH);
		setContentPane(contentPane);
		setModal(true);
		setResizable(true);
		setBounds(0, 0, 950, 580);
		setLocationRelativeTo(mainWindow);
		getRootPane().setDefaultButton(btnFinish);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { getBtnAddmore() }));
	}

	public CartDialog(MainWindow mainWindow2, InitialPanel ip, boolean b) {
		this(mainWindow2);
		ipOnlyRestore = ip;
		restoreBool = b;
	}

	private JPanel getPanelNorth() {
		if (panelNorth == null) {
			panelNorth = new JPanel();
			panelNorth.setBackground(Color.WHITE);
			panelNorth.setLayout(new GridLayout(1, 3, 0, 0));
			panelNorth.add(getLblLogo());
			panelNorth.add(getPanel_1());
			panelNorth.add(getPanelAcount());
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

	private JPanel getBottomPanel() {
		if (bottomPanel == null) {
			bottomPanel = new JPanel();
			bottomPanel.setBackground(Color.WHITE);
			FlowLayout flowLayout = (FlowLayout) bottomPanel.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			bottomPanel.add(getBtnAddmore());
			bottomPanel.add(getBtnFinish());
		}
		return bottomPanel;
	}

	private JButton getBtnAddmore() {
		if (btnAddmore == null) {
			btnAddmore = new JButton(Internationalization.getString("cart_add_more"));
			btnAddmore.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					CartDialog.this.dispose();
					if (restoreBool)
						ipOnlyRestore.loadListProduct();
				}
			});
			btnAddmore.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return btnAddmore;
	}

	protected JButton getBtnFinish() {
		if (btnFinish == null) {
			btnFinish = new JButton(Internationalization.getString("cart_finish"));
			btnFinish.setEnabled(false);
			btnFinish.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (ProductListPanel.getOrder().getItems() == 0)
						JOptionPane.showMessageDialog(CartDialog.this,
								Internationalization.getString("error_cart_emptry"),
								Internationalization.getString("error_cart_emptry_title"), JOptionPane.WARNING_MESSAGE);
					else {
						CartDialog.this.dispose();
						JDialog dialog = new LogUpDialog(mainWindow, CartDialog.this);
						dialog.setVisible(true);
					}
				}
			});
			btnFinish.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return btnFinish;
	}

	private JPanel getPanelCenter() {
		if (panelCenter == null) {
			panelCenter = new JPanel();
			panelCenter.setBackground(Color.WHITE);
			panelCenter.setLayout(new BorderLayout(0, 0));
			panelCenter.add(getLblOrder(), BorderLayout.NORTH);
			panelCenter.add(getScrollPane(), BorderLayout.CENTER);
		}
		return panelCenter;
	}

	private JLabel getLblOrder() {
		if (lblOrder == null) {
			lblOrder = new JLabel(Internationalization.getString("cart_orders"));
			lblOrder.setBackground(Color.WHITE);
			lblOrder.setHorizontalAlignment(SwingConstants.CENTER);
			lblOrder.setFont(new Font("Tahoma", Font.BOLD, 22));
		}
		return lblOrder;
	}

	protected JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setViewportView(getItemPanel());
			scrollPane.getVerticalScrollBar().setValue(0);
			scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		}
		return scrollPane;
	}

	protected JPanel getItemPanel() {
		if (panelItem == null) {
			panelItem = new JPanel();
			panelItem.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent arg0) {
					if (panelItem.getComponentCount() == 0) {
						getBtnFinish().setEnabled(false);
					}

					for (int i = 0; i < panelItem.getComponentCount(); i++) {
						if (panelItem.getComponent(i).isVisible())
							getBtnFinish().setEnabled(true);
					}
				}
			});
			panelItem.setBorder(UIManager.getBorder("Spinner.border"));
			panelItem.setLayout(new BoxLayout(panelItem, BoxLayout.Y_AXIS));
			for (int i = 0; i < ProductListPanel.getOrder().getItems(); i++) {
				panelItem
						.add(new CartItemPanel(mainWindow, CartDialog.this, ProductListPanel.getOrder().getProduct(i)));
			}
		}

		return panelItem;
	}

	public JLabel getLblSubTotal() {
		if (lblSubTotal == null) {
			lblSubTotal = new JLabel(Internationalization
					.getCurrency(ProductListPanel.getOrder().getTotal() - ProductListPanel.getOrder().getDiscount()));
			updatePrice();
			lblSubTotal.setForeground(Color.DARK_GRAY);
			lblSubTotal.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
			lblSubTotal.setBackground(Color.WHITE);
			lblSubTotal.setBorder(new EmptyBorder(0, 0, 0, 10));
			lblSubTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lblSubTotal;
	}

	public void updatePrice() {
		lblSubTotal.setText(Internationalization
				.getCurrency(ProductListPanel.getOrder().getTotal() - ProductListPanel.getOrder().getDiscount())); // $NON-NLS-1$
	}

	private JPanel getPanelAcount() {
		if (panelAcount == null) {
			panelAcount = new JPanel();
			panelAcount.setBackground(Color.WHITE);
			panelAcount.setBorder(new EmptyBorder(5, 5, 5, 5));
			panelAcount.setLayout(new GridLayout(0, 1, 5, 5));
			panelAcount.add(getLblSubtotal());
			panelAcount.add(getLblSubTotal());
		}
		return panelAcount;
	}

	private JLabel getLblSubtotal() {
		if (lblSubtotal == null) {
			lblSubtotal = new JLabel(Internationalization.getString("subtotal")); //$NON-NLS-1$
			lblSubtotal.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return lblSubtotal;
	}

	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBackground(Color.WHITE);
			panel_1.setBorder(null);
			panel_1.setForeground(Color.WHITE);
		}
		return panel_1;
	}
}
