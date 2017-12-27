package event;

import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class NumberTextFieldFormatEvent extends KeyAdapter {

	@Override
	public void keyTyped(KeyEvent arg0) {
		try {// if is number
			Integer.parseInt(String.valueOf(arg0.getKeyChar()));
		} catch (NumberFormatException e) {
			arg0.consume();
			Toolkit.getDefaultToolkit().beep();
		}

	}

}
