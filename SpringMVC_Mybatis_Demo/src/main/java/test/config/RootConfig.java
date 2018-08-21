package test.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @ClassName: RootConfig
 * @Description: TODO
 * @Author: Victor Lv (http://langlv.me)
 * @Date: 2018/8/17 11:22
 * @Version: 1.0
 */

@Configuration
@ComponentScan(basePackages = {"test"},
    excludeFilters = {@Filter(type= FilterType.ANNOTATION, value = EnableWebMvc.class)})
public class RootConfig {
}
