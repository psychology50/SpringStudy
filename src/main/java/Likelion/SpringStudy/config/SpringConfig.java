package Likelion.SpringStudy.config;

import Likelion.SpringStudy.repository.UserRepository;
import Likelion.SpringStudy.repository.UserRepositoryInterface;
import Likelion.SpringStudy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final DataSource dataSource;
    private final EntityManager em;

    @Autowired
    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }

    @Bean
    public UserService userService() {
        return new UserService(userRepositoryInterface());
    }

    @Bean
    public UserRepositoryInterface userRepositoryInterface() {
        return new UserRepository(em);
    }

}
