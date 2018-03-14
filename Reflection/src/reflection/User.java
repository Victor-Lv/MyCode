/**
 * @ClassName: User.java
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

/**
 * User table bean
 */
public class User implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private int age;
		
	@Override
	public String toString() {
		return "User table[userName = " + userName +", age = " + age + "]"; 
	}
	
	public String getUserName() {
		return this.userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public int getAge() {
		return this.age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
