package Likelion.SpringStudy.repository;

import Likelion.SpringStudy.domain.Blog;

import java.util.Optional;

public interface BlogRepo {
    Blog save(Blog blog);
    Optional<Blog> findById(Long id);
    Optional<Blog> findByName(String name);
    void delete(Blog blog);
    void deleteById(Long id);
    Optional<Blog> findByUserId(Long user_id);
}
