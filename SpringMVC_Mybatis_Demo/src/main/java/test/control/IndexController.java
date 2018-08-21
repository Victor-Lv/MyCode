package test.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @ClassName: IndexController
 * @Description: TODO
 * @Author: Victor Lv (http://langlv.me)
 * @Date: 2018/8/17 16:36
 * @Version: 1.0
 */

@Controller
public class IndexController {

    @RequestMapping(value = "/")
    public String index() {
        System.out.println("Index controller");
        return "index";
    }

}
