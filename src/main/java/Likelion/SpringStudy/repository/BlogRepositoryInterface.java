package Likelion.SpringStudy.repository;

import Likelion.SpringStudy.domain.Blog;

import java.util.Optional;

public interface BlogRepositoryInterface {
    Blog save(Blog blog, Long user_id);
    Optional<Blog> findById(Long id);
    Optional<Blog> findByName(String name);
    void delete(Blog blog);
    void deleteById(Long id);
}
