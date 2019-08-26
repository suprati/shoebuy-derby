package shoebuy.derby.cat.browse.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.shoebuy.common.database.DBConnectionFactory;
import com.shoebuy.common.database.IConnectionHelper;
import com.shoebuy.common.database.IDBConnectionManager;

/**
 * This is the db connection utility class for shoebuy derby category browse
 * @author smaiti
 *
 */
public class DBConnectionUtil {
	
	//No instance creation
	private DBConnectionUtil() {
		throw new IllegalStateException("DB Utility class");
	}
	
	public static Connection getConnection(String driverName, String url) throws IllegalAccessException, InstantiationException, ClassNotFoundException, SQLException {
		Class.forName(driverName).newInstance();		
		return DriverManager.getConnection(url);
	}
	
	public static IConnectionHelper getConnectionHelper () {
		IDBConnectionManager dbConnectionManager = DBConnectionFactory.getInstance()
				.getConnectionManager("database.properties", true);
		return dbConnectionManager.getConnectionHelper();
	}

}
