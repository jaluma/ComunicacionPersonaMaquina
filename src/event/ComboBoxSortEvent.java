package event;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collections;

import javax.swing.JComboBox;

import gui.MainWindow;
import gui.ProductListWindow;
import sorter.AmountSorter;
import sorter.NameSorter;

public class ComboBoxSortEvent implements ItemListener {
	
	private ProductListWindow productWindow;
	private MainWindow mainWindow;
	
	public ComboBoxSortEvent(MainWindow main, ProductListWindow productWindow) {
		this.mainWindow = main;
		this.productWindow = productWindow;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void itemStateChanged(ItemEvent e) {
		JComboBox<String> cb = (JComboBox<String>)e.getSource();
		switch (cb.getSelectedIndex()) {
		case 0:
			Collections.sort(productWindow.getListProduct(), new NameSorter());
		case 1:
			Collections.sort(productWindow.getListProduct(), new AmountSorter());
		case 2:
			Collections.sort(productWindow.getListProduct(), new AmountSorter());
			Collections.reverse(productWindow.getListProduct());
		}
		productWindow.refresh();
	}

}
