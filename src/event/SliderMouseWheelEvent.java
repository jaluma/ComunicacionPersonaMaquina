package event;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JSlider;

public class SliderMouseWheelEvent implements MouseWheelListener {

	@Override
	public void mouseWheelMoved(MouseWheelEvent evt) {
		JSlider slider = (JSlider) evt.getSource();
		if (evt.getWheelRotation() < 0) {
			int iNewValue = slider.getValue() - slider.getMinorTickSpacing();
			if (iNewValue >= slider.getMinimum()) {
				slider.setValue(iNewValue);
			} else {
				slider.setValue(0);
			}
		} else {
			int iNewValue = slider.getValue() + slider.getMinorTickSpacing();
			if (iNewValue <= slider.getMaximum()) {
				slider.setValue(iNewValue);
			} else {
				slider.setValue(slider.getMaximum());
			}
		}
	}

}
