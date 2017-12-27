package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import logic.Order;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	public static double RESOLUTION_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public static double RESOLUTION_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();

	private JPanel contentPane;
	private InitialWindow initialPanel;
	private ProductListWindow productListPanel;
	private CartWindow cartWindow;

	private Order order;
	private int numberChild;
	private int numberAdult;
	private Date date;
	private int days;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		order = new Order();
		setTitle("EII TOURS");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/img/ico.png")));
		// Default
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 650);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(900, 650));
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		// inicializamos layout inicial
		initialPanel = new InitialWindow(this);
		contentPane.add(initialPanel);
		// new ProductListWindow(this, contentPane);

		// JDialog dialog = new LogUpWindow(this);
		// dialog.setVisible(true);

		// contentPane.removeAll();
		// new InfoOrderWindow(this, contentPane);
		// contentPane.repaint();
		// contentPane.revalidate();

		// contentPane.add(new FinishWindow());
	}

	public Order getOrder() {
		return order;
	}

	public void setNumberChild(int numberChild) {
		this.numberChild = numberChild;
	}

	public void setNumberAdult(int numberAdult) {
		this.numberAdult = numberAdult;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public int getNumberChild() {
		return numberChild;
	}

	public int getNumberAdult() {
		return numberAdult;
	}

	public Date getDate() {
		if (date == null)
			return new Date();
		return date;
	}

	public int getDays() {
		return days;
	}

	public InitialWindow getInitialPanel() {
		return initialPanel;
	}

	public ProductListWindow getProductListPanel() {
		return productListPanel;
	}

	public void setProductListPanel(ProductListWindow productListPanel) {
		this.productListPanel = productListPanel;
	}

	public CartWindow getCartWindow() {
		return cartWindow;
	}

	public void setCartWindow(CartWindow cartWindow) {
		this.cartWindow = cartWindow;
	}

	public void setOrder(Order order2) {
		this.order = order2;
	}

}
