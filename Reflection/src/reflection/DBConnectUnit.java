/**
 * @ClassName: DBConnectUnit.java
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

import java.sql.Connection;

public class DBConnectUnit {
	
	private int connect_id;
	private Connection conn;

	DBConnectUnit (int connect_id, Connection conn) {
		this.connect_id = connect_id;
		this.conn = conn;
	}
	
	public int getConnect_id() {
		return connect_id;
	}

	public void setConnect_id(int connect_id) {
		this.connect_id = connect_id;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
	
	
}
