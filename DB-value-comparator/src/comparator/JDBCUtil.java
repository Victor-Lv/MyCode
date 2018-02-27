package comparator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName: JDBCUtil
 *
 * @Description: A tool to connection database ( mysql or db2 ..) use JDBC. 
 *
 * @Author: Victor Lv (http://langlv.me)
 *
 * @Email: langlv@qq.com
 *
 * @Date: Nov 28, 2017 12:58:02 PM
 *
 * @Version: V1.0
 * 
 */
public class JDBCUtil {

	public final static String MYSQL_DRIVER = "com.mysql.jdbc.Driver"; //need to import mysql-connector-java-*-bin.jar manually
	public final static String DB2_DRIVER = "com.ibm.db2.jcc.DB2Driver"; //need to import db2-jdbc-jar manually
	
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
    
}
