package Likelion.SpringStudy.dto;

import Likelion.SpringStudy.domain.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
public class PostForm {
    private Long id;
    private String title;
    private String content;
    private Long blog_id;

    public PostForm(Long id, String title, String content, Long blog_id) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.blog_id = blog_id;
    }

    public Post toEntity() {
        return Post.builder()
                .id(id)
                .title(title)
                .content(content)
                .build();
    }
}
