package bcc.springhibernate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
public class TilesConfig {
	//Cấu hình Apache Title
		@Bean(name = "tilesConfigurer")
	    public TilesConfigurer tilesConfigurer() {
	        TilesConfigurer configurer = new TilesConfigurer();
	        configurer.setDefinitions(new String[]{"WEB-INF/configtiles/layout-administrator-tiles.xml", "WEB-INF/configtiles/layout-default-tiles.xml"});
	        //configurer.setDefinitions("/WEB-INF/configtiles/layout-default-tiles.xml");
	        configurer.setCheckRefresh(true);

	        return configurer;
	    }
	    
	    @Bean(name = "viewResolver")
	    public ViewResolver tilesViewResolver() {
	    	UrlBasedViewResolver resolver = new UrlBasedViewResolver();
	        resolver.setViewClass(TilesView.class);
	        return resolver;
	    }
}
