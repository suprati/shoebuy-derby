package shoebuy.derby.cat.browse.data.manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import shoebuy.derby.cat.browse.data.manager.response.BrandDataResponse;

public class ShoebuyDerbyCategoryBrowseDataManager {
	
	public BrandDataResponse findBrandData(Connection conn, String query, List<Object> params) throws Exception {
		BrandDataResponse brandDataResponse = new BrandDataResponse();
		ResultSet rs = conn.prepareStatement(query).executeQuery();		
		while (rs.next()) {
			brandDataResponse.setBrandCount(rs.getInt(1));
		}
		DbUtils.closeQuietly(rs);
		
		return brandDataResponse;
	}

}
