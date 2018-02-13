/**
 * @ClassName: StringUtil.java
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

public class StringUtil {
	
	
	/**
	 * Judge whether two Strings are equal (trim the space of head and tail)
	 * @param str1
	 * @param str2
	 * @return return true if equal and return false conversely 
	 */
	public static boolean equal(String str1, String str2) {
		if (str1 == null & str2 == null) 
			return true;
		if ((str1 == null && str2 != null) 
			|| (str1 != null & str2 ==null))
			return false;
		return str1.trim().equals(str2.trim());
	}
	
	public static boolean notEqual(String str1, String str2) {
		return !equal(str1, str2);
	}
	
	/**
	 * Judge whether the string is in the array.
	 * @param str
	 * @param array
	 * @return return true if in and return false conversely
	 */
	public static boolean isIn(String str, String[] array) {
		for (String s : array) {
			if (equal(str, s))
				return true;
		}
		return false;
	}
	
	public static boolean notIn(String str, String[] array) {
		return !isIn(str, array);
	}
	
	/**
	 * sql injection filter
	 * 
	 * Blog of java how to prevent sql injection£º
	 * 		http://www.cnblogs.com/EasonJim/p/6223216.html
	 * 		http://blog.csdn.net/jun0052/article/details/8541021
	 * @param String to filter
	 * @return filtered String
	 */
	public static String sqlFilter(String str) {
		if (str == null)
			return null;
		return str.replaceAll(".*([';]+|(--)+).*", "");
	}
	
}

