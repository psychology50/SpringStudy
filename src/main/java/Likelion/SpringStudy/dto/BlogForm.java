package Likelion.SpringStudy.dto;

import Likelion.SpringStudy.domain.Blog;
import Likelion.SpringStudy.domain.RoleType;
import Likelion.SpringStudy.domain.UserDomain;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
@NoArgsConstructor
public class BlogForm {
    private Long id;
    private String blog_name;

    @Builder
    public BlogForm(Long id, String blog_name)
    {
        this.id = id;
        this.blog_name = blog_name;
    }

    public Blog toEntity() {
        return Blog.builder()
                .id(id)
                .blog_name(blog_name)
                .build();
    }
}
