/**
 * @ClassName: DBService.java
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

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DBService {

	//此处不负责维护 Connection / PreparedStatement / ResultSet 的关闭，而由调用者维护
    public static ResultSet queryRecord(Connection conn, PreparedStatement psment, String tableName, String userName) throws SQLException{
		
    	if (conn == null) {
			System.out.println("Error: Connection is null.");
			throw new NullPointerException();
		}
    	
    	ResultSet rSet = null;
        String sql = "select * from " + tableName + " where userName=?";
        
        System.out.println("Query sql: " + sql); 
        psment = conn.prepareStatement(sql);
        psment.setString(1, StringUtil.sqlFilter(userName));
        //psment.setString(2, StringUtil.sqlFilter(segm2));
        
        try {
        	rSet = psment.executeQuery();
        }catch (Exception e) {
            e.printStackTrace();
        }
        
        return rSet;
    }

	public static ResultSet queryRecord(Connection conn, PreparedStatement psment, String sql) throws SQLException{
        
		if (conn == null) {
			System.out.println("Error: Connection is null.");
			throw new NullPointerException();
		}
		
		ResultSet rSet = null;
        
        System.out.println("Query sql: " + sql); 
        psment = conn.prepareStatement(sql);
        
        try {
        	rSet = psment.executeQuery();
        }catch (Exception e) {
            e.printStackTrace();
        }
            
        return rSet;
    }
	
    /**
     * convert the record into target object
     * @param rs
     * @param className
     * @return target object
     */
	public static Object convertResult(ResultSet rs, String className) {
		Class cl = null;
		try {
			//return the Class object of className
			cl = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Object obj = null;
		//get all public methods of class. Use getDeclaredClasses if wants to get private methods
		Method[] methods = cl.getMethods();
		try {
			if (rs.next()) {
				//get the real object
				obj = cl.newInstance();
				for (Method method : methods) {
					String methodName = method.getName();
					if (methodName.startsWith("set")) { //only use the setxxx methods
						//get the filed name
						String columnName = methodName.substring(3, methodName.length());
						Class[] parmts = method.getParameterTypes();
						if (parmts[0].equals(String.class)) {
							method.invoke(obj, rs.getString(columnName));
						}
						else if (parmts[0].equals(int.class)) {
							method.invoke(obj, rs.getInt(columnName));
						}
						else if (parmts[0].equals(long.class)) {
							method.invoke(obj, rs.getLong(columnName));
						}
						else if (parmts[0].equals(Timestamp.class)) {
							method.invoke(obj, rs.getTimestamp(columnName));
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
    
}
