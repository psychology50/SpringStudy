package Likelion.SpringStudy.dto;

import Likelion.SpringStudy.domain.Tag;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TagForm {
    private String tag_name;

    public TagForm(String tag_name) {
        this.tag_name = tag_name;
    }

    public Tag toEntity() {
        return Tag.builder()
                .tag_name(tag_name)
                .build();
    }
}
