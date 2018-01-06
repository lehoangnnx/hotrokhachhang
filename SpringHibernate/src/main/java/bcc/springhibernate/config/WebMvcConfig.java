package bcc.springhibernate.config;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public class WebMvcConfig extends WebMvcConfigurerAdapter {
	private static final Charset UTF8 = Charset.forName("UTF-8");
	
	// Cấu hình UTF-8 cho các trang.
		  @Override
		  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		      StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
		      stringConverter.setSupportedMediaTypes(Arrays.asList(new MediaType("text", "plain", UTF8)));
		      converters.add(stringConverter);
		 
		      // Add other converters ...
		  }

	 // Cấu hình để sử dụng các file nguồn tĩnh (html, image, ..)
	    @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/resources/")
	        .setCachePeriod(31556926);
	        registry.addResourceHandler("/img/**").addResourceLocations("/WEB-INF/resources/")
	        .setCachePeriod(31556926);
	        registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/files/images/")
	        .setCachePeriod(31556926);
	        registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/resources/").setCachePeriod(31556926);
	    }
	    //Cấu hình servlet
		@Override
	    public void configureDefaultServletHandling(
	            DefaultServletHandlerConfigurer configurer) {
	        configurer.enable();
	    }
}
