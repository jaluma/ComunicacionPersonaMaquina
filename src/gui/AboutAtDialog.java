package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import gui.guiUtil.ResizableImage;
import gui.guiUtil.internationalization.Internationalization;

public class AboutAtDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public AboutAtDialog() {
		setType(Type.POPUP);
		setLocationRelativeTo(null);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AboutAtDialog.class.getResource("/img/ico.png")));
		setTitle(Internationalization.getString("about") + ": " + Internationalization.getString("company"));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[] { 35, 325, 0 };
			gbl_panel.rowHeights = new int[] { 85, 23, 23, 23, 0 };
			gbl_panel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
			gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
			panel.setLayout(gbl_panel);
			{
				JLabel lblName = new JLabel(Internationalization.getString("name_autor")); //$NON-NLS-1$
				lblName.setHorizontalAlignment(SwingConstants.RIGHT);
				lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
				GridBagConstraints gbc_lblName = new GridBagConstraints();
				gbc_lblName.insets = new Insets(0, 0, 5, 0);
				gbc_lblName.gridx = 1;
				gbc_lblName.gridy = 1;
				panel.add(lblName, gbc_lblName);
			}
			{
				JLabel lblCompany = new JLabel(Internationalization.getString("company")); //$NON-NLS-1$
				lblCompany.setHorizontalAlignment(SwingConstants.RIGHT);
				lblCompany.setFont(new Font("Tahoma", Font.BOLD, 14));
				GridBagConstraints gbc_lblCompany = new GridBagConstraints();
				gbc_lblCompany.insets = new Insets(0, 0, 5, 0);
				gbc_lblCompany.gridx = 1;
				gbc_lblCompany.gridy = 2;
				panel.add(lblCompany, gbc_lblCompany);
			}
			{
				JLabel lblDate = new JLabel(Internationalization.getString("date_copyright")); //$NON-NLS-1$
				lblDate.setHorizontalAlignment(SwingConstants.RIGHT);
				lblDate.setFont(new Font("Tahoma", Font.BOLD, 14));
				GridBagConstraints gbc_lblDate = new GridBagConstraints();
				gbc_lblDate.gridx = 1;
				gbc_lblDate.gridy = 3;
				panel.add(lblDate, gbc_lblDate);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.WEST);
			panel.setLayout(new GridLayout(0, 1, 0, 0));
			{
				JLabel lblLogo = new JLabel(""); //$NON-NLS-1$
				ResizableImage.setResizeImage(contentPanel, lblLogo, "/img/ico.png", 200, 200);
				panel.add(lblLogo);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton closeButton = new JButton(Internationalization.getString("close"));
				closeButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
				getRootPane().setDefaultButton(closeButton);
				closeButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				closeButton.setActionCommand("Cancel");
				buttonPane.add(closeButton);
			}
		}
	}

}
