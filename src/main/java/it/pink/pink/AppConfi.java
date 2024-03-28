package it.pink.pink;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

public interface AppConfi {
    @Bean
    PasswordEncoder encoder();

    @Bean
    Cloudinary getCloudinary(@Value("${cloudinary.name}") String name,
                             @Value("${cloudinary.api_key}") String key,
                             @Value("${cloudinary.api_secret}") String secret);

    @Bean
    JavaMailSenderImpl getMailSender(
            @Value("${mail.smtp.host}") String smtpHost,
            @Value("${mail.smtp.port}") String port,
            @Value("${mail.from}") String from,
            @Value("${mail.password}") String password,
            @Value("${mail.transport.protocol}") String protocol,
            @Value("${mail.smtp.auth}") String auth,
            @Value("${mail.smtp.starttls.enable}") String starttls,
            @Value("${mail.debug}") String debug,
            @Value("${mail.ssl.enable}") String sslEnable
    );

 //  void addCorsMappings(CorsRegistry registry);
}
