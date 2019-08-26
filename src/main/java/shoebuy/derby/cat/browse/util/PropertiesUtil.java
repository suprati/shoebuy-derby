package shoebuy.derby.cat.browse.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is the utility class for shoebuy derby category browse
 * @author smaiti
 *
 */
public class PropertiesUtil {
	
	private static final Logger log = LoggerFactory.getLogger(PropertiesUtil.class);
	
	public static final String PROPERTIES_FILE_COMMENT_SIGN = "#";
    public static final String PROPERTIES_FILE_SEPARATOR_TOKEN = "=";
    public static final String PROPERTIES_FILE_ESCAPED_COLON_TOKEN = "\\:";
    public static final String PROPERTIES_FILE_UNESCAPED_COLON_TOKEN = ":";
    public static final String PROPERTIES_FILE_ORACLE_DATA_EXTRACTION_SEPARATOR = "|";
    public static final String PROPERTIES_FILE_STS_OBJECT_AND_METHOD_SEPARATOR = ".";
    public static final String PROPERTIES_FILE_STS_XML_NODE_INFO_SEPARATOR = "^";
	
	private static final String QUERIES_PROP_FILE = "properties/queries.properties";
	
	private static Properties queryProperties;
	static {
		try {
			queryProperties = loadPropertiesFromFile(QUERIES_PROP_FILE);
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("PropertiesUtil: Exception occured while reading queries properties file.", e);
			}
		}
	}

	private PropertiesUtil() {
		throw new IllegalStateException("Utility class");
	}
	
	/**
     * Loads properties from a properties file to find the file either as a local file or a resource file.
     * 
     * @param filePath
     * @return properties
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static Properties loadPropertiesFromFile(String filePath) throws FileNotFoundException, IOException {
        Properties properties = new Properties();
        InputStream stream = null;
        BufferedReader fileReader = null;
        try {
            stream = getResourceAsStream(filePath);
            fileReader = new BufferedReader(new InputStreamReader(stream));
            String line = fileReader.readLine();
            while (line != null) {
                if (line.trim().isEmpty() || line.trim().startsWith(PROPERTIES_FILE_COMMENT_SIGN)) {
                    line = fileReader.readLine();
                    continue;
                }
                boolean isEscapeInPropertyName = false;
                int indexOfEquals = line.indexOf(PROPERTIES_FILE_SEPARATOR_TOKEN);
                while (indexOfEquals > 0 && line.charAt(indexOfEquals - 1) == '\\') {
                    isEscapeInPropertyName = true;
                    if (indexOfEquals != line.length() - 1) {
                        indexOfEquals += line.substring(indexOfEquals+1).indexOf(PROPERTIES_FILE_SEPARATOR_TOKEN) + 1;
                    }
                }
                if (indexOfEquals > 0 && indexOfEquals < (line.length() - 1)) {
                    String propertyName = line.substring(0, indexOfEquals).trim();
                    String propertyValue = line.substring(indexOfEquals + 1).trim();

                    if (isEscapeInPropertyName) {
                        int indexOfEscape = propertyName.indexOf("\\" + PROPERTIES_FILE_SEPARATOR_TOKEN);
                        while (indexOfEscape >= 0) {
                            String preEscapeStr = propertyName.substring(0, indexOfEscape);
                            propertyName = preEscapeStr + propertyName.substring(indexOfEscape + 1);
                            indexOfEscape = propertyName.indexOf("\\" + PROPERTIES_FILE_SEPARATOR_TOKEN);
                        }
                    }
                    // check to see if the property name has escaped colons, if so then unescape them.
                    int indexOfColon = propertyName.indexOf(PROPERTIES_FILE_ESCAPED_COLON_TOKEN);
                    while (indexOfColon >= 0) {
                        String preColonStr = propertyName.substring(0, indexOfColon);
                        propertyName = preColonStr + PROPERTIES_FILE_UNESCAPED_COLON_TOKEN + propertyName.substring(indexOfColon + PROPERTIES_FILE_ESCAPED_COLON_TOKEN.length());
                        indexOfColon = propertyName.indexOf(PROPERTIES_FILE_ESCAPED_COLON_TOKEN);
                    }

                    properties.setProperty(propertyName, propertyValue);
                }
                line = fileReader.readLine();
            }
        } finally {
            if (fileReader != null) {
                fileReader.close();
            }
            if (stream != null) {
                stream.close();
            }
        }
        return properties;
    }
    
    /**
     * Returns a Stream for a file path provided as parameter whether that file path
     * exists on the local file system or as a resource in the class path.
     * 
     * @param fileName
     * @return stream
     * @throws FileNotFoundException
     */
    public static InputStream getResourceAsStream(String fileName) throws FileNotFoundException {
        InputStream stream;
        if (new File(fileName).exists()) {
            stream = new FileInputStream(fileName);
        } else {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            if (loader == null) {
                loader = ClassLoader.getSystemClassLoader();
            }
            stream = loader.getResourceAsStream(fileName);
        }
        if (stream == null) {
            throw new FileNotFoundException(fileName);
        }
        return stream;
    }
    
    /**
	 * Method to retrieve query for properties
	 * 
	 * @param queryName
	 * @return
	 */
	public static String getQuery(String queryName) {
		if (queryProperties != null) {
			return queryProperties.getProperty(queryName);
		}
		return null;
	}

}
