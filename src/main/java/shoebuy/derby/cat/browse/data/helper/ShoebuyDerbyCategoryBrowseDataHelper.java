package shoebuy.derby.cat.browse.data.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shoebuy.common.database.IConnectionHelper;
import com.shoebuy.common.database.IResultSetHelper;

import shoebuy.derby.cat.browse.constants.ShoebuyDerbyCategoryBrowseConstants;
import shoebuy.derby.cat.browse.data.helper.response.ShoebuyDerbyCategoryBrowseDataHelperResponse;
import shoebuy.derby.cat.browse.util.PropertiesUtil;
import shoebuy.domain.CategoryImpl;
import shoebuy.domain.MutableCategory;

public class ShoebuyDerbyCategoryBrowseDataHelper {
	
	public ShoebuyDerbyCategoryBrowseDataHelperResponse findCategoryAttributeValueForCategoryId (IConnectionHelper connectionHelper, List<Object> params) {
		long startTimeInsertIntoTemp = System.currentTimeMillis();
		IResultSetHelper rsHelper = connectionHelper.executeSelectStatement(PropertiesUtil.getQuery(ShoebuyDerbyCategoryBrowseConstants.FIND_CATEGORYaTTRIBUTE_VALUE_FOR_CATEGORY_ID), params);
		List<Map<String, Object>> resultSetData = rsHelper.getResultSetData();
		System.out.println("Helper DB transaction time taken = "+(System.currentTimeMillis() - startTimeInsertIntoTemp));		
		return populateAndReturnResponse(resultSetData);
	}
	
	private ShoebuyDerbyCategoryBrowseDataHelperResponse populateAndReturnResponse(List<Map<String, Object>> resultSetData) {
		long startTimeInsertIntoTemp = System.currentTimeMillis();
		if (resultSetData == null || resultSetData.isEmpty()) {
			return null;
		}
		ShoebuyDerbyCategoryBrowseDataHelperResponse shoebuyDerbyCategoryBrowseDataHelperResponse = new ShoebuyDerbyCategoryBrowseDataHelperResponse();
		Map<String, MutableCategory> categories = new HashMap<>();		
		for (Map<String, Object> record : resultSetData) {
			MutableCategory category;
			if (categories.containsKey(record.get(ShoebuyDerbyCategoryBrowseConstants.CATEGORY_IDENTIFIER))) {
				category = categories.get(record.get(ShoebuyDerbyCategoryBrowseConstants.CATEGORY_IDENTIFIER));
			} else {
				category = new CategoryImpl();
			}
			
			if (category.getCategoryId() == null || !category.getCategoryId().equals(record.get(ShoebuyDerbyCategoryBrowseConstants.CATEGORY_IDENTIFIER))) {
				category.setCategoryId(record.get(ShoebuyDerbyCategoryBrowseConstants.CATEGORY_IDENTIFIER).toString());
			}
			if (category.getCategoryName() == null || !category.getCategoryName().equals(record.get(ShoebuyDerbyCategoryBrowseConstants.CATEGORY_NAME))) {
				category.setCategoryName(record.get(ShoebuyDerbyCategoryBrowseConstants.CATEGORY_NAME).toString());
			}
			
			category.setAttribute(record.get(ShoebuyDerbyCategoryBrowseConstants.ATTRIBUTE_NAME).toString(), record.get(ShoebuyDerbyCategoryBrowseConstants.ATTRIBUTE_VALUE).toString());
			
			if (!categories.containsKey(record.get(ShoebuyDerbyCategoryBrowseConstants.CATEGORY_IDENTIFIER))) {
				categories.put(record.get(ShoebuyDerbyCategoryBrowseConstants.CATEGORY_IDENTIFIER).toString(), category);
			}
		}
		shoebuyDerbyCategoryBrowseDataHelperResponse.setCategories(categories);
		System.out.println("Helper populate time taken = "+(System.currentTimeMillis() - startTimeInsertIntoTemp));		
		
		return shoebuyDerbyCategoryBrowseDataHelperResponse;
		
	}
	
	

}
