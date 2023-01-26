package Likelion.SpringStudy.dto;

import Likelion.SpringStudy.domain.Comment;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentForm {
    private Long id;
    private String content;

    public CommentForm(String content) {
        this.id = id;
        this.content = content;
    }

    public Comment toEntity() {
        return Comment.builder()
                .id(id)
                .content(content)
                .build();
    }
}
