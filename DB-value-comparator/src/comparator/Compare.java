package comparator;

/**
 * @ClassName: Compare
 *
 * @Description: A tool to compare the segment data of mysql database.
 * Function description£º
 * 		1. Self-defined primary keys. 
 * 		2. Self-defined filter white list.
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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Compare {
	
	private final static String CONNECT_URL = "jdbc:mysql://localhost:3306/test?" +"autoReconnect=true&useUnicode=true&characterEncoding=UTF-8";
	private final static String USER = "root";
	private final static String PWD = "";
	private final static String TABLE_NAME = "victor";
	private static PreparedStatement psment = null;
	private static Connection conn =  JDBCUtil.getConnection(JDBCUtil.MYSQL_DRIVER, CONNECT_URL, USER, PWD);
	
    /**
     * All column names of the table which are arranged in real order.
     */
    public final static String[] columnNames = new String[]{
            "pri_key",
            "userName",
            "phoneNumber",
            "remark"
            
        };
    
    /**
     * White list for filtering, please add on demand. 
     */
    public final static String[] filterList = new String[]{
        "pri_key",
        "remark"
        
    };
 
    /**
     * Query record using self-defined primary keys.
     * @param segm1
     * @param segm2
     * @return	found record resultSet
     * @throws SQLException
     */
    public static ResultSet queryRecord(String segm1, String segm2) throws SQLException{
        ResultSet result = null;

        String sql = "select * from " + TABLE_NAME + " where userName=? and remark=?";
        
        System.out.println("Query sql: " + sql); 
        psment = conn.prepareStatement(sql);
        psment.setString(1, StringUtil.sqlFilter(segm1));
        psment.setString(2, StringUtil.sqlFilter(segm2));
        try {
            result = psment.executeQuery();
        }catch (Exception e) {
            e.printStackTrace();
        }
            
        return result;
    }
    
    public static ArrayList<String> compare(ResultSet rs1, ResultSet rs2) {
        ArrayList<String> notEqualList = new ArrayList<String>();
        try {
            if(rs1.next() && rs2.next()) {
                for(int i =0; i < columnNames.length; i++) {
                    String columnName = columnNames[i];
                    
                    /*compare specific segments of two results ,
                    * and store into list if not equal and not in white list
                    */
                    if (StringUtil.notEqual(rs1.getString(columnName), rs2.getString(columnName)) && StringUtil.notIn(columnName,filterList))
                        notEqualList.add(columnName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notEqualList;
    }
    
    public static void printResult(ArrayList<String> list) {
        if (!list.isEmpty()) {
            System.out.println("The different segments: ");
            int count = 1;
            for (String str : list){
                System.out.println(count + ": " + str);
                count++;
            }
        }
        else {
            System.out.println("No different segments found outside the white list.");
        }    
    }
    
    public static void comparator(String segm1) throws SQLException {
        ResultSet rSet1 = queryRecord(segm1, "lvlang1");
        ResultSet rSet2 = queryRecord(segm1, "lvlang2");
        if((rSet1 != null) && (rSet2 != null))
            printResult(compare(rSet1, rSet2));
        else
            System.out.println("Not all the records were queried.");
        try {
        	JDBCUtil.closeConnection(rSet1, psment, conn);
        	JDBCUtil.closeConnection(rSet2, psment, conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws SQLException {
        comparator("tony");
    }
}