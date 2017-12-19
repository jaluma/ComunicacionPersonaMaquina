package serialice;

import java.util.List;

public interface Serialice<T> {
	
	public List<String> serialize(List<T> list);

}
