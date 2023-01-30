package Likelion.SpringStudy.repository;

import Likelion.SpringStudy.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepo {
    Post save(Post post);
    Optional<Post> findById(Long id);
    List<Post> findByName(String name);
    List<Post> findAll();
    List<Post> findAllByBlogId(Long blog_id);
    void delete(Post post);
    void deleteById(Long id);
}
