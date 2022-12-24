package Likelion.SpringStudy.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="Category")
@Table(name="CATEGORY")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="CATEGORY_ID")
    private Long id;

    @Column(name="category_name")
    private String category_name;

    @OneToMany(mappedBy = "category")
    @ToString.Exclude
    private List<Post> posts = new ArrayList<>();

    @Builder
    public Category(Long id, String category_name) {
        this.id = id;
        this.category_name = category_name;
    }
}
