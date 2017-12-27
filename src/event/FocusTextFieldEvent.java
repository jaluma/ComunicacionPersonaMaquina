package event;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

import internationalization.Internationalization;

public class FocusTextFieldEvent implements FocusListener {
	
	private String code;
	public FocusTextFieldEvent(String code) {
		this.code = code;
	}

	@Override
	public void focusLost(FocusEvent e) {
		JTextField textField = (JTextField)e.getComponent();
		if (textField.getText().isEmpty()) {
			textField.setForeground(Color.DARK_GRAY);
			String str = Internationalization.getString(code).toLowerCase();
			if (str.charAt(0) == '!')
				textField.setText(code);
			else
				textField.setText(str);
		}
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		JTextField textField = (JTextField)e.getComponent();
		textField.setForeground(Color.BLACK);
		textField.selectAll();
		if (Internationalization.getString(code).toLowerCase().equals(textField.getText().toLowerCase()))
			textField.setText("");
	}

}
