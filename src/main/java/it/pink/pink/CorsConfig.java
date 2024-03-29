package it.pink.pink;



import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.util.Arrays;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
  //  @Override
    //public void addCorsMappings(CorsRegistry registry) {
      //  registry.addMapping("/**")

        //        .allowedOrigins("*") // Specifica gli origini consentiti, o "*" per consentire a tutti
          //      .allowedMethods("GET", "POST", "PUT", "DELETE") // Specifica i metodi HTTP consentiti
            //    .allowedHeaders("*"); // Specifica gli header consentiti
//    }


        @Bean
        public CorsFilter corsFilter() {
            System.out.println("-------------------- cors");
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
            config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT","PATCH", "OPTIONS", "DELETE"));
            config.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization","*"));
            config.setAllowCredentials(true);
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", config); // CorsConfiguration applies to all paths

            return new CorsFilter(source);
    }
}


