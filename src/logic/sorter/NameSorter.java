package logic.sorter;

import java.util.Comparator;

import logic.product.Product;

public class NameSorter implements Comparator<Product> {

	@Override
	public int compare(Product arg0, Product arg1) {
		return arg0.getName().compareTo(arg1.getName());
	}

}
