package shoebuy.derby.cat.browse.main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.DbUtils;

import com.shoebuy.common.database.DBConnectionFactory;
import com.shoebuy.common.database.IConnectionHelper;
import com.shoebuy.common.database.IDBConnectionManager;
import com.shoebuy.common.database.IResultSetHelper;

public class Test {
	
	private static IConnectionHelper getConnectionHelper () {
		IDBConnectionManager dbConnectionManager = DBConnectionFactory.getInstance()
				.getConnectionManager("database.properties", true);
		return dbConnectionManager.getConnectionHelper();
	}

	public static void main(String[] args) {
		String databaseURL = "jdbc:derby://localhost:1527/C:/Users/supratim1/Shoes.com/CodeBase/DerbyPOC/HighPerfDb";

		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			/*
			statement = DBConnectionUtil.getConnection(ShoebuyDerbyCategoryBrowseConstants.DERBY_CLIENT_DRIVER_NAME, databaseURL).createStatement();
			rs = statement.executeQuery(PropertiesUtil.getQuery(ShoebuyDerbyCategoryBrowseConstants.SELECT_BRAND_COUNT));

			while (rs.next()) {				
				System.out.println("Count = " + rs.getInt(1));
			}*/
		
			//ShoebuyDerbyCategoryBrowseDataManager manager = new ShoebuyDerbyCategoryBrowseDataManager();
			//System.out.println("Counttt= "+manager.findBrandData(DBConnectionUtil.getConnection(ShoebuyDerbyCategoryBrowseConstants.DERBY_CLIENT_DRIVER_NAME, databaseURL), PropertiesUtil.getQuery(ShoebuyDerbyCategoryBrowseConstants.SELECT_BRAND_COUNT), new ArrayList<Object>()).getBrandCount());
			
			IResultSetHelper rsHelper = getConnectionHelper().executeSelectStatement("SELECT COUNT(1) FROM BRAND", null);
			List<Map<String, Object>> resultSetData = rsHelper.getResultSetData();
			System.out.println("brand count = "+resultSetData.get(0));
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DbUtils.closeQuietly(rs);
		    DbUtils.closeQuietly(statement);
		    DbUtils.closeQuietly(conn);
		}
	}

}
