package shoebuy.derby.cat.browse.main;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shoebuy.common.database.IConnectionHelper;

import shoebuy.derby.cat.browse.data.helper.ShoebuyDerbyCategoryBrowseDataHelper;
import shoebuy.derby.cat.browse.data.helper.response.ShoebuyDerbyCategoryBrowseDataHelperResponse;
import shoebuy.derby.cat.browse.util.DBConnectionUtil;

public class HelperTest {
	
	private static Logger log = LoggerFactory.getLogger(HelperTest.class);

	public static void main(String[] args) {
		List<Object> params = new ArrayList<>();
		params.add("Boots");
		params.add("Boots");
		List<Object> params1 = new ArrayList<>();
		params1.add("Jewelry");
		params1.add("Jewelry");
		long startTimeInsertIntoTemp = System.currentTimeMillis();
		IConnectionHelper connectionHelper = DBConnectionUtil.getConnectionHelper();
		ShoebuyDerbyCategoryBrowseDataHelper shoebuyDerbyCategoryBrowseDataHelper = new ShoebuyDerbyCategoryBrowseDataHelper();
		for (int i = 0; i < 100; i++) {			
			ShoebuyDerbyCategoryBrowseDataHelperResponse res = shoebuyDerbyCategoryBrowseDataHelper.findCategoryAttributeValueForCategoryId(connectionHelper, params);
			System.out.println("No of categories = "+res.getCategories().size());			
			System.out.println("Main each iteration ["+i+"] time taken = "+(System.currentTimeMillis() - startTimeInsertIntoTemp));
		
		}
		
		System.out.println("Main total time taken = "+(System.currentTimeMillis() - startTimeInsertIntoTemp));
		
		
	}

}
