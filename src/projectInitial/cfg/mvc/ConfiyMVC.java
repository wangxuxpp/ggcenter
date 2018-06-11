package projectInitial.cfg.mvc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@ComponentScan(basePackages = {"business,movable"},
			   useDefaultFilters = false,
			   includeFilters ={@Filter(type=FilterType.ANNOTATION , value=org.springframework.stereotype.Controller.class)})
@Configuration
@EnableWebMvc
public class ConfiyMVC {

}
