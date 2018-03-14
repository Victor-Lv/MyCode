/**
 * @ClassName: JDBCUtil.java
 *
 * @Description: 
 *  
 * @Author: Victor Lv (http://langlv.me)
 *
 * @Email: langlv@qq.com
 *
 * @Date: Jan 8, 2018
 *
 * @Version: V1.0
 * 
 */
package reflection;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtil {

	public final static String MYSQL_DRIVER = "com.mysql.jdbc.Driver"; //need to import mysql-connector-java-*-bin.jar manually
	public final static String DB2_DRIVER = "com.ibm.db2.jcc.DB2Driver"; //need to import db2-jdbc-jar manually
	
//	private final static String CONNECT_URL = "jdbc:mysql://localhost:3306/lvlang?" +"autoReconnect=true&useUnicode=true&characterEncoding=UTF-8";
	
    /**
     * Create the database connection and return it.
     * @param DRIVER
     * @param URL
     * @param USER
     * @param PWD
     * @return connection object
     */
    public static Connection getConnection(String driver, String url, String user, String pwd) {
        Connection conn = null;
        
    	try {
    		Class.forName(driver).newInstance();	
    		conn = DriverManager.getConnection(url, user, pwd);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
        System.out.println("Database connected.");
        return conn;
    }
    
    /**
     * Close the connection / resulSet / preparedStatement.
     * @param rSet
     * @param psment
     * @param conn
     * @throws Exception
     */
    public static void closeConnection(ResultSet rSet, PreparedStatement psment, Connection conn) throws Exception {
    	try {
			if (rSet != null) {
				rSet.close();
				rSet = null;
			}
			if (psment != null) {
				psment.close();
				psment = null;
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			throw new Exception("Fail to close ResultSet / PreParedStatment / Connection !");
		}
    }
    
/*    
    public static void closeConnection(ResultSet rSet, PreparedStatement psment) throws Exception {
    	try {
			if (rSet != null) {
				rSet.close();
				rSet = null;
			}
			if (psment != null) {
				psment.close();
				psment = null;
			}
		} catch (SQLException e) {
			throw new Exception("Fail to close ResultSet / PreParedStatment !");
		}
    }
    
    public static void closeConnection(Connection conn) throws Exception {
    	try {
	    	if (conn != null && !conn.isClosed()) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			throw new Exception("Fail to close Connection !");
		}
    }
    */
    
    /**
     * Create the connection according to the configuration read from file.
     * 
     * @param configPath
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static Connection createConnection(String configPath) throws FileNotFoundException, IOException {
		//get the configuration from file
		String dbUrl = PropertyUtil.readConfigProperty(configPath,"dbUrl");
		String db_user = PropertyUtil.readConfigProperty(configPath,"dbUser");
		String db_password = PropertyUtil.readConfigProperty(configPath,"dbPwd");
		
		String connect_url = "jdbc:mysql://" + dbUrl + "?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8";
		
		return getConnection(JDBCUtil.MYSQL_DRIVER, connect_url, db_user, db_password);
    }
        
}