
package test.dao;

import org.apache.ibatis.session.SqlSession;
import test.entity.User;

import java.io.IOException;
import java.util.List;

/**
 * @ClassName: UserTool.java
 * @Description: TODO
 * @Author: http://langlv.me
 * @Date: 2018/7/10 15:44
 * @Version: 1.0
 */

public class UserTool {

    public static void main(String arg[]) throws IOException {
        User user1 = selectUserName(1);
        System.out.println(user1.toString());

        System.out.println("Insert user: " + insertUser("Victor", "New York"));

        System.out.println("Update user: " + updateUser(7, "Bill Gates", "Beijing"));
        User user2 = selectUser(7);
        System.out.println(user2.toString());

        System.out.println("Delete User: " + deleteUser(12));

    }

    public static List<User> selectAllUser() {
        SqlSession session = SessionFactory.getSession();

        System.out.println("Selecting all users...");
        try {
            List<User> userList = session.selectList((String) "UserMapper.queryAll");
            if (null != userList) {
                return userList;
            } else {
                System.out.println("Not found!");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
            return null;
        } finally {
            session.close();
        }
    }

    public static User selectUserName(int id) {
        SqlSession session = SessionFactory.getSession();

        System.out.println("Selecting user...");
        try {

            User user = (User)session.selectOne((String) "UserMapper.selectUserName", id);
            if (null != user) {
                System.out.println(user.toString());
                return user;
            } else {
                System.out.println("Not found!");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
            return null;
        } finally {
            session.close();
        }
    }

    public static User selectUser(int id) {
        SqlSession session = SessionFactory.getSession();

        System.out.println("Selecting user...");
        try {
            User user = (User) session.selectOne((String) "UserMapper.selectUser", id);
            if (null != user) {
                System.out.println(user.toString());
                return user;
            } else {
                System.out.println("Not found!");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
            return null;
        } finally {
            session.close();
        }
    }

    public static boolean insertUser(String name, String address) {
        SqlSession session = SessionFactory.getSession();

        System.out.println("Inserting user...");
        try {
            User user1 = new User();
            user1.setName(name);
            user1.setAddress(address);
            int index = session.insert("UserMapper.insertUser", user1);
            boolean result = (index > 0) ? true : false;
            session.commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    public static boolean updateUser(int id, String name, String address) {
        SqlSession session = SessionFactory.getSession();

        System.out.println("Updating user...");
        try {
            User user = new User(id, name, address);
            int index = session.update("UserMapper.updateUser", user);
            boolean result = (index > 0) ? true : false;
            session.commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    public static boolean deleteUser(int id) {
        SqlSession session = SessionFactory.getSession();

        System.out.println("Deleting user...");
        try {
            int index = session.delete("UserMapper.deleteUser", id);
            boolean result = (index > 0) ? true : false;
            session.commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
            return false;
        } finally {
            session.close();
        }
    }
}
