package shoebuy.derby.cat.browse.constants;

/**
 * This final class is for declaring STS Service Common Constants
 * 
 * @author smaiti
 *
 */
public class ShoebuyDerbyCategoryBrowseConstants {

	// Prevents even the native class from calling this
	private ShoebuyDerbyCategoryBrowseConstants() {		
			throw new AssertionError();
	}
	
	public static final String SELECT_BRAND_COUNT = "select.brand.count";
	public static final String DERBY_CLIENT_DRIVER_NAME = "org.apache.derby.jdbc.ClientDriver";
	public static final String FIND_CATEGORYaTTRIBUTE_VALUE_FOR_CATEGORY_ID = "findCategoryAttributeValueForCategoryId";
	
	public static final String CATEGORY_IDENTIFIER = "CATEGORY_IDENTIFIER";
	public static final String CATEGORY_NAME = "CATEGORY_NAME";
	public static final String ATTRIBUTE_NAME = "ATTRIBUTE_NAME";
	public static final String ATTRIBUTE_VALUE = "ATTRIBUTE_VALUE";

}
