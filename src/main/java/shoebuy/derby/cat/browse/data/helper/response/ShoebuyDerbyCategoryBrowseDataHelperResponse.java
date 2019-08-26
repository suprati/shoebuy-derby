package shoebuy.derby.cat.browse.data.helper.response;

import java.io.Serializable;
import java.util.Map;

import shoebuy.domain.MutableCategory;

public class ShoebuyDerbyCategoryBrowseDataHelperResponse implements Serializable {
	
	private static final long serialVersionUID = 1727107034734677820L;
	
	private Map<String, MutableCategory> categories;

	public Map<String, MutableCategory> getCategories() {
		return categories;
	}

	public void setCategories(Map<String, MutableCategory> categories) {
		this.categories = categories;
	}

}
