package event;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JSlider;
import javax.swing.JTextField;

public class SliderTextFieldEvent implements FocusListener{
	
	private JTextField textField;
	private JSlider slider;
	
	public SliderTextFieldEvent(JTextField textField, JSlider slider) {
		this.textField = textField;
		this.slider = slider;
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		txFilterNumber();
		
	}
	
	protected void txFilterNumber() {
		try {
			int numberAdult = Integer.parseInt(textField.getText());
			slider.setValue(numberAdult);
		} catch (NumberFormatException ex) {	}
	}

}
