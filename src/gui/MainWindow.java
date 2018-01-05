package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Locale;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import gui.guiUtil.internationalization.Internationalization;
import logic.product.ListProduct;

public class MainWindow extends JFrame {

	private static final int DEFAULT_HEIGHT = 650;
	private static final int DEFAULT_WIDTH = 900;
	private static final long serialVersionUID = 1L;
	public static double RESOLUTION_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public static double RESOLUTION_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();

	private JPanel contentPane;
	private InitialPanel initialPanel;
	private ProductListPanel productListPanel;

	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenu mnHelp;
	private JMenu mnFilters;
	private JMenu mnTools;
	private JMenu mnView;
	private JMenuItem mntmNewsearch;
	private JMenuItem mntmExit;
	private JSeparator separatorFile;
	private JMenuItem mntmContents;
	private JMenuItem mntmAbout;
	private JSeparator separatorHekp;
	private JMenuItem mntmLanguage;
	protected JMenuItem mntmPlace;
	protected JMenuItem mntmStars;
	protected JMenuItem mntmPeople;
	protected JMenuItem mntmOnlyphotos;
	protected JMenuItem mntmPrice;
	protected JMenu mnSort;
	private JMenuItem mntmRelevance;
	private JMenuItem mntmPricedown;
	private JMenuItem mntmPriceup;
	protected JMenuItem mntmFullscreen;
	protected JRadioButtonMenuItem rdbtnmntmPanelfilter;
	private JMenuItem mntmRefresh;
	protected JMenuItem mntmCart;
	private JSeparator separator;
	protected Rectangle location;

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
		setTitle("EII TOURS");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/img/ico.png")));
		// Default
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		location = getBounds();
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(900, 650));
		setJMenuBar(getMenuBaR());
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		loadHelp();

		// inicializamos layout inicial
		initialPanel = new InitialPanel(this);
		contentPane.add(initialPanel);

		addWindowStateListener(new WindowStateListener() {
			public void windowStateChanged(WindowEvent arg0) {
				if (isResizable()) {
					mntmFullscreen.setEnabled(true);
					if (getExtendedState() == Frame.MAXIMIZED_BOTH) {
						mntmFullscreen.setSelected(true);
					} else {
						mntmFullscreen.setSelected(false);
					}
				} else {
					mntmFullscreen.setEnabled(false);
				}
			}
		});
	}

	public InitialPanel getInitialPanel() {
		return initialPanel;
	}

	private JMenuBar getMenuBaR() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 14));

			mnFile = new JMenu(Internationalization.getString("file"));
			mnFile.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			mnFile.setMnemonic(Internationalization.getMnemonic("file"));
			menuBar.add(mnFile);

			mntmNewsearch = new JMenuItem(Internationalization.getString("new_search"));
			mntmNewsearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					JFrame frame = new MainWindow();
					frame.setVisible(true);
				}
			});
			mntmNewsearch.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			mntmNewsearch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
			mnFile.add(mntmNewsearch);

			separatorFile = new JSeparator();
			mnFile.add(separatorFile);

			mntmRefresh = new JMenuItem(Internationalization.getString("refresh"));
			mntmRefresh.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					refresh();
				}
			});
			mntmRefresh.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			mnFile.add(mntmRefresh);

			mntmCart = new JMenuItem(Internationalization.getString("cart"));
			mntmCart.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					productListPanel.getBtnCart().doClick();
				}
			});
			mntmCart.setEnabled(false);
			mntmCart.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			mntmCart.setMnemonic(Internationalization.getMnemonic("cart"));
			mnFile.add(mntmCart);

			separator = new JSeparator();
			mnFile.add(separator);

			mntmExit = new JMenuItem(Internationalization.getString("exit"));
			mntmExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);
				}
			});
			mntmExit.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			mnFile.add(mntmExit);

			mnView = new JMenu(Internationalization.getString("view"));
			mnView.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			mnView.setMnemonic(Internationalization.getMnemonic("view"));
			menuBar.add(mnView);

			mntmFullscreen = new JRadioButtonMenuItem(Internationalization.getString("fullscreen")); //$NON-NLS-1$
			mntmFullscreen.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					if (getExtendedState() == Frame.MAXIMIZED_BOTH) {
						mntmFullscreen.setSelected(false);
						MainWindow.this.setBounds(location);
					} else {
						location = getBounds();
						setExtendedState(getExtendedState() | Frame.MAXIMIZED_BOTH);
						mntmFullscreen.setSelected(true);
					}
				}
			});
			mntmFullscreen.setEnabled(false);
			mntmFullscreen.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			mnView.add(mntmFullscreen);

			rdbtnmntmPanelfilter = new JRadioButtonMenuItem(Internationalization.getString("panel_filter"));
			rdbtnmntmPanelfilter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (rdbtnmntmPanelfilter.isSelected()) {
						//productListPanel.getPanelFilter().setVisible(true);
						productListPanel.getScrollPane().setVisible(true);
					} else {
						//productListPanel.getPanelFilter().setVisible(false);
						productListPanel.getScrollPane().setVisible(false);
					}
					revalidate();
					repaint();
				}
			});
			rdbtnmntmPanelfilter.setSelected(true);
			rdbtnmntmPanelfilter.setEnabled(false);
			rdbtnmntmPanelfilter.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			mnView.add(rdbtnmntmPanelfilter);

			mnFilters = new JMenu(Internationalization.getString("filters"));
			mnFilters.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			mnFilters.setMnemonic(Internationalization.getMnemonic("filters"));
			menuBar.add(mnFilters);

			mntmPlace = new JMenuItem(Internationalization.getString("place"));
			mntmPlace.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String value = (String) JOptionPane.showInputDialog(null,
							Internationalization.getString("select_place"),
							Internationalization.getString("select_place_title"), JOptionPane.DEFAULT_OPTION,
							new ImageIcon("/img/DE000.jng"), ListProduct.loadPlaces(),
							productListPanel.getComboBox().getSelectedIndex());
					if (value != null) {
						int index = ListProduct.places.indexOf(value);
						productListPanel.getComboBox().setSelectedIndex(index);
					}
				}
			});
			mntmPlace.setEnabled(false);
			mntmPlace.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			mnFilters.add(mntmPlace);

			mntmStars = new JMenuItem(Internationalization.getString("category"));
			mntmStars.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String[] types = new String[] { Internationalization.getString("type_all_product"),
							Internationalization.getString("type_only_accom"),
							Internationalization.getString("type_only_packages"),
							Internationalization.getString("type_only_ticket") };

					String value = (String) JOptionPane.showInputDialog(null,
							Internationalization.getString("select_category"),
							Internationalization.getString("select_category_title"), JOptionPane.DEFAULT_OPTION,
							new ImageIcon("/img/DE000.jng"), types, 0);
					if (value != null) {
						int index = Arrays.asList(types).indexOf(value);
						int count = 0;
						for (Enumeration<AbstractButton> buttons = productListPanel.buttonGroup.getElements(); buttons
								.hasMoreElements();) {
							AbstractButton button = buttons.nextElement();
							if (count == index) {
								button.doClick();
								break;
							}
							count++;
						}
					}

				}
			});
			mntmStars.setEnabled(false);
			mntmStars.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			mnFilters.add(mntmStars);

			mntmPeople = new JMenuItem(Internationalization.getString("number_people"));
			mntmPeople.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JTextField txNumberAdult = new JTextField();
					JTextField txNumberChild = new JTextField();

					Object[] fields = new Object[] { Internationalization.getString("number_adult"), txNumberAdult,
							Internationalization.getString("number_child"), txNumberChild };

					JOptionPane.showConfirmDialog(MainWindow.this, fields,
							Internationalization.getString("number_people_dialog"), JOptionPane.OK_CANCEL_OPTION);

					try {
						int numberAdult = Integer.parseInt(txNumberAdult.getText());
						int numberChild = Integer.parseInt(txNumberChild.getText());

						if (numberAdult >= productListPanel.getSliderPerson().getMaximum())
							productListPanel.getSliderPerson().setMaximum(productListPanel.setMaxSlider(numberAdult));
						if (numberChild >= productListPanel.getSliderChild().getMaximum())
							productListPanel.getSliderChild().setMaximum(productListPanel.setMaxSlider(numberChild));

						productListPanel.getSliderPerson().setValue(numberAdult);
						productListPanel.getSliderChild().setValue(numberChild);
					} catch (NumberFormatException ex) {
					}
				}
			});
			mntmPeople.setEnabled(false);
			mntmPeople.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			mnFilters.add(mntmPeople);

			mntmPrice = new JMenuItem(Internationalization.getString("price"));
			mntmPrice.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JTextField txNumberAdult = new JTextField();
					JTextField txNumberChild = new JTextField();

					Object[] fields = new Object[] { Internationalization.getString("price_min_dialog"), txNumberAdult,
							Internationalization.getString("price_max_dialog"), txNumberChild };

					JOptionPane.showConfirmDialog(MainWindow.this, fields,
							Internationalization.getString("number_people_dialog"), JOptionPane.OK_CANCEL_OPTION);

					try {
						double priceMin = Double.parseDouble(txNumberAdult.getText());
						double priceMax = Double.parseDouble(txNumberChild.getText());

						productListPanel.getTxtMinprice().setText(String.valueOf(priceMin));
						productListPanel.getTxtMaxprice().setText(String.valueOf(priceMax));

						productListPanel.getBtnGopricefilter().doClick();
					} catch (NumberFormatException ex) {
					}
				}
			});
			mntmPrice.setEnabled(false);
			mntmPrice.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			mnFilters.add(mntmPrice);

			mntmOnlyphotos = new JRadioButtonMenuItem(Internationalization.getString("only_photos"));
			mntmOnlyphotos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					productListPanel.getChOnlyPhotos().doClick();
				}
			});
			mntmOnlyphotos.setEnabled(false);
			mntmOnlyphotos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			mnFilters.add(mntmOnlyphotos);

			mnTools = new JMenu(Internationalization.getString("tools"));
			mnTools.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			mnTools.setMnemonic(Internationalization.getMnemonic("tools"));
			menuBar.add(mnTools);

			mntmLanguage = new JMenuItem(Internationalization.getString("language"));
			mntmLanguage.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					String[] localeString = new String[Internationalization.LOCATION_SUPPORTED.length];

					for (int i = 0; i < localeString.length; i++) {
						localeString[i] = Internationalization.LOCATION_SUPPORTED[i].getDisplayName();
					}

					int in = Internationalization.getIndexLanguageSelected();

					String value = (String) JOptionPane.showInputDialog(null,
							"<html>" + Internationalization.getString("select_language"),
							Internationalization.getString("select_language_title"), JOptionPane.OK_CANCEL_OPTION,
							new ImageIcon("/img/lang.png"), localeString, localeString[in]);
					if (value != null) {
						for (int i = 0; i < localeString.length; i++) {
							if (localeString[i].equals(value)) {
								Locale locale = Internationalization.LOCATION_SUPPORTED[i];
								Internationalization.changeLocation(locale.getLanguage(), locale.getCountry());
								MainWindow.this.dispose();
								main(null);
								break;
							}
						}
					}
				}
			});
			mntmLanguage.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			mnTools.add(mntmLanguage);

			mnSort = new JMenu(Internationalization.getString("sort"));
			mnSort.setEnabled(false);
			mnSort.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			mnTools.add(mnSort);

			mntmRelevance = new JMenuItem(Internationalization.getString("box_combo_relevance"));
			mntmRelevance.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					productListPanel.getComboBoxSort().setSelectedIndex(0);
				}
			});
			mntmRelevance.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			mnSort.add(mntmRelevance);

			mntmPricedown = new JMenuItem(Internationalization.getString("box_combo_price_down"));
			mntmPricedown.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					productListPanel.getComboBoxSort().setSelectedIndex(1);
				}
			});
			mntmPricedown.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			mnSort.add(mntmPricedown);

			mntmPriceup = new JMenuItem(Internationalization.getString("box_combo_price_up"));
			mntmPriceup.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					productListPanel.getComboBoxSort().setSelectedIndex(2);
				}
			});
			mntmPriceup.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			mnSort.add(mntmPriceup);

			mnHelp = new JMenu(Internationalization.getString("help"));
			mnHelp.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			mnHelp.setMnemonic(Internationalization.getMnemonic("help"));
			menuBar.add(mnHelp);

			mntmContents = new JMenuItem(Internationalization.getString("contents"));
			mntmContents.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			mnHelp.add(mntmContents);

			separatorHekp = new JSeparator();
			mnHelp.add(separatorHekp);

			mntmAbout = new JMenuItem(Internationalization.getString("about"));
			mntmAbout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JDialog about = new AboutAtDialog();
					about.setVisible(true);
				}
			});
			mntmAbout.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			mnHelp.add(mntmAbout);
		}
		return menuBar;
	}

	private void refresh() {
		revalidate();
		repaint();
	}

	private void loadHelp() {
		URL hsURL;
		HelpSet hs;

		try {
			File fichero = new File("help/help.hs");
			hsURL = fichero.toURI().toURL();
			hs = new HelpSet(null, hsURL);
		} catch (Exception e) {
			return;
		}

		HelpBroker hb = hs.createHelpBroker();
		hb.enableHelpKey(getRootPane(), "introduction", hs);
		hb.enableHelpOnButton(mntmContents, "introduction", hs);
	}

	public void setProductListPanel(ProductListPanel productListPanel2) {
		this.productListPanel = productListPanel2;

	}

	public ProductListPanel getProductListPanel() {
		return productListPanel;
	}
}
