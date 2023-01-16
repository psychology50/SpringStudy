package Likelion.SpringStudy.repository;

import Likelion.SpringStudy.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepoInterface {
    Post save(Post post);
    Optional<Post> findById(Long id);
    List<Post> findByName(String name);
    List<Post> findAll();
    void delete(Post post);
    void deleteById(Long id);
}
