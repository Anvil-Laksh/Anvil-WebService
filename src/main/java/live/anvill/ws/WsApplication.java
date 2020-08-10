package live.anvill.ws;

import live.anvill.ws.security.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class WsApplication  {
/*extends SpringBootServletInitializer   for war deployment
@Override
protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder){
    return applicationBuilder.sources(WsApplication.class);
} */

    public static void main(String[] args) {
        SpringApplication.run(WsApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SpringApplicationContext springApplicationContext(){
        return new SpringApplicationContext();
    }

    @Bean(name="AppProperties")
    public AppProperties appProperties(){
        return new AppProperties();
    }
}
