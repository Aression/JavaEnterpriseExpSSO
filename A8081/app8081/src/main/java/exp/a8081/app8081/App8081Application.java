package exp.a8081.app8081;

import exp.a8081.app8081.intercepter.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class App8081Application extends WebMvcConfigurerAdapter{

	public static void main(String[] args) {
		SpringApplication.run(App8081Application.class, args);
	}

	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionintercepter()).addPathPatterns("/sysA/*");
    }
	@Bean
    public Sessionintercepter sessionintercepter(){
        return new Sessionintercepter();
    }

}
