package ${basePackage}.config;

import com.qingqing.springboot.config.WebServletConfigBase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
* Created by ${user!'test-api'} on ${date}
*/
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
        "${basePackage}.xinterface",
        "com.qingqing.common.web.inspect",
})
public class WebServletConfig extends WebServletConfigBase {

    @Bean(name = "apiDispatcherServlet")
    public DispatcherServlet getApiDispatcherServlet() {
        return super.getDispatcherServlet();
    }

    @Bean
    public ServletRegistrationBean apiDispatcherServletRegistration(@Qualifier("apiDispatcherServlet") DispatcherServlet dispatcherServlet) {
        return super.getDispatcherServletRegistration(dispatcherServlet, new String[]{"/api/*"}, "api_servlet");
    }
}
