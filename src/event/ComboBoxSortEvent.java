package event;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collections;

import javax.swing.JComboBox;

import gui.MainWindow;
import gui.ProductListPanel;
import sorter.AmountSorter;
import sorter.NameSorter;

public class ComboBoxSortEvent implements ItemListener {

	private ProductListPanel productWindow;
	private MainWindow mainWindow;
	private int count=0;

	public ComboBoxSortEvent(MainWindow main, ProductListPanel productWindow) {
		this.mainWindow = main;
		this.productWindow = productWindow;
		resetCount();
	}

	public void resetCount() {
		count=0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (count==0) {
			JComboBox<String> cb = (JComboBox<String>) e.getSource();
			switch (cb.getSelectedIndex()) {
			case 0:
				Collections.sort(productWindow.getListProduct(), new NameSorter());
				break;
			case 1:
				Collections.sort(productWindow.getListProduct(), new AmountSorter());
				Collections.reverse(productWindow.getListProduct());
				break;
			case 2:
				Collections.sort(productWindow.getListProduct(), new AmountSorter());
				break;
			}
			productWindow.refresh();
		}
		count++;
	}

}
