package filter;

import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import park.Product;

public class Filter {	
	
	public List<Product> filter(List<Product> list, TypesFilter type, String filt) {
		Stream<Product> listFilter = list.stream();
		
		Method method;
		try {
			method = Class.forName("Product").getMethod(type.toString());
		} catch (NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("ERROR");
		}
		
		if (type.getClassType().equals(String.class)) {
			list = listFilter.filter(p -> method.equals(filt)).collect(Collectors.toList());
		} else if (type.getClassType().equals(int.class)) {
			list = listFilter.filter(p -> method.equals(filt)).collect(Collectors.toList());
		} else if (type.getClassType().equals(double.class)) {
			list = listFilter.filter(p -> method.equals(filt)).collect(Collectors.toList());
		} else if (type.getClassType().equals(boolean.class)) {
			list = listFilter.filter(p -> method.equals(true)).collect(Collectors.toList());
		}
			
		return list;
	}

}
