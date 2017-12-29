package sorter;

import java.util.Comparator;

import product.Product;

public class AmountSorter implements Comparator<Product> {

	@Override
	public int compare(Product arg0, Product arg1) {
		return Double.compare(arg0.getTotal() - arg0.getDiscount(), arg0.getTotal() - arg1.getDiscount());
	}

}
