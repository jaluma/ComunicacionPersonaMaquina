package test;

import java.util.Date;

import org.junit.jupiter.api.Test;

import logic.Order;
import park.Accommodation;
import park.Package;
import park.Park;
import park.Ticket;

class testLogic {

	@SuppressWarnings("deprecation")
	@Test
	void test() {		
		Order o = new Order("javi", "71456789");
		Park park = new Park("PT001", "Warner", "España", "LEON", "nada que contar");
		
		o.add(park);
		Accommodation accom = new Accommodation("AL002", "HO", 5, "Hotel Piolin", "PT001", 430, 85);
		Accommodation accom2 = new Accommodation("AL003", "AP", 5, "Hotel Cala", "PT001", 430, 85);
		// para accom
		accom.loadData(5, 9, new Date(1998, 02, 17), 5);
		accom2.loadData(5, 9, new Date(1998, 02, 17), 5);
		
		o.add(accom);
		//o.add(accom2);
		Package pk = new Package("PV001", "Hallowen Terrorifico", "PT001", "AL003", 4, 270, 215);
		//para package
		pk.loadData(9, 5, new Date(1998, 02, 17));
		
		o.add(pk);
		Ticket tk = new Ticket("EN001", "PT001", 5, 10.4);
		//para ticket
		tk.loadData(2, 3, new Date(1998, 00, 17), 8);
		
		o.add(tk);
		System.out.println(o.toString());
		
		// falta por probar ofertas. COMPROBAR PRECIO PACKAGE
	}

}
