/**
 * @ClassName: PropertyUtil.java
 *
 * @Description: Read the configuration file.
 *  
 * @Author: Victor Lv (http://langlv.me)
 *
 * @Email: langlv@qq.com
 *
 * @Date: Mar 14, 2018
 *
 * @Version: V1.0
 * 
 */
package reflection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {
	
	/**
	 * Read the value of propertyName from configuration file.
	 * 
	 * @param configPath
	 * @param propertyName
	 * @return the value of the propertyName
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String readConfigProperty(String configPath, String propertyName) throws FileNotFoundException, IOException {

		File configFile = new File(configPath);
		if (!configFile.exists()) {
			System.out.println("There's no configure file on " + configPath);
			throw new FileNotFoundException();
		}
		Properties config = new Properties();
		config.load(new FileInputStream(configFile));
		if (!config.containsKey(propertyName)) {
			System.out.println("Cannot found property:" + propertyName);
			throw new NullPointerException();
		}
		return config.getProperty(propertyName);		
	}
	
}
