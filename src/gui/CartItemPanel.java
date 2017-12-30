package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import event.FocusTextFieldEvent;
import event.NumberTextFieldFormatEvent;
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
	private JDateChooser dateArrive;
	private JDateChooser dateExit;
	private JPanel panelDate;
	private JTextField txAdult;
	private JTextField txChild;
	private JCheckBox chckbxBreakfast;
	private JPanel panelCentrar;
	private JToggleButton tglbtnEdit;
	private JPanel panel_1;
	private JLabel label;
	private JLabel label_1;
	private JLabel lblPrice;
	private JPanel panelPrice;
	private JPanel panelTools;

	public CartItemPanel(CartDialog cartWindow, Product product) {
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
			panelToolsOrder.setLayout(new BorderLayout(2, 2));
			panelToolsOrder.add(getPanelPrice(), BorderLayout.WEST);
			panelToolsOrder.add(getPanelTools(), BorderLayout.EAST);
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
					ProductListPanel.getOrder().remove(product.getCode());
					mainWindow.getProductListPanel().setNumberItemsCart(ProductListPanel.getOrder().getItems());
					cartWindow.getLblSubTotal()
							.setText(Internationalization.getCurrency(ProductListPanel.getOrder().getTotal()));

					CartItemPanel.this.setVisible(false);
					repaint();

					cartWindow.getBtnFinish().setEnabled(true);
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
			gbc_panelNumber.fill = GridBagConstraints.HORIZONTAL;
			gbc_panelNumber.insets = new Insets(0, 0, 5, 5);
			gbc_panelNumber.gridx = 0;
			gbc_panelNumber.gridy = 0;
			panelEdit.add(getPanel_2_1(), gbc_panelNumber);
			GridBagConstraints gbc_panelDate = new GridBagConstraints();
			gbc_panelDate.fill = GridBagConstraints.HORIZONTAL;
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
			panelNumber.add(getLabel());
			panelNumber.add(getDateArrive());
			panelNumber.add(getDateExit());
		}
		return panelNumber;
	}

	private JDateChooser getDateArrive() {
		if (dateArrive == null) {
			dateArrive = new JDateChooser();
			dateArrive.setFont(new Font("Tahoma", Font.BOLD, 13));
			JTextFieldDateEditor dateEditor = (JTextFieldDateEditor) dateArrive.getDateEditor();
			dateEditor.setHorizontalAlignment(JTextField.CENTER);
			dateArrive.setToolTipText(Internationalization.getToolTips("start"));
			dateArrive.setDate(product.getDate());
			dateArrive.setMinSelectableDate(new Date());
			dateArrive.setDateFormatString(
					((SimpleDateFormat) DateFormat.getDateInstance(DateFormat.SHORT, Internationalization.getLocate()))
							.toLocalizedPattern());
			dateArrive.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent arg0) {
					if (dateArrive.getDate() != null ) {
						if (dateArrive.getDate().getTime() >= getDateExit().getDate().getTime()) {
							Date date2 = new Date(dateArrive.getDate().getTime() + 86400000);
							getDateExit().setDate(date2);
							getDateExit().setMinSelectableDate(date2);
						}
						product.setDate(dateArrive.getDate());
						cartWindow.updatePrice();
					} else
						dateArrive.setDate(product.getDate());
					
					cartWindow.updatePrice();
					updatePrice();
				} 
			});
		}
		return dateArrive;
	}

	private JDateChooser getDateExit() {
		if (dateExit == null) {
			dateExit = new JDateChooser();
			dateExit.setFont(new Font("Tahoma", Font.BOLD, 13));
			JTextFieldDateEditor dateEditor = (JTextFieldDateEditor) dateExit.getDateEditor();
			dateEditor.setHorizontalAlignment(JTextField.CENTER);
			dateExit.setToolTipText(Internationalization.getToolTips("finish"));
			dateExit.setDate(DateUtil.sumDate(dateArrive.getDate(), product.getDuration()));
			dateExit.setMinSelectableDate(new Date(dateArrive.getDate().getTime() + 86400000));
			dateExit.setDateFormatString(
					((SimpleDateFormat) DateFormat.getDateInstance(DateFormat.SHORT, Internationalization.getLocate()))
							.toLocalizedPattern());
			dateExit.addPropertyChangeListener(new PropertyChangeListener() {

				@Override
				public void propertyChange(PropertyChangeEvent arg0) {
					if (dateExit.getDate() != null) {
						product.setDate(dateExit.getDate());
					} else
						dateExit.setDate(DateUtil.sumDate(dateArrive.getDate(), product.getDuration()));
					
					cartWindow.updatePrice();
					updatePrice();
				}
				
			});
		}
		return dateExit;
	}

	private JPanel getPanelDate() {
		if (panelDate == null) {
			panelDate = new JPanel();
			panelDate.setBorder(UIManager.getBorder("Spinner.border"));
			panelDate.setBackground(Color.WHITE);
			panelDate.setLayout(new GridLayout(0, 1, 0, 2));
			panelDate.add(getLabel_1());
			panelDate.add(getTxChild());
			panelDate.add(getTxAdult());
		}
		return panelDate;
	}

	private JTextField getTxAdult() {
		if (txAdult == null) {
			txAdult = new JTextField();
			txAdult.setText(String.valueOf(product.getNumberAdult()));
			txAdult.setToolTipText(Internationalization.getToolTips("number_adult"));
			txAdult.setHorizontalAlignment(SwingConstants.CENTER);
			txAdult.setForeground(Color.DARK_GRAY);
			txAdult.setFont(new Font("Tahoma", Font.BOLD, 13));
			txAdult.setColumns(10);
			txAdult.addKeyListener(new NumberTextFieldFormatEvent());
			txAdult.addFocusListener(new FocusTextFieldEvent(String.valueOf(txAdult.getText())));
			txAdult.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent arg0) {
					int number = product.getNumberAdult() + product.getNumberChild();
					if (product instanceof Accommodation && ((Accommodation)product).getNum() < number || product instanceof Package && ((Package)product).getAccom().getNum() < number) {
						JOptionPane.showMessageDialog(mainWindow, Internationalization.getString("error_number_size"),
								Internationalization.getString("error_number_size_title"),
								JOptionPane.WARNING_MESSAGE);
						return;
					} else {
						product.setNumberChild(Integer.parseInt(txAdult.getText()));
						cartWindow.updatePrice();
						updatePrice();
					}
				}
			});
		}
		return txAdult;
	}

	private JTextField getTxChild() {
		if (txChild == null) {
			txChild = new JTextField();
			txChild.setText(String.valueOf(product.getNumberChild()));
			txChild.setToolTipText(Internationalization.getToolTips("number_child"));
			txChild.setHorizontalAlignment(SwingConstants.CENTER);
			txChild.setForeground(Color.DARK_GRAY);
			txChild.setFont(new Font("Tahoma", Font.BOLD, 13));
			txChild.setColumns(10);
			txChild.addKeyListener(new NumberTextFieldFormatEvent());
			txChild.addFocusListener(new FocusTextFieldEvent(String.valueOf(txChild.getText())));
			txChild.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent arg0) {
					int number = product.getNumberAdult() + product.getNumberChild();
					if (product instanceof Accommodation && ((Accommodation)product).getNum() < number || product instanceof Package && ((Package)product).getAccom().getNum() < number) {
						JOptionPane.showMessageDialog(mainWindow, Internationalization.getString("error_number_size"),
								Internationalization.getString("error_number_size_title"),
								JOptionPane.WARNING_MESSAGE);
						return;
					} else {
						product.setNumberChild(Integer.parseInt(txChild.getText()));
						cartWindow.updatePrice();
						updatePrice();
					}
				}
			});
		}
		return txChild;
	}

	private JCheckBox getChckbxBreakfast() {
		if (chckbxBreakfast == null) {
			chckbxBreakfast = new JCheckBox(Internationalization.getString("breakfast")); //$NON-NLS-1$
			chckbxBreakfast.setHorizontalAlignment(SwingConstants.RIGHT);
			chckbxBreakfast.setSelected(((Accommodation) product).isBreakfast());
			chckbxBreakfast.setBackground(Color.WHITE);
			chckbxBreakfast.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					chckbxBreakfast.setSelected(((Accommodation) product).isBreakfast());
					cartWindow.getLblSubTotal().setText(Internationalization.getCurrency(ProductListPanel.getOrder().getTotal()));
				}
			});
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
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("Fecha de la reserva");
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setFont(new Font("Tahoma", Font.BOLD, 16));
			label.setBackground(Color.WHITE);
			label.setAlignmentY(0.0f);
			label.setAlignmentX(0.5f);
		}
		return label;
	}
	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("N\u00FAmero de personas");
			label_1.setHorizontalAlignment(SwingConstants.CENTER);
			label_1.setFont(new Font("Tahoma", Font.BOLD, 16));
			label_1.setAlignmentY(0.0f);
			label_1.setAlignmentX(0.5f);
		}
		return label_1;
	}
	private JLabel getLblPrice() {
		if (lblPrice == null) {
			lblPrice = new JLabel(); //$NON-NLS-1$
			updatePrice();
			lblPrice.setForeground(Color.DARK_GRAY);
			lblPrice.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
			lblPrice.setBackground(Color.WHITE);
			lblPrice.setBorder(new EmptyBorder(0, 0, 0, 10));
			lblPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lblPrice;
	}
	public void updatePrice() {
		lblPrice.setText(Internationalization.getCurrency(product.getTotal() - product.getDiscount())); // $NON-NLS-1$
	}
	private JPanel getPanelPrice() {
		if (panelPrice == null) {
			panelPrice = new JPanel();
			panelPrice.setBackground(Color.WHITE);
			panelPrice.add(getLblPrice());
		}
		return panelPrice;
	}
	private JPanel getPanelTools() {
		if (panelTools == null) {
			panelTools = new JPanel();
			panelTools.setBackground(Color.WHITE);
			panelTools.setLayout(new BoxLayout(panelTools, BoxLayout.X_AXIS));
			panelTools.add(getTglbtnEdit());
			panelTools.add(getBtnRemove());
		}
		return panelTools;
	}
}
