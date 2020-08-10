package live.anvill.ws.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping;

@Configuration
public class BeanNameUrlHandlerMappingConfig {
    @Bean
    BeanNameUrlHandlerMapping beanNameUrlHandlerMapping() {
        return new BeanNameUrlHandlerMapping();
    }
//
//    @Bean("/beanNameUrl")
//    public WelcomeController welcome() {
//        return new WelcomeController();
//    }
}
