package test.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import test.dao.User;
import test.dao.UserTool;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * @ClassName: UserController
 * @Description: TODO
 * @Author: Victor Lv (http://langlv.me)
 * @Date: 2018/8/20 11:06
 * @Version: 1.0
 */

@Controller
public class UserController {

    @RequestMapping(value = "/selectAllUser" , method = POST)
    public ModelAndView selectAllUser() {
        System.out.println("Selecting All User.");
        List<User> user = UserTool.selectAllUser();
        ModelAndView modelAndView = new ModelAndView("/user_list");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "/selectUserID" , method = POST)
    public ModelAndView selectUserID(HttpServletRequest httpServletRequest) {
        String userID = httpServletRequest.getParameter("userID");
        System.out.println("Selecting myUser who's ID is: " + userID);
        List<User> user = new ArrayList<User>();
        user.add(UserTool.selectUser(Integer.parseInt(userID)));
        ModelAndView modelAndView = new ModelAndView("/user_list");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "/insertUser", method = POST)
    public String insertUser(HttpServletRequest httpServletRequest) {
        String newUserName = httpServletRequest.getParameter("insertUserName");
        String newUserAddress = httpServletRequest.getParameter("insertUserAddress");
        System.out.println("Inserting user, name: " + newUserName + ", address:" + newUserAddress);
        boolean insertSuccess = UserTool.insertUser(newUserName, newUserAddress);
        if (insertSuccess){
            return "success";
        }else {
            return "fail";
        }
    }

    @RequestMapping(value = "/updateUser", method = POST)
    public String updateUser(HttpServletRequest httpServletRequest) {
        String updateUserID = httpServletRequest.getParameter("updateUserID");
        String updateUserName = httpServletRequest.getParameter("updateUserName");
        String updateUserAddress = httpServletRequest.getParameter("updateUserAddress");
        System.out.println("Updating user, ID:" + updateUserID + ", name:" + updateUserName + ", address:" + updateUserAddress);
        boolean updateSuccess = UserTool.updateUser(Integer.parseInt(updateUserID), updateUserName, updateUserAddress);
        if (updateSuccess){
            return "success";
        }else {
            return "fail";
        }
    }

    @RequestMapping(value = "/deleteUser", method = POST)
    public String deleteUser(HttpServletRequest httpServletRequest) {
        String deleteUserID = httpServletRequest.getParameter("deleteUserID");
        System.out.println("Deleting user, ID: " + deleteUserID);
        boolean deleteSuccess = UserTool.deleteUser(Integer.parseInt(deleteUserID));
        if (deleteSuccess){
            return "success";
        }else {
            return "fail";
        }
    }

}
