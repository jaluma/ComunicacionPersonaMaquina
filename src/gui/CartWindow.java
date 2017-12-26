package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import internationalization.Internationalization;
import java.awt.Toolkit;

public class CartWindow extends JDialog {

	private JPanel panelNorth;
	private JLabel lblLogo;
	private JPanel bottomPanel;
	private JButton btnAddmore;
	private JButton btnFinish;
	private JPanel panelCenter;
	private JLabel lblOrder;
	private JScrollPane scrollPane;
	private JPanel panelScroll;
	private JPanel panel;
	private JLabel lblName;
	private JLabel lblPhoto;
	private JPanel panelCenterOrder;
	private JPanel panelToolsOrder;
	private JButton btnRemove;
	private JButton btnEdit;
	private JTextArea txtrDescription;
	private MainWindow mainWindow;
	private JPanel contentPane;

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
		setBounds(0,0,798, 589);
		setLocationRelativeTo(null);
	}

	private JPanel getPanelNorth() {
		if (panelNorth == null) {
			panelNorth = new JPanel();
			panelNorth.setBackground(Color.WHITE);
			FlowLayout flowLayout = (FlowLayout) panelNorth.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			panelNorth.add(getLblLogo());
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
			btnAddmore.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return btnAddmore;
	}

	private JButton getBtnFinish() {
		if (btnFinish == null) {
			btnFinish = new JButton(Internationalization.getString("cart_finish"));
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
			scrollPane.setViewportView(getPanelScroll());
		}
		return scrollPane;
	}

	private JPanel getPanelScroll() {
		if (panelScroll == null) {
			panelScroll = new JPanel();
			panelScroll.setLayout(new GridLayout(2, 1, 0, 0));
			panelScroll.add(getPanel());
		}
		return panelScroll;
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
			lblName = new JLabel("name");
			lblName.setHorizontalAlignment(SwingConstants.CENTER);
			lblName.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return lblName;
	}

	private JLabel getLblPhoto() {
		if (lblPhoto == null) {
			lblPhoto = new JLabel("");
			lblPhoto.setBackground(Color.WHITE);
			lblPhoto.setIcon(new ImageIcon(MainWindow.class.getResource("/img/PT001.jpg")));
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
			txtrDescription.setText("description");
		}
		return txtrDescription;
	}
}
