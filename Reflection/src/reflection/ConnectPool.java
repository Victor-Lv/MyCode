/**
 * @ClassName: ConnectPool.java
 *
 * @Description: 
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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectPool {
	
	private static int INIT_CONNECT_ID = 0;
	private static Map<Integer, Connection> CONNECT_POOL = new HashMap<Integer, Connection>();
	
	public static DBConnectUnit getOneConnect(String configPath)
			throws FileNotFoundException, IOException {
	
		Connection conn = JDBCUtil.createConnection(configPath);
		
		int connect_id = INIT_CONNECT_ID + 1;
		bindId(connect_id, conn);
		
		DBConnectUnit connect = new DBConnectUnit(connect_id, conn);
		
		return connect;
	}
	
	private static void bindId(int connect_id, Connection conn) {
		CONNECT_POOL.put(connect_id, conn);
	}
	
	public static void closeConnection(DBConnectUnit connect) throws SQLException {
		Connection conn = connect.getConn();
		int id = connect.getConnect_id();
		if (CONNECT_POOL.containsKey(id)) {
			CONNECT_POOL.remove(id);
		}
		if (conn != null && !conn.isClosed()) {
			conn.close();
			conn = null;
		}
	}
}