package event;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import internationalization.Internationalization;

public class FocusTextAreaEvent implements FocusListener {
	
	private String code;
	public FocusTextAreaEvent(String code) {
		this.code = code;
	}

	@Override
	public void focusLost(FocusEvent e) {
		JTextArea textField = (JTextArea)e.getComponent();
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
		JTextArea textField = (JTextArea)e.getComponent();
		textField.setForeground(Color.BLACK);
		textField.selectAll();
		if (Internationalization.getString(code).toLowerCase().equals(textField.getText().toLowerCase()))
			textField.setText("");
	}

}
