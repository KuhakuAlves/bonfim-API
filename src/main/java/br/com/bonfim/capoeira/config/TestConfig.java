package br.com.bonfim.capoeira.config;

import br.com.bonfim.capoeira.email.IEmailService;
import br.com.bonfim.capoeira.email.MockEmailService;
import br.com.bonfim.capoeira.service.complemento.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean instantiateDatabase() throws ParseException {
        dbService.instantiateTestDatabase();
        return true;
    }

    @Bean
    public IEmailService emailService() {
        return new MockEmailService();
    }
}
