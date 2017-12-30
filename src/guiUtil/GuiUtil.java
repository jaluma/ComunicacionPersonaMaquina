package guiUtil;

import java.awt.Component;
import java.awt.Container;
import java.text.NumberFormat;

import javax.swing.text.NumberFormatter;

import gui.ItemPanel;

public class GuiUtil {

	public static NumberFormatter numberFormat(int maximum) {
		NumberFormat format = NumberFormat.getInstance();
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(0);

		if (maximum <= 0)
			formatter.setMaximum(Integer.MAX_VALUE);
		else
			formatter.setMaximum(maximum);

		formatter.setAllowsInvalid(false);
		return formatter;
	}

	public static int getVisibleChildrenCount(Component c) {
		if (c == null || !(c instanceof Container))
			return 0;

		int count = 0;
		Container container = (Container) c;

		for (int i = 0; i < container.getComponentCount(); i++)
			if (container.getComponent(i).isVisible() && container.getComponent(i) instanceof ItemPanel)
				count++;

		return count;
	}

}
