package Likelion.SpringStudy.repository;

import Likelion.SpringStudy.domain.Like;
import Likelion.SpringStudy.domain.Post;
import Likelion.SpringStudy.domain.UserDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LikeRepo extends JpaRepository<Like, Long> {
    @Query("select l from Like l where l.user = :user and l.post = :post")
    public Like findLikeByUserAndBlog(@Param("user")UserDomain user, @Param("post") Post post);
}
