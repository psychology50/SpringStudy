package Likelion.SpringStudy.config;

import Likelion.SpringStudy.repository.*;
import Likelion.SpringStudy.service.CommentService;
import Likelion.SpringStudy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class SpringConfig {
    private final EntityManager em;
    private final CommentRepo commentRepo;

    @Bean
    public UserService userService() {
        return new UserService(userRepositoryInterface());
    }

    @Bean
    public UserRepositoryInterface userRepositoryInterface() {
        return new UserRepository(em);
    }

    @Bean
    public BlogRepositoryInterface blogRepositoryInterface() {return new BlogRepository(em); }

    @Bean
    public PostRepoInterface postRepoInterface() {return new PostRepo(em);}

    @Bean
    public CommentService commentService() {
        return new CommentService(
                userRepositoryInterface(),
                blogRepositoryInterface(),
                postRepoInterface(),
                commentRepo
        );
    }
}
