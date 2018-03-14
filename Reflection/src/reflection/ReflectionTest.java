/**
 * @ClassName: ReflectionTest.java
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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReflectionTest {
	
	public static String configPath = "C:\\\\Users\\\\lvlang\\\\Desktop\\\\UserConfig.txt";
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception{

		String queryName = PropertyUtil.readConfigProperty(configPath,"userName");
		String tableClass = PropertyUtil.readConfigProperty(configPath,"tableClass");
		
		Connection conn = JDBCUtil.createConnection(configPath);
		
		try {
			PreparedStatement psment1 = null;
			//get the ResultSet
			ResultSet rs1 = DBService.queryRecord(conn, psment1, "user", queryName);
			
			//convert the ResultSet into User Obejct
			User user = (User) DBService.convertResult(rs1, tableClass);
			
			//close the ResultSet and Statement
			JDBCUtil.closeConnection(rs1, psment1, null);
			
			System.out.println(queryName + "'s age is " + user.getAge());
			
			
			String sql = "select * from Student where name='" + user.getUserName() + "'";
			PreparedStatement psment2 = null;
			ResultSet rs2 = DBService.queryRecord(conn, psment2, sql);
			
			Student stu = new Student();
			String className = stu.getClass().getName();
			System.out.println("Class name is " + className);

			//convert the ResultSet into Student Object
			stu = (Student) DBService.convertResult(rs2, className);
			JDBCUtil.closeConnection(rs2, psment2, null);
			
			System.out.println("And " + stu.getName() + "'s grade is " + stu.getGrade());
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection(null, null, conn);
		}
		
	}

}
