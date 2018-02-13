/**
 * @ClassName: Tester.java
 *
 * @Description: 
 *  
 * @Author: Victor Lv (http://langlv.me)
 *
 * @Email: langlv@qq.com
 *
 * @Date: Jan 10, 2018
 *
 * @Version: V1.0
 * 
 */
package reflection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.Properties;

public class Tester {
	
	public static String getConfigProperty(String propertyName) throws FileNotFoundException, IOException {
		String configPath = "C:\\Users\\lvlang\\Desktop\\UserConfig.txt";
		File configFile = new File(configPath);
		Properties config = new Properties();
		config.load(new FileInputStream(configFile));
		if (!config.containsKey(propertyName)) {
			System.out.println("Cannot found property:" + propertyName);
			return null;
		}
		return config.getProperty(propertyName);		
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException{
		String queryName = getConfigProperty("userName");
		String tableClass = getConfigProperty("tableClass");
		String tableName = "user";
		try {
			ResultSet rs = DBService.queryRecord(tableName, queryName);
			User user = (User) DBService.convertResult(rs, tableClass);
			System.out.println(user.toString());
			System.out.println(queryName + "'s age is " + user.getAge());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
