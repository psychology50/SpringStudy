package Likelion.SpringStudy.domain;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Entity(name="like")
@Table(name="Like")
@Data
@Getter
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="LIKE_ID")
    private Long id;

    public Like() {}
}
