package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import internationalization.Internationalization;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;

public class CartWindow extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel panelNorth;
	private JLabel lblLogo;
	private JPanel bottomPanel;
	private JButton btnAddmore;
	private JButton btnFinish;
	private MainWindow mainWindow;
	private JPanel contentPane;
	private JPanel panelCenter;
	private JLabel lblOrder;
	private JScrollPane scrollPane;
	private JLabel lblSubTotal;
	private JPanel panelAcount;
	private JLabel lblSubtotal;
	private JPanel panel_1;

	public CartWindow(MainWindow mainWindow) {
		setTitle(Internationalization.getString("CartWindow.this.title")); //$NON-NLS-1$
		setIconImage(Toolkit.getDefaultToolkit().getImage(CartWindow.class.getResource("/img/cesta.png")));
		this.mainWindow = mainWindow;
		this.contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanelNorth(), BorderLayout.NORTH);
		contentPane.add(getPanelCenter(), BorderLayout.CENTER);
		contentPane.add(getBottomPanel(), BorderLayout.SOUTH);
		setContentPane(contentPane);
		setModal(true);
		setBounds(0,0,900, 589);
		setLocationRelativeTo(mainWindow);
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
					CartWindow.this.dispose();
				}
			});
			btnAddmore.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return btnAddmore;
	}

	private JButton getBtnFinish() {
		if (btnFinish == null) {
			btnFinish = new JButton(Internationalization.getString("cart_finish"));
			btnFinish.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (mainWindow.getOrder().getItems() == 0)
						JOptionPane.showMessageDialog(CartWindow.this, Internationalization.getString("error_cart_emptry"), Internationalization.getString("error_cart_emptry_title"), JOptionPane.WARNING_MESSAGE);
					else {
						CartWindow.this.dispose();
						System.out.println(mainWindow.getOrder().toString());
						JDialog dialog = new LogUpWindow(mainWindow);
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
	
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			JPanel panel = new JPanel();
			panel.setBorder(UIManager.getBorder("Spinner.border"));
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			scrollPane.setViewportView(panel);
			scrollPane.getVerticalScrollBar().setValue(0);
			scrollPane.getVerticalScrollBar().setUnitIncrement(20);
			for (int i = 0; i < mainWindow.getOrder().getItems(); i++) {
				panel.add(new CartItemPanel(mainWindow, CartWindow.this, mainWindow.getOrder().getProduct(i)));
			}
		}
		return scrollPane;
	}

	public void refresh() {
		scrollPane.removeAll();
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		scrollPane.setViewportView(panel);
		for (int i = 0; i < mainWindow.getOrder().getItems(); i++) {
			panel.add(new CartItemPanel(mainWindow, this, mainWindow.getOrder().getProduct(i)));
		}
		scrollPane.repaint();
		scrollPane.revalidate();
		
	}
	public JLabel getLblSubTotal() {
		if (lblSubTotal == null) {
			lblSubTotal = new JLabel(Internationalization.getCurrency(mainWindow.getOrder().getTotal())); //$NON-NLS-1$
			lblSubTotal.setForeground(Color.DARK_GRAY);
			lblSubTotal.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
			lblSubTotal.setBackground(Color.WHITE);
			lblSubTotal.setBorder(new EmptyBorder(0, 0, 0, 10));
			lblSubTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lblSubTotal;
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
