package Likelion.SpringStudy.config;

import Likelion.SpringStudy.repository.*;
import Likelion.SpringStudy.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
@RequiredArgsConstructor
public class SpringConfig {
    private final EntityManager em;
    private final CommentRepo commentRepo;
    private final CategoryRepo categoryRepo;
    private final TagRepo tagRepo;
    private final LikeRepo likeRepo;

    @Bean
    public UserService userService() {
        return new UserService(userRepo());
    }

    @Bean
    public UserRepo userRepo() {
        return new UserRepoImpl(em);
    }

    @Bean
    public BlogRepo blogRepo() {return new BlogRepoImpl(em); }

    @Bean
    public PostRepo postRepo() {return new PostRepoImpl(em);}

    @Bean
    public CommentService commentService() {
        return new CommentService(
                userRepo(),
                blogRepo(),
                postRepo(),
                commentRepo
        );
    }

    @Bean
    public CategoryService categoryService() {
        return new CategoryService(
                postRepo(),
                categoryRepo
        );
    }

    @Bean
    public TagService tagService() {
        return new TagService(
                postRepo(),
                tagRepo
        );
    }

    @Bean
    public LikeService likeService() {
        return new LikeService(
                userRepo(),
                postRepo(),
                likeRepo
        );
    }
}
