package Likelion.SpringStudy.repository;

import Likelion.SpringStudy.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Long> {

}
