package test;

import java.util.Date;

import org.junit.jupiter.api.Test;

import logic.Order;

class testLogic {

	@SuppressWarnings("deprecation")
	@Test
	void test() {
		Order o = new Order(null, "javi", "71456789", "jejejej");
		// para accom
		o.add("AL002", 5, 9, new Date(1998, 02, 17), 4);
		// para package
		o.add("PV001", 9, 5, new Date(1998, 02, 17), -1);
		// para ticket
		o.add("EN001", 5, 9, new Date(), 7);
		System.out.println(o.toString());
	}

}
