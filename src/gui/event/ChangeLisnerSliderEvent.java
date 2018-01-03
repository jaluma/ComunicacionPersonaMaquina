package gui.event;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ChangeLisnerSliderEvent implements ChangeListener {

	private JLabel label;

	public ChangeLisnerSliderEvent(JLabel label) {
		this.label = label;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider) e.getSource();
		String value = String.valueOf(source.getValue());
		label.setText(value);
	}

}
