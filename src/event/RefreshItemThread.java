package event;

import gui.ProductListPanel;

public class RefreshItemThread implements Runnable {
	
	private ProductListPanel panel;
	
	public RefreshItemThread(ProductListPanel panel) {
		this.panel = panel;
	}

	@Override
	public void run() {
		panel.loadItems();
		panel.filterPlaceChange();
		panel.getPanelLoading().setVisible(false);
		panel.getRbAll().doClick();
	}

}
