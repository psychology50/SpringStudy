package Likelion.SpringStudy.dto;

import Likelion.SpringStudy.domain.Comment;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentForm {
    private String content;

    public CommentForm(String content) {
        this.content = content;
    }

    public Comment toEntity() {
        return Comment.builder()
                .content(content)
                .build();
    }
}
