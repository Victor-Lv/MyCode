/**
 * @ClassName: ConnectDB.java
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

import java.sql.Connection;

public class ConnectDB {
    private final static String CONNECT_URL = "jdbc:mysql://localhost:3306/lvlang?" +"autoReconnect=true&useUnicode=true&characterEncoding=UTF-8";
    private final static String USER = "root";
    private final static String PWD = "";
    public static Connection conn =  JDBCUtil.getConnection(JDBCUtil.MYSQL_DRIVER, CONNECT_URL, USER, PWD);
}
