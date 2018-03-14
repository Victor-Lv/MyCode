/**
 * @ClassName: CloneUtil.java
 *
 * @Description: Used to clone object
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
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CloneUtil {
	public static Object clone(Object srcObj) {
		Object destObj = null;
		
		try {
			//convert object into stream
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(srcObj);
			
			//convert stream to object
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			destObj = ois.readObject();
			
		} catch (IOException | ClassNotFoundException e){
			e.printStackTrace();
		}
		return destObj;
	}
}
