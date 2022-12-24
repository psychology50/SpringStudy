package Likelion.SpringStudy.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name="Tag")
@Table(name="TAG")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TAG_ID")
    private Long id;

    @Column(name="tag_name")
    private String tag_name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="POST_ID")
    private Post post;

    @Builder
    public Tag(Long id, String tag_name) {
        this.id = id;
        this.tag_name = tag_name;
    }
}
