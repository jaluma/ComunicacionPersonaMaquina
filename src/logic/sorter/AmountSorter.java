package logic.sorter;

import java.util.Comparator;

import logic.product.Product;

public class AmountSorter implements Comparator<Product> {

	@Override
	public int compare(Product arg0, Product arg1) {
		return Double.compare(arg0.getTotal() - arg0.getDiscount(), arg1.getTotal() - arg1.getDiscount());
	}

}
