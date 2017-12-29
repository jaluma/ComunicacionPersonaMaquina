package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import guiUtil.DateUtil;
import guiUtil.ResizableImage;
import internationalization.Internationalization;
import product.Accommodation;
import product.Package;
import product.Product;
import product.Ticket;
import product.TypeHotel;

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

	private MainWindow mainWindow;
	private Product product;
	private CartDialog cartWindow;
	private JEditorPane editorPane;
	private JPanel panelEdit;
	private JPanel panelNumber;
	private JDateChooser dateChooser;
	private JDateChooser dateChooser_1;
	private JPanel panelDate;
	private JTextField textField;
	private JTextField textField_1;
	private JCheckBox chckbxBreakfast;
	private JPanel panelCentrar;
	private JLabel lblNumber;
	private JLabel lblDate;
	private JToggleButton tglbtnEdit;
	private JPanel panel_1;

	public CartItemPanel(MainWindow mainWindow, CartDialog cartWindow, Product product) {
		this.mainWindow = mainWindow;
		this.cartWindow = cartWindow;
		this.product = product;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(UIManager.getBorder("Spinner.border"));
		setMaximumSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, 230));
		setPreferredSize(new Dimension(905, 230));
		add(getPanel());
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(UIManager.getBorder("Spinner.border"));
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getPanel_1(), BorderLayout.WEST);
			panel.add(getPanelCenterOrder(), BorderLayout.CENTER);
		}
		return panel;
	}

	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel(Internationalization.getProduct(product.getCode()));
			lblName.setBackground(Color.WHITE);
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
				path = "/img/" + ((Package) product).getAccom().getCode() + ".jpg";
			else
				path = "/img/" + product.getCode() + ".jpg";

			ResizableImage.setResizeImage(this, lblPhoto, path, 250, 150);
		}
		return lblPhoto;
	}

	private JPanel getPanelCenterOrder() {
		if (panelCenterOrder == null) {
			panelCenterOrder = new JPanel();
			panelCenterOrder.setBackground(Color.WHITE);
			panelCenterOrder.setLayout(new BorderLayout(0, 0));
			panelCenterOrder.add(getPanelToolsOrder(), BorderLayout.SOUTH);
			panelCenterOrder.add(getPanelCentrar(), BorderLayout.CENTER);
		}
		return panelCenterOrder;
	}

	private JPanel getPanelToolsOrder() {
		if (panelToolsOrder == null) {
			panelToolsOrder = new JPanel();
			panelToolsOrder.setBackground(Color.WHITE);
			FlowLayout fl_panelToolsOrder = (FlowLayout) panelToolsOrder.getLayout();
			fl_panelToolsOrder.setAlignment(FlowLayout.RIGHT);
			panelToolsOrder.add(getTglbtnEdit());
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
					cartWindow.getLblSubTotal()
							.setText(Internationalization.getCurrency(mainWindow.getOrder().getTotal()));

					CartItemPanel.this.setVisible(false);
					repaint();

					mainWindow.getCartWindow().getBtnFinish().setEnabled(true);
				}
			});
		}
		return btnRemove;
	}

	private JEditorPane getEditorPane() {
		if (editorPane == null) {
			editorPane = new JEditorPane();
			editorPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			editorPane.setContentType("text/html");
			editorPane.setFont(new Font("Tahoma", Font.PLAIN, 13));
			editorPane.setEditable(false);
			editorPane.setHighlighter(null);
			editorPane.setText("<font face=\"Tahoma\">" + product.toString3() + "</font>");
		}
		return editorPane;
	}

	private JPanel getPanelEdit() {
		if (panelEdit == null) {
			panelEdit = new JPanel();
			panelEdit.setBorder(null);
			panelEdit.setBackground(Color.WHITE);
			panelEdit.setVisible(getTglbtnEdit().isSelected());
			GridBagLayout gbl_panelEdit = new GridBagLayout();
			gbl_panelEdit.columnWidths = new int[] { 250, 0 };
			gbl_panelEdit.rowHeights = new int[] { 90, 90, 0, 0 };
			gbl_panelEdit.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
			gbl_panelEdit.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
			panelEdit.setLayout(gbl_panelEdit);
			GridBagConstraints gbc_panelNumber = new GridBagConstraints();
			gbc_panelNumber.insets = new Insets(0, 0, 5, 5);
			gbc_panelNumber.gridx = 0;
			gbc_panelNumber.gridy = 0;
			panelEdit.add(getPanel_2_1(), gbc_panelNumber);
			GridBagConstraints gbc_panelDate = new GridBagConstraints();
			gbc_panelDate.insets = new Insets(0, 0, 5, 5);
			gbc_panelDate.gridx = 0;
			gbc_panelDate.gridy = 1;
			panelEdit.add(getPanelDate(), gbc_panelDate);
			GridBagConstraints gbc_chckbxBreakfast = new GridBagConstraints();
			gbc_chckbxBreakfast.insets = new Insets(0, 0, 0, 5);
			gbc_chckbxBreakfast.gridx = 0;
			gbc_chckbxBreakfast.gridy = 2;
			if (product instanceof Accommodation && ((Accommodation) product).getType() == TypeHotel.HO.toString())
				panelEdit.add(getChckbxBreakfast(), gbc_chckbxBreakfast);
		}
		return panelEdit;
	}

	private JPanel getPanel_2_1() {
		if (panelNumber == null) {
			panelNumber = new JPanel();
			panelNumber.setBorder(UIManager.getBorder("Spinner.border"));
			panelNumber.setBackground(Color.WHITE);
			panelNumber.setLayout(new GridLayout(0, 1, 0, 2));
			panelNumber.add(getLblNumber());
			panelNumber.add(getDateChooser());
			panelNumber.add(getDateChooser_1());
		}
		return panelNumber;
	}

	private JDateChooser getDateChooser() {
		if (dateChooser == null) {
			dateChooser = new JDateChooser();
			dateChooser.setFont(new Font("Tahoma", Font.BOLD, 13));
			dateChooser.setDate(product.getDate());
			dateChooser.setMinSelectableDate(new Date());
			dateChooser.setDateFormatString(
					((SimpleDateFormat) DateFormat.getDateInstance(DateFormat.SHORT, Internationalization.getLocate()))
							.toLocalizedPattern());
		}
		return dateChooser;
	}

	private JDateChooser getDateChooser_1() {
		if (dateChooser_1 == null) {
			dateChooser_1 = new JDateChooser();
			dateChooser_1.setFont(new Font("Tahoma", Font.BOLD, 13));
			dateChooser_1.setDate(DateUtil.sumDate(dateChooser.getDate(), product.getDuration()));
			dateChooser_1.setMinSelectableDate(new Date(dateChooser.getDate().getTime() + 86400000));
			dateChooser_1.setDateFormatString(
					((SimpleDateFormat) DateFormat.getDateInstance(DateFormat.SHORT, Internationalization.getLocate()))
							.toLocalizedPattern());
		}
		return dateChooser_1;
	}

	private JPanel getPanelDate() {
		if (panelDate == null) {
			panelDate = new JPanel();
			panelDate.setBorder(UIManager.getBorder("Spinner.border"));
			panelDate.setBackground(Color.WHITE);
			panelDate.setLayout(new GridLayout(0, 1, 0, 2));
			panelDate.add(getLblDate());
			panelDate.add(getTextField());
			panelDate.add(getTextField_1());
		}
		return panelDate;
	}

	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setText(String.valueOf(product.getNumberAdult()));
			textField.setHorizontalAlignment(SwingConstants.CENTER);
			textField.setForeground(Color.DARK_GRAY);
			textField.setFont(new Font("Tahoma", Font.BOLD, 13));
			textField.setColumns(10);
		}
		return textField;
	}

	private JTextField getTextField_1() {
		if (textField_1 == null) {
			textField_1 = new JTextField();
			textField_1.setText(String.valueOf(product.getNumberChild()));
			textField_1.setHorizontalAlignment(SwingConstants.CENTER);
			textField_1.setForeground(Color.DARK_GRAY);
			textField_1.setFont(new Font("Tahoma", Font.BOLD, 13));
			textField_1.setColumns(10);
		}
		return textField_1;
	}

	private JCheckBox getChckbxBreakfast() {
		if (chckbxBreakfast == null) {
			chckbxBreakfast = new JCheckBox(Internationalization.getString("breakfast")); //$NON-NLS-1$
			chckbxBreakfast.setHorizontalAlignment(SwingConstants.RIGHT);
			chckbxBreakfast.setSelected(((Accommodation) product).isBreakfast());
			chckbxBreakfast.setBackground(Color.WHITE);
		}
		return chckbxBreakfast;
	}

	private JPanel getPanelCentrar() {
		if (panelCentrar == null) {
			panelCentrar = new JPanel();
			panelCentrar.setBackground(Color.WHITE);
			panelCentrar.setLayout(new BorderLayout(0, 0));
			panelCentrar.add(getLblName(), BorderLayout.NORTH);
			panelCentrar.add(getEditorPane());
		}
		return panelCentrar;
	}

	private JLabel getLblNumber() {
		if (lblNumber == null) {
			lblNumber = new JLabel(Internationalization.getString("number_person"));
			lblNumber.setHorizontalAlignment(SwingConstants.CENTER);
			lblNumber.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblNumber.setAlignmentY(0.0f);
			lblNumber.setAlignmentX(0.5f);
		}
		return lblNumber;
	}

	private JLabel getLblDate() {
		if (lblDate == null) {
			lblDate = new JLabel(Internationalization.getString("date"));
			lblDate.setHorizontalAlignment(SwingConstants.CENTER);
			lblDate.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblDate.setBackground(Color.WHITE);
			lblDate.setAlignmentY(0.0f);
			lblDate.setAlignmentX(0.5f);
		}
		return lblDate;
	}

	private JToggleButton getTglbtnEdit() {
		if (tglbtnEdit == null) {
			tglbtnEdit = new JToggleButton(Internationalization.getString("cart_edit")); //$NON-NLS-1$
			tglbtnEdit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					getLblPhoto().setVisible(!tglbtnEdit.isSelected());
					getPanelEdit().setVisible(tglbtnEdit.isSelected());
				}
			});
			tglbtnEdit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return tglbtnEdit;
	}

	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBorder(UIManager.getBorder("Spinner.border"));
			panel_1.setBackground(Color.WHITE);
			panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
			panel_1.add(getLblPhoto());
			panel_1.add(getPanelEdit());
		}
		return panel_1;
	}
}
