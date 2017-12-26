package gui;

import java.text.Format;
import java.text.NumberFormat;

import javax.swing.text.NumberFormatter;

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
}
